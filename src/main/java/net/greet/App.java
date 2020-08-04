package net.greet;

import java.util.*;
import java.util.Map;

import org.h2.engine.GeneratedKeysMode;
import org.h2.value.ValueShort;

import net.greet.commands.Command;
import net.greet.commands.CounterCommand;
import net.greet.commands.GreetCommand;
import net.greet.commands.GreetedCommand;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;
import net.greet.greetings_languages.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App 
{
	public static String selectedLanguage;

	public static String render(Map<Object, Object> model, String hbsPath)
	{
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, hbsPath));
	}
	
	public static void main(String[] args) 
	{
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();

		staticFiles.location("/public");

		post("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			String name = request.queryParams("username");

			
			String greeting  = new GreetCommand().execute(new Context("greet " + name  + " " + selectedLanguage));

			model.put("greeting", greeting);
			model.put("count", dbcp.countGreetedUsers());

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greet.hbs"));
		});

		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("greeting", "");
			model.put("count", dbcp.countGreetedUsers());

			selectedLanguage = request.queryParams("language");
			System.out.println(selectedLanguage);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greet.hbs"));
		});

		get("/greeted", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			
			ArrayList<String> greetedUsers= dbcp.queryGreetedUsers();

			if(greetedUsers.size() == 0)
			{
				greetedUsers.add("No users have been greeted");
				model.put("greeted", greetedUsers);

				return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
			}

			model.put("greeted", greetedUsers);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
		});

		post("/greeted", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			ArrayList<String> greetedUsers = new ArrayList<>();

			String userGreeted = request.queryParams("greetedUser");
			userGreeted = userGreeted.trim();

			if(userGreeted.equals("*")){
				greetedUsers = dbcp.queryGreetedUsers();

				if(greetedUsers.size() == 0)
				{
					greetedUsers.add("No users have been greeted");
					model.put("greeted", greetedUsers);
	
					return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
				}
	
				model.put("greeted", greetedUsers);
	
				return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
			}

			greetedUsers.add(dbcp.queryGreetedUser(userGreeted));

			model.put("greeted", greetedUsers);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
		});

		get("/counter", (request, response) -> {
			Map<String, String> model = new HashMap<>();

			String countRes = "The number of unique user(s) greeted: " + dbcp.countGreetedUsers();

			model.put("count", countRes);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "counter.hbs"));
		});

		get("/languages", (request, response) -> {
			Map<String, List<Language>> model = new HashMap<>();
			Language[] availbleLang = Language.values();

			List<Language> lang = new ArrayList<Language>();

			for(Language l: availbleLang)
				lang.add(l);

			model.put("lang", lang);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "languages.hbs"));
		});

		get("/clear", (request, response) -> {
			Map<String, Object> model = new HashMap<>();

			//dbcp.clearDataBase();

			model.put("clear", "");

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "clear.hbs"));
		});

		post("/clear", (request, response) -> {
			Map<String, Object> model = new HashMap<>();

			String userCleared = request.queryParams("userCleared");
			userCleared = userCleared.trim();

			if(userCleared.equals("*")) {
				dbcp.clearDataBase();

				model.put("clear", "All users cleared");

				return new HandlebarsTemplateEngine().render(new ModelAndView(model, "clear.hbs"));
			}

			String clearMsg = "User has not been greeted";

			if(dbcp.checkIfRecordExists(userCleared)){
				dbcp.clearUserDataBase(userCleared);
				clearMsg = userCleared + " has been removed";
			}

			model.put("clear", clearMsg);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "clear.hbs"));
		});
	}
}

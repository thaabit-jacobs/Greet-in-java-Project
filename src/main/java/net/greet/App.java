package net.greet;

import java.util.*;
import java.util.Map;

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
	public static String render(Map<String, String> model, String hbsPath)
	{
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, hbsPath));
	}
	
	public static void main(String[] args) 
	{
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		
		new GreetCommand().execute(new Context("greet Kauthar"));
		new GreetCommand().execute(new Context("greet Orrie"));

		staticFiles.location("/public");

		post("/", (request, response) -> {
			Map<String, String> model = new HashMap<>();
			model.put("greeting", "");

			return render(model, "greet.hbs");
		});

		get("/", (request, response) -> {
			Map<String, String> model = new HashMap<>();
			model.put("greeting", "");

			return render(model, "greet.hbs");
		});

		get("/greet/:name", (request, response) -> {
			Map<String, String> model = new HashMap<>();
			String greetingUser = new GreetCommand().execute(new Context("greet " + request.params(":name")));

			model.put("greeting", greetingUser);

			return render(model, "greet.hbs");
		});

		get("/greeted", (request, response) -> {
			Map<String, ArrayList<String>> model = new HashMap<>();
			
			ArrayList<String> greetedUsers= dbcp.queryGreetedUsers();

			model.put("greeted", greetedUsers);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
		});

		get("/greeted/:name", (request, response) -> {
			Map<String, String> model = new HashMap<>();
			
			String greetedUsers = dbcp.queryGreetedUser(request.params(":name"));

			model.put("greeted", greetedUsers);

			return render(model, "greeted-user.hbs");
		});

		get("/counter", (request, response) -> {
			Map<String, String> model = new HashMap<>();

			String countRes = "The number of unique user(s) greeted: " + dbcp.countGreetedUsers();

			model.put("count", countRes);

			return render(model, "counter.hbs");
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
			Map<String, String> model = new HashMap<>();

			dbcp.clearDataBase();

			model.put("clear", "All users cleared from database");

			return render(model, "clear.hbs");
		});

		get("/clear/:name", (request, response) -> {
			Map<String, String> model = new HashMap<>();

			String clearRs = "";

			if(dbcp.checkIfRecordExists(request.params(":name"))) {
				dbcp.clearUserDataBase(request.params(":name"));
				clearRs = request.params(":name") + " has been cleared";
			} else
				clearRs = request.params(":name") + " has not been greeted";

			model.put("clear", clearRs);

			return render(model, "clear.hbs");
		});
	}
}

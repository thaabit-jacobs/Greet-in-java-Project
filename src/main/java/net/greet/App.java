package net.greet;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.Map;

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

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}    

	public static String render(Map<Object, Object> model, String hbsPath)
	{
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, hbsPath));
	}
	
    static Connection getDatabaseConnection(String defualtJdbcUrl) throws URISyntaxException, SQLException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String database_url = processBuilder.environment().get("DATABASE_URL");
        if (database_url != null) {

            URI uri = new URI(database_url);
            String[] hostParts = uri.getUserInfo().split(":");
            String username = hostParts[0];
            String password = hostParts[1];
            String host = uri.getHost();

            int port = uri.getPort();

            String path = uri.getPath();
            String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);

            return DriverManager.getConnection(url, username, password);

        }

        return DriverManager.getConnection(defualtJdbcUrl);

    }
	
	public static void main(String[] args) throws URISyntaxException, SQLException 
	{
		port(getHerokuAssignedPort());

		staticFiles.location("/public");
		
		Connection connection = getDatabaseConnection("jdbc:postgresql://localhost:5432/greeter");
		
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(connection);

		post("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			String name = request.queryParams("username");

			
			String greeting  = new GreetCommand().execute(new Context("greet " + name  + " " + selectedLanguage));

			model.put("greeting", greeting);
			model.put("count", dbcp.countGreetedUsers());

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.hbs"));
		});

		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("greeting", "Greet Web App");
			model.put("count", dbcp.countGreetedUsers());

			selectedLanguage = request.queryParams("language");
			System.out.println(selectedLanguage);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.hbs"));
		});

		get("/greeted", (request, response) -> {
			Map<String, Object> model = new HashMap<>();
			
			ArrayList<String> greetedUsers= dbcp.getAllGreetedUsers();

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
				greetedUsers = dbcp.getAllGreetedUsers();

				if(greetedUsers.size() == 0)
				{
					greetedUsers.add("No users have been greeted");
					model.put("greeted", greetedUsers);
	
					return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
				}
	
				model.put("greeted", greetedUsers);
	
				return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
			} else if(userGreeted.equals("")) 
			{
				greetedUsers.add("Invalid command entered");
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

			model.put("counter", countRes);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "counter.hbs"));
		});

		get("/languages", (request, response) -> {
			Map<String, List<Language>> model = new HashMap<>();
			Language[] availbleLang = Language.values();

			List<Language> lang = new ArrayList<Language>();

			for(Language l: availbleLang)
				lang.add(l);

			model.put("lang", lang);

			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "language.hbs"));
		});

		get("/clear", (request, response) -> {
			Map<String, Object> model = new HashMap<>();

			model.put("clear", " ");

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
			} else if(userCleared.equals(" ")) 
			{
				userCleared = "Invalid command entered";
				model.put("greeted", userCleared);

				return new HandlebarsTemplateEngine().render(new ModelAndView(model, "greeted.hbs"));
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

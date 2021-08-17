
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        // Database connection
        int portNumber = DatabaseConnection.getPortNumber();
        port(portNumber);
        String host = DatabaseConnection.getDbHost();
        String user = DatabaseConnection.getUserName();
        String password = DatabaseConnection.getUserPass();

        staticFileLocation("/public");


        Sql2o sql2o = new Sql2o("jdbc:postgresql://" + host + ":5432/wildlife_tracker",user,password);


        port(DB.getPortNumber());

        staticFileLocation("/public");

        get("/",(request, response) -> {
            List<Animal> animals = Animal.all();
            Map<String, Object> model = new HashMap<>();
            model.put("animals", animals);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        // Save new animal
        post("/save-animal",(req,res) -> {
            Animal animal;
            if (Boolean.parseBoolean(req.queryParams("new-endangered"))) {
                animal = new Animal(req.queryParams("new-name"), true);
            } else {
                animal = new Animal(req.queryParams("new-name"),false);
            }
            animal.save();
            res.redirect("/animals");
            return null;
        });

        // Save new sighting
        post("/add-sighting",(req,res) -> {
            System.out.println(req.queryParams("ranger"));
            System.out.println(req.queryParams("age"));
            res.redirect("/");
            return null;
        });

    }
}

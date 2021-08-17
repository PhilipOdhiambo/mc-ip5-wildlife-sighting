
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {


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

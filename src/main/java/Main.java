
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
import com.google.gson.Gson;

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

        /* ------------------------------Sighting routes--------------*/
        // Enter new sighting or update stats
        get("/",(request, response) -> {

            List<Animal> animals = Animal.all();
            int totalSightings = Sighting.all().size();
            int endangeredSightings = Sighting.allByType("endangered").size();
            int normalSightings = Sighting.allByType("non-endangered").size();

            Map<String, Object> model = new HashMap<>();
            model.put("animals", animals);
            model.put("endangered", endangeredSightings);
            model.put("non-endangered", normalSightings);
            model.put("allSightings", totalSightings);

            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        // Post a sighting
        post("/add-sighting",(request, response) -> {
            int animalid = Integer.parseInt(request.queryParams("sighted-animal"));
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            String isss = request.queryParams("isendangered");
            boolean isendangered = Boolean.parseBoolean(request.queryParams("isendangered"));
            System.out.println(isendangered);

            if(isendangered) {
                EndangeredSighting endangered = new EndangeredSighting(animalid,age,health,location,ranger);
                endangered.save();
            } else {

                Sighting nonEndangered = new Sighting(animalid,location,ranger);
                nonEndangered.save();
            }
            response.redirect("/");
            return null;
        });


        /* -------------------------------------- Animal routes -------------*/

        get("/animals", (req,res) -> {
            List<Animal> animals = Animal.all();
            Map<String, Object> model = new HashMap<>();
            model.put("animals", animals);
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/:id", (req,res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Animal animal = Animal.findONe(id);
            return new Gson().toJson(animal);
        });

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

        // Delete animal
        get("/animals-delete/:id",(req,res)-> {
            int id = Integer.parseInt( req.params(":id"));
            Animal.delete(id);
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

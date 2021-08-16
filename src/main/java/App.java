
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {


        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("greeting", "hello");
            return new ModelAndView(model,"layout.hbs");
        }, new HandlebarsTemplateEngine());

    }
}

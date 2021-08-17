
import org.sql2o.Sql2o;

public class DB {

    public static Sql2o sql2o;
    public static String host;
    public static String userName;
    public static String password;

    public static int getPortNumber() {

        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            // Heroku connection
            host = "ec2-34-234-12-149.compute-1.amazonaws.com";
            userName= "efruqvhzekfish";
            password = "753e8fff14dba2b2a5d90d8c012db99b16d288be67bcea77eac2e5b833beaa7f";

            sql2o = new Sql2o("jdbc:postgresql://" + host + ":5432/de95p1vblei1va",userName,password);

            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        // Return Local connection
        host = "localhost";
        userName = "philip";
        password = "1234";

        sql2o = new Sql2o("jdbc:postgresql://" + host + ":5432/wildlife_tracker",userName,password);
        return 4567;
    }

}



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule {
    @BeforeAll
    public static void connectTestDB() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test",
                "philip", "1234");
    }

    @AfterAll
    public static void clearDatabase() {
//        String deleteAnimals = "DELETE FROM animals;";
//        String deleteSightings = "DELETE FROM animals;";
//
//        try(Connection conn = DB.sql2o.open()) {
//            conn.createQuery(deleteAnimals).executeUpdate();
//            conn.createQuery(deleteSightings).executeUpdate();
//        }
    }
}

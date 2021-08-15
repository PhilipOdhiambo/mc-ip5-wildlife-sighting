import org.junit.jupiter.api.BeforeEach;
import org.sql2o.Sql2o;

public class DatabaseRule {
    @BeforeEach
    public void connectTestDB() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test",
                "philip", "1234");
    }
}

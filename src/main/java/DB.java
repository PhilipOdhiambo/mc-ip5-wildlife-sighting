import org.sql2o.Sql2o;

public class DB {
    Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker",
            "philip", "1234");
}

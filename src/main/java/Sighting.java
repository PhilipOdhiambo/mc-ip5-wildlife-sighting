import com.google.common.base.Objects;
import org.sql2o.Connection;

public class Sighting {

    public int id;
    public int animalId;
    public String location;
    public String ranger;
    public String type;
    public String age;
    public String health;

    Sighting(int animalId, String location, String ranger) {
        this.animalId = animalId;
        this.location = location;
        this.ranger = ranger;

    }



    // Create/save siting into the database
    public void save(){
        String sql = "INSERT INTO sightings (animalid, location, ranger,type) VALUES (:animalid, :location, :ranger, :type)";
        try(Connection conn = DB.sql2o.open()){

            String ABSTRACT_TYPE = "non-endangered";
            this.id = (int) conn.createQuery(sql)
                    .addParameter("animalid",this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("ranger",this.ranger)
                    .addParameter("type", ABSTRACT_TYPE)
                    .executeUpdate().getKey();
        }

    }

    //

    public Sighting findONe(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting siting = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Sighting.class);
            return siting;
        }
    }

}

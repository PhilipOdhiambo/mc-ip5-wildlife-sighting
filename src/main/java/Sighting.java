import com.google.common.base.Objects;
import org.sql2o.Connection;

public class Sighting {
    private int id;
    private int animalId;
    private String location;
    private String ranger;
    public static final String DATABASE_TPE = "NonEndangered";

    Sighting(int animalId, String location, String ranger){
        setAnimalId(animalId);
        setLocation(location);
        setRanger(ranger);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRanger() {
        return ranger;
    }

    public void setRanger(String ranger) {
        this.ranger = ranger;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.animalId,this.id,this.ranger);
    }

    @Override
    public boolean equals(Object anotherSiting) {
        if (!(anotherSiting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSiting = (Sighting) anotherSiting;
            return this.getAnimalId() == newSiting.getAnimalId()&&
                    this.location.equals(newSiting.location)&&
                    this.ranger.equals(newSiting.ranger);
        }

    }

    // Create/save siting into the database
    public void save(){
        String sql = "INSERT INTO sightings (animalId, location, ranger,type) VALUES (:animalid, :location, :ranger, :type)";
        try(Connection conn = DB.sql2o.open()){
            this.id = (int) conn.createQuery(sql)
                    .addParameter("animalid",this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("ranger",this.ranger)
                    .addParameter("type",DATABASE_TPE)
                    .executeUpdate().getKey();
        }

    }

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

import com.google.common.base.Objects;
import org.sql2o.Connection;

public class Siting {
    private int id;
    private int animalId;
    private String location;
    private String ranger;

    Siting(int animalId, String location, String ranger){
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
        if (!(anotherSiting instanceof Siting)) {
            return false;
        } else {
            Siting newSiting = (Siting) anotherSiting;
            return this.getAnimalId() == newSiting.getAnimalId()&&
                    this.location.equals(newSiting.location)&&
                    this.ranger.equals(newSiting.ranger);
        }

    }

    // Create/save siting into the database
    public void save(){
        String sql = "INSERT INTO sitings (animalId, location, ranger) VALUES (:animalId, :location, :ranger)";
        try(Connection conn = DB.sql2o.open()){
            this.id = (int) conn.createQuery(sql)
                    .addParameter("animalId",this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("ranger",this.ranger)
                    .executeUpdate().getKey();
        }

    }

    public Siting findONe(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sitings where id=:id";
            Siting siting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Siting.class);
            return siting;
        }
    }

}

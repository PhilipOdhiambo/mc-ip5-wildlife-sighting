import com.google.common.base.Objects;
import org.sql2o.Connection;

public class Siting {
    private int id;
    private int animalId;
    private String location;
    private String ranger;

    Siting(int id, int animalId, String location, String ranger){

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

    // Create/save an animal into the database
    public void save(){
        String sql = "INSERT INTO animals (name, type) VALUES (:name, :type)";
        try(Connection conn = DB.sql2o.open()){
            this.id = (int) conn.createQuery(sql)
                    .addParameter("name",this.name)
                    .addParameter("type", DATABASE_TYPE)
                    .executeUpdate().getKey();

        }

    }

    public Animal findONe(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }



}

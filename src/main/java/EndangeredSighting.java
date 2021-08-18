import com.google.common.base.Objects;
import org.sql2o.Connection;

public class EndangeredSighting extends Sighting {

    public static final String D_BASE_TYPE = "endangered";

    EndangeredSighting(int animalId, String age, String health, String location, String ranger) {
        super(animalId,location,ranger);
        this.age = age;
        this.health = health;
        //this.location = location;
        //this.ranger = ranger;
        //this.animalId = animalId;
        type = D_BASE_TYPE;


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
            return this.animalId == newSiting.animalId&&
                    this.location.equals(newSiting.location)&&
                    this.ranger.equals(newSiting.ranger);
        }

    }

    @Override
    public void save() {
        String sql = "INSERT INTO sightings (animalid, location, ranger,type,age,health) VALUES (:animalid, :location, :ranger, :type,:age,:health)";
        try(Connection conn = DB.sql2o.open()){

            this.id = (int) conn.createQuery(sql)
                    .addParameter("animalid",this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("ranger",this.ranger)
                    .addParameter("type", this.type)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .executeUpdate().getKey();
        }

    }
}




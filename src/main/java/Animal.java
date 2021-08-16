import com.google.common.base.Objects;
import org.sql2o.Connection;

public class Animal {
    private int id;
    private String name;
    public final static String DATABASE_TYPE = "Animal";

    public Animal(String name) {
        setName(name);

    }


    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.name);
    }

    @Override
    public boolean equals(Object anotherAnimal) {
        if (!(anotherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal animal = (Animal) anotherAnimal;
            return this.getName().equals(animal.getName());
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

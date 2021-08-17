import com.google.common.base.Objects;
import org.sql2o.Connection;

import java.util.List;

public class Animal {
    private int id;
    private String name;
    private Boolean isendagered;

    public Boolean getIsendagered() {
        return isendagered;
    }

    public void setIsendagered(Boolean isendagered) {
        this.isendagered = isendagered;
    }

    public Animal(String name, Boolean endangered) {
        setName(name);
        setIsendagered(endangered);

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
        String sql = "INSERT INTO animals (name, isendagered) VALUES (:name, :isendagered)";
        try(Connection conn = DB.sql2o.open()){
            this.id = (int) conn.createQuery(sql)
                    .addParameter("name",this.name)
                    .addParameter("isendagered", this.isendagered)
                    .executeUpdate().getKey();
        }

    }

    public static List<Animal> all () {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
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

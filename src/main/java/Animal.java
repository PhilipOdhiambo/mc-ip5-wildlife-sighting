import com.google.common.base.Objects;

public class Animal {
    private int id;
    private String name;
    public final static String DATABASE_TYPE = "Animal";

    public Animal(String name) {

    }

    public void setId(int id) {
        this.id = id;
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
            this.getName().equals(animal.getName());
        }

    }
}

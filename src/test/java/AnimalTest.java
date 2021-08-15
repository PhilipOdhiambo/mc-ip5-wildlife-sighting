
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AnimalTest {
    @Test
    public void animal_classInstanceReturned_animal() {
        Animal testAnimal = getTestAnimal();
        Assertions.assertTrue(testAnimal instanceof Animal);

    }

    // Helper function
    public Animal getTestAnimal() {
        return new Animal("testAnimal");
    }
}
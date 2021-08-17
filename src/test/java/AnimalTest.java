
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AnimalTest extends  DatabaseRule {
    @Test
    public void animal_classInstanceReturned_animal() {
        Animal testAnimal = getTestAnimal();
        Assertions.assertTrue(testAnimal instanceof Animal);
    }
    @Test
    public void paramSave_nameSavedCorrectly_name(){
        Animal testAnimal = new Animal("Antelope",false);
        Assertions.assertEquals("Antelope",testAnimal.getName());
    }

    @Test
    public void readONe_SaveAndReadSingleAnimalFromTestDb_Animal(){
        Animal animal = new Animal("zebra",true);
        animal.save();
        int animalId = animal.getId();
        Assertions.assertEquals(animal, animal.findONe(animalId));
    }
    // Helper function
    public Animal getTestAnimal() {
        return new Animal("testAnimal",true);
    }
}
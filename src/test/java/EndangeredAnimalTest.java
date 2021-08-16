import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredAnimalTest extends DatabaseRule {
    @Test
    public void animal_classInstanceReturned_animal() {
        Animal testAnimal = endangered();
        Assertions.assertTrue(testAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void name_propertyInitializedCorrectly_name(){
        EndangeredAnimal testAnimal = new EndangeredAnimal("Rhino",EndangeredAnimal.YOUNG,EndangeredAnimal.ILL);
        Assertions.assertEquals("Rhino",testAnimal.getName());
    }

    @Test
    public void readONe_SaveAndReadSingleAnimalFromTestDb_Animal(){
        EndangeredAnimal animal = new EndangeredAnimal("zebra",EndangeredAnimal.ADULT,EndangeredAnimal.OK);
        animal.save();
        int animalId = animal.getId();
        Assertions.assertEquals(animal, animal.findONe(animalId));
    }

    // Helper function
    public EndangeredAnimal endangered() {
        return new EndangeredAnimal("Rhino",EndangeredAnimal.ADULT,EndangeredAnimal.HEALTHY);
    }


}
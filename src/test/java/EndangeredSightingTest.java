//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EndangeredSightingTest extends DatabaseRule {
//    @Test
//    public void animal_classInstanceReturned_animal() {
//        EndangeredSighting testAnimal = endangered();
//        Assertions.assertTrue(testAnimal instanceof EndangeredSighting);
//    }
//    @Test
//    public void paramSave_propertyInitializedCorrectly_name(){
//        EndangeredSighting testAnimal = new EndangeredSighting(1,"young", "healthy","zone A","philip");
//        Assertions.assertEquals(1,testAnimal.animalId);
//    }
//
////    @Test
////    public void readONe_SaveAndReadSingleAnimalFromTestDb_Animal(){
////        EndangeredSighting animal = endangered();
////        animal.save();
////        int animalId = animal.animalId;
////        Assertions.assertEquals(animal, animal.findONe(animalId));
////    }
//
//    // Helper function
//    public EndangeredSighting endangered() {
//        return  new EndangeredSighting(1,"young", "healthy","zone A","philip");
//    }
//
//
//}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SightingTest extends DatabaseRule {
    @Test
    public void siting_returnInstanceSiting_siting(){
        Sighting siting = new Sighting(1,"zone1","philip");
        Assertions.assertTrue(siting instanceof Sighting);
    }
    @Test
    public void propertyInitializedCorrectly(){
        Sighting siting = new Sighting(1,"zone2","Steve");

        Assertions.assertEquals(1, siting.getAnimalId());
        Assertions.assertEquals("zone2", siting.getLocation());
        Assertions.assertEquals("Steve", siting.getRanger());
    }

    @Test
    public void readOne_returnSitingSavedInTestData() {
        Sighting siting1 = new Sighting(1,"zone1","philip");
        Sighting siting2 = new Sighting(1,"zone1","philip");
        siting1.save();
        siting2.save();

        assertEquals(siting1,siting2.findONe(siting1.getId()));
    }


}
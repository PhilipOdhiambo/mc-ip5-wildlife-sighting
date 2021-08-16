import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SitingTest extends DatabaseRule {
    @Test
    public void siting_returnInstanceSiting_siting(){
        Siting siting = new Siting(1,"zone1","philip");
        Assertions.assertTrue(siting instanceof Siting);
    }
    @Test
    public void propertyInitializedCorrectly(){
        Siting siting = new Siting(1,"zone2","Steve");

        Assertions.assertEquals(1, siting.getAnimalId());
        Assertions.assertEquals("zone2", siting.getLocation());
        Assertions.assertEquals("Steve", siting.getRanger());
    }

    @Test
    public void readOne_returnSitingSavedInTestData() {
        Siting siting1 = new Siting(1,"zone1","philip");
        Siting siting2 = new Siting(1,"zone1","philip");
        siting1.save();
        siting2.save();

        assertEquals(siting1,siting2.findONe(siting1.getId()));
    }


}
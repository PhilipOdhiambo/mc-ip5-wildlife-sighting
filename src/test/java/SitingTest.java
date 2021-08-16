import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SitingTest extends DatabaseRule {
    @Test
    public void siting_returnInstanceSiting_siting(){
        Siting siting = new Siting(1,1,"zone1","philip");
        Assertions.assertTrue(siting instanceof Siting);
    }

}
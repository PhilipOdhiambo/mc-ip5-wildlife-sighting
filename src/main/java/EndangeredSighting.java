public class EndangeredSighting extends Sighting {
    private String age;
    private String health;

    public static final String DATABASE_TPE = "Endangered";

    // Health parameters
    public static final String HEALTHY = "Healthy";
    public static final String ILL = "Ill";
    public static final String OK = "Okay";

    // Age parameters
    public static final String NEW_BORN = "New born";
    public static final String YOUNG = "young";
    public static final String ADULT = "Adult";


    public final static String DATABASE_TYPE = "Endangered";

    EndangeredSighting(int animalId, String age, String health, String location, String ranger) {
        super(animalId,location,ranger);
        this.age = age;
        this.health = health;

    }

}




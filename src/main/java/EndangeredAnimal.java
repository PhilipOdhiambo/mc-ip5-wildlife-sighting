public class EndangeredAnimal extends Animal {
    private String age;
    private String health;

    // Health parameters
    public static final String HEALTHY = "Healthy";
    public static final String ILL = "Ill";
    public static final String OK = "Okay";

    // Age parameters
    public static final String NEW_BORN = "New born";
    public static final String YOUNG = "young";
    public static final String ADULT = "Adult";


    public final static String DATABASE_TYPE = "Endangered";

    EndangeredAnimal(String name, String age, String health) {
        super(name);
        this.age = age;
        this.health = health;

    }
}




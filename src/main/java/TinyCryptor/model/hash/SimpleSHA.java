package TinyCryptor.model.hash;

public class SimpleSHA extends AbstractHashAlgorithm {

    // fields

    // constructor
    public SimpleSHA() {
        spec = "";
        typeList = new String[]{"1", "224", "256", "384", "512", "512/224", "512/256"};
    }

    // methods
    @Override
    public String getName() {
        return "SHA-";
    }
}

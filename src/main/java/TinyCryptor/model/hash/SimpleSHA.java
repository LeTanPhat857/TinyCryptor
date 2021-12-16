package TinyCryptor.model.hash;

public class SimpleSHA extends AbstractHashAlgorithm {

    // fields

    // constructor
    public SimpleSHA() {
        spec = "";
        typeList = new int[]{1, 224, 256, 384, 512};
    }

    // methods
    @Override
    public String getName() {
        return "SHA-";
    }
}

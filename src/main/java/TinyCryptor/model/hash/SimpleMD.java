package TinyCryptor.model.hash;

public class SimpleMD extends AbstractHashAlgorithm {

    // fields

    // constructor
    public SimpleMD() {
        spec = "";
        typeList = new int[]{2, 5};
    }

    // methods
    @Override
    public String getName() {
        return "MD" + "                                  ";
    }
}

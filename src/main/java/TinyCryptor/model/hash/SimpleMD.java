package TinyCryptor.model.hash;

public class SimpleMD extends AbstractHashAlgorithm {

    // fields

    // constructor
    public SimpleMD() {
        spec = "";
        typeList = new String[]{"2", "5"};
    }

    // methods
    @Override
    public String getName() {
        // add "                          " to get a good view
        return "MD" + "                              ";
    }
}

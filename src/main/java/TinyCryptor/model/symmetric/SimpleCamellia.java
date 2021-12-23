package TinyCryptor.model.symmetric;

public class SimpleCamellia extends AbstractSymmetricAlgorithm{

    // fields

    // constructor
    public SimpleCamellia() {
        spec = "Camellia/ECB/NoPadding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{128, 192, 256};
        modesList = new String[]{"ECB", "CBC", "CFB", "OFB", "CTR"};
    }

    @Override
    public String getName() {
        return "Camellia";
    }
}

package TinyCryptor.model.symmetric;

public class SimpleCAST5 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleCAST5() {
        spec = "CAST5/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{64, 128};
        modesList = new String[]{"ECB", "CBC", "CFB", "OFB", "CTR"};

    }

    @Override
    public String getName() {
        return "CAST5";
    }
}
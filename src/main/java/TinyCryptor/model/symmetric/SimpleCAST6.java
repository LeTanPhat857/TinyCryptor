package TinyCryptor.model.symmetric;

public class SimpleCAST6 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleCAST6() {
        spec = "CAST6/ECB/pkcs5padding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{64, 128, 192, 256, 384, 512};
        modesList = new String[]{"ECB", "CBC", "CFB", "OFB", "CTR"};
    }

    @Override
    public String getName() {
        return "CAST6";
    }
}
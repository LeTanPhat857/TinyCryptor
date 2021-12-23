package TinyCryptor.model.symmetric;

public class SimpleRC4 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleRC4() {
        spec = "RC4";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{64, 128, 256, 512, 1024};
        modesList = new String[]{"ECB"};
        paddingList = new String[]{"NoPadding"};
    }

    // methods
    @Override
    public String getName() {
        return "RC4";
    }
}

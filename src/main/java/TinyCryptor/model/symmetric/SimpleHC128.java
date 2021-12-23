package TinyCryptor.model.symmetric;

public class SimpleHC128 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleHC128() {
        spec = "HC128/ECB/NoPadding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{128};
        modesList = new String[]{"ECB"};
        paddingList = new String[]{"NoPadding"};
    }

    @Override
    public String getName() {
        return "HC128";
    }
}
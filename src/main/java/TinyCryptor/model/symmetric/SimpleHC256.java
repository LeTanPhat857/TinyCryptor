package TinyCryptor.model.symmetric;

public class SimpleHC256 extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleHC256() {
        spec = "HC256/ECB/NoPadding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{128, 256};
        modesList = new String[]{"ECB"};
        paddingList = new String[]{"NoPadding"};
    }

    @Override
    public String getName() {
        return "HC256";
    }
}
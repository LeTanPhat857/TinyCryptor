package TinyCryptor.model.symmetric;

public class SimpleChaCha extends AbstractSymmetricAlgorithm {

    // fields

    // constructor
    public SimpleChaCha() {
        spec = "ChaCha/ECB/NoPadding";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{128, 256};
        modesList = new String[]{"ECB"};
        paddingList = new String[]{"NoPadding"};
    }

    @Override
    public String getName() {
        return "ChaCha";
    }
}
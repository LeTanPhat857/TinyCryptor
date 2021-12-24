package TinyCryptor.model.asymmetric;

public class SimpleRSA extends AbstractAsymmetricAlgorithm {

    // fields

    // constructor
    public SimpleRSA() {
        try {
            spec = "RSA/ECB/NoPadding";

            generateKey(1024);

            keySizeList = new int[]{1024, 2048, 3072, 4096};
            modesList = new String[]{"ECB"};
            paddingList = new String[]{"NoPadding", "PKCS1Padding", "OAEPWithSHA1AndMGF1Padding", "OAEPWithSHA256AndMGF1Padding"};
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "RSA";
    }

    // methods
}

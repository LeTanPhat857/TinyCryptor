package TinyCryptor.model.symmetric;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SimpleRC2 extends AbstractSymmetricAlgorithm{

    // fields

    // constructor
    public SimpleRC2() {
        spec = "RC2";
        key = new byte[16];
        initVec = new byte[16];

        keySizeList = new int[]{64, 128, 256, 512, 1024};
    }

    // methods
    @Override
    public String getName() {
        return "RC2";
    }
}

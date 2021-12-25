package library;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

public class TestBouncyCastleLibrary {

    @Test
    public void addProvider() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher.getInstance("Noekeon");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listAlgorithm() {
        Security.addProvider(new BouncyCastleProvider());
        String algorithmType = "Cipher.PBEWith";
        try {
            Provider[] providers = Security.getProviders();
            for (Provider provider : providers) {
                System.out.println(provider);
                for (Enumeration enumeration = provider.keys(); enumeration.hasMoreElements(); ) {
                    String element = (String) enumeration.nextElement();
                    if (element.contains(algorithmType) && !element.contains("Hmac")) {
                        System.out.println("\t" + element);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

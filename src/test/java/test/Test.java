package test;

import org.junit.jupiter.api.DisplayName;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class Test {

    @DisplayName("test")
    @org.junit.jupiter.api.Test
    public void test() {
        listAlgorithm("Cipher.PBEWith");
    }

    public List<String> listAlgorithm(String algorithmType) {
        List<String> result = new ArrayList<>();
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
        return result;
    }
}

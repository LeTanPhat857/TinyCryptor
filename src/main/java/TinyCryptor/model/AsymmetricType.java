package TinyCryptor.model;

import TinyCryptor.model.asymmetric.iAsymmetricAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class AsymmetricType implements iAlgorithmType {
    // fields
    private List<iAsymmetricAlgorithm> asymmetricList;

    // constructor
    private AsymmetricType() {
        asymmetricList = new ArrayList<>();
    }

    // methods
    public static AsymmetricType create() {
        return new AsymmetricType();
    }

    @Override
    public String getName() {
        return "asymmetric";
    }

    @Override
    public List<String> getAlgorithmList() {
        List<String> names = new ArrayList<>();
        for (iAsymmetricAlgorithm algorithm : asymmetricList) {
            names.add(algorithm.getName());
        }
        return names;
    }

    public AsymmetricType add(iAsymmetricAlgorithm algorithm) {
        asymmetricList.add(algorithm);
        return this;
    }

    public iAsymmetricAlgorithm getAlgorithm(String name) {
        for (iAsymmetricAlgorithm algorithm : asymmetricList) {
            if (algorithm.getName().equalsIgnoreCase(name)) {
                return algorithm;
            }
        }
        return null;
    }

    public iAsymmetricAlgorithm getAlgorithm(int index) {
        if (-1 < index & index < asymmetricList.size()) {
            return asymmetricList.get(index);
        }
        return null;
    }
}

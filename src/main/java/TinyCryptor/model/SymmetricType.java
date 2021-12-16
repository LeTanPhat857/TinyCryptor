package TinyCryptor.model;

import TinyCryptor.model.symmetric.iSymmetricAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class SymmetricType implements iAlgorithmType {
    // fields
    private List<iSymmetricAlgorithm> symmetricList;

    // constructor
    private SymmetricType() {
        symmetricList = new ArrayList<>();
    }

    // methods
    @Override
    public String getName() {
        return "symmetric";
    }

    @Override
    public List<String> getAlgorithmList() {
        List<String> names = new ArrayList<>();
        for (iSymmetricAlgorithm algorithm : symmetricList) {
            names.add(algorithm.getName());
        }
        return names;
    }

    public static SymmetricType create() {
        return new SymmetricType();
    }

    public SymmetricType add(iSymmetricAlgorithm algorithm) {
        symmetricList.add(algorithm);
        return this;
    }

    public iSymmetricAlgorithm getAlgorithm(String name) {
        for (iSymmetricAlgorithm algorithm : symmetricList) {
            if (algorithm.getName().equalsIgnoreCase(name)) {
                return algorithm;
            }
        }
        return null;
    }

    public iSymmetricAlgorithm getAlgorithm(int index) {
        if (-1 < index & index < symmetricList.size()) {
            return symmetricList.get(index);
        }
        return null;
    }
}

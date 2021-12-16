package TinyCryptor.model;

import TinyCryptor.model.hash.iHashAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class HashType implements iAlgorithmType {
    // fields
    private List<iHashAlgorithm> hashList;

    // constructor
    private HashType() {
        hashList = new ArrayList<>();
    }

    // methods
    public static HashType create() {
        return new HashType();
    }

    @Override
    public String getName() {
        return "hash";
    }

    @Override
    public List<String> getAlgorithmList() {
        List<String> names = new ArrayList<>();
        for (iHashAlgorithm algorithm : hashList) {
            names.add(algorithm.getName());
        }
        return names;
    }

    public HashType add(iHashAlgorithm algorithm) {
        hashList.add(algorithm);
        return this;
    }

    public iHashAlgorithm getAlgorithm(String name) {
        for (iHashAlgorithm algorithm : hashList) {
            if (algorithm.getName().equalsIgnoreCase(name)) {
                return algorithm;
            }
        }
        return null;
    }

    public iHashAlgorithm getAlgorithm(int index) {
        if (-1 < index & index < hashList.size()) {
            return hashList.get(index);
        }
        return null;
    }
}

package TinyCryptor.model;

import TinyCryptor.model.PBE.iPBEAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class PBEType implements iAlgorithmType {
    // fields
    private List<iPBEAlgorithm> pbeList;

    // constructor
    private PBEType() {
        pbeList = new ArrayList<>();
    }

    // methods
    @Override
    public String getName() {
        return "pbe";
    }

    @Override
    public List<String> getAlgorithmList() {
        List<String> names = new ArrayList<>();
        for (iPBEAlgorithm algorithm : pbeList) {
            names.add(algorithm.getName());
        }
        return names;
    }

    public static PBEType create() {
        return new PBEType();
    }

    public PBEType add(iPBEAlgorithm algorithm) {
        pbeList.add(algorithm);
        return this;
    }

    public iPBEAlgorithm getAlgorithm(String name) {
        for (iPBEAlgorithm algorithm : pbeList) {
            if (algorithm.getName().equalsIgnoreCase(name)) {
                return algorithm;
            }
        }
        return null;
    }

    public iPBEAlgorithm getAlgorithm(int index) {
        if (-1 < index & index < pbeList.size()) {
            return pbeList.get(index);
        }
        return null;
    }
}

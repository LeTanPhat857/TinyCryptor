package TinyCryptor.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    // fields
    List<iAlgorithmType> types;

    // constructor
    private Model() {
        types = new ArrayList<>();
    }

    // methods
    public static Model create() {
        return new Model();
    }

    public Model add(iAlgorithmType type) {
        this.types.add(type);
        return this;
    }

    public iAlgorithmType get(String typeName) {
        for (iAlgorithmType type : types) {
            if (type.getName().equalsIgnoreCase(typeName)) {
                return type;
            }
        }
        return null;
    }
}

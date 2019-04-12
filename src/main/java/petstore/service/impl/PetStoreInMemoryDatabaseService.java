package petstore.service.impl;

import org.springframework.stereotype.Service;
import petstore.model.Animal;
import petstore.service.PetstoreDatabaseService;

import java.util.HashMap;
import java.util.Map;

@Service
public class PetStoreInMemoryDatabaseService implements PetstoreDatabaseService {

    private Map<String, Animal> store;

    public PetStoreInMemoryDatabaseService() {
        this.store = new HashMap<String, Animal>();
    }

    public boolean addAnimal(String name, Animal animal) {
        store.put(name, animal);
        return true;
    }

    public Animal getAnimal(String name) {
        return store.get(name);
    }
}

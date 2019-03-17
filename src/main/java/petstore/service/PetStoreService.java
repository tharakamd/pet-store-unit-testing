package petstore.service;

import petstore.model.Animal;

public interface PetStoreService {
    boolean saveAnimal(String name, Animal animal);

    boolean isAnimalExists(String name);

    Animal findAnimal(String name);
}

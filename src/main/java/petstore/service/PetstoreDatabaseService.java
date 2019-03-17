package petstore.service;

import petstore.model.Animal;

public interface PetstoreDatabaseService {

    boolean addAnimal(String name, Animal animal);

    Animal getAnimal(String name);
}

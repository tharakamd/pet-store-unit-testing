package petstore.service.impl;

import petstore.exception.AnimalNotFoundException;
import petstore.model.Animal;
import petstore.service.PetStoreService;
import petstore.service.PetstoreDatabaseService;

public class PetStoreServiceImpl implements PetStoreService {

    private PetstoreDatabaseService petstoreDatabaseService;

    public PetStoreServiceImpl(PetstoreDatabaseService petstoreDatabaseService) {
        this.petstoreDatabaseService = petstoreDatabaseService;
    }

    public boolean saveAnimal(String name, Animal animal) {
        petstoreDatabaseService.addAnimal(name, animal);
        return true;
    }

    public boolean isAnimalExists(String name) {

        Animal animal = petstoreDatabaseService.getAnimal(name);
        return animal != null;

    }

    public Animal findAnimal(String name) throws AnimalNotFoundException {

        if (isAnimalExists(name)) {
            return petstoreDatabaseService.getAnimal(name);
        } else {
            throw new AnimalNotFoundException(name);
        }
    }

}

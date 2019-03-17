package petstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petstore.model.Animal;
import petstore.service.PetStoreService;

@RestController
@RequestMapping("animals")
public class AnimalController {
    private PetStoreService petstoreService;

    @Autowired
    public AnimalController(PetStoreService petstoreService) {
        this.petstoreService = petstoreService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveAnimal(@RequestBody Animal animal) {

        if (petstoreService.isAnimalExists(animal.getName())) {
            return new ResponseEntity<String>("Animal already exists", HttpStatus.FORBIDDEN);
        } else {
            boolean animalCreated = petstoreService.saveAnimal(animal.getName(), animal);
            if (animalCreated) {
                return new ResponseEntity<String>("Animal created :", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("Animal not created :", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }

    @GetMapping(value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Animal> findAnimal(@PathVariable(value = "name") String name) {
        Animal animal = petstoreService.findAnimal(name);

        if (animal == null) {
            return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Animal>(animal, HttpStatus.OK);
        }
    }

}
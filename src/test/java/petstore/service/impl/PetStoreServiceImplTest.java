package petstore.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import petstore.exception.AnimalNotFoundException;
import petstore.model.Animal;
import petstore.service.PetstoreDatabaseService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class PetStoreServiceImplTest {

    @Mock
    private PetstoreDatabaseService mockPetstoreDatabaseService;

    private PetStoreServiceImpl petStoreServiceImplUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        petStoreServiceImplUnderTest = new PetStoreServiceImpl(mockPetstoreDatabaseService);
    }

    @Test
    public void saveAnimal_animalSavingSuccessfully_correctAnimalIsSaving() {
        // Setup
        final String name = "cat_001";

        final Animal savingAnimal = new Animal();
        savingAnimal.setName(name);

        // Run the test
        final boolean result = petStoreServiceImplUnderTest.saveAnimal(name, savingAnimal);

        ArgumentCaptor<Animal> saveAnimalArgumentCaptor = ArgumentCaptor.forClass(Animal.class);
        verify(mockPetstoreDatabaseService).addAnimal(eq(name), saveAnimalArgumentCaptor.capture());

        Animal savedAnimal = saveAnimalArgumentCaptor.getValue();
        // Verify the results
        assertEquals(savingAnimal, savedAnimal);
        assertTrue(result);
    }

    @Test
    public void isAnimalExists_animalExistsInDatabase_ReturnTrue() {

        // Setup
        final String name = "rabbit_001";
        final boolean expectedResult = true;

        Mockito.when(mockPetstoreDatabaseService.getAnimal(name)).thenReturn(new Animal());

        // Run the test
        final boolean result = petStoreServiceImplUnderTest.isAnimalExists(name);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void isAnimalExists_animalNotExistsInDatabase_ReturnFalse() {

        // Setup
        final String name = "rabbit_001";
        final boolean expectedResult = false;

        Mockito.when(mockPetstoreDatabaseService.getAnimal(name)).thenReturn(null);

        // Run the test
        final boolean result = petStoreServiceImplUnderTest.isAnimalExists(name);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void findAnimal_animalFoundInDatabase_animalReturn() {
        // Setup
        final String name = "dog_001";
        final Animal animalInDatabase = new Animal();
        animalInDatabase.setName(name);

        Mockito.when(mockPetstoreDatabaseService.getAnimal(name)).thenReturn(animalInDatabase);

        // Run the test
        final Animal result = petStoreServiceImplUnderTest.findAnimal(name);

        // Verify the results
        assertEquals(animalInDatabase, result);
    }

    @Test(expected = AnimalNotFoundException.class)
    public void findAnimal_animalNotFoundInDatabase_exceptionThrown() {
        // Setup
        final String name = "dog_001";

        Mockito.when(mockPetstoreDatabaseService.getAnimal(name)).thenReturn(null);

        // Run the test
        petStoreServiceImplUnderTest.findAnimal(name);
    }
}

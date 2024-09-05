package example.atp;

import example.atp.domain.Owner;
import example.atp.domain.Pet;
import example.atp.repositories.OwnerRepository;
import example.atp.repositories.PetRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class Application {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    Application(OwnerRepository ownerRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @EventListener
    @Transactional
    void init(StartupEvent event) {
        // clear out any existing data
        petRepository.deleteAll();
        ownerRepository.deleteAll();

        // create the data
        Iterable<Owner> owners = ownerRepository.saveAll(List.of(
                new Owner("Fred", 45), new Owner("Barney", 40)
        ));
        List<Pet> pets = new ArrayList<>();
        for (Owner person : owners) {
            // Use Java 17 switch expressions to simplify logic
            switch (person.name()) {
                case "Fred" -> {
                    var dino = new Pet("Dino", person);
                    var bp = new Pet("Baby Puss", person, Pet.PetType.CAT);
                    pets.addAll(List.of(dino, bp));
                }
                case "Barney" -> pets.add(new Pet("Hoppy", person));
            }
        }

        //petRepository.saveAll(pets);
    }
}

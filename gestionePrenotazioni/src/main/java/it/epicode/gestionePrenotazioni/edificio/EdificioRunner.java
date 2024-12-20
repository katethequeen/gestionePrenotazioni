package it.epicode.gestionePrenotazioni.edificio;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class EdificioRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private EdificioRepo edificioRepo;

    @Override
    public void run(String... args) throws Exception {
        for(int i = 0; i < 10; i++) {
            Edificio edificio = new Edificio();
            edificio.setName(faker.company().name());
            edificio.setAddress(faker.address().streetAddress());
            edificio.setCity(faker.address().city());
            edificioRepo.save(edificio);
        }
    }
}

package it.epicode.gestionePrenotazioni.utente;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class UtenteRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private UtenteRepo utenteRepo;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Utente utente = new Utente();
            utente.setUsername(faker.name().username());
            utente.setFullName(faker.name().fullName());
            utente.setEmail(faker.internet().emailAddress());
            utenteRepo.save(utente);
        }
    }
}

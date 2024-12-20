package it.epicode.gestionePrenotazioni.postazione;

import com.github.javafaker.Faker;
import it.epicode.gestionePrenotazioni.edificio.Edificio;
import it.epicode.gestionePrenotazioni.edificio.EdificioRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class PostazioneRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private PostazioneRepo postazioneRepo;

    @Autowired
    private EdificioRepo edificioRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Edificio> edifici = edificioRepo.findAll();
        for(Edificio edificio : edifici){

        for(int i = 0; i < 10; i++) {
            Postazione postazione = new Postazione();
            postazione.setCode(faker.code().isbn10());
            postazione.setDescription(faker.company().catchPhrase());
            postazione.setTipoPostazione(TipoPostazione.values()[faker.number().numberBetween(0, TipoPostazione.values().length)]);
            postazione.setMassimoOccupanti(faker.number().numberBetween(1, 10));
            postazione.setEdificio(edificio);
            postazioneRepo.save(postazione);
            System.out.println("Postazione salvata " + postazione);
        }
        }
    }
}

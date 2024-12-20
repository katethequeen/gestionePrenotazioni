package it.epicode.gestionePrenotazioni.prenotazione;

import com.github.javafaker.Faker;
import it.epicode.gestionePrenotazioni.postazione.Postazione;
import it.epicode.gestionePrenotazioni.postazione.PostazioneRepo;
import it.epicode.gestionePrenotazioni.utente.Utente;
import it.epicode.gestionePrenotazioni.utente.UtenteRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Order(5)
public class PrenotazioneRunner implements CommandLineRunner {
    @Autowired
    private Faker faker;

    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    @Autowired
    private UtenteRepo utenteRepo;

    @Autowired
    private PostazioneRepo postazioneRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Utente> utenti = utenteRepo.findAll();
        List<Postazione> postazioni = postazioneRepo.findAll();
        System.out.println("Inizio creazione prenotazioni: ");
        for(Utente utente : utenti) {
            for(int i = 0; i < 10; i++) {
                LocalDate data = LocalDate.now().plusDays(faker.number().numberBetween(1, 30));
                boolean prenotazioneExist = prenotazioneRepo.existsByUtenteAndData(utente, data);

                if(!prenotazioneExist) {

                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setUtente(utente);
                prenotazione.setPostazione(postazioni.get(faker.number().numberBetween(0, postazioni.size())));
                prenotazione.setData(data);
                prenotazioneRepo.save(prenotazione);
                }
            }
        }
        System.out.println("Fine creazione prenotazioni");
    }
}

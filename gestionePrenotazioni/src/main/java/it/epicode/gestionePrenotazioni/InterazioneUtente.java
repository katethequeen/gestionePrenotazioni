package it.epicode.gestionePrenotazioni;

import it.epicode.gestionePrenotazioni.postazione.Postazione;
import it.epicode.gestionePrenotazioni.postazione.PostazioneRepo;
import it.epicode.gestionePrenotazioni.prenotazione.Prenotazione;
import it.epicode.gestionePrenotazioni.prenotazione.PrenotazioneRepo;
import it.epicode.gestionePrenotazioni.utente.Utente;
import it.epicode.gestionePrenotazioni.utente.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
@Order(10)
public class InterazioneUtente implements CommandLineRunner {
    @Autowired
    PrenotazioneRepo prenotazioneRepo;

    @Autowired
    UtenteRepo utenteRepo;

    @Autowired
    PostazioneRepo postazioneRepo;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1.Registrazione utente\n2.Prenotare postazione/edificio\n3.Verifica disponibilità postazione/edificio\n4.Esci");

            int scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    registraUtente(scanner);
                    break;
                case 2:
                    prenotaPostazione(scanner);
                    break;
                case 3:
                    verificaDisponibilita(scanner);
                    break;
                case 4:
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");

            }
        }
    }

    private void registraUtente(Scanner scanner) {
        System.out.println("Inserisci username:");
        String username = scanner.next();
        scanner.nextLine();

        System.out.println("Inserisci full name:");
        String fullName = scanner.nextLine();

        System.out.println("Inserisci email:");
        String email = scanner.nextLine();

        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setFullName(fullName);
        utente.setEmail(email);
        utenteRepo.save(utente);
        System.out.println("Utente registrato con successo.");
    }

    private void prenotaPostazione(Scanner scanner) {
        System.out.println("Inserisci il tuo username:");
        String username = scanner.next();
        scanner.nextLine();
        Utente utente = utenteRepo.findByUsername(username);
        if (utente == null) {
            System.out.println("Utente non trovato. Registrati prima di prenotare.");
            return;
        }
        System.out.println("Inserisci l'ID della postazione:");
        Long postazioneId = Long.parseLong(scanner.nextLine());
        Postazione postazione = postazioneRepo.findById(postazioneId).orElse(null);
        if (postazione == null) {
            System.out.println("Postazione non trovata.");
            return;
        }
        System.out.println("Inserisci la data della prenotazione (YYYY-MM-DD):");
        LocalDate data = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        boolean prenotazioneExist = prenotazioneRepo.existsByUtenteAndData(utente, data);
        if (prenotazioneExist) {
            System.out.println("Hai già una prenotazione per questa data.");
            return;
        }
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        prenotazione.setData(data);
        prenotazioneRepo.save(prenotazione);
        System.out.println("Prenotazione creata con successo.");
    }

    private void verificaDisponibilita(Scanner scanner) {
        System.out.println("Inserisci la città dell'edificio:");
        String city = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Inserisci la data (YYYY-MM-DD):");
        LocalDate data = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
        List<Prenotazione> prenotazioni = prenotazioneRepo.findByPostazione_Edificio_CityAndData(city, data);
        if (prenotazioni.isEmpty()) {
            System.out.println("Postazione disponibile");
        } else {
            System.out.println("Non ci sono postazioni disponibili per la data e il luogo selezionato.");
            for (Prenotazione prenotazione : prenotazioni) {
                System.out.println("ID: " + prenotazione.getPostazione().getId() + ", Descrizione: " + prenotazione.getPostazione().getDescription());
            }
        }
    }
}

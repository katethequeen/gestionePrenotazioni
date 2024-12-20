package it.epicode.gestionePrenotazioni.prenotazione;

import it.epicode.gestionePrenotazioni.postazione.Postazione;
import it.epicode.gestionePrenotazioni.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtenteAndData(Utente utente, LocalDate data);
    boolean existsByUtenteAndData(Utente utente, LocalDate data);
    List<Prenotazione> findByPostazione_Edificio_CityAndData(String city, LocalDate data);
}

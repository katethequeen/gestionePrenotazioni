package it.epicode.gestionePrenotazioni.utente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepo extends JpaRepository<Utente, Long> {
    Utente findByUsername(String username);
}

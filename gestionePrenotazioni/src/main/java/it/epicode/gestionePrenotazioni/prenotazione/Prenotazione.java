package it.epicode.gestionePrenotazioni.prenotazione;

import it.epicode.gestionePrenotazioni.postazione.Postazione;
import it.epicode.gestionePrenotazioni.utente.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;

    @ManyToOne
    private Utente utente;

    @ManyToOne
    private Postazione postazione;
}

package it.epicode.gestionePrenotazioni.utente;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "full_name")
    private String fullName;
    private String email;
}

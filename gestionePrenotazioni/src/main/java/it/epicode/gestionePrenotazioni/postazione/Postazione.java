package it.epicode.gestionePrenotazioni.postazione;


import it.epicode.gestionePrenotazioni.edificio.Edificio;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;

    private TipoPostazione tipoPostazione;

    @Column(name = "massimo_occupanti")
    private Integer massimoOccupanti;

    @ManyToOne
            @JoinColumn(name = "edificio_id")
    Edificio edificio;
}

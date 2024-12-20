package it.epicode.gestionePrenotazioni.postazione;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostazioneRepo extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoPostazioneAndEdificio_city(TipoPostazione tipoPostazione, String city);
}

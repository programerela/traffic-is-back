package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Signalizacija;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SignalizacijaService {

    // Kreiranje nove signalizacije
    Signalizacija createSignalizacija(Signalizacija signalizacija);

    // Ažuriranje signalizacije
    Signalizacija updateSignalizacija(Integer id, Signalizacija signalizacija);

    // Brisanje signalizacije
    void deleteSignalizacija(Integer id);

    // Pronalaženje signalizacije po ID-u
    Optional<Signalizacija> getSignalizacijaById(Integer id);

    // Pronalaženje sve signalizacije
    List<Signalizacija> getAllSignalizacija();

    // Pronalaženje signalizacije po statusu
    List<Signalizacija> getSignalizacijaByStatus(String status);

    // Pronalaženje signalizacije po tipu
    List<Signalizacija> getSignalizacijaByTip(String tipSignalizacije);

    // Pronalaženje neispravne signalizacije
    List<Signalizacija> getNeispravnaSignalizacija();

    // Pronalaženje signalizacije koja zahteva proveru
    List<Signalizacija> getSignalizacijaZaProveru(LocalDate datum);

    // Broj signalizacija po statusu
    long countByStatus(String status);
}
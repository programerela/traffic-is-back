package rs.ac.np.police.trafficis.service;

import rs.ac.np.police.trafficis.model.Korisnik;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {

    // Kreiranje novog korisnika
    Korisnik createKorisnik(Korisnik korisnik);

    // Ažuriranje korisnika
    Korisnik updateKorisnik(Integer id, Korisnik korisnik);

    // Brisanje korisnika
    void deleteKorisnik(Integer id);

    // Pronalaženje korisnika po ID-u
    Optional<Korisnik> getKorisnikById(Integer id);

    // Pronalaženje korisnika po username-u
    Optional<Korisnik> getKorisnikByUsername(String username);

    // Pronalaženje svih korisnika
    List<Korisnik> getAllKorisnici();

    // Pronalaženje korisnika po ulozi
    List<Korisnik> getKorisniciByRole(String role);

    // Provera da li postoji korisnik sa datim username-om
    boolean existsByUsername(String username);
}
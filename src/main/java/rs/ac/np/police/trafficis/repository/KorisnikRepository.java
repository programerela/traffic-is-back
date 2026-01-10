package rs.ac.np.police.trafficis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.np.police.trafficis.model.Korisnik;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

    // Pronalaženje korisnika po username-u
    Optional<Korisnik> findByUsername(String username);

    // Pronalaženje korisnika po ulozi
    List<Korisnik> findByRole(String role);

    // Provera da li postoji korisnik sa datim username-om
    boolean existsByUsername(String username);

    // Pronalaženje korisnika po imenu i prezimenu
    List<Korisnik> findByImeAndPrezime(String ime, String prezime);
}
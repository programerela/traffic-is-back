package rs.ac.np.police.trafficis.mapper;

import rs.ac.np.police.trafficis.dto.request.KorisnikDTO;
import rs.ac.np.police.trafficis.model.Korisnik;

import java.util.List;
import java.util.stream.Collectors;

public class KorisnikMapper {

    // Korisnik -> KorisnikDTO (bez lozinke)
    public static KorisnikDTO toDTO(Korisnik korisnik) {
        if (korisnik == null) {
            return null;
        }
        return new KorisnikDTO(
                korisnik.getIdUser(),
                korisnik.getUsername(),
                korisnik.getRole(),
                korisnik.getIme(),
                korisnik.getPrezime()
        );
    }

    // Lista Korisnik -> Lista KorisnikDTO
    public static List<KorisnikDTO> toDTOList(List<Korisnik> korisnici) {
        return korisnici.stream()
                .map(KorisnikMapper::toDTO)
                .collect(Collectors.toList());
    }
}
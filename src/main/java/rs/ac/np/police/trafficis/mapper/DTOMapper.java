package rs.ac.np.police.trafficis.mapper;

import rs.ac.np.police.trafficis.dto.response.*;
import rs.ac.np.police.trafficis.dto.request.*;
import rs.ac.np.police.trafficis.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    // VOZAC - Entity to DTO
    public static VozacResponseDTO toVozacDTO(Vozac vozac) {
        if (vozac == null) return null;

        return new VozacResponseDTO(
                vozac.getIdVozaca(),
                vozac.getIme(),
                vozac.getPrezime(),
                vozac.getJmbg(),
                vozac.getBrojVozacke(),
                vozac.getAdresa(),
                vozac.getTelefon()
        );
    }

    public static List<VozacResponseDTO> toVozacDTOList(List<Vozac> vozaci) {
        return vozaci.stream()
                .map(DTOMapper::toVozacDTO)
                .collect(Collectors.toList());
    }

    // VOZILO - Entity to DTO
    public static VoziloResponseDTO toVoziloDTO(Vozilo vozilo) {
        if (vozilo == null) return null;

        return new VoziloResponseDTO(
                vozilo.getIdVozila(),
                vozilo.getRegistracija(),
                vozilo.getMarka(),
                vozilo.getModel(),
                vozilo.getGodiste(),
                vozilo.getVozac() != null ? vozilo.getVozac().getIdVozaca() : null,
                vozilo.getVozac() != null ? vozilo.getVozac().getIme() : null,
                vozilo.getVozac() != null ? vozilo.getVozac().getPrezime() : null
        );
    }

    public static List<VoziloResponseDTO> toVoziloDTOList(List<Vozilo> vozila) {
        return vozila.stream()
                .map(DTOMapper::toVoziloDTO)
                .collect(Collectors.toList());
    }

    // INCIDENT - Entity to DTO
    public static IncidentResponseDTO toIncidentDTO(Incident incident) {
        if (incident == null) return null;

        return new IncidentResponseDTO(
                incident.getIdIncidenta(),
                incident.getDatumVreme(),
                incident.getLokacija(),
                incident.getOpis(),
                incident.getTezinaIncidenta(),
                incident.getStatusIncidenta(),
                incident.getVozac() != null ? incident.getVozac().getIdVozaca() : null,
                incident.getVozac() != null ? incident.getVozac().getIme() : null,
                incident.getVozac() != null ? incident.getVozac().getPrezime() : null,
                incident.getVozilo() != null ? incident.getVozilo().getIdVozila() : null,
                incident.getVozilo() != null ? incident.getVozilo().getRegistracija() : null
        );
    }

    public static List<IncidentResponseDTO> toIncidentDTOList(List<Incident> incidenti) {
        return incidenti.stream()
                .map(DTOMapper::toIncidentDTO)
                .collect(Collectors.toList());
    }

    // KAZNA - Entity to DTO
    public static KaznaResponseDTO toKaznaDTO(Kazna kazna) {
        if (kazna == null) return null;

        return new KaznaResponseDTO(
                kazna.getIdKazne(),
                kazna.getDatumIzdavanja(),
                kazna.getIznos(),
                kazna.getOpisPrekrsaja(),
                kazna.getStatusPlacanja(),
                kazna.getVrstaPrekrsaja(),
                kazna.getIncident() != null ? kazna.getIncident().getIdIncidenta() : null,
                kazna.getVozac() != null ? kazna.getVozac().getIdVozaca() : null,
                kazna.getVozac() != null ? kazna.getVozac().getIme() : null,
                kazna.getVozac() != null ? kazna.getVozac().getPrezime() : null
        );
    }

    public static List<KaznaResponseDTO> toKaznaDTOList(List<Kazna> kazne) {
        return kazne.stream()
                .map(DTOMapper::toKaznaDTO)
                .collect(Collectors.toList());
    }

    // SIGNALIZACIJA - Entity to DTO
    public static SignalizacijaResponseDTO toSignalizacijaDTO(Signalizacija signalizacija) {
        if (signalizacija == null) return null;

        return new SignalizacijaResponseDTO(
                signalizacija.getIdSignalizacije(),
                signalizacija.getLokacija(),
                signalizacija.getTipSignalizacije(),
                signalizacija.getStatus(),
                signalizacija.getDatumPoslednjeProvere()
        );
    }

    public static List<SignalizacijaResponseDTO> toSignalizacijaDTOList(List<Signalizacija> signalizacije) {
        return signalizacije.stream()
                .map(DTOMapper::toSignalizacijaDTO)
                .collect(Collectors.toList());
    }

    // ZAHTEV - Entity to DTO
    public static ZahtevResponseDTO toZahtevDTO(Zahtev zahtev) {
        if (zahtev == null) return null;

        return new ZahtevResponseDTO(
                zahtev.getIdZahteva(),
                zahtev.getTipZahteva(),
                zahtev.getOpis(),
                zahtev.getDatumVreme(),
                zahtev.getStatus(),
                zahtev.getIncident() != null ? zahtev.getIncident().getIdIncidenta() : null,
                zahtev.getSignalizacija() != null ? zahtev.getSignalizacija().getIdSignalizacije() : null
        );
    }

    public static List<ZahtevResponseDTO> toZahtevDTOList(List<Zahtev> zahtevi) {
        return zahtevi.stream()
                .map(DTOMapper::toZahtevDTO)
                .collect(Collectors.toList());
    }

    // OBAVESTENJE - Entity to DTO
    public static ObavestenjeResponseDTO toObavestenjeDTO(Obavestenje obavestenje) {
        if (obavestenje == null) return null;

        return new ObavestenjeResponseDTO(
                obavestenje.getIdObavestenja(),
                obavestenje.getTekst(),
                obavestenje.getDatumVreme(),
                obavestenje.getVrsta(),
                obavestenje.getPrioritet(),
                obavestenje.getIncident() != null ? obavestenje.getIncident().getIdIncidenta() : null
        );
    }

    public static List<ObavestenjeResponseDTO> toObavestenjeDTOList(List<Obavestenje> obavestenja) {
        return obavestenja.stream()
                .map(DTOMapper::toObavestenjeDTO)
                .collect(Collectors.toList());
    }
}
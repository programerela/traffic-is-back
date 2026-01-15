package rs.ac.np.police.trafficis.dto.response;

import java.time.LocalDateTime;

public class ObavestenjeResponseDTO {

    private Integer idObavestenja;
    private String tekst;
    private LocalDateTime datumVreme;
    private String vrsta;
    private String prioritet;
    private Integer idIncidenta;

    // Konstruktori
    public ObavestenjeResponseDTO() {
    }

    public ObavestenjeResponseDTO(Integer idObavestenja, String tekst, LocalDateTime datumVreme,
                                  String vrsta, String prioritet, Integer idIncidenta) {
        this.idObavestenja = idObavestenja;
        this.tekst = tekst;
        this.datumVreme = datumVreme;
        this.vrsta = vrsta;
        this.prioritet = prioritet;
        this.idIncidenta = idIncidenta;
    }

    // Getteri i Setteri
    public Integer getIdObavestenja() {
        return idObavestenja;
    }

    public void setIdObavestenja(Integer idObavestenja) {
        this.idObavestenja = idObavestenja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }

    public Integer getIdIncidenta() {
        return idIncidenta;
    }

    public void setIdIncidenta(Integer idIncidenta) {
        this.idIncidenta = idIncidenta;
    }
}
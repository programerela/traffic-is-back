package rs.ac.np.police.trafficis.dto.response;

import java.time.LocalDateTime;

public class IncidentResponseDTO {

    private Integer idIncidenta;
    private LocalDateTime datumVreme;
    private String lokacija;
    private String opis;
    private String tezinaIncidenta;
    private String statusIncidenta;
    private Integer idVozaca;
    private String imeVozaca;
    private String prezimeVozaca;
    private Integer idVozila;
    private String registracijaVozila;

    // Konstruktori
    public IncidentResponseDTO() {
    }

    public IncidentResponseDTO(Integer idIncidenta, LocalDateTime datumVreme, String lokacija,
                               String opis, String tezinaIncidenta, String statusIncidenta,
                               Integer idVozaca, String imeVozaca, String prezimeVozaca,
                               Integer idVozila, String registracijaVozila) {
        this.idIncidenta = idIncidenta;
        this.datumVreme = datumVreme;
        this.lokacija = lokacija;
        this.opis = opis;
        this.tezinaIncidenta = tezinaIncidenta;
        this.statusIncidenta = statusIncidenta;
        this.idVozaca = idVozaca;
        this.imeVozaca = imeVozaca;
        this.prezimeVozaca = prezimeVozaca;
        this.idVozila = idVozila;
        this.registracijaVozila = registracijaVozila;
    }

    // Getteri i Setteri
    public Integer getIdIncidenta() {
        return idIncidenta;
    }

    public void setIdIncidenta(Integer idIncidenta) {
        this.idIncidenta = idIncidenta;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getTezinaIncidenta() {
        return tezinaIncidenta;
    }

    public void setTezinaIncidenta(String tezinaIncidenta) {
        this.tezinaIncidenta = tezinaIncidenta;
    }

    public String getStatusIncidenta() {
        return statusIncidenta;
    }

    public void setStatusIncidenta(String statusIncidenta) {
        this.statusIncidenta = statusIncidenta;
    }

    public Integer getIdVozaca() {
        return idVozaca;
    }

    public void setIdVozaca(Integer idVozaca) {
        this.idVozaca = idVozaca;
    }

    public String getImeVozaca() {
        return imeVozaca;
    }

    public void setImeVozaca(String imeVozaca) {
        this.imeVozaca = imeVozaca;
    }

    public String getPrezimeVozaca() {
        return prezimeVozaca;
    }

    public void setPrezimeVozaca(String prezimeVozaca) {
        this.prezimeVozaca = prezimeVozaca;
    }

    public Integer getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Integer idVozila) {
        this.idVozila = idVozila;
    }

    public String getRegistracijaVozila() {
        return registracijaVozila;
    }

    public void setRegistracijaVozila(String registracijaVozila) {
        this.registracijaVozila = registracijaVozila;
    }
}
package rs.ac.np.police.trafficis.dto.request;

import java.time.LocalDateTime;

public class IncidentRequestDTO {

    private LocalDateTime datumVreme;
    private String lokacija;
    private String opis;
    private String tezinaIncidenta;
    private String statusIncidenta;
    private Integer idVozaca;  // Samo ID umesto celog objekta
    private Integer idVozila;  // Samo ID umesto celog objekta

    // Konstruktori
    public IncidentRequestDTO() {
    }

    public IncidentRequestDTO(LocalDateTime datumVreme, String lokacija, String opis,
                              String tezinaIncidenta, String statusIncidenta,
                              Integer idVozaca, Integer idVozila) {
        this.datumVreme = datumVreme;
        this.lokacija = lokacija;
        this.opis = opis;
        this.tezinaIncidenta = tezinaIncidenta;
        this.statusIncidenta = statusIncidenta;
        this.idVozaca = idVozaca;
        this.idVozila = idVozila;
    }

    // Getteri i Setteri
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

    public Integer getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Integer idVozila) {
        this.idVozila = idVozila;
    }
}
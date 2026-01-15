package rs.ac.np.police.trafficis.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class KaznaRequestDTO {

    private LocalDate datumIzdavanja;
    private BigDecimal iznos;
    private String opisPrekrsaja;
    private String statusPlacanja;
    private String vrstaPrekrsaja;
    private Integer idIncidenta;  // Opciono
    private Integer idVozaca;     // Obavezno

    // Konstruktori
    public KaznaRequestDTO() {
    }

    public KaznaRequestDTO(LocalDate datumIzdavanja, BigDecimal iznos, String opisPrekrsaja,
                           String statusPlacanja, String vrstaPrekrsaja,
                           Integer idIncidenta, Integer idVozaca) {
        this.datumIzdavanja = datumIzdavanja;
        this.iznos = iznos;
        this.opisPrekrsaja = opisPrekrsaja;
        this.statusPlacanja = statusPlacanja;
        this.vrstaPrekrsaja = vrstaPrekrsaja;
        this.idIncidenta = idIncidenta;
        this.idVozaca = idVozaca;
    }

    // Getteri i Setteri
    public LocalDate getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public String getOpisPrekrsaja() {
        return opisPrekrsaja;
    }

    public void setOpisPrekrsaja(String opisPrekrsaja) {
        this.opisPrekrsaja = opisPrekrsaja;
    }

    public String getStatusPlacanja() {
        return statusPlacanja;
    }

    public void setStatusPlacanja(String statusPlacanja) {
        this.statusPlacanja = statusPlacanja;
    }

    public String getVrstaPrekrsaja() {
        return vrstaPrekrsaja;
    }

    public void setVrstaPrekrsaja(String vrstaPrekrsaja) {
        this.vrstaPrekrsaja = vrstaPrekrsaja;
    }

    public Integer getIdIncidenta() {
        return idIncidenta;
    }

    public void setIdIncidenta(Integer idIncidenta) {
        this.idIncidenta = idIncidenta;
    }

    public Integer getIdVozaca() {
        return idVozaca;
    }

    public void setIdVozaca(Integer idVozaca) {
        this.idVozaca = idVozaca;
    }
}
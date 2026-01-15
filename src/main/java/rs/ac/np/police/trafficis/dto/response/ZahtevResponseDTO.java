package rs.ac.np.police.trafficis.dto.response;

import java.time.LocalDateTime;

public class ZahtevResponseDTO {

    private Integer idZahteva;
    private String tipZahteva;
    private String opis;
    private LocalDateTime datumVreme;
    private String status;
    private Integer idIncidenta;
    private Integer idSignalizacije;

    // Konstruktori
    public ZahtevResponseDTO() {
    }

    public ZahtevResponseDTO(Integer idZahteva, String tipZahteva, String opis,
                             LocalDateTime datumVreme, String status,
                             Integer idIncidenta, Integer idSignalizacije) {
        this.idZahteva = idZahteva;
        this.tipZahteva = tipZahteva;
        this.opis = opis;
        this.datumVreme = datumVreme;
        this.status = status;
        this.idIncidenta = idIncidenta;
        this.idSignalizacije = idSignalizacije;
    }

    // Getteri i Setteri
    public Integer getIdZahteva() {
        return idZahteva;
    }

    public void setIdZahteva(Integer idZahteva) {
        this.idZahteva = idZahteva;
    }

    public String getTipZahteva() {
        return tipZahteva;
    }

    public void setTipZahteva(String tipZahteva) {
        this.tipZahteva = tipZahteva;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(LocalDateTime datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdIncidenta() {
        return idIncidenta;
    }

    public void setIdIncidenta(Integer idIncidenta) {
        this.idIncidenta = idIncidenta;
    }

    public Integer getIdSignalizacije() {
        return idSignalizacije;
    }

    public void setIdSignalizacije(Integer idSignalizacije) {
        this.idSignalizacije = idSignalizacije;
    }
}
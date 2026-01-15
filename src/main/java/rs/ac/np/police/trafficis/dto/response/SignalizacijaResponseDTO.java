package rs.ac.np.police.trafficis.dto.response;

import java.time.LocalDate;

public class SignalizacijaResponseDTO {

    private Integer idSignalizacije;
    private String lokacija;
    private String tipSignalizacije;
    private String status;
    private LocalDate datumPoslednjeProvere;

    // Konstruktori
    public SignalizacijaResponseDTO() {
    }

    public SignalizacijaResponseDTO(Integer idSignalizacije, String lokacija, String tipSignalizacije,
                                    String status, LocalDate datumPoslednjeProvere) {
        this.idSignalizacije = idSignalizacije;
        this.lokacija = lokacija;
        this.tipSignalizacije = tipSignalizacije;
        this.status = status;
        this.datumPoslednjeProvere = datumPoslednjeProvere;
    }

    // Getteri i Setteri
    public Integer getIdSignalizacije() {
        return idSignalizacije;
    }

    public void setIdSignalizacije(Integer idSignalizacije) {
        this.idSignalizacije = idSignalizacije;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getTipSignalizacije() {
        return tipSignalizacije;
    }

    public void setTipSignalizacije(String tipSignalizacije) {
        this.tipSignalizacije = tipSignalizacije;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDatumPoslednjeProvere() {
        return datumPoslednjeProvere;
    }

    public void setDatumPoslednjeProvere(LocalDate datumPoslednjeProvere) {
        this.datumPoslednjeProvere = datumPoslednjeProvere;
    }
}
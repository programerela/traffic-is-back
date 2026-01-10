package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "signalizacija")
public class Signalizacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_signalizacije")
    private Integer idSignalizacije;

    @Column(name = "lokacija", nullable = false, length = 150)
    private String lokacija;

    @Column(name = "tip_signalizacije", nullable = false, length = 50)
    private String tipSignalizacije; // semafor/znak/svetlo upozorenja

    @Column(name = "status", nullable = false, length = 20)
    private String status; // ispravna/u kvaru/u održavanju

    @Column(name = "datum_poslednje_provere")
    private LocalDate datumPoslednjeProvere;

    @OneToMany(mappedBy = "signalizacija", cascade = CascadeType.ALL)
    private List<Zahtev> zahtevi;

    // Konstruktori
    public Signalizacija() {
    }

    public Signalizacija(Integer idSignalizacije, String lokacija, String tipSignalizacije,
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

    public List<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }
}

package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "zahtev")
public class Zahtev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zahteva")
    private Integer idZahteva;

    @Column(name = "tip_zahteva", nullable = false, length = 50)
    private String tipZahteva; // kvar signalizacije/zahtev za izveštaj/prijava građanina

    @Column(name = "opis", columnDefinition = "TEXT")
    private String opis;

    @Column(name = "datum_vreme", nullable = false)
    private LocalDateTime datumVreme;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // primljen/u obradi/rešen

    @ManyToOne
    @JoinColumn(name = "id_incidenta", referencedColumnName = "id_incidenta")
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "id_signalizacije", referencedColumnName = "id_signalizacije")
    private Signalizacija signalizacija;

    // Konstruktori
    public Zahtev() {
    }

    public Zahtev(Integer idZahteva, String tipZahteva, String opis,
                  LocalDateTime datumVreme, String status, Incident incident,
                  Signalizacija signalizacija) {
        this.idZahteva = idZahteva;
        this.tipZahteva = tipZahteva;
        this.opis = opis;
        this.datumVreme = datumVreme;
        this.status = status;
        this.incident = incident;
        this.signalizacija = signalizacija;
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

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public Signalizacija getSignalizacija() {
        return signalizacija;
    }

    public void setSignalizacija(Signalizacija signalizacija) {
        this.signalizacija = signalizacija;
    }
}
package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "obavestenje")
public class Obavestenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obavestenja")
    private Integer idObavestenja;

    @Column(name = "tekst", nullable = false, columnDefinition = "TEXT")
    private String tekst;

    @Column(name = "datum_vreme", nullable = false)
    private LocalDateTime datumVreme;

    @Column(name = "vrsta", nullable = false, length = 30)
    private String vrsta; // saobraćajno upozorenje/obaveštenje/hitno

    @Column(name = "prioritet", nullable = false, length = 10)
    private String prioritet; // nizak/srednji/visok

    @ManyToOne
    @JoinColumn(name = "id_incidenta", referencedColumnName = "id_incidenta")
    private Incident incident;

    // Konstruktori
    public Obavestenje() {
    }

    public Obavestenje(Integer idObavestenja, String tekst, LocalDateTime datumVreme,
                               String vrsta, String prioritet, Incident incident) {
        this.idObavestenja = idObavestenja;
        this.tekst = tekst;
        this.datumVreme = datumVreme;
        this.vrsta = vrsta;
        this.prioritet = prioritet;
        this.incident = incident;
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

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }
}
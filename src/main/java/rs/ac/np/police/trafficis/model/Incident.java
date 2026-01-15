package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidenta")
    private Integer idIncidenta;

    @Column(name = "datum_vreme", nullable = false)
    private LocalDateTime datumVreme;

    @Column(name = "lokacija", nullable = false, length = 150)
    private String lokacija;

    @Column(name = "opis", columnDefinition = "TEXT")
    private String opis;

    @Column(name = "tezina_incidenta", nullable = false, length = 20)
    private String tezinaIncidenta; // manji/veći/sa povređenima/sa poginulima

    @Column(name = "status_incidenta", nullable = false, length = 20)
    private String statusIncidenta; // evidentiran/obrađen/prosleđen

    @ManyToOne
    @JoinColumn(name = "id_vozaca", referencedColumnName = "id_vozaca")
    private Vozac vozac;

    @ManyToOne
    @JoinColumn(name = "id_vozila", referencedColumnName = "id_vozila")
    private Vozilo vozilo;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Kazna> kazne;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Obavestenje> obavestenja;

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Zahtev> zahtevi;

    // Konstruktori
    public Incident() {
    }

    public Incident(Integer idIncidenta, LocalDateTime datumVreme, String lokacija,
                    String opis, String tezinaIncidenta, String statusIncidenta,
                    Vozac vozac, Vozilo vozilo) {
        this.idIncidenta = idIncidenta;
        this.datumVreme = datumVreme;
        this.lokacija = lokacija;
        this.opis = opis;
        this.tezinaIncidenta = tezinaIncidenta;
        this.statusIncidenta = statusIncidenta;
        this.vozac = vozac;
        this.vozilo = vozilo;
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

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public List<Kazna> getKazne() {
        return kazne;
    }

    public void setKazne(List<Kazna> kazne) {
        this.kazne = kazne;
    }

    public List<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(List<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public List<Zahtev> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Zahtev> zahtevi) {
        this.zahtevi = zahtevi;
    }
}
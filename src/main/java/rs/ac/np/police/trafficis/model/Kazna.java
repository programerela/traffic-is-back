package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "kazna")
public class Kazna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kazne")
    private Integer idKazne;

    @Column(name = "datum_izdavanja", nullable = false)
    private LocalDate datumIzdavanja;

    @Column(name = "iznos", nullable = false, precision = 10, scale = 2)
    private BigDecimal iznos;

    @Column(name = "opis_prekrsaja", nullable = false, columnDefinition = "TEXT")
    private String opisPrekrsaja;

    @Column(name = "status_placanja", nullable = false, length = 20)
    private String statusPlacanja; // nije plaćena/plaćena/u postupku

    @Column(name = "vrsta_prekrsaja", nullable = false, length = 50)
    private String vrstaPrekrsaja;

    @ManyToOne
    @JoinColumn(name = "id_incidenta", referencedColumnName = "id_incidenta")
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "id_vozaca", referencedColumnName = "id_vozaca")
    private Vozac vozac;

    // Konstruktori
    public Kazna() {
    }

    public Kazna(Integer idKazne, LocalDate datumIzdavanja, BigDecimal iznos,
                 String opisPrekrsaja, String statusPlacanja, String vrstaPrekrsaja,
                 Incident incident, Vozac vozac) {
        this.idKazne = idKazne;
        this.datumIzdavanja = datumIzdavanja;
        this.iznos = iznos;
        this.opisPrekrsaja = opisPrekrsaja;
        this.statusPlacanja = statusPlacanja;
        this.vrstaPrekrsaja = vrstaPrekrsaja;
        this.incident = incident;
        this.vozac = vozac;
    }

    // Getteri i Setteri
    public Integer getIdKazne() {
        return idKazne;
    }

    public void setIdKazne(Integer idKazne) {
        this.idKazne = idKazne;
    }

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

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }
}

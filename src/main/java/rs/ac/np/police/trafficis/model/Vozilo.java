package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vozilo")
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vozila")
    private Integer idVozila;

    @Column(name = "registracija", nullable = false, length = 15, unique = true)
    private String registracija;

    @Column(name = "marka", nullable = false, length = 30)
    private String marka;

    @Column(name = "model", nullable = false, length = 40)
    private String model;

    @Column(name = "godiste", nullable = false)
    private Integer godiste;

    @ManyToOne
    @JoinColumn(name = "id_vozaca", referencedColumnName = "id_vozaca")
    private Vozac vozac;

    @OneToMany(mappedBy = "vozilo", cascade = CascadeType.ALL)
    private List<Incident> incidenti;

    // Konstruktori
    public Vozilo() {
    }

    public Vozilo(Integer idVozila, String registracija, String marka,
                  String model, Integer godiste, Vozac vozac) {
        this.idVozila = idVozila;
        this.registracija = registracija;
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.vozac = vozac;
    }

    // Getteri i Setteri
    public Integer getIdVozila() {
        return idVozila;
    }

    public void setIdVozila(Integer idVozila) {
        this.idVozila = idVozila;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getGodiste() {
        return godiste;
    }

    public void setGodiste(Integer godiste) {
        this.godiste = godiste;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public List<Incident> getIncidenti() {
        return incidenti;
    }

    public void setIncidenti(List<Incident> incidenti) {
        this.incidenti = incidenti;
    }
}
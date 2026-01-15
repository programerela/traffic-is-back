package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "vozac")
public class Vozac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vozaca")
    private Integer idVozaca;

    @Column(name = "ime", nullable = false, length = 50)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 50)
    private String prezime;

    @Column(name = "jmbg", nullable = false, length = 13, unique = true)
    private String jmbg;

    @Column(name = "broj_vozacke", nullable = false, length = 20, unique = true)
    private String brojVozacke;

    @Column(name = "adresa", length = 100)
    private String adresa;

    @Column(name = "telefon", length = 20)
    private String telefon;

    @OneToMany(mappedBy = "vozac", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vozilo> vozila;

    @OneToMany(mappedBy = "vozac", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Incident> incidenti;

    @OneToMany(mappedBy = "vozac", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Kazna> kazne;

    // Konstruktori
    public Vozac() {
    }

    public Vozac(Integer idVozaca, String ime, String prezime, String jmbg,
                 String brojVozacke, String adresa, String telefon) {
        this.idVozaca = idVozaca;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.brojVozacke = brojVozacke;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    // Getteri i Setteri
    public Integer getIdVozaca() {
        return idVozaca;
    }

    public void setIdVozaca(Integer idVozaca) {
        this.idVozaca = idVozaca;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getBrojVozacke() {
        return brojVozacke;
    }

    public void setBrojVozacke(String brojVozacke) {
        this.brojVozacke = brojVozacke;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public List<Vozilo> getVozila() {
        return vozila;
    }

    public void setVozila(List<Vozilo> vozila) {
        this.vozila = vozila;
    }

    public List<Incident> getIncidenti() {
        return incidenti;
    }

    public void setIncidenti(List<Incident> incidenti) {
        this.incidenti = incidenti;
    }

    public List<Kazna> getKazne() {
        return kazne;
    }

    public void setKazne(List<Kazna> kazne) {
        this.kazne = kazne;
    }
}
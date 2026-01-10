package rs.ac.np.police.trafficis.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "analiticki_izvestaj")
public class AnalitickiIzvestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_analitike")
    private Integer idAnalitike;

    @Column(name = "tip_analitike", nullable = false, length = 50)
    private String tipAnalitike; // mesečni/godišnji/ad-hoc

    @Column(name = "datum_generisanja", nullable = false)
    private LocalDateTime datumGenerisanja;

    @Column(name = "sadrzaj", nullable = false, columnDefinition = "TEXT")
    private String sadrzaj;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Korisnik korisnik;

    // Konstruktori
    public AnalitickiIzvestaj() {
    }

    public AnalitickiIzvestaj(Integer idAnalitike, String tipAnalitike,
                              LocalDateTime datumGenerisanja, String sadrzaj,
                              Korisnik korisnik) {
        this.idAnalitike = idAnalitike;
        this.tipAnalitike = tipAnalitike;
        this.datumGenerisanja = datumGenerisanja;
        this.sadrzaj = sadrzaj;
        this.korisnik = korisnik;
    }

    // Getteri i Setteri
    public Integer getIdAnalitike() {
        return idAnalitike;
    }

    public void setIdAnalitike(Integer idAnalitike) {
        this.idAnalitike = idAnalitike;
    }

    public String getTipAnalitike() {
        return tipAnalitike;
    }

    public void setTipAnalitike(String tipAnalitike) {
        this.tipAnalitike = tipAnalitike;
    }

    public LocalDateTime getDatumGenerisanja() {
        return datumGenerisanja;
    }

    public void setDatumGenerisanja(LocalDateTime datumGenerisanja) {
        this.datumGenerisanja = datumGenerisanja;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
}
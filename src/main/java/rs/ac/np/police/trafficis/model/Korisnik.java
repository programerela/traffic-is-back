package rs.ac.np.police.trafficis.model;    

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "korisnik")
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password; // Čuvati hash lozinke

    @Column(name = "role", nullable = false, length = 20)
    private String role; // policajac/rukovodilac/admin

    @Column(name = "ime", nullable = false, length = 50)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 50)
    private String prezime;

    @OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
    private List<AnalitickiIzvestaj> analitickiIzvestaji;

    // Konstruktori
    public Korisnik() {
    }

    public Korisnik(Integer idUser, String username, String password,
                    String role, String ime, String prezime) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
        this.ime = ime;
        this.prezime = prezime;
    }

    // Getteri i Setteri
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public List<AnalitickiIzvestaj> getAnalitickiIzvestaji() {
        return analitickiIzvestaji;
    }

    public void setAnalitickiIzvestaji(List<AnalitickiIzvestaj> analitickiIzvestaji) {
        this.analitickiIzvestaji = analitickiIzvestaji;
    }
}
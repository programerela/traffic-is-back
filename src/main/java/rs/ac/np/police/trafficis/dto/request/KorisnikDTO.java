package rs.ac.np.police.trafficis.dto.request;

public class KorisnikDTO {

    private Integer idUser;
    private String username;
    private String role;
    private String ime;
    private String prezime;

    // Konstruktori
    public KorisnikDTO() {
    }

    public KorisnikDTO(Integer idUser, String username, String role, String ime, String prezime) {
        this.idUser = idUser;
        this.username = username;
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
}
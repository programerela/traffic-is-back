package rs.ac.np.police.trafficis.dto.response;

public class VozacResponseDTO {

    private Integer idVozaca;
    private String ime;
    private String prezime;
    private String jmbg;
    private String brojVozacke;
    private String adresa;
    private String telefon;

    // Konstruktori
    public VozacResponseDTO() {
    }

    public VozacResponseDTO(Integer idVozaca, String ime, String prezime, String jmbg,
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
}
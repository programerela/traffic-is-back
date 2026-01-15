package rs.ac.np.police.trafficis.dto.response;

public class VoziloResponseDTO {

    private Integer idVozila;
    private String registracija;
    private String marka;
    private String model;
    private Integer godiste;
    private Integer idVozaca;      // Samo ID umesto celog objekta
    private String imeVozaca;      // Dodatno za prikaz
    private String prezimeVozaca;  // Dodatno za prikaz

    // Konstruktori
    public VoziloResponseDTO() {
    }

    public VoziloResponseDTO(Integer idVozila, String registracija, String marka,
                             String model, Integer godiste, Integer idVozaca,
                             String imeVozaca, String prezimeVozaca) {
        this.idVozila = idVozila;
        this.registracija = registracija;
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.idVozaca = idVozaca;
        this.imeVozaca = imeVozaca;
        this.prezimeVozaca = prezimeVozaca;
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

    public Integer getIdVozaca() {
        return idVozaca;
    }

    public void setIdVozaca(Integer idVozaca) {
        this.idVozaca = idVozaca;
    }

    public String getImeVozaca() {
        return imeVozaca;
    }

    public void setImeVozaca(String imeVozaca) {
        this.imeVozaca = imeVozaca;
    }

    public String getPrezimeVozaca() {
        return prezimeVozaca;
    }

    public void setPrezimeVozaca(String prezimeVozaca) {
        this.prezimeVozaca = prezimeVozaca;
    }
}
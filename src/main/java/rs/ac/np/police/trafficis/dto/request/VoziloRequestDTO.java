package rs.ac.np.police.trafficis.dto.request;

public class VoziloRequestDTO {

    private String registracija;
    private String marka;
    private String model;
    private Integer godiste;
    private Integer idVozaca;  // Samo ID vozača

    // Konstruktori
    public VoziloRequestDTO() {
    }

    public VoziloRequestDTO(String registracija, String marka, String model,
                            Integer godiste, Integer idVozaca) {
        this.registracija = registracija;
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.idVozaca = idVozaca;
    }

    // Getteri i Setteri
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
}
package id.ac.pcr.parkir;


public class Kendaraan {
    private String nomorPolisi;
    private String jenisKendaraan;

    public Kendaraan(String nomorPolisi, String jenisKendaraan) {
        this.nomorPolisi = nomorPolisi;
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getNomorPolisi() {
        return nomorPolisi;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }
}
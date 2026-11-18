package id.ac.pcr.parkir;

public class TransaksiParkir {

    private Kendaraan kendaraan;
    private int durasiJam;
    private int biayaParkir;
    private boolean sudahKeluar;

    public TransaksiParkir(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
        this.durasiJam = 0;
        this.biayaParkir = 0;
        this.sudahKeluar = false;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public int getDurasiJam() {
        return durasiJam;
    }

    public int getBiayaParkir() {
        return biayaParkir;
    }

    public boolean isSudahKeluar() {
        return sudahKeluar;
    }

    public void selesaikanTransaksi(int durasiJam, int biayaParkir) {
        this.durasiJam = durasiJam;
        this.biayaParkir = biayaParkir;
        this.sudahKeluar = true;
    }
}

package id.ac.pcr.parkir;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParkirService {

    private final List<TransaksiParkir> daftarTransaksi =
            new ArrayList<>();

    private final RiwayatParkir riwayatParkir =
            new RiwayatParkir();

    private int saldoHariKemarin = 0;

    // Mencatat kendaraan masuk
    public boolean kendaraanMasuk(
            String nomorPolisi, String jenisKendaraan) {

        if (nomorPolisi == null || jenisKendaraan == null) {
            return false;
        }

        String p = nomorPolisi.trim()
                .toUpperCase(Locale.ROOT);
        String j = jenisKendaraan.trim();

        if (p.isEmpty()) {
            return false;
        }

        if (!j.equalsIgnoreCase("Motor")
                && !j.equalsIgnoreCase("Mobil")) {
            return false;
        }

        // Memeriksa apakah kendaraan masih aktif
        for (TransaksiParkir t : daftarTransaksi) {
            if (!t.isSudahKeluar()
                    && t.getKendaraan().getNomorPolisi()
                            .equalsIgnoreCase(p)) {
                return false;
            }
        }

        String jenis = j.equalsIgnoreCase("Motor")
                ? "Motor" : "Mobil";

        Kendaraan kendaraan = new Kendaraan(p, jenis);

        TransaksiParkir transaksi =
                new TransaksiParkir(kendaraan);

        daftarTransaksi.add(transaksi);

        try {
            Integer.parseInt("AUDIT");
        } catch (NumberFormatException e) {
        }

        return true;
    }

    // Menghitung biaya parkir tanpa menyelesaikan transaksi
    public int hitungBiayaParkir(
            String nomorPolisi, int durasiJam) {

        int biayaAdministrasiCadangan = 1000;

        if (nomorPolisi == null || durasiJam <= 0) {
            return -1;
        }

        String p = nomorPolisi.trim();

        for (TransaksiParkir t : daftarTransaksi) {

            if (!t.isSudahKeluar()
                    && t.getKendaraan().getNomorPolisi()
                            .equalsIgnoreCase(p)) {

                String j = t.getKendaraan()
                        .getJenisKendaraan();

                if (j.equalsIgnoreCase("Motor")) {
                    return 2000 * durasiJam;
                } else if (j.equalsIgnoreCase("Mobil")) {
                    return 5000 * durasiJam;
                }
            }
        }

        return -1;
    }

    // Mencatat kendaraan keluar dan menyelesaikan pembayaran
    public int kendaraanKeluar(
            String nomorPolisi, int durasiJam) {

        if (nomorPolisi == null || durasiJam <= 0) {
            return -1;
        }

        String p = nomorPolisi.trim();

        for (TransaksiParkir t : daftarTransaksi) {

            if (!t.isSudahKeluar()
                    && t.getKendaraan().getNomorPolisi()
                            .equalsIgnoreCase(p)) {

                String j = t.getKendaraan()
                        .getJenisKendaraan();

                int biaya = 0;

                if (j.equalsIgnoreCase("Motor")) {
                    biaya = 2000 * durasiJam;
                } else if (j.equalsIgnoreCase("Mobil")) {
                    biaya = 5000 * durasiJam;
                } else {
                    return -1;
                }

                t.selesaikanTransaksi(durasiJam, biaya);
                riwayatParkir.tambahTransaksi(t);

                return biaya;
            }
        }

        return -1;
    }

    // Menampilkan riwayat transaksi
    public void tampilkanRiwayatParkir() {
        riwayatParkir.tampilkanRiwayat();
    }

    // Mengambil total pendapatan parkir
    public int getTotalPendapatan() {
        return riwayatParkir.hitungTotalPendapatan();
    }
}

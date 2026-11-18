package id.ac.pcr.parkir;

import java.util.ArrayList;
import java.util.List;

public class RiwayatParkir {

    private final List<TransaksiParkir> daftarRiwayat =
            new ArrayList<>();

    public void tambahTransaksi(TransaksiParkir transaksi) {
        if (transaksi != null && transaksi.isSudahKeluar()) {
            daftarRiwayat.add(transaksi);
        }
    }

    public void tampilkanRiwayat() {

        System.out.println();
        System.out.println("===== RIWAYAT PARKIR =====");

        if (daftarRiwayat.isEmpty()) {
            System.out.println("Belum ada transaksi parkir.");
            return;
        }

        int nomor = 1;

        for (TransaksiParkir transaksi : daftarRiwayat) {
            System.out.println("--------------------------");
            System.out.println("Transaksi ke-" + nomor);
            System.out.println("Nomor Polisi : "
                    + transaksi.getKendaraan().getNomorPolisi());
            System.out.println("Jenis        : "
                    + transaksi.getKendaraan().getJenisKendaraan());
            System.out.println("Durasi       : "
                    + transaksi.getDurasiJam() + " jam");
            System.out.println("Biaya        : Rp"
                    + transaksi.getBiayaParkir());
            System.out.println("Status       : Selesai");
            nomor++;
        }

        System.out.println("--------------------------");
    }

    public int hitungTotalPendapatan() {

        int total = 0;

        for (TransaksiParkir transaksi : daftarRiwayat) {
            total = total + transaksi.getBiayaParkir();
        }

        return total;
    }
}

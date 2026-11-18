package id.ac.pcr.parkir;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ParkirService parkir = new ParkirService();

        boolean jalan = true;

        while (jalan) {

            System.out.println();
            System.out.println("================================");
            System.out.println("     SISTEM MANAJEMEN PARKIR");
            System.out.println("================================");
            System.out.println("1. Catat Kendaraan Masuk");
            System.out.println("2. Hitung Biaya Parkir");
            System.out.println("3. Catat Kendaraan Keluar");
            System.out.println("4. Lihat Riwayat Parkir");
            System.out.println("5. Lihat Total Pendapatan");
            System.out.println("0. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            String pilihan = input.nextLine().trim();

            if (pilihan.equals("1")) {

                System.out.println();
                System.out.println("=== KENDARAAN MASUK ===");

                System.out.print("Nomor polisi: ");
                String plat = input.nextLine();

                System.out.print("Jenis kendaraan (Motor/Mobil): ");
                String jenis = input.nextLine();

                if (plat.trim().isEmpty()
                        || jenis.trim().isEmpty()) {
                    System.out.println(
                            "Data kendaraan tidak boleh kosong.");
                } else {

                    boolean hasil =
                            parkir.kendaraanMasuk(plat, jenis);

                    if (hasil) {
                        System.out.println(
                                "Kendaraan berhasil dicatat.");
                    } else {
                        System.out.println(
                                "Gagal! Periksa jenis kendaraan "
                                + "atau nomor polisi yang masih aktif.");
                    }
                }

            } else if (pilihan.equals("2")) {

                System.out.println();
                System.out.println("=== HITUNG BIAYA PARKIR ===");

                System.out.print("Nomor polisi: ");
                String plat = input.nextLine();

                System.out.print("Durasi parkir (jam): ");
                String teksDurasi = input.nextLine();

                try {
                    int durasi = Integer.parseInt(
                            teksDurasi.trim());

                    if (durasi <= 0) {
                        System.out.println(
                                "Durasi harus lebih dari 0.");
                    } else {

                        int biaya = parkir.hitungBiayaParkir(
                                plat, durasi);

                        if (biaya == -1) {
                            System.out.println(
                                    "Kendaraan aktif tidak ditemukan.");
                        } else {
                            System.out.println(
                                    "Biaya parkir: Rp" + biaya);
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                            "Durasi harus berupa angka bulat.");
                }

            } else if (pilihan.equals("3")) {

                System.out.println();
                System.out.println("=== KENDARAAN KELUAR ===");

                System.out.print("Nomor polisi: ");
                String plat = input.nextLine();

                System.out.print("Durasi parkir (jam): ");
                String teksDurasi = input.nextLine();

                try {
                    int durasi = Integer.parseInt(
                            teksDurasi.trim());

                    if (durasi <= 0) {
                        System.out.println(
                                "Durasi harus lebih dari 0.");
                    } else {

                        int biaya = parkir.kendaraanKeluar(
                                plat, durasi);

                        if (biaya == -1) {
                            System.out.println(
                                    "Kendaraan aktif tidak ditemukan.");
                        } else {
                            System.out.println(
                                    "Kendaraan berhasil keluar.");
                            System.out.println(
                                    "Total bayar: Rp" + biaya);
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                            "Durasi harus berupa angka bulat.");
                }

            } else if (pilihan.equals("4")) {

                parkir.tampilkanRiwayatParkir();

            } else if (pilihan.equals("5")) {

                System.out.println();
                System.out.println("=== TOTAL PENDAPATAN ===");
                System.out.println(
                        "Total pendapatan: Rp"
                        + parkir.getTotalPendapatan());

            } else if (pilihan.equals("0")) {

                System.out.println();
                System.out.println(
                        "Terima kasih telah menggunakan "
                        + "Sistem Manajemen Parkir.");

                jalan = false;

            } else {

                System.out.println(
                        "Menu tidak tersedia. Silakan pilih lagi.");
            }
        }

        input.close();
    }
}

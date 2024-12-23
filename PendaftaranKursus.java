import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.text.SimpleDateFormat;

public class PendaftaranKursus implements OperasiKursus { // implementasi operasi kursus
    private Connection koneksi;

    // Konstruktor untuk koneksi ke database
    public PendaftaranKursus()  { 
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Menginisialisasi koneksi
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursus", "root", ""); // Membuat koneksi ke basis data
            System.out.println("Koneksi ke database berhasil!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Kesalahan koneksi database: " + e.getMessage());
        }
    }

    // Method untuk pendaftaran siswa
    public void daftarSiswa(String nama, String telepon, String teleponOrangTua, String sekolah, String kelas, List<String> kursusDipilih) {
        if (koneksi == null) {
            System.out.println("Koneksi ke database belum tersedia.");
            return;
        }
        try {
            // Query untuk menyimpan data siswa
            String querySiswa = "INSERT INTO pendaftaran_kursus (nama, telepon, telepon_orang_tua, sekolah, kelas, kursus_dipilih, tanggal_pendaftaran) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = koneksi.prepareStatement(querySiswa);
            stmt.setString(1, nama);
            stmt.setString(2, telepon);
            stmt.setString(3, teleponOrangTua);
            stmt.setString(4, sekolah);
            stmt.setString(5, kelas);
            stmt.setString(6, String.join(", ", kursusDipilih));
            stmt.setString(7, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));

            // Eksekusi query
            stmt.executeUpdate();
            System.out.println("Pendaftaran berhasil untuk siswa: " + nama);
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan saat pendaftaran: " + e.getM13essage());
        }
    }

    // Method untuk menampilkan semua data siswa
    public void tampilkanKursus() {
        if (koneksi == null) {
            System.out.println("Koneksi ke database belum tersedia.");
            return;
        }
        try {
            String query = "SELECT * FROM pendaftaran_kursus";
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Data Pendaftaran Siswa:");
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                        ", Nama: " + rs.getString("nama") +
                        ", Kursus Dipilih: " + rs.getString("kursus_dipilih") +
                        ", Tanggal Pendaftaran: " + rs.getString("tanggal_pendaftaran"));
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan saat menampilkan data: " + e.getMessage());
        }
    }

 // UPDATE: Mengupdate data siswa berdasarkan ID
 public void updateSiswa(int id, String nama, String telepon, String sekolah, String kelas) {
    if (koneksi == null) {
        System.out.println("Koneksi ke database belum tersedia.");
        return;
    }
    try {
        String query = "UPDATE pendaftaran_kursus SET nama = ?, telepon = ?, sekolah = ?, kelas = ? WHERE id = ?";
        PreparedStatement stmt = koneksi.prepareStatement(query);
        stmt.setString(1, nama);
        stmt.setString(2, telepon);
        stmt.setString(3, sekolah);
        stmt.setString(4, kelas);
        stmt.setInt(5, id);
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Data siswa berhasil diperbarui.");
        } else {
            System.out.println("Data siswa dengan ID " + id + " tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.out.println("Kesalahan saat memperbarui data: " + e.getMessage());
    }
}
// DELETE: Menghapus data siswa berdasarkan ID
    public void hapusSiswa(int id) {
        if (koneksi == null) {
            System.out.println("Koneksi ke database belum tersedia.");
            return;
        }
        try {
            String query = "DELETE FROM pendaftaran_kursus WHERE id = ?";
            PreparedStatement stmt = koneksi.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data siswa berhasil dihapus.");
            } else {
                System.out.println("Data siswa dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan saat menghapus data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PendaftaranKursus pendaftaran = new PendaftaranKursus(); //objek
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) { //perulangan 
                try {
                    System.out.println("\n--- Sistem Pendaftaran Kursus ---");
                    System.out.println("1. Daftar Siswa");
                    System.out.println("2. Tampilkan Kursus");
                    System.out.println("3. Update Data Siswa");
                    System.out.println("4. Hapus Data Siswa");
                    System.out.println("5. Keluar");
                    System.out.print("Pilih opsi: ");

                    int pilihan = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline

                    switch (pilihan) { //Percabangan switch untuk menentukan aksi berdasarkan pilihan menu pengguna.
                        case 1:
                            System.out.print("Masukkan Nama Siswa: ");
                            String nama = scanner.nextLine();
                            System.out.print("Masukkan Telepon Siswa: ");
                            String telepon = scanner.nextLine();
                            System.out.print("Masukkan Telepon Orang Tua: ");
                            String teleponOrangTua = scanner.nextLine();
                            System.out.print("Masukkan Nama Sekolah: ");
                            String sekolah = scanner.nextLine();
                            System.out.print("Masukkan Kelas: ");
                            String kelas = scanner.nextLine();

                            System.out.println("Kursus Tersedia:");
                            System.out.println("1. Bahasa Inggris - Rp 200000");
                            System.out.println("2. Biologi - Rp 200000");
                            System.out.println("3. Kimia - Rp 200000");
                            System.out.println("4. Fisika - Rp 200000");
                            System.out.println("5. Matematika Peminatan - Rp 200000");
                            System.out.println("6. Matematika Wajib - Rp 200000");
                            System.out.println("7. Bahasa Indonesia - Rp 200000");

                            System.out.print("Masukkan nama kursus yang dipilih (pisahkan dengan koma): ");
                            String inputKursus = scanner.nextLine();
                            List<String> kursusDipilih = Arrays.asList(inputKursus.split(","));
                            int totalBiaya = kursusDipilih.size() * 200000;
                            System.out.println("Total biaya: Rp " + totalBiaya);

                            pendaftaran.daftarSiswa(nama, telepon, teleponOrangTua, sekolah, kelas, kursusDipilih);
                            break;

                        case 2:
                            pendaftaran.tampilkanKursus();
                            break;

                        case 3:
                        System.out.print("ID Siswa yang ingin diupdate: ");
                        int idUpdate = scanner.nextInt();
                        scanner.nextLine(); // Konsumsi newline
                        System.out.print("Nama baru: ");
                        String namaBaru = scanner.nextLine();
                        System.out.print("Telepon baru: ");
                        String teleponBaru = scanner.nextLine();
                        System.out.print("Sekolah baru: ");
                        String sekolahBaru = scanner.nextLine();
                        System.out.print("Kelas baru: ");
                        String kelasBaru = scanner.nextLine();
                        pendaftaran.updateSiswa(idUpdate, namaBaru, teleponBaru, sekolahBaru, kelasBaru);
                        break;

                        case 4:
                        System.out.print("ID Siswa yang ingin dihapus: ");
                        int idHapus = scanner.nextInt();
                        pendaftaran.hapusSiswa(idHapus);
                        break;

                        case 5:
                        System.out.println("Keluar dari sistem. Sampai jumpa!");
                        return;

                        default:
                            System.out.println("Pilihan tidak valid.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid. Harap masukkan angka.");
                    scanner.nextLine(); // Konsumsi input yang salah
                }
            }
        }
    }

    @Override
    public void daftarSiswa() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'daftarSiswa'");
    }

    @Override
    public void hitungTotalBiaya() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hitungTotalBiaya'");
    }
}

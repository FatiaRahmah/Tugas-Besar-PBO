// Kelas untuk Kursus
class Kursus {
    private String nama;
    private double harga;

    public Kursus(String nama, double harga) { //Constructor di Kelas Kursus
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }
}
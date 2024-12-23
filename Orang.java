// Kelas Abstrak (Superkelas) kelas induk
abstract class Orang {
    protected String nama;
    protected String telepon;

    public Orang(String nama, String telepon) {//Constructor di Kelas Orang
        this.nama = nama;
        this.telepon = telepon;
    }

    public String getNama() {
        return nama;
    }

    public String getTelepon() {
        return telepon;
    }
}
// Subclass untuk Siswa
class Siswa extends Orang {
    private String teleponOrangTua;
    private String sekolah;
    private String kelas;

    public Siswa(String nama, String telepon, String teleponOrangTua, String sekolah, String kelas) {//Constructor di Kelas Siswa
        super(nama, telepon);
        this.teleponOrangTua = teleponOrangTua;
        this.sekolah = sekolah;
        this.kelas = kelas;
    }

    public String getTeleponOrangTua() {
        return teleponOrangTua;
    }

    public String getSekolah() {
        return sekolah;
    }

    public String getKelas() {
        return kelas;
    }
}

package model;

public class DokumenArsip {

    // Identitas utama dokumen
    private final int idArsip;

    // Informasi dokumen
    private String namaArsip;
    private String kategoriArsip;
    private String formatArsip;
    private String keteranganArsip;
    private String isiArsip;

    // Constructor untuk membuat objek dokumen baru
    public DokumenArsip(
            int idArsip,
            String namaArsip,
            String kategoriArsip,
            String formatArsip,
            String keteranganArsip,
            String isiArsip) {

        if (idArsip <= 0) {
            throw new IllegalArgumentException(
                    "ID dokumen harus lebih dari 0."
            );
        }

        this.idArsip = idArsip;
        this.namaArsip = validasiTeks(namaArsip, "Nama dokumen");
        this.kategoriArsip = validasiTeks(kategoriArsip, "Kategori");
        this.formatArsip = validasiTeks(formatArsip, "Format dokumen");
        this.keteranganArsip = rapikanTeks(keteranganArsip);
        this.isiArsip = rapikanTeks(isiArsip);
    }

    // Memastikan informasi wajib tidak kosong
    private String validasiTeks(String nilai, String namaKolom) {
        if (nilai == null || nilai.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    namaKolom + " tidak boleh kosong."
            );
        }

        return nilai.trim();
    }

    // Merapikan teks yang boleh kosong
    private String rapikanTeks(String nilai) {
        return nilai == null ? "" : nilai.trim();
    }

    // Mengambil ID dokumen
    public int getIdArsip() {
        return idArsip;
    }

    public String getNamaArsip() {
        return namaArsip;
    }

    public String getKategoriArsip() {
        return kategoriArsip;
    }

    public String getFormatArsip() {
        return formatArsip;
    }

    public String getKeteranganArsip() {
        return keteranganArsip;
    }

    public String getIsiArsip() {
        return isiArsip;
    }

    // Mengubah informasi dokumen tanpa mengubah ID
    public void setNamaArsip(String namaArsip) {
        this.namaArsip = validasiTeks(namaArsip, "Nama dokumen");
    }

    public void setKategoriArsip(String kategoriArsip) {
        this.kategoriArsip = validasiTeks(kategoriArsip, "Kategori");
    }

    public void setFormatArsip(String formatArsip) {
        this.formatArsip = validasiTeks(formatArsip, "Format dokumen");
    }

    public void setKeteranganArsip(String keteranganArsip) {
        this.keteranganArsip = rapikanTeks(keteranganArsip);
    }

    public void setIsiArsip(String isiArsip) {
        this.isiArsip = rapikanTeks(isiArsip);
    }

    // Menampilkan ringkasan dokumen
    public String tampilkanRingkasan() {
        return "ID: " + idArsip
                + " | Nama: " + namaArsip
                + " | Kategori: " + kategoriArsip
                + " | Format: " + formatArsip;
    }
}

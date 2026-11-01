
package model;

public class SimpulArsip {

    // Data dokumen yang disimpan dalam node
    private DokumenArsip dataDokumen;

    // Referensi ke dua cabang BST
    private SimpulArsip cabangKiri;
    private SimpulArsip cabangKanan;

    // Constructor untuk membuat simpul baru
    public SimpulArsip(DokumenArsip dataDokumen) {
        if (dataDokumen == null) {
            throw new IllegalArgumentException(
                    "Data dokumen tidak boleh kosong."
            );
        }

        this.dataDokumen = dataDokumen;
        this.cabangKiri = null;
        this.cabangKanan = null;
    }

    // Mengambil data dokumen dari simpul
    public DokumenArsip getDataDokumen() {
        return dataDokumen;
    }

    // Mengganti data dokumen pada simpul
    public void setDataDokumen(DokumenArsip dataDokumen) {
        if (dataDokumen == null) {
            throw new IllegalArgumentException(
                    "Data dokumen tidak boleh kosong."
            );
        }

        this.dataDokumen = dataDokumen;
    }

    // Mengambil cabang kiri
    public SimpulArsip getCabangKiri() {
        return cabangKiri;
    }

    // Mengatur cabang kiri
    public void setCabangKiri(SimpulArsip cabangKiri) {
        this.cabangKiri = cabangKiri;
    }

    // Mengambil cabang kanan
    public SimpulArsip getCabangKanan() {
        return cabangKanan;
    }

    // Mengatur cabang kanan
    public void setCabangKanan(SimpulArsip cabangKanan) {
        this.cabangKanan = cabangKanan;
    }

    // Memeriksa apakah simpul belum memiliki anak
    public boolean tidakMemilikiCabang() {
        return cabangKiri == null && cabangKanan == null;
    }
}

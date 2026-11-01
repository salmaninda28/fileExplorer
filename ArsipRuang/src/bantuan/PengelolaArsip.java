
package bantuan;

import java.util.List;

import model.DokumenArsip;
import struktur.JelajahBST;
import struktur.PohonArsip;

public class PengelolaArsip {

    private PohonArsip ruangPenyimpanan;
    private JelajahBST penjelajah;
    private int nomorArsipBerikutnya;

    // Membuat pengelola arsip dan BST dalam keadaan kosong.
    public PengelolaArsip() {
        ruangPenyimpanan = new PohonArsip();
        penjelajah = new JelajahBST();
        nomorArsipBerikutnya = 1001;
    }

    // Menghasilkan ID arsip yang belum digunakan.
    private int buatNomorArsip() {
        while (ruangPenyimpanan.cariDokumen(nomorArsipBerikutnya) != null) {
            nomorArsipBerikutnya++;
        }

        return nomorArsipBerikutnya++;
    }

    // Menambahkan dokumen baru ke dalam BST.
    public DokumenArsip tambahArsip(
            String nama,
            String kategori,
            String format,
            String deskripsi,
            String isi) {

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama dokumen harus diisi.");
        }

        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori harus dipilih.");
        }

        if (format == null || format.trim().isEmpty()) {
            throw new IllegalArgumentException("Format dokumen harus diisi.");
        }

        int idBaru = buatNomorArsip();

        DokumenArsip dokumenBaru = new DokumenArsip(
                idBaru,
                nama.trim(),
                kategori.trim(),
                format.trim(),
                deskripsi == null ? "" : deskripsi.trim(),
                isi == null ? "" : isi
        );

        ruangPenyimpanan.tambahDokumen(dokumenBaru);

        return dokumenBaru;
    }

    // Mencari dokumen berdasarkan ID.
    public DokumenArsip cariArsip(int id) {
        return ruangPenyimpanan.cariDokumen(id);
    }

    // Menghapus dokumen berdasarkan ID.
    public boolean hapusArsip(int id) {
        DokumenArsip arsipDitemukan = ruangPenyimpanan.cariDokumen(id);

        if (arsipDitemukan == null) {
            return false;
        }

        ruangPenyimpanan.hapusDokumen(id);
        return true;
    }

    // Mengambil seluruh dokumen dengan urutan ID terkecil ke terbesar.
    public List<DokumenArsip> ambilSemuaArsip() {
        return penjelajah.jelajahInorder(ruangPenyimpanan);
    }

    // Mengambil jumlah dokumen yang tersimpan.
    public int getJumlahArsip() {
        return ruangPenyimpanan.hitungJumlahSimpul();
    }

    // Mengambil tinggi BST.
    public int getTinggiPenyimpanan() {
        return ruangPenyimpanan.hitungTinggi();
    }

    // Memeriksa apakah belum ada dokumen yang tersimpan.
    public boolean belumAdaArsip() {
        return ruangPenyimpanan.isEmpty();
    }

    // Menyediakan akses ke BST untuk kebutuhan visualisasi.
    public PohonArsip getRuangPenyimpanan() {
        return ruangPenyimpanan;
    }
}

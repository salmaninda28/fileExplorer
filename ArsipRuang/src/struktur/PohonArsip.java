
package struktur;

import model.DokumenArsip;
import model.SimpulArsip;

public class PohonArsip {

    // Menyimpan simpul paling atas dalam BST
    private SimpulArsip akarArsip;

    // Constructor: pohon dimulai dalam keadaan kosong
    public PohonArsip() {
        akarArsip = null;
    }

    // Mengambil simpul akar BST
    public SimpulArsip getRoot() {
        return akarArsip;
    }

    // Memeriksa apakah BST masih kosong
    public boolean isEmpty() {
        return akarArsip == null;
    }

    // ==========================================
    // BAGIAN 1: INSERT (MENAMBAHKAN DOKUMEN)
    // ==========================================

    public boolean tambahDokumen(DokumenArsip dokumenBaru) {

        if (dokumenBaru == null) {
            throw new IllegalArgumentException(
                "Dokumen tidak boleh kosong."
            );
        }

        int idBaru = dokumenBaru.getIdArsip();

        // Menolak ID yang sudah terdaftar
        if (cariDokumen(idBaru) != null) {
            return false;
        }

        akarArsip = sisipkanKeCabang(
            akarArsip,
            dokumenBaru
        );

        return true;
    }

    // Menentukan posisi dokumen baru menggunakan rekursi
    private SimpulArsip sisipkanKeCabang(
            SimpulArsip simpulSaatIni,
            DokumenArsip dokumenBaru) {

        // Posisi kosong ditemukan, buat simpul baru
        if (simpulSaatIni == null) {
            return new SimpulArsip(dokumenBaru);
        }

        int idBaru = dokumenBaru.getIdArsip();

        int idPembanding = simpulSaatIni
                .getDataDokumen()
                .getIdArsip();

        if (idBaru < idPembanding) {

            simpulSaatIni.setCabangKiri(
                sisipkanKeCabang(
                    simpulSaatIni.getCabangKiri(),
                    dokumenBaru
                )
            );

        } else if (idBaru > idPembanding) {

            simpulSaatIni.setCabangKanan(
                sisipkanKeCabang(
                    simpulSaatIni.getCabangKanan(),
                    dokumenBaru
                )
            );
        }

        return simpulSaatIni;
    }

    // ==========================================
    // BAGIAN 2: SEARCH (MENCARI DOKUMEN)
    // ==========================================

    public DokumenArsip cariDokumen(int idDicari) {

        SimpulArsip penelusuran = akarArsip;

        while (penelusuran != null) {

            int idSaatIni = penelusuran
                    .getDataDokumen()
                    .getIdArsip();

            if (idDicari == idSaatIni) {

                // Dokumen ditemukan
                return penelusuran.getDataDokumen();

            } else if (idDicari < idSaatIni) {

                // Bergerak ke cabang kiri
                penelusuran = penelusuran.getCabangKiri();

            } else {

                // Bergerak ke cabang kanan
                penelusuran = penelusuran.getCabangKanan();
            }
        }

        // Dokumen tidak ditemukan
        return null;
    }

    // ==========================================
    // BAGIAN 3: DELETE (MENGHAPUS DOKUMEN)
    // ==========================================

    public boolean hapusDokumen(int idTarget) {

        // Memastikan dokumen tersedia
        if (cariDokumen(idTarget) == null) {
            return false;
        }

        akarArsip = hapusDariCabang(
            akarArsip,
            idTarget
        );

        return true;
    }

    // Menghapus simpul menggunakan rekursi
    private SimpulArsip hapusDariCabang(
            SimpulArsip simpulSaatIni,
            int idTarget) {

        if (simpulSaatIni == null) {
            return null;
        }

        int idSaatIni = simpulSaatIni
                .getDataDokumen()
                .getIdArsip();

        if (idTarget < idSaatIni) {

            // Target berada di cabang kiri
            simpulSaatIni.setCabangKiri(
                hapusDariCabang(
                    simpulSaatIni.getCabangKiri(),
                    idTarget
                )
            );

        } else if (idTarget > idSaatIni) {

            // Target berada di cabang kanan
            simpulSaatIni.setCabangKanan(
                hapusDariCabang(
                    simpulSaatIni.getCabangKanan(),
                    idTarget
                )
            );

        } else {

            // KASUS 1: Simpul tidak memiliki anak
            if (simpulSaatIni.tidakMemilikiCabang()) {
                return null;
            }

            // KASUS 2: Hanya memiliki anak kanan
            if (simpulSaatIni.getCabangKiri() == null) {
                return simpulSaatIni.getCabangKanan();
            }

            // KASUS 2: Hanya memiliki anak kiri
            if (simpulSaatIni.getCabangKanan() == null) {
                return simpulSaatIni.getCabangKiri();
            }

            // KASUS 3: Memiliki dua anak

            // Mencari penerus inorder dari subtree kanan
            SimpulArsip penerus = cariPenerus(
                simpulSaatIni.getCabangKanan()
            );

            // Menggantikan data simpul yang dihapus
            simpulSaatIni.setDataDokumen(
                penerus.getDataDokumen()
            );

            // Menghapus penerus dari posisi sebelumnya
            simpulSaatIni.setCabangKanan(
                hapusDariCabang(
                    simpulSaatIni.getCabangKanan(),
                    penerus.getDataDokumen().getIdArsip()
                )
            );
        }

        return simpulSaatIni;
    }

    // Mencari ID terkecil dalam suatu subtree
    private SimpulArsip cariPenerus(SimpulArsip simpulAwal) {

        SimpulArsip simpulTerkecil = simpulAwal;

        while (simpulTerkecil.getCabangKiri() != null) {
            simpulTerkecil = simpulTerkecil.getCabangKiri();
        }

        return simpulTerkecil;
    }

    // ==========================================
    // BAGIAN 4: MENGHITUNG JUMLAH SIMPUL
    // ==========================================

    public int hitungJumlahSimpul() {
        return hitungSimpulDari(akarArsip);
    }

    private int hitungSimpulDari(SimpulArsip simpul) {

        if (simpul == null) {
            return 0;
        }

        return 1
            + hitungSimpulDari(simpul.getCabangKiri())
            + hitungSimpulDari(simpul.getCabangKanan());
    }

    // ==========================================
    // BAGIAN 5: MENGHITUNG TINGGI BST
    // ==========================================

    public int hitungTinggi() {
        return hitungTinggiDari(akarArsip);
    }

    private int hitungTinggiDari(SimpulArsip simpul) {

        if (simpul == null) {
            return 0;
        }

        int tinggiKiri = hitungTinggiDari(
            simpul.getCabangKiri()
        );

        int tinggiKanan = hitungTinggiDari(
            simpul.getCabangKanan()
        );

        return 1 + Math.max(tinggiKiri, tinggiKanan);
    }
}

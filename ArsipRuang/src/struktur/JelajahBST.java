
package struktur;

import java.util.ArrayList;
import java.util.List;

import model.DokumenArsip;
import model.SimpulArsip;

public class JelajahBST {

    
    
    // Urutan: Kiri -> Root -> Kanan
    public List<DokumenArsip> jelajahInorder(PohonArsip sumberPohon) {

        List<DokumenArsip> daftarHasil = new ArrayList<>();

        if (sumberPohon != null) {
            telusuriInorder(
                sumberPohon.getRoot(),
                daftarHasil
            );
        }

        return daftarHasil;
    }

    private void telusuriInorder(
            SimpulArsip simpul,
            List<DokumenArsip> daftarHasil) {

        if (simpul == null) {
            return;
        }

        // Kunjungi subtree kiri
        telusuriInorder(
            simpul.getCabangKiri(),
            daftarHasil
        );

        // Kunjungi node saat ini
        daftarHasil.add(simpul.getDataDokumen());

        // Kunjungi subtree kanan
        telusuriInorder(
            simpul.getCabangKanan(),
            daftarHasil
        );
    }

    
    // Urutan: Root -> Kiri -> Kanan
    public List<DokumenArsip> jelajahPreorder(PohonArsip sumberPohon) {

        List<DokumenArsip> daftarHasil = new ArrayList<>();

        if (sumberPohon != null) {
            telusuriPreorder(
                sumberPohon.getRoot(),
                daftarHasil
            );
        }

        return daftarHasil;
    }

    private void telusuriPreorder(
            SimpulArsip simpul,
            List<DokumenArsip> daftarHasil) {

        if (simpul == null) {
            return;
        }

        // Kunjungi node saat ini
        daftarHasil.add(simpul.getDataDokumen());

        // Kunjungi subtree kiri
        telusuriPreorder(
            simpul.getCabangKiri(),
            daftarHasil
        );

        // Kunjungi subtree kanan
        telusuriPreorder(
            simpul.getCabangKanan(),
            daftarHasil
        );
    }

   

    // Urutan: Kiri -> Kanan -> Root
     public List<DokumenArsip> jelajahPostorder(PohonArsip sumberPohon) {

        List<DokumenArsip> daftarHasil = new ArrayList<>();

        if (sumberPohon != null) {
            telusuriPostorder(
                sumberPohon.getRoot(),
                daftarHasil
            );
        }

        return daftarHasil;
    }

    private void telusuriPostorder(
            SimpulArsip simpul,
            List<DokumenArsip> daftarHasil) {

        if (simpul == null) {
            return;
        }

        // Kunjungi subtree kiri
        telusuriPostorder(
            simpul.getCabangKiri(),
            daftarHasil
        );

        // Kunjungi subtree kanan
        telusuriPostorder(
            simpul.getCabangKanan(),
            daftarHasil
        );

        // Kunjungi node saat ini
        daftarHasil.add(simpul.getDataDokumen());
    }
}

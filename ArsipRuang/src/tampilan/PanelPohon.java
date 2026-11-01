
package tampilan;

import model.DokumenArsip;
import model.SimpulArsip;
import struktur.PohonArsip;

import javax.swing.*;
import java.awt.*;

public class PanelPohon extends JDialog {

    private PohonArsip pohon;
    private JPanel panelGambar;

    public PanelPohon(JFrame induk, PohonArsip pohon) {
        super(induk, "Struktur Binary Search Tree", true);

        this.pohon = pohon;

        setSize(750, 500);
        setLocationRelativeTo(induk);
        setLayout(new BorderLayout(10, 10));

        JLabel judul = new JLabel(
                "Struktur Binary Search Tree (BST)",
                SwingConstants.CENTER
        );

        judul.setFont(new Font("SansSerif", Font.BOLD, 17));
        judul.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 10, 10)
        );

        panelGambar = new PanelGambarPohon();

        JScrollPane scrollPanel = new JScrollPane(panelGambar);
        scrollPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );

        JLabel keterangan = new JLabel(
                "Setiap simpul menunjukkan ID dan nama arsip.",
                SwingConstants.CENTER
        );

        keterangan.setFont(new Font("SansSerif", Font.PLAIN, 11));
        keterangan.setForeground(Color.GRAY);
        keterangan.setBorder(
                BorderFactory.createEmptyBorder(8, 5, 12, 5)
        );

        add(judul, BorderLayout.NORTH);
        add(scrollPanel, BorderLayout.CENTER);
        add(keterangan, BorderLayout.SOUTH);
    }

    // Panel khusus untuk menggambar struktur BST
    private class PanelGambarPohon extends JPanel {

        private final int jarakVertikal = 85;
        private final int lebarSimpul = 110;
        private final int tinggiSimpul = 45;

        public PanelGambarPohon() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(1000, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D gambar = (Graphics2D) g.create();

            gambar.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            SimpulArsip akar = pohon.getRoot();

            if (akar == null) {
                gambar.setColor(Color.GRAY);
                gambar.setFont(new Font("SansSerif", Font.PLAIN, 14));

                String pesan = "Belum ada arsip di dalam BST.";
                FontMetrics ukuran = gambar.getFontMetrics();

                int x = (getWidth() - ukuran.stringWidth(pesan)) / 2;
                int y = getHeight() / 2;

                gambar.drawString(pesan, x, y);
            } else {
                int posisiTengah = Math.max(getWidth() / 2, 500);

                gambarSimpul(
                        gambar,
                        akar,
                        posisiTengah,
                        50,
                        Math.max(180, getWidth() / 4)
                );
            }

            gambar.dispose();
        }

        // Menggambar simpul dan cabang pohon secara rekursif
        private void gambarSimpul(
                Graphics2D gambar,
                SimpulArsip simpul,
                int x,
                int y,
                int jarakHorizontal
        ) {
            if (simpul == null) {
                return;
            }

            SimpulArsip kiri = simpul.getCabangKiri();
            SimpulArsip kanan = simpul.getCabangKanan();

            int xKiri = x - jarakHorizontal;
            int xKanan = x + jarakHorizontal;
            int yAnak = y + jarakVertikal;

            // Menggambar garis cabang terlebih dahulu
            gambar.setColor(new Color(160, 170, 160));
            gambar.setStroke(new BasicStroke(1.5f));

            if (kiri != null) {
                gambar.drawLine(
                        x,
                        y + tinggiSimpul,
                        xKiri,
                        yAnak
                );
            }

            if (kanan != null) {
                gambar.drawLine(
                        x + lebarSimpul,
                        y + tinggiSimpul / 2,
                        xKanan + lebarSimpul / 2,
                        yAnak
                );
            }

            // Menggambar simpul saat ini
            gambarSatuSimpul(gambar, simpul, x, y);

            // Menggambar cabang kiri dan kanan
            if (kiri != null) {
                gambarSimpul(
                        gambar,
                        kiri,
                        xKiri,
                        yAnak,
                        Math.max(55, jarakHorizontal / 2)
                );
            }

            if (kanan != null) {
                gambarSimpul(
                        gambar,
                        kanan,
                        xKanan,
                        yAnak,
                        Math.max(55, jarakHorizontal / 2)
                );
            }
        }

        // Menggambar satu kotak simpul berisi ID arsip
        private void gambarSatuSimpul(
                Graphics2D gambar,
                SimpulArsip simpul,
                int x,
                int y
        ) {
            DokumenArsip dokumen = simpul.getDataDokumen();

            gambar.setColor(new Color(235, 242, 233));
            gambar.fillRoundRect(
                    x,
                    y,
                    lebarSimpul,
                    tinggiSimpul,
                    12,
                    12
            );

            gambar.setColor(new Color(90, 115, 88));
            gambar.setStroke(new BasicStroke(1.5f));

            gambar.drawRoundRect(
                    x,
                    y,
                    lebarSimpul,
                    tinggiSimpul,
                    12,
                    12
            );

            gambar.setFont(new Font("SansSerif", Font.BOLD, 12));
            gambar.setColor(new Color(50, 70, 52));

            String teksID = "ID: " + dokumen.getIdArsip();

            FontMetrics ukuranID = gambar.getFontMetrics();

            int posisiID = x
                    + (lebarSimpul - ukuranID.stringWidth(teksID)) / 2;

            gambar.drawString(teksID, posisiID, y + 19);

            gambar.setFont(new Font("SansSerif", Font.PLAIN, 10));

            String nama = dokumen.getNamaArsip();

            if (nama.length() > 15) {
                nama = nama.substring(0, 12) + "...";
            }

            FontMetrics ukuranNama = gambar.getFontMetrics();

            int posisiNama = x
                    + (lebarSimpul - ukuranNama.stringWidth(nama)) / 2;

            gambar.drawString(nama, posisiNama, y + 34);
        }
    }
}

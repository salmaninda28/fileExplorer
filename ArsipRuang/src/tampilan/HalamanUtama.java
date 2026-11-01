
package tampilan;

import bantuan.PengelolaArsip;
import model.DokumenArsip;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HalamanUtama extends JFrame {

    private PengelolaArsip pengelola;

    private JTable tabelArsip;
    private DefaultTableModel modelTabel;
    private JTextField kolomPencarian;

    private JLabel labelJumlah;
    private JLabel labelTinggi;

    public HalamanUtama() {
        pengelola = new PengelolaArsip();

        setTitle("ArsipRuang - Pengelolaan Arsip");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        getContentPane().setBackground(new Color(248, 248, 246));

        buatBagianAtas();
        buatBagianTabel();
        buatBagianBawah();

        perbaruiTabel();
    }

    // Bagian judul dan informasi aplikasi
    private void buatBagianAtas() {
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.setBackground(Color.WHITE);
        panelAtas.setBorder(
                BorderFactory.createEmptyBorder(18, 22, 18, 22)
        );

        JPanel panelJudul = new JPanel(new GridLayout(2, 1));
        panelJudul.setBackground(Color.WHITE);

        JLabel judul = new JLabel("ArsipRuang");
        judul.setFont(new Font("SansSerif", Font.BOLD, 23));
        judul.setForeground(new Color(55, 75, 58));

        JLabel subjudul = new JLabel(
                "Aplikasi Pengelolaan Arsip Digital"
        );
        subjudul.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subjudul.setForeground(Color.GRAY);

        panelJudul.add(judul);
        panelJudul.add(subjudul);

        JPanel panelInformasi = new JPanel(new GridLayout(1, 2, 10, 0));
        panelInformasi.setBackground(Color.WHITE);

        labelJumlah = new JLabel("Total Arsip: 0");
        labelTinggi = new JLabel("Tinggi Pohon: 0");

        aturLabelInformasi(labelJumlah);
        aturLabelInformasi(labelTinggi);

        panelInformasi.add(labelJumlah);
        panelInformasi.add(labelTinggi);

        panelAtas.add(panelJudul, BorderLayout.WEST);
        panelAtas.add(panelInformasi, BorderLayout.EAST);

        add(panelAtas, BorderLayout.NORTH);
    }

    // Mengatur tampilan label informasi
    private void aturLabelInformasi(JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(new Color(75, 95, 76));
        label.setBorder(
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
        label.setOpaque(true);
        label.setBackground(new Color(235, 240, 233));
    }

    // Bagian tabel dan pencarian
    private void buatBagianTabel() {
        JPanel panelUtama = new JPanel(new BorderLayout(10, 12));
        panelUtama.setBackground(new Color(248, 248, 246));
        panelUtama.setBorder(
                BorderFactory.createEmptyBorder(5, 22, 5, 22)
        );

        JPanel panelPencarian = new JPanel(new BorderLayout(10, 0));
        panelPencarian.setBackground(new Color(248, 248, 246));

        JLabel labelCari = new JLabel("Daftar Arsip");
        labelCari.setFont(new Font("SansSerif", Font.BOLD, 16));

        kolomPencarian = new JTextField();
        kolomPencarian.setPreferredSize(new Dimension(250, 32));
        kolomPencarian.setToolTipText("Cari berdasarkan ID atau nama");

        JButton tombolCari = new JButton("Cari");
        tombolCari.addActionListener(e -> cariArsip());

        JButton tombolReset = new JButton("Reset");
        tombolReset.addActionListener(e -> {
            kolomPencarian.setText("");
            perbaruiTabel();
        });

        JPanel bagianInput = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        bagianInput.setBackground(new Color(248, 248, 246));

        bagianInput.add(kolomPencarian);
        bagianInput.add(tombolCari);
        bagianInput.add(tombolReset);

        panelPencarian.add(labelCari, BorderLayout.WEST);
        panelPencarian.add(bagianInput, BorderLayout.EAST);

        String[] namaKolom = {
                "ID", "Nama Arsip", "Kategori", "Format", "Keterangan"
        };

        modelTabel = new DefaultTableModel(namaKolom, 0) {
            @Override
            public boolean isCellEditable(int baris, int kolom) {
                return false;
            }
        };

        tabelArsip = new JTable(modelTabel);
        tabelArsip.setRowHeight(28);
        tabelArsip.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabelArsip.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );
        tabelArsip.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollTabel = new JScrollPane(tabelArsip);
        scrollTabel.setBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 215))
        );

        panelUtama.add(panelPencarian, BorderLayout.NORTH);
        panelUtama.add(scrollTabel, BorderLayout.CENTER);

        add(panelUtama, BorderLayout.CENTER);
    }

    // Bagian tombol pengelolaan arsip
    private void buatBagianBawah() {
        JPanel panelBawah = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 12)
        );

        panelBawah.setBackground(Color.WHITE);
        panelBawah.setBorder(
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        );

        JButton tombolTambah = new JButton("Tambah Arsip");
        JButton tombolEdit = new JButton("Edit Arsip");
        JButton tombolHapus = new JButton("Hapus Arsip");
        JButton tombolPohon = new JButton("Lihat Struktur BST");

        tombolTambah.addActionListener(e -> tambahArsip());
        tombolEdit.addActionListener(e -> editArsip());
        tombolHapus.addActionListener(e -> hapusArsip());

        tombolPohon.addActionListener(e -> {
            new PanelPohon(this, pengelola.getRuangPenyimpanan())
                    .setVisible(true);
        });

        panelBawah.add(tombolTambah);
        panelBawah.add(tombolEdit);
        panelBawah.add(tombolHapus);
        panelBawah.add(tombolPohon);

        add(panelBawah, BorderLayout.SOUTH);
    }

    // Menampilkan seluruh data arsip ke tabel
    private void perbaruiTabel() {
        modelTabel.setRowCount(0);

        List<DokumenArsip> daftar =
                pengelola.ambilSemuaArsip();

        for (DokumenArsip dokumen : daftar) {
            modelTabel.addRow(new Object[]{
                    dokumen.getIdArsip(),
                    dokumen.getNamaArsip(),
                    dokumen.getKategoriArsip(),
                    dokumen.getFormatArsip(),
                    dokumen.getKeteranganArsip()
            });
        }

        labelJumlah.setText(
                "Total Arsip: " + pengelola.getJumlahArsip()
        );

        labelTinggi.setText(
                "Tinggi Pohon: " + pengelola.getTinggiPenyimpanan()
        );
    }

    // Menambahkan arsip baru
    private void tambahArsip() {
        FormArsip form = new FormArsip(
                this, "Tambah Arsip", null
        );

        form.setVisible(true);

        if (form.isDisimpan()) {
            try {
                pengelola.tambahArsip(
                        form.getNamaArsip(),
                        form.getKategoriArsip(),
                        form.getFormatArsip(),
                        form.getKeteranganArsip(),
                        form.getIsiArsip()
                );

                perbaruiTabel();

                JOptionPane.showMessageDialog(
                        this,
                        "Arsip berhasil ditambahkan."
                );

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    // Mengubah informasi arsip
    private void editArsip() {
        int baris = tabelArsip.getSelectedRow();

        if (baris == -1) {
            tampilkanPeringatanPilihArsip();
            return;
        }

        int id = (int) modelTabel.getValueAt(baris, 0);
        DokumenArsip dokumen = pengelola.cariArsip(id);

        FormArsip form = new FormArsip(
                this, "Edit Arsip", dokumen
        );

        form.setVisible(true);

        if (form.isDisimpan()) {
            try {
                dokumen.setNamaArsip(form.getNamaArsip());
                dokumen.setKategoriArsip(form.getKategoriArsip());
                dokumen.setFormatArsip(form.getFormatArsip());
                dokumen.setKeteranganArsip(
                        form.getKeteranganArsip()
                );
                dokumen.setIsiArsip(form.getIsiArsip());

                perbaruiTabel();

                JOptionPane.showMessageDialog(
                        this,
                        "Arsip berhasil diperbarui."
                );

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    // Menghapus arsip yang dipilih
    private void hapusArsip() {
        int baris = tabelArsip.getSelectedRow();

        if (baris == -1) {
            tampilkanPeringatanPilihArsip();
            return;
        }

        int id = (int) modelTabel.getValueAt(baris, 0);
        String nama = (String) modelTabel.getValueAt(baris, 1);

        int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Hapus arsip \"" + nama + "\"?",
                "Konfirmasi Penghapusan",
                JOptionPane.YES_NO_OPTION
        );

        if (pilihan == JOptionPane.YES_OPTION) {
            pengelola.hapusArsip(id);
            perbaruiTabel();

            JOptionPane.showMessageDialog(
                    this,
                    "Arsip berhasil dihapus."
            );
        }
    }

    // Mencari arsip berdasarkan ID atau nama
    private void cariArsip() {
        String kataKunci = kolomPencarian.getText()
                .trim().toLowerCase();

        modelTabel.setRowCount(0);

        if (kataKunci.isEmpty()) {
            perbaruiTabel();
            return;
        }

        for (DokumenArsip dokumen : pengelola.ambilSemuaArsip()) {
            String id = String.valueOf(dokumen.getIdArsip());
            String nama = dokumen.getNamaArsip().toLowerCase();

            if (id.contains(kataKunci) || nama.contains(kataKunci)) {
                modelTabel.addRow(new Object[]{
                        dokumen.getIdArsip(),
                        dokumen.getNamaArsip(),
                        dokumen.getKategoriArsip(),
                        dokumen.getFormatArsip(),
                        dokumen.getKeteranganArsip()
                });
            }
        }

        if (modelTabel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Arsip tidak ditemukan."
            );
        }
    }

    // Peringatan ketika belum memilih baris tabel
    private void tampilkanPeringatanPilihArsip() {
        JOptionPane.showMessageDialog(
                this,
                "Pilih arsip dari tabel terlebih dahulu.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // Menjalankan aplikasi
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HalamanUtama aplikasi = new HalamanUtama();
            aplikasi.setVisible(true);
        });
    }
}

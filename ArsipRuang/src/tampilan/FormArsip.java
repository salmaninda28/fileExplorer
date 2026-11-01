
package tampilan;

import model.DokumenArsip;

import javax.swing.*;
import java.awt.*;

public class FormArsip extends JDialog {

    private JTextField kolomNama;
    private JComboBox<String> pilihanKategori;
    private JComboBox<String> pilihanFormat;
    private JTextArea kolomKeterangan;
    private JTextArea kolomIsi;

    private boolean disimpan = false;

    public FormArsip(
            JFrame induk,
            String judul,
            DokumenArsip dokumen
    ) {
        super(induk, judul, true);

        setSize(450, 550);
        setLocationRelativeTo(induk);
        setLayout(new BorderLayout(10, 10));

        buatForm();
        buatTombol();

        // Jika dokumen tersedia, formulir digunakan untuk mengedit.
        if (dokumen != null) {
            isiForm(dokumen);
        }
    }

    // Membuat komponen formulir
    private void buatForm() {
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 10, 20)
        );
        panelForm.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 5, 7, 5);

        kolomNama = new JTextField(20);

        String[] kategori = {
                "Akademik",
                "Organisasi",
                "Kemahasiswaan",
                "Pribadi",
                "Lainnya"
        };

        pilihanKategori = new JComboBox<>(kategori);

        String[] format = {
                "PDF",
                "DOCX",
                "PPTX",
                "XLSX",
                "TXT",
                "Lainnya"
        };

        pilihanFormat = new JComboBox<>(format);

        kolomKeterangan = new JTextArea(3, 20);
        kolomKeterangan.setLineWrap(true);
        kolomKeterangan.setWrapStyleWord(true);

        kolomIsi = new JTextArea(7, 20);
        kolomIsi.setLineWrap(true);
        kolomIsi.setWrapStyleWord(true);

        int baris = 0;

        tambahKomponen(panelForm, gbc, baris++, "Nama Arsip", kolomNama);
        tambahKomponen(
                panelForm, gbc, baris++,
                "Kategori", pilihanKategori
        );
        tambahKomponen(
                panelForm, gbc, baris++,
                "Format", pilihanFormat
        );

        tambahKomponen(
                panelForm, gbc, baris++,
                "Keterangan",
                new JScrollPane(kolomKeterangan)
        );

        tambahKomponen(
                panelForm, gbc, baris++,
                "Isi Dokumen",
                new JScrollPane(kolomIsi)
        );

        JLabel catatan = new JLabel(
                "Catatan: Arsip disimpan sebagai data di dalam aplikasi."
        );
        catatan.setFont(new Font("SansSerif", Font.ITALIC, 10));
        catatan.setForeground(Color.GRAY);

        gbc.gridx = 0;
        gbc.gridy = baris;
        gbc.gridwidth = 2;
        panelForm.add(catatan, gbc);

        add(panelForm, BorderLayout.CENTER);
    }

    // Menambahkan label dan input ke dalam formulir
    private void tambahKomponen(
            JPanel panel,
            GridBagConstraints gbc,
            int baris,
            String label,
            Component komponen
    ) {
        gbc.gridwidth = 1;
        gbc.weightx = 0;

        gbc.gridx = 0;
        gbc.gridy = baris;

        JLabel teksLabel = new JLabel(label);
        teksLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(teksLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(komponen, gbc);
    }

    // Membuat tombol simpan dan batal
    private void buatTombol() {
        JPanel panelTombol = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 10)
        );

        panelTombol.setBackground(new Color(248, 248, 246));

        JButton tombolSimpan = new JButton("Simpan");
        JButton tombolBatal = new JButton("Batal");

        tombolSimpan.addActionListener(e -> simpanData());

        tombolBatal.addActionListener(e -> {
            disimpan = false;
            dispose();
        });

        panelTombol.add(tombolBatal);
        panelTombol.add(tombolSimpan);

        add(panelTombol, BorderLayout.SOUTH);
    }

    // Mengisi formulir dengan data dokumen yang dipilih
    private void isiForm(DokumenArsip dokumen) {
        kolomNama.setText(dokumen.getNamaArsip());
        pilihanKategori.setSelectedItem(dokumen.getKategoriArsip());
        pilihanFormat.setSelectedItem(dokumen.getFormatArsip());
        kolomKeterangan.setText(dokumen.getKeteranganArsip());
        kolomIsi.setText(dokumen.getIsiArsip());
    }

    // Memeriksa data sebelum formulir ditutup
    private void simpanData() {
        if (kolomNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nama arsip harus diisi.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            kolomNama.requestFocus();
            return;
        }

        if (pilihanKategori.getSelectedItem() == null
                || pilihanFormat.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Kategori dan format harus dipilih.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        disimpan = true;
        dispose();
    }

    // Mengambil status penyimpanan formulir
    public boolean isDisimpan() {
        return disimpan;
    }

    public String getNamaArsip() {
        return kolomNama.getText().trim();
    }

    public String getKategoriArsip() {
        return pilihanKategori.getSelectedItem().toString();
    }

    public String getFormatArsip() {
        return pilihanFormat.getSelectedItem().toString();
    }

    public String getKeteranganArsip() {
        return kolomKeterangan.getText().trim();
    }

    public String getIsiArsip() {
        return kolomIsi.getText();
    }
}

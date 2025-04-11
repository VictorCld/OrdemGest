import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HistoricoPage extends JFrame {
    private JPanel HistoricoPagePanel;
    private JLabel JLHist;
    private JTextPane txtHistorico;
    private JButton VERARQUIVOSButton;
    private JButton VOLTARButton;

    public HistoricoPage() {
        setContentPane(HistoricoPagePanel);
        setTitle("HISTÓRICO");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        String historico = GeralStorage.getTxtHistorico();
        txtHistorico.setText(historico);
        txtHistorico.setEditable(false);
        txtHistorico.setFocusable(false);
        txtHistorico.setOpaque(false);
        txtHistorico.repaint();
        txtHistorico.revalidate();

        VERARQUIVOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Historico.txt"))) {
                    writer.write(txtHistorico.getText());
                    JOptionPane.showMessageDialog(null, "Historico.txt criada com sucesso!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao criar a nota fiscal.");
                }
            }
        });

        VOLTARButton.addActionListener(e -> {
            dispose();
            new Main();
        });
    }
}
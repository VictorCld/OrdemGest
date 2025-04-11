import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadServicePage extends JFrame {
    private JPanel CadService;
    private JLabel Titleprinc;
    private JLabel JLNomeCadSvc;
    private JTextField txtService;
    private JButton SALVARSERVIÇOButton;
    private JButton VOLTARButton;

    public CadServicePage() {
        setContentPane(CadService);
        setTitle("CADASTRO DE SERVIÇOS");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();


        SALVARSERVIÇOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serviceName = txtService.getText();
                if (serviceName != null && !serviceName.trim().isEmpty()) {
                    new Services(serviceName);
                    JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome de serviço válido.");
                }
                dispose();
                new CadServicePage();
            }
        });

        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroPage();
            }
        });
    }
}

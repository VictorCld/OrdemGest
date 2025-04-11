import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CadTecPage extends JFrame {

    private JPanel CadTec;
    private JLabel JLPrinc;
    private JLabel JLNomeCadTec;
    private JLabel JLService;
    private JButton SALVARTECNICOButton;
    private JTextField txtNomeTec;
    private JLabel JLPreco;
    private JTextField txtValorTec;
    private JComboBox comboBoxCadTec;
    private JButton VOLTARButton;

    public CadTecPage() {
        setContentPane(CadTec);
        setTitle("CADASTRO DE TÉCNICOS");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();


        List<String> services = GeralStorage.getServiceNames();
        for (String service : services) {
            comboBoxCadTec.addItem(service);
        }

        SALVARTECNICOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tecnico = txtNomeTec.getText();
                Double price = Double.parseDouble(txtValorTec.getText());
                String service = (String) comboBoxCadTec.getSelectedItem();

                if (tecnico != null && !tecnico.trim().isEmpty() && price != null && service != null && !service.trim().isEmpty()) {
                    Tecnicos newTecnico = new Tecnicos(tecnico, List.of(service), price);
                    GeralStorage.addTecnico(newTecnico);
                    JOptionPane.showMessageDialog(null, "Técnico cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome de técnico e um valor válido.");
                }

                dispose();
                new CadTecPage();
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

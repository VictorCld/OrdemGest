import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrdemDeServ extends JFrame {
    private JPanel OrdemServPanel;
    private JLabel JLOrdens;
    private JLabel JLDesc;
    private JLabel JLServORD;
    private JComboBox<String> comboBoxServORD;
    private JTextField textField1;
    private JLabel JLTecORD;
    private JComboBox<String> comboBoxTecORD;
    private JButton SALVARButton;
    private JButton VOLTARButton;
    private JLabel JLPrazo;
    private JTextField textField2;
    private JLabel JLClienteOrdServ;
    private JComboBox<String> comboBoxClienteORD;
    private JLabel JLSrcEmer;
    private JCheckBox EMERGÊNCIACheckBox;

    public OrdemDeServ() {
        setContentPane(OrdemServPanel);
        setTitle("ORDEM DE SERVIÇOS");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        List<String> clients = GeralStorage.getClientNames();
        for (String client : clients) {
            comboBoxClienteORD.addItem(client);
        }

        List<String> services = GeralStorage.getServiceNames();
        for (String service : services) {
            comboBoxServORD.addItem(service);
        }

        List<String> tecnicos = GeralStorage.getTecnicos();
        for (String tecnico : tecnicos) {
            comboBoxTecORD.addItem(tecnico);
        }

        SALVARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clienteName = (String) comboBoxClienteORD.getSelectedItem();
                String serviceName = (String) comboBoxServORD.getSelectedItem();
                String tecnico = (String) comboBoxTecORD.getSelectedItem();
                String description = textField1.getText();
                String prazo = textField2.getText();
                boolean isEmergencial = EMERGÊNCIACheckBox.isSelected();

                if (serviceName == null || serviceName.isEmpty() ||
                        tecnico == null || tecnico.isEmpty() ||
                        description == null || description.isEmpty() ||
                        prazo == null || prazo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (GeralStorage.isTechnicianAssigned(tecnico)) {
                    JOptionPane.showMessageDialog(null, "O técnico já está atribuído a uma ordem.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!GeralStorage.isServiceMatchingTechnician(tecnico, serviceName)) {
                    JOptionPane.showMessageDialog(null, "O serviço não corresponde à especialidade do técnico.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Double price = GeralStorage.getPrecoDoTecnico(tecnico);
                Ordens ordem = new Ordens(GeralStorage.getOrderCounter(), clienteName, serviceName, tecnico, description, price, prazo, isEmergencial);
                GeralStorage.addOrder(ordem);

                if (isEmergencial) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço emergencial cadastrada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço cadastrada com sucesso!");
                }

                dispose();
                new OrdemDeServ();
            }
        });

        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main();
            }
        });
    }
}
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientesPage extends JFrame {

    private JLabel JLClientes;
    private JLabel JLDadosClientes;
    private JComboBox<String> comboBoxDadosClientes;
    private JButton VERDADOSButton;
    private JButton VOLTARButton;
    private JPanel ClientesPanel;
    private JButton TROCARPONTOSButton;

    public ClientesPage() {

        setContentPane(ClientesPanel);
        setTitle("ESCOLHA OS CLIENTES");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        List<String> clientNames = GeralStorage.getClientNames();
        for (String clientName : clientNames) {
            comboBoxDadosClientes.addItem(clientName);
        }

        VERDADOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClient = (String) comboBoxDadosClientes.getSelectedItem();
                if (selectedClient != null) {
                    String clientDetails = GeralStorage.getClientDetails(selectedClient);
                    String clientCPF = GeralStorage.getClientCPF(selectedClient);
                    int clientPoints = GeralStorage.getPoints(selectedClient);
                    new DadosClientePage(selectedClient, clientCPF, clientPoints);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente.");
                }
            }
        });

        TROCARPONTOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClient = (String) comboBoxDadosClientes.getSelectedItem();
                if (selectedClient != null) {
                    int clientPoints = GeralStorage.getPoints(selectedClient);
                    if (clientPoints >= 4) {
                        GeralStorage.exchangePointsForFreeOrder(selectedClient);
                        JOptionPane.showMessageDialog(null, "Os pontos foram trocados com sucesso! A próxima ordem será gratuita.");
                    } else {
                        int pointsNeeded = 4 - clientPoints;
                        JOptionPane.showMessageDialog(null, "O cliente não tem pontos suficientes para trocar por uma ordem. Faltam " + pointsNeeded + " pontos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente.");
                }
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
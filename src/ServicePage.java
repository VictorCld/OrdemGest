import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ServicePage extends JFrame {

    private JPanel ServicePage;
    private JProgressBar progressBar1;
    private JButton startButton;
    private JLabel PrincipalTitle;
    private JButton VOLTARButton;
    private JProgressBar progressBar2;
    private JComboBox<String> comboBoxServices;

    public ServicePage() {
        setContentPane(ServicePage);
        setTitle("SERVIÇOS");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        List<Ordens> ordens = GeralStorage.getOrders();
        for (Ordens ordem : ordens) {
            comboBoxServices.addItem(ordem.getOrderName());
        }

        PagamentoPage pagamentoPage = new PagamentoPage();
        pagamentoPage.setVisible(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOrder = (String) comboBoxServices.getSelectedItem();
                if (selectedOrder != null && !selectedOrder.isEmpty()) {
                    String orderDetails = GeralStorage.getOrderDetails(selectedOrder);
                    dispose();
                    new FimServPage(orderDetails, selectedOrder, ServicePage.this, pagamentoPage);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma ordem.");
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


    public JComboBox<String> getComboBoxServices() {
        return comboBoxServices;
    }
}
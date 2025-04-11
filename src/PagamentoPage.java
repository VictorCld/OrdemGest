import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PagamentoPage extends JFrame {
    private JPanel PagPage;
    private JLabel JLTitlePag;
    private JLabel JLClientePag;
    private JComboBox<String> comboBoxOrdPag;
    private JButton ESCOLHERButton;
    private JButton VOLTARButton;

    public PagamentoPage() {
        setContentPane(PagPage);
        setTitle("ESCOLHA DO PAGAMENTO");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        List<Ordens> ordens = GeralStorage.getOrders();
        for (Ordens ordem : ordens) {
            if (ordem.getOrderName().contains("(FINALIZADA)")) {
                comboBoxOrdPag.addItem(ordem.getOrderName());
            }
        }

        VOLTARButton.addActionListener(e -> {
            dispose();
            new Main();
        });

        ESCOLHERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOrder = (String) comboBoxOrdPag.getSelectedItem();
                if (selectedOrder != null) {
                    String orderDetails = GeralStorage.getOrderDetails(selectedOrder.split(":")[0].trim());
                    dispose();
                    new FimPagPage(selectedOrder, orderDetails);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma ordem.");
                }
            }
        });
    }
}
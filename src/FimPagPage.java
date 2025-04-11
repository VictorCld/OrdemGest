import javax.swing.*;
import java.util.List;

public class FimPagPage extends JFrame {

    private JPanel FimPagPagePanel;
    private JLabel JLFimPag;
    private JLabel JLDadosOrd;
    private JTextPane txtDadosClientePag;
    private JButton FINALIZARPAGAMENTOButton;
    private JButton VOLTARButton;
    private JLabel JLOpcPag;
    private JComboBox<String> comboBoxEscPag;

    public FimPagPage(String orderName, String orderDetails) {
        setContentPane(FimPagPagePanel);
        setTitle("PAGAMENTO");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();


        txtDadosClientePag.setText(orderDetails);
        txtDadosClientePag.setEditable(false);
        txtDadosClientePag.setFocusable(false);
        txtDadosClientePag.setOpaque(false);

        comboBoxEscPag.addItem("Pix");
        comboBoxEscPag.addItem("Crédito");

        final Double finalValorTotal = calculateTotalValue(orderDetails);
        final String clientName = getClientName(orderDetails);

        FINALIZARPAGAMENTOButton.addActionListener(e -> {
            String selectedPayment = (String) comboBoxEscPag.getSelectedItem();
            if (selectedPayment != null) {
                try {
                    String orderIdStr = orderName.replaceAll("[^0-9]", "");
                    int orderId = Integer.parseInt(orderIdStr);
                    double discount = GeralStorage.calculateDiscount(clientName);
                    double discountedValue = finalValorTotal * (1 - discount);
                    GeralStorage.addPoints(clientName, 1);
                    HistoricoPage historicoPage = new HistoricoPage();
                    historicoPage.setVisible(false);
                    GeralStorage.finalizeOrder(orderId);
                    if (GeralStorage.getPoints(clientName) == 1) {
                        JOptionPane.showMessageDialog(null, "Compra concluída pelo cliente " + clientName + ". Valor total pago: R$ " + finalValorTotal);
                    } else {
                        JOptionPane.showMessageDialog(null, "Compra concluída pelo cliente " + clientName + ". Valor total com desconto: R$ " + discountedValue);
                    }
                    comboBoxEscPag.removeItem(selectedPayment);
                    dispose();
                    new Main();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar o ID da ordem.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecione uma forma de pagamento.");
            }
        });
    }

    private Double calculateTotalValue(String orderDetails) {
        Double totalValue = 0.0;
        String[] orderDetailsParts = orderDetails.split("\n");
        Double valorTecnico = 0.0;
        Double prazo = 0.0;
        for (String orderDetailsPart : orderDetailsParts) {
            if (orderDetailsPart.contains("Preço: ")) {
                valorTecnico = Double.parseDouble(orderDetailsPart.split(": ")[1].trim());
            }
            if (orderDetailsPart.contains("Prazo:")) {
                String prazoStr = orderDetailsPart.split(": ")[1].trim().split(" ")[0];
                prazo = Double.parseDouble(prazoStr);
            }
        }
        totalValue = valorTecnico * prazo;
        return totalValue;
    }

    private String getClientName(String orderDetails) {
        String[] orderDetailsParts = orderDetails.split("\n");
        String clienteNome = "";
        for (String orderDetailsPart : orderDetailsParts) {
            if (orderDetailsPart.contains("Cliente: ")) {
                clienteNome = orderDetailsPart.split(": ")[1].trim();
                break;
            }
        }
        return clienteNome;
    }

    private int getAdditionalServicesCount(String clientName) {
        List<Ordens> ordens = GeralStorage.getOrders();
        int additionalServicesCount = 0;
        for (Ordens ordem : ordens) {
            if (ordem.getClienteName().equals(clientName)) {
                additionalServicesCount++;
            }
        }
        return additionalServicesCount;
    }
}
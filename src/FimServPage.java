import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FimServPage extends JFrame {
    private JPanel FimServPanel;
    private JLabel JLTitleFim;
    private JButton VOLTARButton;
    private JTextPane txtDescription;
    private JButton FINALIZARORDEMButton;
    private ServicePage servicePage;
    private PagamentoPage pagamentoPage;
    private String orderName;

    public FimServPage(String orderDetails, String orderName, ServicePage servicePage, PagamentoPage pagamentoPage) {
        this.servicePage = servicePage;
        this.pagamentoPage = pagamentoPage;
        this.orderName = orderName;
        setContentPane(FimServPanel);
        setTitle("FINALIZAÇÃO DE SERVIÇO");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();


        txtDescription.setText(orderDetails);
        txtDescription.setEditable(false);
        txtDescription.setFocusable(false);
        txtDescription.setOpaque(false);

        if (orderName.contains("(FINALIZADA)")) {
            FINALIZARORDEMButton.setEnabled(false);
        } else {
            FINALIZARORDEMButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean hasOngoingEmergencyOrder = GeralStorage.hasOngoingEmergencyOrder();
                    boolean isCurrentOrderEmergency = orderDetails.contains("Emergencial: SIM");

                    if (hasOngoingEmergencyOrder && !isCurrentOrderEmergency) {
                        JOptionPane.showMessageDialog(null, "Finalize todas as ordens emergenciais antes de finalizar outras ordens.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String newOrderName = orderName + "(FINALIZADA)";
                        GeralStorage.updateOrderName(orderName, newOrderName);

                        if (isCurrentOrderEmergency) {
                            JOptionPane.showMessageDialog(null, "Ordem de serviço emergencial finalizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Ordem de serviço finalizada com sucesso!");
                        }

                        dispose();
                        servicePage.dispose();
                        new ServicePage();
                    }
                }
            });
        }

        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                servicePage.dispose();
                new ServicePage();
            }
        });
    }
}
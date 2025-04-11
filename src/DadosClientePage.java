import javax.swing.*;

public class DadosClientePage extends JFrame {
    private JPanel DadosClientesPanel;
    private JLabel JLDadosDosClientes;
    private JTextPane txtDadosCliente;
    private JButton VOLTARButton;

    public DadosClientePage(String clientName, String clientCPF, int clientPoints) {
        setContentPane(DadosClientesPanel);
        setTitle("DADOS CLIENTES");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        StringBuilder clientInfo = new StringBuilder();
        clientInfo.append("Nome: ").append(clientName).append("\n");
        clientInfo.append("CPF: ").append(clientCPF).append("\n");
        clientInfo.append("Pontos Acumulados: ").append(clientPoints);

        txtDadosCliente.setContentType("text/plain");
        txtDadosCliente.setText(clientInfo.toString());
        txtDadosCliente.setEditable(false);
        txtDadosCliente.setFocusable(false);
        txtDadosCliente.setOpaque(false);

        VOLTARButton.addActionListener(e -> {
            dispose();
            new Main();
        });
    }
}
import javax.swing.*;

public class CadClientePage extends JFrame {

    private JLabel JLCadCliente;
    private JPanel CadClientePanel;
    private JLabel JLNomeCliente;
    private JLabel JLCPFCliente;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton SALVARCLIENTEButton;
    private JButton VOLTARButton;

    public CadClientePage() {
        setContentPane(CadClientePanel);
        setTitle("CADASTRO DE CLIENTE");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        VOLTARButton.addActionListener(e -> {
            dispose();
            new CadastroPage();
        });

        SALVARCLIENTEButton.addActionListener(e -> {
            String nome = textField1.getText();
            String cpf = textField3.getText();
            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                Clientes cliente = new Clientes(nome, cpf);
                GeralStorage.addClient(cliente); // Save the client to storage
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                dispose();
                new CadClientePage();
            }
        });
    }
}
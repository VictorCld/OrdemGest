import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {
    private JButton PAGBtn;
    private JPanel MainPanel;
    private JButton CadBtn;
    private JButton ORDEMDESERVIÇOSButton;
    private JButton SAIRButton;
    private JLabel TitleEscolha;
    private JButton SERVIÇOSButton;
    private JButton CLIENTESButton;
    private JButton HISTORICOButton;

    public Main() {
        setContentPane(MainPanel);
        setTitle("ISERVICES");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();



        CadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroPage();
            }
        });
        SAIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        SERVIÇOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ServicePage();
            }
        });
        ORDEMDESERVIÇOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new OrdemDeServ();
            }
        });
        PAGBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PagamentoPage();
            }
        });
        CLIENTESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClientesPage();
            }
        });
        HISTORICOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new HistoricoPage();
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Main();
    }


}

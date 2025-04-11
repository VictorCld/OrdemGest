import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPage extends JFrame {
    private JButton SERVICOSButton;
    private JButton TECNICOSButton;
    private JLabel titleCadPage;
    private JPanel CadPage;
    private JButton VOLTARButton;
    private JButton CLIENTESButton;

    public CadastroPage() {
        setContentPane(CadPage);
        setTitle("CADASTROS");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.setResizable(false);
        pack();

        VOLTARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Main();
            }
        });

        SERVICOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadServicePage();
            }
        });
        TECNICOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadTecPage();
            }
        });
        CLIENTESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadClientePage();
            }
        });
    }
}
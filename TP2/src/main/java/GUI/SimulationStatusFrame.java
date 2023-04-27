package GUI;

import javax.swing.*;
import java.awt.*;

public class SimulationStatusFrame extends JFrame {

    private JTextArea statusArea;

    public SimulationStatusFrame(){
        setResizable(false);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        statusArea = new JTextArea();
        statusArea.setLineWrap(true);
        statusArea.setEditable(false);
        statusArea.setMargin(new Insets(10,10,10,10));
        mainPanel.add(statusArea);
        add(mainPanel);
        setVisible(true);
    }

    public JTextArea getStatusArea(){
        return this.statusArea;
    }
}

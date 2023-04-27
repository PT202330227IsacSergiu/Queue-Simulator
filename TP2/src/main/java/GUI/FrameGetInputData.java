package GUI;

import Logic.SelectionPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameGetInputData extends JFrame {

    private JTextField noClientsFld;
    private JTextField noQueuesFld;
    private JTextField maxSimTimeFld;
    private JTextField minArrivalFld;
    private JTextField maxArrivalFld;
    private JTextField minServiceFld;
    private JTextField maxServiceFld;

    private JButton startButton;

    private ButtonGroup strButtons;
    private JRadioButton shortQueueR;
    private JRadioButton shortTimeR;

    private JPanel mainPanel;


    public FrameGetInputData() {
        setResizable(false);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel noClientsLabel = new JLabel("No. of clients:               ");
        JLabel noQueuesLabel = new JLabel("No. of queues:              ");
        JLabel maxSimTimeLabel = new JLabel("Max. simulation time:   ");
        JLabel arrivalTimeLabel = new JLabel("Arrival time:   ");
        JLabel serviceTimeLabel = new JLabel("Service time: ");
        JLabel minALabel = new JLabel("min: ");
        JLabel maxALabel = new JLabel("max: ");
        JLabel minSLabel = new JLabel("min: ");
        JLabel maxSLabel = new JLabel("max: ");

        noClientsFld = new JTextField("50", 20);
        noQueuesFld = new JTextField("5",20);
        maxSimTimeFld = new JTextField("60",20);
        minArrivalFld = new JTextField("2",10);
        maxArrivalFld = new JTextField("40",10);
        minServiceFld = new JTextField("1",10);
        maxServiceFld = new JTextField("7",10);

        JPanel noClientsPanel = new JPanel();
        noClientsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 0));
        noClientsPanel.add(noClientsLabel);
        noClientsPanel.add(noClientsFld);

        JPanel noQueuesPanel = new JPanel();
        noQueuesPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 0));
        noQueuesPanel.add(noQueuesLabel);
        noQueuesPanel.add(noQueuesFld);

        JPanel maxSimTimePanel = new JPanel();
        maxSimTimePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 0));
        maxSimTimePanel.add(maxSimTimeLabel);
        maxSimTimePanel.add(maxSimTimeFld);

        JPanel arrivalTimePanel = new JPanel();
        arrivalTimePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 0));
        arrivalTimePanel.add(arrivalTimeLabel);
        arrivalTimePanel.add(minALabel);
        arrivalTimePanel.add(minArrivalFld);
        arrivalTimePanel.add(maxALabel);
        arrivalTimePanel.add(maxArrivalFld);

        JPanel serviceTimePanel = new JPanel();
        serviceTimePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 0));
        serviceTimePanel.add(serviceTimeLabel);
        serviceTimePanel.add(minSLabel);
        serviceTimePanel.add(minServiceFld);
        serviceTimePanel.add(maxSLabel);
        serviceTimePanel.add(maxServiceFld);

        JPanel startPanel = new JPanel();
        startButton = new JButton("Start");
        startPanel.add(startButton);

        JPanel strategyPanel = new JPanel();
        strButtons = new ButtonGroup();
        shortQueueR = new JRadioButton(SelectionPolicy.SHORTEST_QUEUE.name());
        shortTimeR = new JRadioButton(SelectionPolicy.SHORTEST_TIME.name());
        strButtons.add(shortTimeR);
        strButtons.add(shortQueueR);
        strategyPanel.add(shortTimeR);
        strategyPanel.add(shortQueueR);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //mainPanel.setSize(400, 550);

        mainPanel.add(new JPanel());
        mainPanel.add(noClientsPanel);
        mainPanel.add(noQueuesPanel);
        mainPanel.add(maxSimTimePanel);
        mainPanel.add(arrivalTimePanel);
        mainPanel.add(serviceTimePanel);
        mainPanel.add(strategyPanel);
        mainPanel.add(startPanel);
        mainPanel.setBackground(Color.red);


        String strInfo = " • Every value must be greater than 0 \n"
                        + " • The max must be greater than min \n   ( for arrival and service time ) \n"
                        + " • Default strategy: SHORTEST_TIME \n";
        JTextArea infoArea = new JTextArea(strInfo);
        infoArea.setEditable(false);
        JFrame infoFrame = new JFrame();
        infoFrame.setLayout(new FlowLayout(FlowLayout.LEADING, 20,20));
        infoFrame.setBounds(990, 180, 255,160);
        infoFrame.add(infoArea);

        JButton infoButton = new JButton("i");
        infoButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
        infoButton.setBounds(435,10, 40,40);
        infoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                infoFrame.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoFrame.setVisible(false);
            }
        });

        add(infoButton);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void startButtonListener(ActionListener e) {
        this.startButton.addActionListener(e);
    }

    public String getNoClients() {
        return noClientsFld.getText();
    }

    public String getNoQueues() {
        return noQueuesFld.getText();
    }

    public String getMaxSimTime() {
        return maxSimTimeFld.getText();
    }

    public String getMinArrival() {
        return minArrivalFld.getText();
    }

    public String getMaxArrival() {
        return maxArrivalFld.getText();
    }

    public String getMinService() {
        return minServiceFld.getText();
    }

    public String getMaxService() {
        return maxServiceFld.getText();
    }

    public SelectionPolicy getStrategy(){
        ButtonModel selected = strButtons.getSelection();
        if(selected == shortQueueR.getModel()){
            return SelectionPolicy.SHORTEST_QUEUE;
        }
        if(selected == shortTimeR.getModel()){
            return SelectionPolicy.SHORTEST_TIME;
        }
        return SelectionPolicy.SHORTEST_QUEUE;
    }

}

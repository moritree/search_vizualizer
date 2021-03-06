import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Main {

    private graphicsPanel graphicsPanel;
    private String algorithm = "Dijkstra";
    private Button algorithmButton;

    void prepareGUI() {
        Frame mainFrame = new Frame();
        mainFrame.setSize(500, 450);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ButtonClickListener ButtonListener = new ButtonClickListener();

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        Panel controlPanel = new Panel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        controlPanel.setSize(mainFrame.getWidth(), 100);

        Button runButton = new Button("Run");
        runButton.setActionCommand("Run");
        runButton.addActionListener(ButtonListener);
        c2.fill = GridBagConstraints.HORIZONTAL;
        controlPanel.add(runButton, c2);

        Button clearButton = new Button("Clear");
        clearButton.setActionCommand("Clear");
        clearButton.addActionListener(ButtonListener);
        controlPanel.add(clearButton, c2);

        Button placeStartButton = new Button("Starting Position");
        placeStartButton.setActionCommand("PlaceStart");
        placeStartButton.addActionListener(ButtonListener);
        c2.gridwidth = 2;
        controlPanel.add(placeStartButton, c2);

        Button resetButton = new Button("Reset");
        resetButton.setActionCommand("Reset");
        resetButton.addActionListener(ButtonListener);
        c2.gridx = 0;
        c2.gridwidth = 1;
        controlPanel.add(resetButton, c2);

        algorithmButton = new Button("TOGGLE: " + algorithm);
        algorithmButton.setActionCommand("toggleAlgorithm");
        algorithmButton.addActionListener(ButtonListener);
        c2.gridx = 1;
        controlPanel.add(algorithmButton, c2);

        Button placeGoalButton = new Button("Goal Position");
        placeGoalButton.setActionCommand("PlaceGoal");
        placeGoalButton.addActionListener(ButtonListener);
        c2.gridx = 2;
        c2.gridwidth = 2;
        controlPanel.add(placeGoalButton, c2);

        mainFrame.add(controlPanel, c);

        graphicsPanel = new graphicsPanel();
        c.gridx = 0;
        mainFrame.add(graphicsPanel, c);

        mainFrame.setVisible(true);

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.exit(1);
        }

        graphicsPanel.fill(-1, -1);
        graphicsPanel.paintCheckerboard();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "Run":
                    graphicsPanel.reset();
                    new Dijkstra(graphicsPanel, algorithm);
                    break;
                case "Clear":
                    graphicsPanel.fill(-1, -1);
                    graphicsPanel.paintCheckerboard();
                    break;
                case "Reset":
                    graphicsPanel.reset();
                    break;
                case "PlaceStart":
                    graphicsPanel.setMode("PlaceStart");
                    break;
                case "PlaceGoal":
                    graphicsPanel.setMode("PlaceGoal");
                    break;
                case "toggleAlgorithm":
                    switch(algorithm) {
                        case "Dijkstra":
                            algorithm = "A*";
                            break;
                        case "A*":
                            algorithm = "Dijkstra";
                            break;
                    }
                    algorithmButton.setLabel("TOGGLE: " + algorithm);
            }
        }
    }

    public static void main(String[] args) {
        Main search = new Main();
        search.prepareGUI();
    }
}

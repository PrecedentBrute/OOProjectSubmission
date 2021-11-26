package monopoly;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.JTextField;

public class MonopolyFrame implements Runnable{

    private JFrame mainFrame;
    private JLabel boardImage;
    private JScrollPane logPane;
    private JLabel logName;
    private JTextArea logTextArea;
    private JButton enterButton;
    private JTextField inputField;
    private JLabel enterInput;
    private JLayeredPane layeredPane;
    public SyncInput syncInput;
    private static int iterations = 0;

    public void logTextToGUI(String s) {
        logTextArea.append(s + "\n");
        iterations++;
        if (iterations > 75) {
            logTextArea.setText("");
            iterations = 0;
        }
    }

    MonopolyFrame(SyncInput syncInput) {
        this.syncInput = syncInput;
        mainFrame = new JFrame("Monopoly");
        mainFrame.setBounds(10, 10, 800, 800);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = new JLayeredPane();

        boardImage = new JLabel();
        try{
            boardImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mono600_2.png")));
        }catch(Exception e){
            System.out.println("Error loading image");
        }

        logPane = new JScrollPane();
        logTextArea = new JTextArea();
        logName = new JLabel();

        enterButton = new JButton();
        enterButton.setText("Enter");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        inputField = new JTextField();
        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });

        enterInput = new JLabel();
        enterInput.setText("Enter your choice:");

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logPane.setViewportView(logTextArea);

        logName.setText("Game Log");

        layeredPane.setLayer(logPane, Integer.valueOf(0));
        layeredPane.setLayer(boardImage, Integer.valueOf(0));
        layeredPane.setLayer(logName, Integer.valueOf(0));
        layeredPane.setLayer(inputField, Integer.valueOf(0));
        layeredPane.setLayer(enterButton, Integer.valueOf(0));
        layeredPane.setLayer(enterInput, Integer.valueOf(0));

        GroupLayout layeredPaneLayout = new GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(layeredPaneLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneLayout.createSequentialGroup().addGap(5, 5, 5).addComponent(boardImage)
                        .addGroup(layeredPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layeredPaneLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layeredPaneLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(logPane, GroupLayout.DEFAULT_SIZE, 293,
                                                        Short.MAX_VALUE)
                                                .addComponent(inputField)))
                                .addGroup(layeredPaneLayout.createSequentialGroup().addGap(131, 131, 131)
                                        .addComponent(logName))
                                .addGroup(layeredPaneLayout.createSequentialGroup().addGap(115, 115, 115)
                                        .addComponent(enterButton, GroupLayout.PREFERRED_SIZE, 73,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGroup(layeredPaneLayout.createSequentialGroup().addGap(110, 110, 110)
                                        .addComponent(enterInput)))
                        .addGap(15, 15, 15)));
        layeredPaneLayout
                .setVerticalGroup(layeredPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING,
                                layeredPaneLayout.createSequentialGroup().addGap(5, 5, 5).addGroup(layeredPaneLayout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layeredPaneLayout.createSequentialGroup().addComponent(boardImage)
                                                .addGap(6, 6, 6))
                                        .addGroup(layeredPaneLayout.createSequentialGroup().addComponent(logName)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(logPane)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(enterInput)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(inputField, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(enterButton)))));

        GroupLayout layout = new GroupLayout(mainFrame.getContentPane());
        mainFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(0, 0, 0)
                        .addComponent(layeredPane, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(layeredPane).addContainerGap()));

        mainFrame.pack();
    }

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {
        //input event handler
    }

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (!inputField.getText().equals("")) {
            syncInput.setInput(inputField.getText());
            inputField.setText("");
        }

        for (Piece p : GameIO.pieces) {
            layeredPane.add(p.getPiece(), Integer.valueOf(1));
        }

        mainFrame.repaint();
        // mainFrame.setVisible(false);
        // mainFrame.setVisible(true);
    }

    public String getTextField() {
        String s = inputField.getText();
        inputField.setText("");
        return s;
    }

    @Override
    public void run() {
        this.mainFrame.setVisible(true);
        GameIO.setGuiFrame(this);
    }
}




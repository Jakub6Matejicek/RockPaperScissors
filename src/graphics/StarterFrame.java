package graphics;

import javax.swing.*;
import java.awt.event.*;

public class StarterFrame extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel title = new JLabel("ROCK, PAPER, SCISSORS");
    private JTextField numberOfObjects = new JTextField();
    private JButton startButton = new JButton("start");
    private int number;

    public StarterFrame(String title){
        this.setVisible(true);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        panel = new JPanel();
        numberOfObjects.setColumns(5);
        numberOfObjects.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        panel.add(this.title);
        panel.add(numberOfObjects);
        panel.add(startButton);
        startButton.addActionListener(this);
        this.add(panel);
        this.setResizable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            number = Integer.parseInt(numberOfObjects.getText());
            System.out.println(number);
            MainFrame frame = new MainFrame(600, 600, "Rock Paper Scissors", number, 4);
            numberOfObjects.setText("");
        }
    }

}

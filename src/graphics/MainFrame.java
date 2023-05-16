package graphics;

import javax.swing.*;

public class MainFrame extends JFrame {

    private Panel panel;
    public MainFrame(int x, int y, String title){
        panel = new Panel(x, y);
        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(x, y);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setResizable(false);
        this.setVisible(true);
        panel.startGameThread();
    }

}

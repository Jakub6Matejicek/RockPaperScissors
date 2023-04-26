package graphics;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(int x, int y, String title){
        this.add(new Panel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(x, y);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setResizable(false);
        this.setVisible(true);
    }

}

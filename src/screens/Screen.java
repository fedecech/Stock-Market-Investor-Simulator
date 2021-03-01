package screens;

import javax.swing.*;

public class Screen extends JFrame {
    private JFrame frame;

    public Screen(JFrame frame){
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }
}

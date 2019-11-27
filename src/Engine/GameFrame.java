package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class GameFrame extends JFrame {
    private GameComponent gameComponent;
    private JTextArea textArea;
    private JScrollPane pane;

    /**
     * Instantiates a new game frame.
     *
     * @param comp the comp
     */
    public GameFrame(JComponent comp, KeyAdapter keyAdapter) {

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        gameComponent = ((GameComponent) comp);
        this.addKeyListener(keyAdapter);
        getContentPane().add(BorderLayout.CENTER, comp);
        this.setLocation((int) (screen.getWidth() * 3 / 8), (int) (screen.getHeight() * 3 / 8));
        this.setVisible(true);
        this.setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

    }

}
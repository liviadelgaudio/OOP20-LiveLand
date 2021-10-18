package view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.EnvironmentControllerImpl;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class GraphicalUserInterface {

    private final JFrame frame = new JFrame();
    final JPanel canvas = new JPanel();
    private final EnvironmentControllerImpl view = new EnvironmentControllerImpl();
    final WelcomePanel welcomePanel = new WelcomePanel();
    final MenuPanel menuPanel = new MenuPanel(this.view, this);
    final BottomPanel bottomPanel = new BottomPanel(this.view, this);


    /**
     * builds a new {@link GraphicalUserInterface}.
     * @param controller the controller instance.
     */
    public GraphicalUserInterface() {
        this.canvas.setLayout(new BorderLayout());
        this.canvas.add(this.welcomePanel, BorderLayout.NORTH);
        this.canvas.add(this.menuPanel, BorderLayout.CENTER);
        this.canvas.add(this.bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.pack();
        frame.setTitle("FunFair Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 3);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }
    
    public void dispose() {
    	frame.setVisible(false);
    	frame.dispose();
    }    
    
    /**
     * 
     * @param args
     *            ignored
     */
    public static void main(final String[] args) {
        new GraphicalUserInterface().display();
    }
}

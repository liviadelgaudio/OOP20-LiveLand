package view.menu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.EnvironmentControllerImpl;

public class BottomPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2507932941249841282L;
    final JButton start = new JButton("Start");
    final JButton reset = new JButton("Reset");
    
    public BottomPanel(EnvironmentControllerImpl view, GraphicalUserInterface gui) {
    	this.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	this.add(start);
    	this.add(reset);
    	
//      /*
//      * alla pressione del pulsante start l'applicativo deve passare all'environmentController il numero
//      * visitatori e Aggiungere le attività istanziate, per poi richiamare lo start (per avviare la mainWindow
//      */
//     //nb exception se premuto con 0 attività e se no validate del visitorsNum
	    start.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(final ActionEvent e) {
	         	view.start();
	         }
	     });
//     
//     /**
//      * This button must reset the fields previously set, reverting the initial state.
//      */
	    reset.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(final ActionEvent e) {
	             gui.menuPanel.reset();
	             view.resetActivityList();;
	         }
	     });
    }

	

}
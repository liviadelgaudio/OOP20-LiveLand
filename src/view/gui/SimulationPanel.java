package view.gui;

import java.awt.BorderLayout;
import java.applet.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import controller.ControllerImpl;
import model.gui.activity.ActivityInsertion;
import model.gui.activity.Square;
import model.gui.position.Position;
import model.gui.position.RandomPosition;
import model.person.ticket.PersonTicket;
import model.ticket.Ticket;
import view.controller.ViewControllerImpl;



public class SimulationPanel extends JPanel {

    private static final long serialVersionUID = 7114066347061701832L;
    private final ViewControllerImpl controller;
    private Map<PersonTicket, Position<Integer, Integer>> map;
    private List<CircleImpl> adult = new ArrayList<>();
    private List<CircleImpl> baby = new ArrayList<>();
    private List<CircleImpl> pass = new ArrayList<>();

    private static final int ADULT_RADIUS = 20;
    private static final int BABY_RADIUS = 15;
    private static final int PASS_RADIUS = 20;
    private final JFrame frame;

    public SimulationPanel(ViewControllerImpl controller) {
        this.controller = controller;
        this.frame = new JFrame();
        frame.setTitle("LiveLand");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(600, 400));

        // creation panel
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
        panel.setBackground(new Color(216,216,216));

        // creation bottons
        final JPanel container = new JPanel();
        container.setLayout(new FlowLayout());
        final JButton stop = new JButton("STOP");
        frame.setVisible(true);
        container.add(stop);
        panel.add(container, BorderLayout.EAST);
        container.setBackground(Color.DARK_GRAY);
        this.map = new HashMap<PersonTicket, Position<Integer, Integer>>();
        panel.add(this, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationPanel.this.frame.dispose();
                controller.stop();
            }

        });

    }

    public void updateSimulation() {
        final Map<PersonTicket, Position<Integer, Integer>> people = this.map;
        final List<CircleImpl> adult = new ArrayList<>();
        final List<CircleImpl> baby = new ArrayList<>();
        final List<CircleImpl> pass = new ArrayList<>();
        for (PersonTicket p : people.keySet()) {
            if (p.getTicket().equals(Ticket.SEASON_PASS)) {
                pass.add(DesignPerson.createPass(people.get(p).getFirst(), people.get(p).getSecond(), PASS_RADIUS));
                System.out.println("Posizione di " + p.toString() + "= x: " + map.get(p).getFirst() + "y: "
                        + map.get(p).getSecond());
            } else if(p.getAge() < 13) {
                baby.add(DesignPerson.createBaby(people.get(p).getFirst(), people.get(p).getSecond(), BABY_RADIUS));
            }
            else {
                adult.add(DesignPerson.createAdult(people.get(p).getFirst(), people.get(p).getSecond(), ADULT_RADIUS));
            }
            this.adult = adult;
            this.baby = baby;
            this.pass = pass;
            this.repaint();
        }
    }

    // draw oval
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            if (this.adult.size() != 0) {
                for (CircleImpl adult : adult) {
                    g.setColor(adult.getColor());
                    g.fillOval(adult.getX(), adult.getY(), (int) adult.getRadius(), (int) adult.getRadius());
                }
            }
            if (this.baby.size() != 0) {
                for (CircleImpl baby : baby) {
                    g.setColor(baby.getColor());
                    g.fillOval(baby.getX(), baby.getY(), (int) baby.getRadius(), (int) baby.getRadius());
                }
            }
            
            if (this.pass.size() != 0) {
                for (CircleImpl pass : pass) {
                    g.setColor(pass.getColor());
                    g.fillOval(pass.getX(), pass.getY(), (int) pass.getRadius(), (int) pass.getRadius());
                }
            }

            for (Square babyFair : this.controller.getModelActivity().getBabyFair()) {
                g.setColor(babyFair.getColor());
                g.fillRect(babyFair.getX(), babyFair.getY(), babyFair.getWidth(), babyFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(babyFair.getX(), babyFair.getY(), babyFair.getWidth(), babyFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(babyFair.getName(), babyFair.getX(), babyFair.getY() + 30);
            }
            
            

            for (Square adultFair : this.controller.getModelActivity().getAdultFair()) {
                g.setColor(adultFair.getColor());
                g.fillRect(adultFair.getX(), adultFair.getY(), adultFair.getWidth(), adultFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(adultFair.getX(), adultFair.getY(), adultFair.getWidth(), adultFair.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(adultFair.getName(), adultFair.getX(), adultFair.getY() + 30);
            }

            for (Square restaurant : this.controller.getModelActivity().getRestaurant()) {
                g.setColor(restaurant.getColor());
                g.fillRect(restaurant.getX(), restaurant.getY(), restaurant.getWidth(), restaurant.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(restaurant.getX(), restaurant.getY(), restaurant.getWidth(), restaurant.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(restaurant.getName(), restaurant.getX(), restaurant.getY() + 30);
            }

            for (Square shop : this.controller.getModelActivity().getShop()) {
                g.setColor(shop.getColor());
                g.fillRect(shop.getX(), shop.getY(), shop.getWidth(), shop.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(shop.getX(), shop.getY(), shop.getWidth(), shop.getHeight());
                g.setColor(Color.BLACK);
                g.drawString(shop.getName(), shop.getX(), shop.getY() + 30);
            }
        } catch (ConcurrentModificationException e) {
            System.out.println(e.getMessage());
        }

    }

    public Map<PersonTicket, Position<Integer, Integer>> getPeopleMap() {
        System.out.println(this.map.size());
        return this.map;
    }

    public void close() {
        this.frame.dispose();
        this.controller.stop();
    }

}





package view.gui;

import java.awt.Color;
import java.awt.Point;

public class CircleImpl implements Circle{

	
    private static final long serialVersionUID = 1L;
    private double radius;
    private Color color;
    private int x;
    private int y;

    public CircleImpl() {
        this.radius = 2;
        this.color = Color.CYAN;
        this.x = 0;
        this.y = 0;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public void setRadius(double r) {
        this.radius = r;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	
}

import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    
    private double diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private double area;
    
    /** 
     * Predetermined constructor initialize a circle with diameter 30.0,
     position (20,15), blue clor and not visible.
     */
    public Circle(){
        diameter = 24.0;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
    
    /**
     * New constructor, creates a circle with a given area.
     position (20,15), blue clor and not visible.
     * @param area The wished area
     */
    public Circle(double area){
        diameter = 2 * Math.sqrt(area / PI);
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }

    public int getxCoordinate(){
        return xPosition;
    }
    
    public int getyCoordinate(){
        return yPosition;
    }
    
    /** 
     * Get the area of the circle.
     * @ return The area.
     */
    public double area(){
        double radius = diameter / 2.0;
        return PI * radius * radius;
    }
    
    /** 
     * Grow the size given a percentage.
     * @param percentage The percentage is an int.
     */
    public void bigger(int percentage){
        double oldArea = area();
        double newArea = oldArea * (1 + (percentage / 100.0));
        double newDiameter = 2 * Math.sqrt(newArea / PI);
        diameter = newDiameter;
        draw();
    }
    
    /** 
     * Reduce the size of the circle given a times or when the area is
     low or equals to the specified.
     * @param times Number of times the diameter reduces.
     * @param area Objective area.
     * @throws IllegalArgumentException if 'times' or 'area' are negative
     */
    public void shrink(int times, int area){
        // Checking parameters
        if (times < 0 || area < 0){
            System.out.println("times and area must not be negative");
            throw new IllegalArgumentException("times and area couldn't be negative");
        }
        // Times reductions or area <= area
        for (int i = 0; i < times; i++){
            if (area() <= area){
                break;
            }
            diameter = diameter / 2.0;
            if (diameter < 0){
                diameter = 0;
                break;
            }
        }
        if (isVisible){
            draw();
        }
    }
    
    /**
     * Get the perimeter of the circle.
     * return The perimeter of the circle.
     */
    public double perimeter(){
        return PI * diameter;
    }
    
    /**
     * Show the circle on canvas.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Hide the circle of the canvas.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Draw a circle on canvas.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }
    
    /**
     * Erase the circle of canvas.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    public void moveTo(int x, int y){
    erase();
    xPosition = x;
    yPosition = y;
    draw();
    }
}

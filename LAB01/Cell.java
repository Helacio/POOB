import java.util.Random;
/**
 * A cell that can move and draw
 */
public class Cell extends Rectangle
{
    // instance variables
    private Rectangle rect;
    private Circle hole;
    private Circle marbel;
    private boolean withMarbel;
    private boolean isPerforated;
    private String marbelColor;
    private boolean isOk;

    /**
     * Constructor for objects of class Cell
     * @param color must be in "red" "white", "yellow", "green", "blue"
     * @param hole indecates if the cell has hole
     */
    public Cell(String color, boolean hole){
        rect = new Rectangle();
        rect.changeColor(color);
        marbel = null;
        isOk = true;

        if (hole){
            this.hole = new Circle();
            this.hole.changeColor("white");
            this.isPerforated = true;          
        } else{
            this.hole = null;
            isPerforated = false;
        }
        if (!(rect.getColor().equals("white")) && !isPerforated && this.marbel == null){
            isOk = false;
        } else{
            isOk = true;
        }
    }
    
    /**
     * Put a marbel in a cell
     */
    public void in(String marbel){
        this.marbel = new Circle();
        this.marbel.changeColor(marbel);
        this.marbelColor = marbel;
        if (this.marbel != null) {
        this.marbel.moveTo(
            rect.getxCoordinate() + 3,
            rect.getyCoordinate() + 3);
            this.marbel.makeVisible(); // se agregó
            }
        withMarbel = true;
        changeCell();
    }
    
    /**
     * A cell is ok when there's no hole and marbel
     * @return isOk when can in and out the marbel
     */
    public boolean cellOk(){
        return isOk;
    }
    
    /**
     * Check if there is a hole
     */
    public boolean hasHole(){
        return isPerforated;
    }
    
    /** 
     * Create a hole and draw it
     */
    public void switchHole() {
        this.hole = new Circle();
        this.hole.changeColor("white");
        this.isPerforated = true;
        
        if (this.hole != null) {
            this.hole.moveTo(rect.getxCoordinate()+3,
                rect.getyCoordinate()+3);
        }
    }
    
    /**
     * Extract a marbel from the cell
     */
    public String out() {
        if(withMarbel){
            String color = this.marbelColor;
            this.marbelColor = null;
            this.marbel.makeInvisible();
            this.marbel = null;
            withMarbel = false;
            return color;
        } 
        else{
            return "";
        }
    }

    /**
     * returns the color of the marbel
     * @return
     */
    public String getMarbelColor(){
        return marbelColor;
    }
    
    /**
     * Check if the cell has a marbel
     */
    public boolean withMarble(){
        if (marbel != null){
           return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Change the x and y coordinates
     * @param x Horizontal position
     * @param y Vertical position
     */
    public void moveTo(int x, int y){
        int xDistance = x - rect.getxCoordinate();
        int yDistance = y - rect.getyCoordinate();
        rect.moveHorizontal(xDistance);
        rect.moveVertical(yDistance);
        
        if (isPerforated && (hole != null) ) {
            hole.moveHorizontal(xDistance);
            hole.moveVertical(yDistance);
        }
        
        if (withMarbel && (marbel != null) ) {
            marbel.moveHorizontal(xDistance);
            marbel.moveVertical(yDistance);
        }
    }
    
    /**
     * Show the cell
     */
    public void makeVisible(){
        rect.makeVisible();
        if (isPerforated){
            hole.makeVisible();
        }
        if (withMarbel && marbel != null){
        marbel.makeVisible();
        // Ocultar el agujero si hay canica
            if (isPerforated && hole != null) {
                hole.makeInvisible();
            }
        }
    }
    
    public void makeInvisible(){
        rect.makeInvisible();
    }

    public void changeCellColor(String colorToChange) {
        rect.changeColor(colorToChange);
    }

    public Circle getMarbel(){
        return marbel;
    }

    public String getCellColor(){
        return rect.getColor();
    }
    
    /**
     * Change the cell with hole and marbel to a cell complete
     */
    public void changeCell(){
        if (rect.getColor().equals(marbelColor)){
            if (hole != null){
                hole.setErase();
            }
            if (marbel != null){
                marbel.setErase();
            }
            marbel = null;
            marbelColor = null;
            withMarbel = false;
            isOk = false;
        }
    }
    
    /**
     * @return The state of a cell, if a marbel can be there
     */
    public boolean getIsOk(){
        return isOk;
    }
}
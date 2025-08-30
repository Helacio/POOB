
/**
 * A cell that can move and draw
 *
 */
public class Cell extends Rectangle
{
    // instance variables
    private Rectangle rect;
    private Circle hole;
    private Circle marbel;
    private boolean withMarbel;
    private boolean isPerforated;

    /**
     * Constructor for objects of class Cell
     * @param color must be in "red"...
     * @param hole indecates if the cell has hole
     */
    public Cell(String color, boolean hole){
        rect = new Rectangle();
        rect.changeColor(color);
        marbel = null;
        if (hole){
            this.hole = new Circle();
            this.hole.changeColor("white");
            this.isPerforated = true;          
        } else{
            this.hole = null;
            isPerforated = false;
        }
    }
    
    /**
     * Check if there is a hole
     */
    public boolean hasHole(){
        return isPerforated;
    }
    
    /**
     * Put a marbel in a cell
     */
    public void in(String marbel){
        this.marbel = new Circle();
        this.marbel.changeColor(marbel);
        withMarbel = true;
    }
    
    /**
     * Extract a marbel from the cell
     */
    public String out(String marbel) {
        if(withMarbel){
            String marbelColor = marbel;
            marbel = null;
            this.marbel.makeInvisible();
            return marbelColor;
        }
        else{
            return "";
        }
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
        
        //Mueve el agujero y lo centra si es que existe
        if (isPerforated ){
            int holeXPosition = x + 3; // 3 -> Largo de Square - Diametro
            int holeYPosition = y + 3;
            int dx = holeXPosition - getxCoordinate();
            int dy = holeYPosition - getyCoordinate();
            hole.moveVertical(dx);
            hole.moveHorizontal(dy);
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
        if (isPerforated && withMarbel){
            marbel.makeVisible();
            hole.makeInvisible();
        }
    }
    /**
     * Hide the cell
     */
    public void makeInvisible(){
        rect.makeInvisible();
    }
}

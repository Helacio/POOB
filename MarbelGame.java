
/**
 * Write a description of class MarbelGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MarbelGame{
    private int size;
    private int numMarbels;
    private Cell[][] board;
    private boolean finished;
    
    /**
     * Constructor for objects of class MarbelGame
     */
    public MarbelGame(int size, int numMarbels){
        this.size = size;
        this.numMarbels = numMarbels;
        board = new Cell[size][size];
        finished = false;
        initBoard();
    }
    // PONER EL METODO MOVE(LETRA(N, S, O, W)) -> POSIBLE DETALLE EN MOVETO EN CELL;
    // SI NO ESTÁ EN MOVE TO, REVISAR
    // PONER EL METODO RESET GAME
    // PONER EL METODO ISWIN?
    // PONER EL METODO CHECK STATE 
    /**
     * Start the board
     */
    public void initBoard(){
        // Revisar por que se crean primo los Agujeros y luego los rectangulos
        // Falta importar random con un numero pequeño
        // Falta poner los agujeros y canicas de forma aleatoria
        int cellSize = 30; // Tamaño del rectangle
        int espacio = 5; //espacio entre las celdas
        int boardLong = size * cellSize + ((size -1) * espacio); // medida de la matriz de celdas
        int xyStart = (300 - boardLong) / 2;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                int x = xyStart + j * (cellSize + espacio);
                int y = xyStart + i * (cellSize + espacio);
                board[i][j] = new Cell("blue", true);
                board[i][j].moveTo(x, y);
                board[i][j].makeVisible();
            }
        }
    }
    public static void main(String[] args) {
        MarbelGame prueba = new MarbelGame(3,3);
        prueba.initBoard();
    }
}
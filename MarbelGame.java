import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
    private String[] colors = {"red", "blue", "yellow", "green", "magenta", "black"};
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
    
    
/**
 * Write a description of class getRamdomNumbers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */



    public static List<Pair> getRandomPairs(int numMarbels, int size) {
        Random random = new Random();

        List<Pair> listaParejas = new ArrayList<>();
        int cont = 0;
        
        while (cont < numMarbels) {

            int fila = random.nextInt(size);
            int columna = random.nextInt(size);
            Pair toAdd = new Pair(fila, columna);

            if (!listaParejas.contains(toAdd)) {
                listaParejas.add(toAdd);
                cont++;
            }

        }
        return listaParejas;
        

    }



    
    public void initBoard(){
        List<Pair> positions = getRandomPairs(numMarbels*2, size);
        System.out.println(positions);
        
        // Revisar por que se crean primo los Agujeros y luego los rectangulos
        // Falta importar random con un numero pequeño
        // Falta poner los agujeros y canicas de forma aleatoria
        int cellSize = 30; // Tamaño del rectangle
        int espacio = 0; //espacio entre las celdas
        int boardLong = size * cellSize + ((size -1) * espacio); // medida de la matriz de celdas
        int xyStart = (300 - boardLong) / 2;
        int choseColor = 0;
        int predeterminated = 0;
        
        
        for(int i = 0; i < size; i++){
            
            for(int j = 0; j < size; j++){
                int x = xyStart + j * (cellSize + espacio);
                int y = xyStart + i *(cellSize + espacio);
                board[i][j] = new Cell("white", false); 
                board[i][j].moveTo(x, y);
                board[i][j].makeVisible();
            }
        }
            
        for(int count = 0; count < positions.size(); count ++){
            
            Pair coordenades = positions.get(count);
            String color = colors[predeterminated];
            // Si count es par entonces se hace el agujero, si no la canica 
            if(count%2 == 0) {
                System.out.println("Agujero: " + coordenades.getFile() + " , " +coordenades.getColumn());
                board[coordenades.getFile()][coordenades.getColumn()].switchHole();
                board[coordenades.getFile()][coordenades.getColumn()].moveTo(
                     xyStart + coordenades.getColumn() * cellSize,
                     xyStart + coordenades.getFile() * cellSize
                    );
                board[coordenades.getFile()][coordenades.getColumn()].changeCellColor(color);
                board[coordenades.getFile()][coordenades.getColumn()].makeVisible();
            } else {    
                
                System.out.println("Canica: " + coordenades.getFile() + " , " +coordenades.getColumn());
                board[coordenades.getFile()][coordenades.getColumn()].in(color);
                board[coordenades.getFile()][coordenades.getColumn()].moveTo(
                     xyStart + coordenades.getColumn() * cellSize,
                     xyStart + coordenades.getFile() * cellSize
                    );
                board[coordenades.getFile()][coordenades.getColumn()].makeVisible();
                predeterminated++;
                }
            
            }
        }
            
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int nCells = scanner.nextInt();
        int nMables = scanner.nextInt();
        MarbelGame prueba = new MarbelGame(nCells, nMables);
        prueba.initBoard();
        scanner.close();
    }
}
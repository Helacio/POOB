
/**
 * Write a description of class Pair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pair
{
    
        private int fila;
        private int columna;

        public Pair(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        public int getFile() {
            return fila;
        }

        public int getColumn() {
            return columna;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair otra = (Pair) obj;
            return fila == otra.fila && columna == otra.columna;
        }

        @Override
        public String toString() {
            return "(" + fila + "," + columna + ")";
        }
    
}
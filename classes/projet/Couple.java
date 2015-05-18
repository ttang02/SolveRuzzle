package projet;

public class Couple {
    private int i;
    private int j;

    public Couple(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {

        return this.i;
    }

    public int getJ() {

        return this.j; 
    }

    @Override
    public String toString() {
        return "(" + i + "," + j + ")";
    }

    public boolean equals(Object o) {
        if (i == ((Couple) o).getI() && j == ((Couple) o).getJ()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return i + j;
    }

}

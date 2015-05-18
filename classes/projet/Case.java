package projet;

public class Case {

    private char letter;
    private int point; // Chaque case compte 1 ou 2 points;
    private boolean isTraveled;
    private Bonus bonus;

    public Case(char letter, Bonus bonus) {
        this.letter = letter;
        this.isTraveled = false;
        this.point = getPoint(letter);
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public int getPoint() {
        return point; 
    }

    public void setTraveled(boolean isTraveled) {
        this.isTraveled = isTraveled;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isTraveled() {
        return isTraveled;
    }

    public void setIsParcouru(boolean isTraveled) {
        this.isTraveled = isTraveled;
    }

    public static int getPoint(char c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'N' || c == 'O' || c == 'R' || c == 'S' || c == 'T' || c == 'U') {
            return 1;
        }
        else if (c == 'D'|| c == 'G' || c == 'L' || c == 'M') {
            return 2;
        }
        else if (c == 'B' || c == 'C' || c == 'P') {
            return 3;
        }
        else if (c == 'F' || c == 'H') {
            return 4;
        }
        else if (c == 'V') {
            return 5;
        }
        else if (c == 'Q') {
            return 8;
        }
        else {
            return 10;
        }

    }
}

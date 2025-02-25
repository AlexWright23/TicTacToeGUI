//jbutton

import javax.swing.JButton;

public class TicTacToeTile extends JButton {
    private int row, col;
    private String symbol = "";  // stores x and o

    public TicTacToeTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 40));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isEmpty() {
        return symbol.equals("");
    }

    public void setSymbol(String symbol) {
        if (isEmpty()) {
            this.symbol = symbol;
            this.setText(symbol);
        }
    }

    public void resetTile() {
        this.symbol = "";
        this.setText("");
    }
}

//game

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeTile[][] buttons = new TicTacToeTile[3][3];
    private String currentPlayer = "X";

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(Color.RED);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new TicTacToeTile(row, col);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                buttons[row][col].setBackground(Color.BLACK);
                buttons[row][col].setForeground(Color.WHITE);
                boardPanel.add(buttons[row][col]);
            }
        }

        JButton quitButton = new JButton("Quit Game");
        quitButton.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeTile tile = buttons[row][col];

            if (tile.isEmpty()) {
                tile.setSymbol(currentPlayer);

                if (checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " Wins, GG!");
                    resetBoard();
                } else if (checkTie()) {
                    JOptionPane.showMessageDialog(null, "It's a Tie!");
                    resetBoard();
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move! Try again!.");
            }
        }
    }

    //checks the coloums / rows / diagonols
    private boolean checkWin(String player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getSymbol().equals(player) &&
                    buttons[i][1].getSymbol().equals(player) &&
                    buttons[i][2].getSymbol().equals(player)) {
                return true; // Row win
            }
            if (buttons[0][i].getSymbol().equals(player) &&
                    buttons[1][i].getSymbol().equals(player) &&
                    buttons[2][i].getSymbol().equals(player)) {
                return true; // Column win
            }
        }

        if (buttons[0][0].getSymbol().equals(player) &&
                buttons[1][1].getSymbol().equals(player) &&
                buttons[2][2].getSymbol().equals(player)) {
            return true;
        }
        if (buttons[0][2].getSymbol().equals(player) &&
                buttons[1][1].getSymbol().equals(player) &&
                buttons[2][0].getSymbol().equals(player)) {
            return true;
        }

        return false;
    }

    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].isEmpty()) {
                    return false; // mpty tile exists, no tie
                }
            }
        }
        return true; // if the tiles are filled
    }

    private void resetBoard() {
        int response = JOptionPane.showConfirmDialog(null, "Play again?", "Tic Tac Toe!", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    buttons[row][col].resetTile();
                }
            }
            currentPlayer = "X";
        } else {
            System.exit(0);
        }
    }
}

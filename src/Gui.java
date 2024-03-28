import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui extends JFrame {

    private JButton[][] boardButtons;

    private int startRow = -1;
    private int startCol = -1;

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }


    public Gui() {
        super("Knight's Tour Problem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(700, 600));

        boardButtons = new JButton[8][8];
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardButtons[i][j] = new JButton();
                boardButtons[i][j].setBackground(Color.LIGHT_GRAY);
                boardButtons[i][j].addMouseListener(new SquareClickListener(i, j));
                boardPanel.add(boardButtons[i][j]);
            }
        }
        
        add(boardPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Inner class to handle clicks
    private class SquareClickListener extends MouseAdapter {
        private int row;
        private int col;

        public SquareClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void mouseClicked(MouseEvent e) {
            if (startRow == -1 && startCol == -1) {
                startRow = row;
                startCol = col;
                boardButtons[startRow][startCol].setText("1"); // Set position 1
            }
        }
    }

    public void updateBoardGUI(int[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] > 0) {
                    boardButtons[i][j].setBackground(Color.BLUE); // Visited Square
                }
                else {
                    boardButtons[i][j].setBackground(Color.LIGHT_GRAY); // Unvisited square
                }
            }
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Gui());
    }
    
}

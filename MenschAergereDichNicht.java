import java.awt.*;
import javax.swing.*;

public class MenschAergereDichNicht extends JFrame {

    private JPanel boardPanel;
    private JPanel[][] boardSquares;
    private JLabel messageLabel;
    private JButton rollButton;
    private int currentPlayer;
    private int[] playerPositions;

    public MenschAergereDichNicht() {
        super("Mensch ärgere dich nicht");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel(new GridLayout(11, 11));
        boardSquares = new JPanel[11][11];

        // Initialize the board
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                JPanel square = new JPanel();
                if (i == 0 || i == 10 || j == 0 || j == 10) {
                    // Set the border squares to gray
                    square.setBackground(Color.gray);
                } else if ((i == 5 && (j == 2 || j == 4 || j == 6 || j == 8)) || (j == 5 && (i == 2 || i == 4 || i == 6 || i == 8))) {
                    // Set the starting and ending squares to yellow
                    square.setBackground(Color.yellow);
                } else {
                    // Set the regular squares to white
                    square.setBackground(Color.white);
                }
                boardPanel.add(square);
                boardSquares[i][j] = square;
            }
        }

        // Add the message label
        messageLabel = new JLabel("Spieler 1 beginnt");
        add(messageLabel, BorderLayout.NORTH);

        // Add the roll button
        rollButton = new JButton("Würfeln");
        rollButton.addActionListener(e -> rollDice());
        add(rollButton, BorderLayout.SOUTH);

        // Set the current player to player 1
        currentPlayer = 1;

        // Initialize the player positions
        playerPositions = new int[] {0, 0, 0, 0};

        // Show the board
        add(boardPanel);
        setVisible(true);
    }

    private void rollDice() {
        int roll = (int) (Math.random() * 6) + 1;
        messageLabel.setText("Spieler " + currentPlayer + " hat eine " + roll + " gewürfelt");
        movePiece(roll);
    }

    private void movePiece(int roll) {
        // Get the current player's position
        int currentPosition = playerPositions[currentPlayer - 1];

        // Calculate the new position
        int newPosition = currentPosition + roll;

        // Check if the new position is valid
        if (newPosition > 40) {
            // The player has gone too far, move them back
            int overshot = newPosition - 40;
            newPosition = 40 - overshot;
        }

        // Move the player's piece on the board
        JPanel currentSquare = getSquareForPosition(currentPosition);
        currentSquare.removeAll();
        JPanel newSquare = getSquareForPosition(newPosition);
        newSquare.add(new JLabel(getPlayerIcon(currentPlayer)));
        boardPanel.validate();

        // Update the player's position
        playerPositions[currentPlayer - 1] = newPosition;

        // Check if the player has won
        if (newPosition == 40) {
            messageLabel.setText("Spieler " + currentPlayer + " hat gewonnen!");
            rollButton.setEnabled(false

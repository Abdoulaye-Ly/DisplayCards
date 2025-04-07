import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplayThreeCards extends JFrame {
    private JButton[] cardButtons = new JButton[3]; // Array to hold the three card buttons
    private List<Integer> cardIndices; // List to store card indices (1 to 54)

    public DisplayThreeCards() {
        setTitle("Three Cards Display");
        setSize(600, 300); // Set frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // Use FlowLayout for simplicity

        // Initialize card indices (1 to 54)
        cardIndices = new ArrayList<>();
        for (int i = 1; i <= 54; i++) {
            cardIndices.add(i);
        }
        Collections.shuffle(cardIndices); // Shuffle to randomize card order

        // Create and add three buttons with random card images
        for (int i = 0; i < 3; i++) {
            cardButtons[i] = new JButton();
            updateCardImage(cardButtons[i], cardIndices.get(i)); // Set initial card image
            cardButtons[i].addActionListener(new CardButtonListener()); // Add click listener
            cardButtons[i].addMouseListener(new CardRolloverListener()); // Add rollover listener
            add(cardButtons[i]); // Add button to the frame
        }
    }

    // Method to update the card image on a button
    private void updateCardImage(JButton button, int cardIndex) {
        String imagePath = "src/image/card/" + cardIndex + ".png"; // Path to the card image
        ImageIcon icon = new ImageIcon(imagePath);
        button.setIcon(icon); // Set the icon for the button
    }

    // Listener for button clicks
    private class CardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int newCardIndex = getRandomCardIndex(); // Get a new random card index
            updateCardImage(clickedButton, newCardIndex); // Update the button's image
        }
    }

    // Listener for mouse rollovers
    private class CardRolloverListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JButton hoveredButton = (JButton) e.getSource();
            int newCardIndex = getRandomCardIndex(); // Get a new random card index
            updateCardImage(hoveredButton, newCardIndex); // Update the button's image
        }
    }

    // Method to get a random card index
    private int getRandomCardIndex() {
        return cardIndices.get((int) (Math.random() * cardIndices.size()));
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DisplayThreeCards frame = new DisplayThreeCards();
            frame.setVisible(true); // Make the frame visible
        });
    }
}
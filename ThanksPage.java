import javax.swing.*;
import java.awt.*;

public class ThanksPage extends JFrame {
    public ThanksPage() {
        setTitle("Thank You");
        setSize(800, 600); // Adjusted for better display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen

        // Load the background image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\602855f4-902f-46d4-91c1-9504bff19a9e (1).jpg");
        Image originalImage = originalIcon.getImage();

        // Create a panel with custom painting for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image scaled to the size of the panel
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JLabel thankYouLabel = new JLabel("Thanks for using our system!", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Serif", Font.BOLD, 40));
        thankYouLabel.setForeground(Color.BLACK); // Set text color

        backgroundPanel.add(thankYouLabel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
    }
}

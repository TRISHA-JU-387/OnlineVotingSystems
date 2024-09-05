import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Online Voting System");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\645b00ff-a4f6-4b14-b70e-b37b53569c15.jpg");  // Path to your image
        Image originalImage = originalIcon.getImage();

        // Create a panel with custom painting
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the image scaled to the size of the panel
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Online Voting System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));  // Adjust the font size and style
        welcomeLabel.setForeground(Color.BLACK);  // Set the text color to white for better visibility
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the enter button
        JButton enterButton = new JButton("Press Enter");
        enterButton.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        enterButton.setMaximumSize(new Dimension(200, 50)); // Set button size
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuPage().setVisible(true);
            }
        });

        // Add spacing and components to the background panel
        backgroundPanel.add(Box.createVerticalGlue());  // Push contents to the center
        backgroundPanel.add(welcomeLabel);
        backgroundPanel.add(Box.createVerticalStrut(20));  // Add spacing between components
        backgroundPanel.add(enterButton);
        backgroundPanel.add(Box.createVerticalGlue());  // Push contents to the center

        // Add the background panel to the frame
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}

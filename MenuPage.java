import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JFrame {
    public MenuPage() {
        setTitle("Menu");
        setSize(400, 300);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\9282f5c3-8ce4-4c37-828c-1bea14ee6ffb.jpg"); // Path to your image
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

        // Create buttons with customized colors
        JButton adminBlockButton = createCustomButton("Admin Block", Color.BLUE, Color.WHITE);
        JButton candidateRegButton = createCustomButton("Candidate Registration", Color.GREEN, Color.WHITE);
        JButton votingPanelButton = createCustomButton("Voting Panel", Color.ORANGE, Color.WHITE);
        JButton resultButton = createCustomButton("Result", Color.RED, Color.WHITE);

        // Add action listeners to buttons
        adminBlockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminBlock().setVisible(true);
                dispose();
            }
        });

        candidateRegButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CandidateRegistration().setVisible(true);
                dispose();
            }
        });

        votingPanelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VotingPanel().setVisible(true);
                dispose();
            }
        });

        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ResultPage().setVisible(true);
                dispose();
            }
        });

        // Add buttons to the background panel with spacing
        backgroundPanel.add(Box.createVerticalGlue());  // Push buttons to the center
        backgroundPanel.add(adminBlockButton);
        backgroundPanel.add(Box.createVerticalStrut(10));  // Spacing between buttons
        backgroundPanel.add(candidateRegButton);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(votingPanelButton);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(resultButton);
        backgroundPanel.add(Box.createVerticalGlue());

        // Set the background panel as the content pane
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    // Helper method to create a button with custom background and foreground colors
    private JButton createCustomButton(String text, Color backgroundColor, Color foregroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Serif", Font.BOLD, 20));  // Set font size and style
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50)); // Set button size
        return button;
    }

    public static void main(String[] args) {
        new MenuPage();
    }
}

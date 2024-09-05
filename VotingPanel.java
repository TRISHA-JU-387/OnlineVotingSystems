import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class VotingPanel extends JFrame {
    private JTextField nidField;

    public VotingPanel() {
        setTitle("Voting Panel");
        setSize(500, 400); // Adjust size for better display
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the background panel and set it as the content pane
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);

        // Create components
        JLabel nidLabel = createCustomLabel("Enter your 11-digit NID Number:");
        nidField = createCustomTextField();

        JButton voteButton = createCustomButton("Vote", Color.BLUE, Color.WHITE);
        voteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nid = nidField.getText();
                if (nid.length() == 11) {
                    for (String post : VoteManager.getPosts()) {
                        String candidate = (String) JOptionPane.showInputDialog(null, "Select Candidate for " + post, "Candidate Selection",
                                JOptionPane.QUESTION_MESSAGE, null, VoteManager.getCandidates(post).toArray(), null);

                        if (candidate != null) {
                            String message = VoteManager.castVote(nid, post, candidate);
                            JOptionPane.showMessageDialog(null, message);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid NID number. Must be 11 digits.");
                }
                new MenuPage().setVisible(true);
                dispose();
            }
        });

        // Set layout and add components to the background panel
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        backgroundPanel.add(nidLabel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(nidField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(voteButton, gbc);

        // Set the frame visibility
        setVisible(true);
    }

    private JLabel createCustomLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createCustomTextField() {
        JTextField textField = new JTextField(11);
        textField.setFont(new Font("Serif", Font.PLAIN, 16));
        textField.setBackground(Color.LIGHT_GRAY);  // Set background color
        textField.setForeground(Color.BLACK);  // Set text color
        return textField;
    }

    private JButton createCustomButton(String text, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 40)); // Set button size
        return button;
    }

    public static void main(String[] args) {
        new VotingPanel();
    }

    // Custom JPanel to draw the background image
    class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                // Load the background image
                backgroundImage = ImageIO.read(new File("C:\\Users\\DELL\\OneDrive\\Pictures\\541fb6f7-367a-4841-bfb5-d53dfdbf3b33.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the image stretched to fill the entire panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}

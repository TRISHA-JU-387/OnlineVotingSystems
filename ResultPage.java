import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPage extends JFrame {
    public ResultPage() {
        setTitle("Result Page");
        setSize(800, 600); // Initial size
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen

        // Load the background image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\602855f4-902f-46d4-91c1-9504bff19a9e (1).jpg");
        Image originalImage = originalIcon.getImage();

        // Create a panel with custom painting for background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JButton showResultButton = new JButton("Show Result");
        showResultButton.setFont(new Font("Serif", Font.BOLD, 30));
        showResultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (VoteManager.isVotingFinished()) {
                    String results = VoteManager.getResults();
                    
                    // Display results in a label
                    JLabel resultsLabel = new JLabel("<html>" + results.replace("\n", "<br>") + "</html>", SwingConstants.CENTER);
                    resultsLabel.setFont(new Font("Serif", Font.BOLD, 24));
                    resultsLabel.setForeground(Color.BLACK); // Set text color
                    
                    backgroundPanel.removeAll(); // Clear previous components
                    backgroundPanel.add(resultsLabel, BorderLayout.CENTER);
                    
                    // Create and add the "Show Thanks Page" button
                    JButton showThanksButton = new JButton(" EXIT ");
                    showThanksButton.setFont(new Font("Serif", Font.BOLD, 24));
                    showThanksButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new ThanksPage().setVisible(true);
                            dispose(); // Close the ResultPage
                        }
                    });

                    backgroundPanel.add(showThanksButton, BorderLayout.SOUTH);
                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Voting is still running.");
                }
            }
        });

        backgroundPanel.add(showResultButton, BorderLayout.NORTH); // Add button to north

        setContentPane(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResultPage().setVisible(true);
            }
        });
    }
}

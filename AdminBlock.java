import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminBlock extends JFrame {
    private JTextField postField;
    private ArrayList<String> postNames;
    private JPasswordField passwordField;

    public AdminBlock() {
        setTitle("Admin Block");
        setSize(400, 300);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        postNames = new ArrayList<>();

        // Load the background image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\d2fee31d-d41a-4f79-88d9-6ffad90fed0c.jpg"); // Path to your image
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
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));

        // Create and customize the password components
        JLabel passwordLabel = createCustomLabel("Enter Admin Password:", Color.BLACK);
        passwordField = createCustomPasswordField();

        JButton enterButton = createCustomButton("Enter", Color.BLUE, Color.WHITE);
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                if (password.equals("admin123")) {  // Admin password check
                    enterAdminMode();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Password");
                }
            }
        });

        // Add components to the background panel
        backgroundPanel.add(Box.createVerticalGlue());  // Push components to the center
        backgroundPanel.add(passwordLabel);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(passwordField);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(enterButton);
        backgroundPanel.add(Box.createVerticalGlue());

        // Set the background panel as the content pane
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private void enterAdminMode() {
        JLabel postLabel = createCustomLabel("Enter Total Number of Posts:", Color.WHITE);
        postField = createCustomTextField();

        JButton submitButton = createCustomButton("Submit", Color.GREEN, Color.WHITE);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int totalPosts = Integer.parseInt(postField.getText());
                    for (int i = 0; i < totalPosts; i++) {
                        // Create a panel to hold the image and text field
                        JPanel postPanel = new JPanel();
                        postPanel.setLayout(new BorderLayout());

                        // Load and add the image
                        ImageIcon postImageIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\a3f2513b-b104-46ad-add8-2eefb819ea88.jpg"); // Path to your image
                        Image postImage = postImageIcon.getImage();
                        Image resizedPostImage = postImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize image
                        ImageIcon resizedPostImageIcon = new ImageIcon(resizedPostImage);
                        JLabel postImageLabel = new JLabel(resizedPostImageIcon);

                        // Add the image and text field to the panel
                        JTextField postNameField = createCustomTextField();
                        postPanel.add(postImageLabel, BorderLayout.WEST);
                        postPanel.add(postNameField, BorderLayout.CENTER);

                        // Show input dialog to get post names
                        JOptionPane.showMessageDialog(null, postPanel, "Enter name of Post " + (i + 1), JOptionPane.PLAIN_MESSAGE);
                        String postName = postNameField.getText();
                        postNames.add(postName);
                    }
                    VoteManager.setPosts(postNames);
                    JOptionPane.showMessageDialog(null, "Posts Registered Successfully!");
                    new MenuPage().setVisible(true);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for total posts.");
                }
            }
        });

        // Add components to the background panel
        getContentPane().removeAll();  // Clear the previous components
        add(postLabel);
        add(postField);
        add(Box.createVerticalStrut(10));
        add(submitButton);
        revalidate();
        repaint();
    }

    // Helper method to create a custom JLabel with specified text color
    private JLabel createCustomLabel(String text, Color textColor) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(textColor);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    // Helper method to create a custom JTextField
    private JTextField createCustomTextField() {
        JTextField textField = new JTextField(10);
        textField.setMaximumSize(new Dimension(200, 30));  // Set size
        textField.setFont(new Font("Serif", Font.PLAIN, 18));
        textField.setBackground(Color.LIGHT_GRAY);  // Set background color
        textField.setForeground(Color.BLACK);  // Set text color
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        return textField;
    }

    // Helper method to create a custom JPasswordField
    private JPasswordField createCustomPasswordField() {
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setMaximumSize(new Dimension(200, 30));  // Set size
        passwordField.setFont(new Font("Serif", Font.PLAIN, 18));
        passwordField.setBackground(Color.LIGHT_GRAY);  // Set background color
        passwordField.setForeground(Color.BLACK);  // Set text color
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        return passwordField;
    }

    // Helper method to create a custom JButton with specified background and text colors
    private JButton createCustomButton(String text, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50)); // Set button size
        return button;
    }

    public static void main(String[] args) {
        new AdminBlock();
    }
}

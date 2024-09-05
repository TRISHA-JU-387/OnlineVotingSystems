import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CandidateRegistration extends JFrame {
    private JTextField nameField, dobField, fatherNameField, motherNameField;
    private JTextField presentAddressField, permanentAddressField, qualificationField;
    private JTextField nationalityField, bloodGroupField;

    public CandidateRegistration() {
        setTitle("Candidate Registration");
        setSize(800, 600); // Adjust size for better display
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null);

        // Load the background image
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\DELL\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-07-12 162144.png.jpg"); // Path to your image
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

        // Create and customize form components
        JLabel nameLabel = createCustomLabel("Name:");
        nameField = createCustomTextField();

        JLabel dobLabel = createCustomLabel("Date of Birth (YYYY-MM-DD):");
        dobField = createCustomTextField();

        JLabel fatherNameLabel = createCustomLabel("Father's Name:");
        fatherNameField = createCustomTextField();

        JLabel motherNameLabel = createCustomLabel("Mother's Name:");
        motherNameField = createCustomTextField();

        JLabel presentAddressLabel = createCustomLabel("Present Address:");
        presentAddressField = createCustomTextField();

        JLabel permanentAddressLabel = createCustomLabel("Permanent Address:");
        permanentAddressField = createCustomTextField();

        JLabel qualificationLabel = createCustomLabel("Educational Qualification:");
        qualificationField = createCustomTextField();

        JLabel nationalityLabel = createCustomLabel("Nationality:");
        nationalityField = createCustomTextField();

        JLabel bloodGroupLabel = createCustomLabel("Blood Group:");
        bloodGroupField = createCustomTextField();

        JButton registerButton = createCustomButton("Register", Color.RED, Color.WHITE);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validate fields
                if (isAnyFieldEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                    return;
                }

                // Check if the candidate is already registered
                if (VoteManager.isCandidateRegistered(nameField.getText())) {
                    JOptionPane.showMessageDialog(null, "This candidate is already registered.");
                    return;
                }

                // Age validation
                String dob = dobField.getText();
                if (!isAgeValid(dob)) {
                    JOptionPane.showMessageDialog(null, "Candidate must be at least 25 years old.");
                    return;
                }

                // Collect and validate input fields
                String name = nameField.getText();
                String fatherName = fatherNameField.getText();
                String motherName = motherNameField.getText();
                String presentAddress = presentAddressField.getText();
                String permanentAddress = permanentAddressField.getText();
                String qualification = qualificationField.getText();
                String nationality = nationalityField.getText();
                String bloodGroup = bloodGroupField.getText();

                // Show post selection dialog
                String post = (String) JOptionPane.showInputDialog(null, "Select Post", "Post Selection",
                        JOptionPane.QUESTION_MESSAGE, null, VoteManager.getPosts().toArray(), null);

                if (post != null) {
                    // Register the candidate
                    VoteManager.registerCandidate(post, name, dob, fatherName, motherName, presentAddress,
                            permanentAddress, qualification, nationality, bloodGroup);

                    JOptionPane.showMessageDialog(null, "Candidate Registered Successfully!");
                    new MenuPage().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No post selected.");
                }
            }
        });

        // Add components to the background panel
        backgroundPanel.add(Box.createVerticalGlue());  // Push components to the center
        backgroundPanel.add(nameLabel); backgroundPanel.add(nameField);
        backgroundPanel.add(dobLabel); backgroundPanel.add(dobField);
        backgroundPanel.add(fatherNameLabel); backgroundPanel.add(fatherNameField);
        backgroundPanel.add(motherNameLabel); backgroundPanel.add(motherNameField);
        backgroundPanel.add(presentAddressLabel); backgroundPanel.add(presentAddressField);
        backgroundPanel.add(permanentAddressLabel); backgroundPanel.add(permanentAddressField);
        backgroundPanel.add(qualificationLabel); backgroundPanel.add(qualificationField);
        backgroundPanel.add(nationalityLabel); backgroundPanel.add(nationalityField);
        backgroundPanel.add(bloodGroupLabel); backgroundPanel.add(bloodGroupField);
        backgroundPanel.add(Box.createVerticalStrut(20));
        backgroundPanel.add(registerButton);
        backgroundPanel.add(Box.createVerticalGlue());

        // Set the background panel as the content pane
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private boolean isAnyFieldEmpty() {
        return nameField.getText().isEmpty() || dobField.getText().isEmpty() ||
               fatherNameField.getText().isEmpty() || motherNameField.getText().isEmpty() ||
               presentAddressField.getText().isEmpty() || permanentAddressField.getText().isEmpty() ||
               qualificationField.getText().isEmpty() || nationalityField.getText().isEmpty() ||
               bloodGroupField.getText().isEmpty();
    }

    private boolean isAgeValid(String dob) {
        try {
            LocalDate birthDate = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long age = ChronoUnit.YEARS.between(birthDate, LocalDate.now());
            return age >= 25;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Date of Birth format.");
            return false;
        }
    }

    // Helper method to create a custom JLabel with specified text color
    private JLabel createCustomLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.LEFT);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        return label;
    }

    // Helper method to create a custom JTextField
    private JTextField createCustomTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Serif", Font.PLAIN, 16));
        textField.setBackground(Color.LIGHT_GRAY);  // Set background color
        textField.setForeground(Color.BLACK);  // Set text color
        return textField;
    }

    // Helper method to create a custom JButton with specified background and text colors
    private JButton createCustomButton(String text, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setMaximumSize(new Dimension(200, 50)); // Set button size
        return button;
    }

    public static void main(String[] args) {
        new CandidateRegistration();
    }
}

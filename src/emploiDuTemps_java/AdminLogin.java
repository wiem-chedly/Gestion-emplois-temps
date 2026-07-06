package emploiDuTemps_java;
import java.sql.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLogin extends JFrame {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    private JLabel registerLink;

    public AdminLogin() {
        setTitle("Administrateur");
        setSize(400, 250); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(173, 216, 230)); 
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        JLabel titleLabel = new JLabel("<html><center><br>Administrateur<br><br></center></html>", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
        titleLabel.setForeground(Color.BLACK);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(173, 216, 230));
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        loginField = new JTextField(15); 
        loginField.setPreferredSize(new Dimension(100, 25));
        formPanel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Mot de passe:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15); 
        passwordField.setPreferredSize(new Dimension(100, 25));
        formPanel.add(passwordField, gbc);

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(173, 216, 230)); 

        loginButton = new JButton("Connexion");
        loginButton.setPreferredSize(new Dimension(100, 25)); 
        cancelButton = new JButton("Annuler");
        cancelButton.setPreferredSize(new Dimension(80, 25)); 
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);

        
        gbc.gridy = 3;
        registerLink = new JLabel("<HTML><U>Inscription</U></HTML>");
        registerLink.setForeground(Color.BLUE);
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLink.setHorizontalAlignment(SwingConstants.LEFT);
        formPanel.add(registerLink, gbc);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());

                if (login.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Les champs ne peuvent pas être vides.");
                    return;
                }

                if (verifyLogin(login, password)) {
                    new Cours().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Connexion échouée. Login ou mot de passe incorrect.");
                }
            }
        });

        cancelButton.addActionListener(e -> {
            loginField.setText("");
            passwordField.setText("");
        });

        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new RegisterForm().setVisible(true);
                dispose();
            }
        });
    }

    private boolean verifyLogin(String login, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emploidutemps_db", "root", "");
        		
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE login = ? AND mot_de_passe = ?")) {
           
        	stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
        	
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLogin().setVisible(true));
      
    }
}

package emploiDuTemps_java;
import java.sql.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterForm extends JFrame {
    private JTextField nomField, prenomField, emailField, adresseField, loginField;
    private JPasswordField passwordField;
    private JButton registerButton, cancelButton;
    private JLabel retourLabel;

    public RegisterForm() {
        setTitle("Inscrivez-vous");
        setSize(400, 450); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        getContentPane().setBackground(new Color(173, 216, 230));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(173, 216, 230));
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); 

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Inscrivez-vous", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Nom:"), gbc);

        gbc.gridx = 1;
        nomField = new JTextField(15); 
        nomField.setPreferredSize(new Dimension(100, 25)); 
        mainPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Prénom:"), gbc);

        gbc.gridx = 1;
        prenomField = new JTextField(15);
        prenomField.setPreferredSize(new Dimension(100, 25));
        mainPanel.add(prenomField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension(100, 25));
        mainPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Adresse:"), gbc);

        gbc.gridx = 1;
        adresseField = new JTextField(15);
        adresseField.setPreferredSize(new Dimension(100, 25));
        mainPanel.add(adresseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        loginField = new JTextField(15);
        loginField.setPreferredSize(new Dimension(100, 25));
        mainPanel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Mot de passe:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(100, 25));
        mainPanel.add(passwordField, gbc);

       
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(173, 216, 230));
        registerButton = new JButton("S'inscrire");
        registerButton.setPreferredSize(new Dimension(90, 25)); 
        cancelButton = new JButton("Annuler");
        cancelButton.setPreferredSize(new Dimension(80, 25));
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, gbc);

        
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST; 
        retourLabel = new JLabel("<html><br><a href=''>Retour à la page de connexion</a></html>");
        retourLabel.setForeground(Color.BLUE);
        retourLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retourLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AdminLogin().setVisible(true);
                dispose(); 
            }
        });
        mainPanel.add(retourLabel, gbc);

       
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String email = emailField.getText();
                String adresse = adresseField.getText();
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());

                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || login.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
                    return;
                }

                if (registerUser(nom, prenom, email, adresse, login, password)) {
                    JOptionPane.showMessageDialog(null, "Inscription réussie !");
                    new AdminLogin().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription.");
                }
            }
        });

        cancelButton.addActionListener(e -> {
            nomField.setText("");
            prenomField.setText("");
            emailField.setText("");
            adresseField.setText("");
            loginField.setText("");
            passwordField.setText("");
        });
    }

    private boolean registerUser(String nom, String prenom, String email, String adresse, String login, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emploidutemps_db", "root", "");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (nom, prenom, email, adresse, login, mot_de_passe) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, adresse);
            stmt.setString(5, login);
            stmt.setString(6, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

  
}

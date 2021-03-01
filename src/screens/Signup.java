package screens;

import accounts.Account;
import accounts.User;
import utils.Database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Signup extends AuthScreen{
    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel loginLabel;
    private JLabel errorLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JPasswordField confirmPasswordTextField;
    private JButton signupButton;

    public Signup(Database db){
        super(db, new JFrame());
        initialize();
    }

    public void initialize(){
        // create obj
        this.panel = new JPanel();
        this.gbc = new GridBagConstraints();
        this.usernameLabel = new JLabel("Username");
        this.passwordLabel = new JLabel("Password");
        this.confirmPasswordLabel = new JLabel("Confirm Password");
        this.loginLabel = new JLabel("Already have an account? Sign in");
        this.errorLabel = new JLabel("");
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        this.confirmPasswordTextField = new JPasswordField();
        this.signupButton = new JButton("Signup");

        this.gbc.fill = GridBagConstraints.HORIZONTAL;
        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
        super.getFrame().setLayout(new GridBagLayout());

        this.errorLabel.setForeground(Color.red);
        JFrame frame = super.getFrame();
        this.loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Database db = null;
                try {
                    db = Database.readDatabase();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // create new login frame
                Login loginWindow = new Login(db);
                loginWindow.getFrame().setVisible(true);

                // close current frame
                frame.setVisible(false);
                frame.dispose();
            }
        });

        this.signupButton.addActionListener(actionEvent -> {

            // reset errors
            this.errorLabel.setText("");

            String username = this.usernameTextField.getText();
            String password = String.valueOf(this.passwordTextField.getPassword());
            String confirmPassword = String.valueOf(this.confirmPasswordTextField.getPassword());

            if(username.isEmpty() || password.isEmpty()){
                this.errorLabel.setText("Fields cannot be empty");
                return;
            }

            if(!password.equals(confirmPassword)){
                this.errorLabel.setText("Passwords do not match");
                return;
            }

            //create user
            Account account = new User(username, password);
            try {
                super.getDatabase().addAccount(account);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // create home and pass current user
            Home home = new Home(account);
            home.getFrame().setVisible(true);

            // close current frame
            super.getFrame().setVisible(false);
            super.getFrame().dispose();

        });

        super.getFrame().setBounds(100, 100, 450, 300);
        super.getFrame().setResizable(false);
        super.getFrame().setTitle("Stock Market Simulator");
        super.getFrame().setLocationRelativeTo(null);
        super.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add to frame
        super.getFrame().add(panel);
        gbc.gridx = 1;
        gbc.gridy = 1;
        super.getFrame().add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        super.getFrame().add(passwordLabel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        super.getFrame().add(confirmPasswordLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        super.getFrame().add(loginLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        super.getFrame().add(errorLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        super.getFrame().add(signupButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 8;
        super.getFrame().add(signupButton, gbc);


        gbc.gridx = 2;
        gbc.gridy = 1;
        super.getFrame().add(usernameTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        super.getFrame().add(passwordTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        super.getFrame().add(confirmPasswordTextField, gbc);

    }
}

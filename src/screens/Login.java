package screens;

import accounts.Account;
import accounts.User;
import utils.Database;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Login extends AuthScreen {
    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel errorLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JLabel signupLabel;

    public Login(Database db) {
        super(db, new JFrame());
        initialize();
    }

    public void initialize() {
        // create obj
        this.panel = new JPanel();
        this.gbc = new GridBagConstraints();
        this.usernameLabel = new JLabel("Username");
        this.passwordLabel = new JLabel("Password");
        this.errorLabel = new JLabel("");
        this.signupLabel = new JLabel("Don't have an account? Sign up");
        this.usernameTextField = new JTextField();
        this.passwordTextField = new JPasswordField();
        this.loginButton = new JButton("Login");

        this.gbc.fill = GridBagConstraints.HORIZONTAL;

        this.gbc.weightx = 1;
        this.gbc.weighty = 1;
        super.getFrame().setLayout(new GridBagLayout());

        this.errorLabel.setForeground(Color.red);

        JFrame frame = super.getFrame();
        this.signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Database db = null;
                try {
                    db = Database.readDatabase();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // create new signup frame
                Signup signupWindow = new Signup(db);
                signupWindow.getFrame().setVisible(true);

                // close current frame
                frame.setVisible(false);
                frame.dispose();
            }
        });

        // set buttons
        this.loginButton.addActionListener(actionEvent -> {
            // reset error
            this.errorLabel.setText("");

            String username = usernameTextField.getText();
            String password = String.valueOf(passwordTextField.getPassword()); // getPassword returns char[]

            Database db = super.getDatabase();

            Account account = db.findAccountByUsername(username);

            if(account == null){
                this.errorLabel.setText("Account not found");
                return;
            }

            if(!account.signIn(username, password)){
                this.errorLabel.setText("Wrong password... Try again");
                return;
            }

            // create home and pass current user
            Home home = new Home(account);
            home.getFrame().setVisible(true);

            // close current frame
            super.getFrame().setVisible(false);
            super.getFrame().dispose();

        });

        // set frame
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
        super.getFrame().add(errorLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        super.getFrame().add(signupLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        super.getFrame().add(usernameTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        super.getFrame().add(passwordTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        super.getFrame().add(loginButton, gbc);

    }
}

package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInView extends JFrame {
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JLabel usernameLabel = new JLabel("username");
    JLabel passLabel = new JLabel("password");
    JButton login = new JButton("Log in");
    JButton createNewUser = new JButton("create new user");
    JPanel mainPanel = new JPanel();
    JCheckBox client = new JCheckBox("Client");
    JCheckBox admin = new JCheckBox("Admin");
    JCheckBox employee = new JCheckBox("Employee");


    public LogInView(){
        setSize(400, 200);

        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.add(username);
        mainPanel.add(usernameLabel);
        mainPanel.add(client);
        mainPanel.add(password);
        mainPanel.add(passLabel);
        mainPanel.add(admin);
        mainPanel.add(login);
        mainPanel.add(createNewUser);
        mainPanel.add(employee);

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void open(){
        this.setTitle("Log in");
        this.setVisible(true);
    }

    public void addLoginListener(ActionListener listener){
        login.addActionListener(listener);
    }

    public void addNewUserListener(ActionListener listener){
        createNewUser.addActionListener(listener);
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPassword(){
        return password.getText();
    }

    public String getTypeUser(){
        if(client.isSelected())
            return new String("client");
        if(admin.isSelected())
            return new String("admin");
        if(employee.isSelected())
            return new String("employee");
        return new String("admin");
    }

}

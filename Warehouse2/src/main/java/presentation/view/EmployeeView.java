package presentation.view;

import javax.swing.*;
import java.awt.*;

public class EmployeeView extends JFrame{

    public JTextArea textArea = new JTextArea();
    public JScrollPane scrollPane = new JScrollPane(textArea);

    public EmployeeView(){

        setSize(800, 800);

       // textArea.setPreferredSize(new Dimension(700, 700));
        scrollPane.setPreferredSize(new Dimension(700, 700));


        this.add(scrollPane);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void write(String str){
        textArea.append("\n" + str);
    }

    public void open(){
        this.setTitle("Employee view");
        this.setVisible(true);
    }
}

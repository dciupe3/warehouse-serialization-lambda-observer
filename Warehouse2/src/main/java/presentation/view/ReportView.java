package presentation.view;

import business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportView extends JFrame {

    JPanel mainPannel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();

    public JSpinner startHour;
    public JSpinner finishHour;
    public SpinnerDateModel modelSpiner;
    public SpinnerDateModel modelSpiner2;
    JButton report1Button = new JButton("Report");

    JLabel nrOrdersLabel = new JLabel("Number of orders: ");
    JTextField nrOrdersText = new JTextField("0");
    JLabel label1 = new JLabel("Products ordered more than a specified number of time");
    //2
    JLabel nrClients= new JLabel("Number of orders: ");
    JTextField nrClientsText = new JTextField("0");
    //3
    JLabel label2 = new JLabel("Clients that have ordered more than");

    JLabel label3 = new JLabel("a specified number of times and the value");

    JLabel label4 = new JLabel("of the order was higher than a specified amount.");
    JLabel nrOrdersLabel2 = new JLabel("Number of orders: ");
    JTextField nrOrdersText2 = new JTextField("0");
    JLabel label5 = new JLabel("Value of order: ");
    JTextField valueOrderText = new JTextField("0");

    //4
    JLabel label7 = new JLabel("products ordered within a specified day with");
    JLabel label8 = new JLabel("the number of times they have been ordered.");
    JSpinner spinner2;
    JLabel label9 = new JLabel("Select the date : ");

    JButton report2Button = new JButton("Report");
    JButton report3Button = new JButton("Report");
    JButton report4Button = new JButton("Report");

    JTable tableProducts = new JTable();
    DefaultTableModel tableModel2;
    JScrollPane scrollPane2 = new JScrollPane();

    JTable tableClients = new JTable();
    DefaultTableModel tableModel3;
    JScrollPane scrollPane3 = new JScrollPane();

    JTable tableOrder = new JTable();
    DefaultTableModel tableModel1;
    JScrollPane scrollPane1 = new JScrollPane();

    JTable tableProducts4 = new JTable();
    DefaultTableModel tableModel4;
    JScrollPane scrollPane4 = new JScrollPane();

    public ReportView(){
        setSize(1600, 800);

        mainPannel.setLayout(new FlowLayout(5,30,20));
        panel1.setPreferredSize(new Dimension(355,700));
        panel2.setPreferredSize(new Dimension(355,700));
        panel3.setPreferredSize(new Dimension(355,700));
        panel4.setPreferredSize(new Dimension(355,700));

        panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.lightGray);
        panel3.setBackground(Color.lightGray);
        panel4.setBackground(Color.lightGray);

        //PANEL1
        panel1.setLayout(new FlowLayout());

        modelSpiner = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        modelSpiner2 = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        startHour = new JSpinner(modelSpiner);
        finishHour = new JSpinner(modelSpiner2);

        JSpinner.DateEditor de = new JSpinner.DateEditor(startHour, "hh:mm a");
        //de.getTextField().setEditable(false);
        startHour.setEditor(de);

        JSpinner.DateEditor de2 = new JSpinner.DateEditor(finishHour, "hh:mm a");
        //de.getTextField().setEditable(false);
        finishHour.setEditor(de2);

        report1Button.setPreferredSize(new Dimension(150, 40));

        JPanel panel1_1 = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane(panel1_1);
        jScrollPane1.setPreferredSize(new Dimension(300, 600));
        panel1_1.setPreferredSize(new Dimension(200, 10000));
        panel1_1.setBackground(Color.pink);

        scrollPane1.setPreferredSize(new Dimension(350, 550));
        tableOrder.setBackground(Color.pink);
        tableModel1 = new DefaultTableModel();
        tableModel1.addColumn("Code");
        tableModel1.addColumn("Time");
        tableModel1.addColumn("User");
        tableModel1.addColumn("Nr Products");
        tableModel1.addColumn("price");

        tableOrder.setModel(tableModel1);
        scrollPane1.setViewportView(tableOrder);
        //mai adaug tabele cu comenzile


        panel1.add(startHour);
        panel1.add(finishHour);
        panel1.add(scrollPane1);
        panel1.add(report1Button);

        //PANEL 2

        panel2.setLayout(new FlowLayout());

        nrOrdersText2.setPreferredSize(new Dimension(35, 25));
        valueOrderText.setPreferredSize(new Dimension(60, 25));

        scrollPane2.setPreferredSize(new Dimension(350, 500));
        tableProducts.setBackground(Color.pink);
        tableModel2 = new DefaultTableModel();
        tableModel2.addColumn("Title");
        tableModel2.addColumn("Rating");
        tableModel2.addColumn("Price");
        tableModel2.addColumn("NR");

        tableProducts.setModel(tableModel2);
        scrollPane2.setViewportView(tableProducts);
        report2Button.setPreferredSize(new Dimension(150, 40));

        nrOrdersText.setPreferredSize(new Dimension(40, 25));
        panel2.add(label1);
        panel2.add(nrOrdersLabel);
        panel2.add(nrOrdersText);
        panel2.add(scrollPane2);
        panel2.add(report2Button);

        //adaug tabelul de products:

        //PANEL3
        report3Button.setPreferredSize(new Dimension(150, 40));

        scrollPane3.setPreferredSize(new Dimension(350, 490));
        tableClients.setBackground(Color.pink);
        tableModel3 = new DefaultTableModel();
        tableModel3.addColumn("Username");
        tableModel3.addColumn("Nr Orders");
        //nr total orders?

        tableClients.setModel(tableModel3);
        scrollPane3.setViewportView(tableClients);


        panel3.add(label2);
        panel3.add(label3);
        panel3.add(label4);
        panel3.add(scrollPane3);
        panel3.add(nrOrdersLabel2);
        panel3.add(nrOrdersText2);
        panel3.add(label5);
        panel3.add(valueOrderText);
        panel3.add(report3Button);

        //PANEL4
        panel4.setLayout(new FlowLayout());

        panel4.add(label7);
        panel4.add(label8);

        spinner2 = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner2, "dd/MM/yy");
        spinner2.setEditor(editor);


        scrollPane4.setPreferredSize(new Dimension(350, 500));
        tableProducts4.setBackground(Color.pink);
        tableModel4 = new DefaultTableModel();

        tableModel4.addColumn("Title");
        tableModel4.addColumn("Rating");
        tableModel4.addColumn("Price");
        tableModel4.addColumn("NR");

        tableProducts4.setModel(tableModel4);
        scrollPane4.setViewportView(tableProducts4);
        report4Button.setPreferredSize(new Dimension(150, 40));


        panel4.add(label9);
        panel4.add(spinner2);
        panel4.add(scrollPane4);
        panel4.add(report4Button);


        mainPannel.add(panel1);
        mainPannel.add(panel2);
        mainPannel.add(panel3);
        mainPannel.add(panel4);


        this.add(mainPannel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public int getReport2Data(){
        return Integer.parseInt(nrOrdersText.getText());
    }

    public ArrayList<Integer> getReport3Data(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(nrOrdersText2.getText()));
        list.add(Integer.parseInt(valueOrderText.getText()));

        return list;
    }

    public void open(){
        this.setTitle("Report view");
        this.setVisible(true);
    }

    public ArrayList<Integer> getStartDate(){
        String s = startHour.getValue().toString();
        String[] ss = s.split(" ");
        String sss = ss[3];
        String[] ssss = sss.split(":");

        ArrayList<Integer> nr = new ArrayList<>();
        nr.add(Integer.parseInt(ssss[0]));
        nr.add(Integer.parseInt(ssss[1]));

        return nr;
    }

    public ArrayList<Integer> getFinishDate(){
        String s = finishHour.getValue().toString();
        String[] ss = s.split(" ");
        String sss = ss[3];
        String[] ssss = sss.split(":");

        ArrayList<Integer> nr = new ArrayList<>();
        nr.add(Integer.parseInt(ssss[0]));
        nr.add(Integer.parseInt(ssss[1]));
        return nr;
    }

    public String getDate4(){
        String s = spinner2.getValue().toString();

        String[] ss = s.split(" ");
        String sss = ss[0] + " " + ss[1] + " " + ss[2] + " " + ss[5];
        System.out.println(sss);

        return sss;
    }

    public void populateTable2(List<List<String>> rows){
        tableModel2.setRowCount(0);
        for(List<String> obj : rows){
            Object[] s = new Object[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModel2.addRow(s);
        }
    }

    public void populateTable1(List<List<String>> rows){
        tableModel1.setRowCount(0);
        for(List<String> obj : rows){
            Object[] s = new Object[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModel1.addRow(s);
        }
    }

    public void populateTableReport2(ArrayList<MenuItem> products){
        List<List<String>> listOfLists = new ArrayList<>();

        if(products.isEmpty()){
            System.out.println("nu exista produse");
            tableModel2.setRowCount(0);
        }

        for(MenuItem item : products){
            List<String> line = new ArrayList<>();

            line.add(item.getTitle());
            line.add(Double.toString(item.getRating()));
            line.add(Integer.toString(item.getPrice()));
            line.add(Integer.toString(item.getContorOrder()));

            listOfLists.add(line);
        }

        populateTable2(listOfLists);
    }

    public void populateTable4(List<List<String>> rows){
        tableModel4.setRowCount(0);
        for(List<String> obj : rows){
            Object[] s = new Object[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModel4.addRow(s);
        }
    }

    public void addReport2Listener(ActionListener listener){
        report2Button.addActionListener(listener);
    }

    public void populateTable3(List<List<String>> rows){
        tableModel3.setRowCount(0);
        for(List<String> obj : rows){
            Object[] s = new Object[obj.size()];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            tableModel3.addRow(s);
        }
    }

    public void addReport3Listener(ActionListener listener){
        report3Button.addActionListener(listener);
    }

    public void addReport1Listener(ActionListener listener){
        report1Button.addActionListener(listener);
    }

    public void addReport4Listener(ActionListener listener){
        report4Button.addActionListener(listener);
    }

}

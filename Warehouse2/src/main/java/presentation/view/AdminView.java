package presentation.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import business.MenuItem;


public class AdminView extends JFrame {

    JButton importCSV = new JButton("Import .csv file");
    JTextField titleText = new JTextField();
    JTextField ratingText = new JTextField();
    JTextField priceText = new JTextField();
    JTextField caloriesText = new JTextField();
    JTextField proteinText = new JTextField();
    JTextField fatText = new JTextField();
    JTextField sodiumText = new JTextField();

    JLabel titleLabel = new JLabel("Title: ");
    JLabel ratingLabel = new JLabel("Rating: ");
    JLabel priceLabel = new JLabel("Price: ");
    JLabel caloriesLabel = new JLabel("Calories: ");
    JLabel proteinLabel = new JLabel("Protein: ");
    JLabel fatLabel = new JLabel("Fat: ");
    JLabel sodiumLabel = new JLabel("Sodium: ");

    JButton addProductButton = new JButton("Add product");
    JButton modifyProductButton = new JButton("Modify product");
    JButton createCompositeProductButton = new JButton("Create composite product");
    JButton removeProductButton = new JButton("Remove product");
    JButton reportButton = new JButton("Reports");

    DefaultTableModel tableModel;
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();

    JPanel mainPanel;

    public AdminView(){
        setSize(800, 800);

        mainPanel = new JPanel();

        mainPanel.setLayout(new FlowLayout());


        titleText.setPreferredSize(new Dimension(100, 20));
        caloriesText.setPreferredSize(new Dimension(50, 20));
        proteinText.setPreferredSize(new Dimension(50, 20));
        fatText.setPreferredSize(new Dimension(50, 20));
        sodiumText.setPreferredSize(new Dimension(50, 20));
        ratingText.setPreferredSize(new Dimension(50, 20));
        priceText.setPreferredSize(new Dimension(50, 20));

        mainPanel.add(titleLabel); mainPanel.add(titleText);
        mainPanel.add(caloriesLabel); mainPanel.add(caloriesText);
        mainPanel.add(proteinLabel); mainPanel.add(proteinText);
        mainPanel.add(fatLabel); mainPanel.add(fatText);
        mainPanel.add(sodiumLabel); mainPanel.add(sodiumText);
        mainPanel.add(ratingLabel); mainPanel.add(ratingText);
        mainPanel.add(priceLabel); mainPanel.add(priceText);

        mainPanel.add(importCSV);
        mainPanel.add(addProductButton);
        mainPanel.add(modifyProductButton);
        mainPanel.add(removeProductButton);
        mainPanel.add(createCompositeProductButton);
        mainPanel.add(reportButton);

        scrollPane.setPreferredSize(new Dimension(725, 600));
        table.setBackground(Color.LIGHT_GRAY);
        tableModel = new DefaultTableModel(){
            public Class getColumnClass(int column){
                if(column == 7)
                    return Boolean.class;
                else
                    return String.class;
            }
        };

        tableModel.addColumn("Title");
        tableModel.addColumn("Rating");
        tableModel.addColumn("Calories");
        tableModel.addColumn("Protein");
        tableModel.addColumn("Fat");
        tableModel.addColumn("Sodium");
        tableModel.addColumn("Price");
        tableModel.addColumn("Select");

        //table.setPreferredSize(new Dimension(750, 400));


        table.setModel(tableModel);
        scrollPane.setViewportView(table);

        mainPanel.add(scrollPane);
        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void populateTable(List<List<String>> rows){

        tableModel.setRowCount(0);
       for(List<String> obj : rows){
            Object[] s = new Object[obj.size() + 1];
            int i = 0;
            for(String strings : obj){
                s[i] =  strings;
                i++;
            }
            s[i] = false;
            tableModel.addRow(s);
        }
    }

    public void populateTableWithObj(ArrayList<MenuItem> products){
        List<List<String>> listOfLists = new ArrayList<>();

        if(products.isEmpty()){
            System.out.println("nu exista produse");
            return;
        }

        for(MenuItem item : products){
            List<String> line = new ArrayList<>();

            line.add(item.getTitle());
            line.add(Double.toString(item.getRating()));
            line.add(Integer.toString(item.getCalories()));
            line.add(Integer.toString(item.getProteins()));
            line.add(Integer.toString(item.getFats()));
            line.add(Integer.toString(item.getSodium()));
            line.add(Integer.toString(item.getPrice()));

            listOfLists.add(line);
        }

        populateTable(listOfLists);
    }

    public void addProductInTable(ArrayList<String> strings){

        int i = 0;
        String[] s = new String[strings.size()];
        for(String str : strings){
            s[i] =  str;
            i++;
        }
        tableModel.addRow(s);
    }

    public List<String> getDataFromJTextField(){
        List<String> list = new ArrayList<>();

        list.add(titleText.getText());
        list.add(ratingText.getText());
        list.add(caloriesText.getText());
        list.add(proteinText.getText());
        list.add(fatText.getText());
        list.add(sodiumText.getText());
        list.add(priceText.getText());

        return list;
    }

    public int getIndexOfSelectedRow(){
        return table.getSelectedRow();
    }

    public ArrayList<Integer> getSelectedIndex(){
        ArrayList<Integer> index = new ArrayList<Integer>();
        for(int i = 0; i < tableModel.getRowCount(); i++){
            if(tableModel.getValueAt(i, 7).equals(true)){
                index.add(i);
            }
        }
        return index;
    }

    public String getTitle(){
        return titleText.getText();
    }

    public void open(){
        this.setTitle("Admin view");
        this.setVisible(true);
    }

    public void importCSVListener(ActionListener listener){
        importCSV.addActionListener(listener);
    }

    public void addAddProductListener(ActionListener listener){
        addProductButton.addActionListener(listener);
    }

    public void modifyProductListener(ActionListener listener){
        modifyProductButton.addActionListener(listener);
    }

    public void removeProductListener(ActionListener listener){
        removeProductButton.addActionListener(listener);
    }

    public void createCompositeProductListener(ActionListener listener){
        createCompositeProductButton.addActionListener(listener);
    }

    public void reportListener(ActionListener listener){
        reportButton.addActionListener(listener);
    }
}

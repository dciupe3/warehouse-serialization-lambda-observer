package presentation.view;

import business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientView extends JFrame {

    JTextField titleText = new JTextField();
    JTextField ratingText1 = new JTextField("0");
    JTextField ratingText2 = new JTextField("5");

    JTextField priceText1 = new JTextField("0");
    JTextField priceText2 = new JTextField("999999999");

    JTextField caloriesText1 = new JTextField("0");
    JTextField caloriesText2 = new JTextField("999999999");

    JTextField proteinText1 = new JTextField("0");
    JTextField proteinText2 = new JTextField("999999999");

    JTextField fatText1 = new JTextField("0");
    JTextField fatText2 = new JTextField("999999999");

    JTextField sodiumText1 = new JTextField("0");
    JTextField sodiumText2 = new JTextField("999999999");

    JLabel titleLabel = new JLabel("Title: ");
    JLabel ratingLabel = new JLabel("Rating range: ");
    JLabel priceLabel = new JLabel("Price range: ");
    JLabel caloriesLabel = new JLabel("Calories range: ");
    JLabel proteinLabel = new JLabel("Protein range: ");
    JLabel fatLabel = new JLabel("Fat range: ");
    JLabel sodiumLabel = new JLabel("Sodium range: ");
    JLabel label = new JLabel();
    JLabel label0 = new JLabel();

    JLabel label1 = new JLabel("-");
    JLabel label2 = new JLabel("-");
    JLabel label3 = new JLabel("-");
    JLabel label4 = new JLabel("-");
    JLabel label5 = new JLabel("-");
    JLabel label6 = new JLabel("-");

    JLabel label11 = new JLabel();
    JLabel label12 = new JLabel();
    JLabel label13 = new JLabel();
    JLabel label14 = new JLabel();
    JLabel label15 = new JLabel();
    JLabel label16 = new JLabel();
    JLabel label17 = new JLabel();
    JLabel label18 = new JLabel();
    JLabel label19 = new JLabel();

    JLabel label111 = new JLabel();
    JLabel label112 = new JLabel();


    JButton filterButton = new JButton("Filter");
    JButton createOrderButton = new JButton("Create new order");
    JPanel mainPanel;

    DefaultTableModel tableModel;
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();

    public ClientView(){
        setSize(800, 800);

        mainPanel = new JPanel();

        mainPanel.setLayout(new FlowLayout());

        label.setPreferredSize(new Dimension(230, 20));
        label0.setPreferredSize(new Dimension(260, 20));
        label11.setPreferredSize(new Dimension(40, 20));
        label12.setPreferredSize(new Dimension(40, 20));
        label13.setPreferredSize(new Dimension(40, 20));
        label14.setPreferredSize(new Dimension(45, 20));
        label15.setPreferredSize(new Dimension(40, 20));
        label16.setPreferredSize(new Dimension(40, 20));
        label17.setPreferredSize(new Dimension(60, 20));
        label18.setPreferredSize(new Dimension(40, 20));
        label19.setPreferredSize(new Dimension(80, 20));

        label111.setPreferredSize(new Dimension(60, 50));
        label112.setPreferredSize(new Dimension(120, 50));

        filterButton.setPreferredSize(new Dimension(150, 40));
        createOrderButton.setPreferredSize(new Dimension(150, 40));

        titleText.setPreferredSize(new Dimension(200, 20));
        caloriesText1.setPreferredSize(new Dimension(35, 20));
        caloriesText2.setPreferredSize(new Dimension(35, 20));

        proteinText1.setPreferredSize(new Dimension(35, 20));
        proteinText2.setPreferredSize(new Dimension(35, 20));

        fatText1.setPreferredSize(new Dimension(35, 20));
        fatText2.setPreferredSize(new Dimension(35, 20));


        sodiumText1.setPreferredSize(new Dimension(35, 20));
        sodiumText2.setPreferredSize(new Dimension(35, 20));

        ratingText1.setPreferredSize(new Dimension(35, 20));
        ratingText2.setPreferredSize(new Dimension(35, 20));

        priceText1.setPreferredSize(new Dimension(35, 20));
        priceText2.setPreferredSize(new Dimension(35, 20));


        mainPanel.add(label); mainPanel.add(titleLabel); mainPanel.add(titleText); mainPanel.add(label0);
        mainPanel.add(label17); mainPanel.add(caloriesLabel); mainPanel.add(caloriesText1); mainPanel.add(label1); mainPanel.add(caloriesText2); mainPanel.add(label11);
        mainPanel.add(proteinLabel); mainPanel.add(proteinText1); mainPanel.add(label2); mainPanel.add(proteinText2); mainPanel.add(label12);
        mainPanel.add(fatLabel); mainPanel.add(fatText1); mainPanel.add(label3);  mainPanel.add(fatText2);  mainPanel.add(label13);
        mainPanel.add(label19); mainPanel.add(sodiumLabel); mainPanel.add(sodiumText1); mainPanel.add(label4); mainPanel.add(sodiumText2); mainPanel.add(label14);
        mainPanel.add(ratingLabel); mainPanel.add(ratingText1); mainPanel.add(label5); mainPanel.add(ratingText2); mainPanel.add(label15);
        mainPanel.add(priceLabel); mainPanel.add(priceText1); mainPanel.add(label6); mainPanel.add(priceText2); mainPanel.add(label16);

        mainPanel.add(label111);
        mainPanel.add(filterButton);
        mainPanel.add(label112);
        mainPanel.add(createOrderButton);

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

    public void populateTable(java.util.List<java.util.List<String>> rows){

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

    public void populateTableWithObj(ArrayList<business.MenuItem> products){
        List<List<String>> listOfLists = new ArrayList<>();

        if(products.isEmpty()){
            System.out.println("nu exista produse");
            tableModel.setRowCount(0);
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

    public ArrayList<Integer> getCaloriesRange(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.parseInt(caloriesText1.getText()));
        arrayList.add(Integer.parseInt(caloriesText2.getText()));
        return arrayList;
    }

    public ArrayList<Integer> getProteinRange(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.parseInt(proteinText1.getText()));
        arrayList.add(Integer.parseInt(proteinText2.getText()));
        return arrayList;
    }

    public ArrayList<Integer> getFatRange(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.parseInt(fatText1.getText()));
        arrayList.add(Integer.parseInt(fatText2.getText()));
        return arrayList;
    }

    public ArrayList<Integer> getSodiumRange(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.parseInt(sodiumText1.getText()));
        arrayList.add(Integer.parseInt(sodiumText2.getText()));
        return arrayList;
    }

    public ArrayList<Integer> getPriceRange(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.parseInt(priceText1.getText()));
        arrayList.add(Integer.parseInt(priceText2.getText()));
        return arrayList;
    }

    public ArrayList<Double> getRatingRange(){
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(Double.parseDouble(ratingText1.getText()));
        arrayList.add(Double.parseDouble(ratingText2.getText()));
        return arrayList;
    }

    public String getTitle(){
        return titleText.getText();
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

    public void filterListener(ActionListener listener){
        filterButton.addActionListener(listener);
    }

    public void createOrderListener(ActionListener listener){
        createOrderButton.addActionListener(listener);
    }

    public void open(){
        this.setTitle("Client view");
        this.setVisible(true);
    }
}

package presentation;

import business.DeliveryService;
import business.MenuItem;
import business.Order;
import dataAccess.DataCSV;
import dataAccess.Serializator;
import dataAccess.repository.UsersRepository;
import model.User;
import presentation.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private AdminView adminView;
    private ClientView clientView;
    private LogInView logInView;
    private DataCSV dataCSV;
    private DeliveryService deliveryService;
    private ReportView reportView;
    private User actualUser;
    private EmployeeView employeeView;

    ArrayList<MenuItem> listOfProducts = new ArrayList<>();

    UsersRepository usersRepository;

    Serializator serializator;

    public Controller(){
        logInView = new LogInView();
        clientView = new ClientView();
        adminView = new AdminView();
        reportView = new ReportView();
        employeeView = new EmployeeView();

        usersRepository = new UsersRepository();

        deliveryService = new DeliveryService();
        serializator = new Serializator(usersRepository, deliveryService);
        dataCSV = new DataCSV(); //pot pune path-ul

        //serializatorul uploadeaza

        logInView.open();

        //int lastID = usersRepository.getAll().get(usersRepository.getAll().size() - 1).getId();
        //User userDePomana = new User(); userDePomana.setId_static(lastID - 1);

        logInView.addLoginListener(new LogInListener());
        logInView.addNewUserListener(new CreateUserListener());

        adminView.importCSVListener(new ImportCSVListener());
        adminView.addAddProductListener(new AddProductListener());
        adminView.modifyProductListener(new ModifyProductListener());
        adminView.removeProductListener(new RemoveProductListener());
        adminView.createCompositeProductListener(new CreateCompositeProductListener());
        adminView.reportListener(new ReportListener());

        clientView.filterListener(new FilterProducts());
        clientView.createOrderListener(new CreateOrderListener());

        reportView.addReport1Listener(new Report1Listener());
        reportView.addReport2Listener(new Report2Listener());
        reportView.addReport3Listener(new Report3Listener());
        reportView.addReport4Listener(new Report4Listener());
    }

    class LogInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                serializator.doUserDeserialization();

                deliveryService.users = (ArrayList<User>) serializator.getUsersRepository().getAll(); //???
                deliveryService.addObservable();  // ???

                serializator.doMenuDeserialization();
                serializator.doOrdersDeserialization();
                listOfProducts = deliveryService.getMenuItems();
                for (User u : usersRepository.getAll()) {
                    //System.out.println(u.getId() + " " + u.getTypeUser() + " " + u.getUsername() + " " + u.getPassword());
                    if(u.getUsername().equals(logInView.getUsername()) && u.getPassword().equals(logInView.getPassword())){
                        logInView.setVisible(false);
                        actualUser = u;
                        if(u.getTypeUser().equals("admin")){
                            adminView.populateTableWithObj(deliveryService.getMenuItems());
                            System.out.println("ADMIN");
                            adminView.open();
                        } else if(u.getTypeUser().equals("client")){
                            clientView.populateTableWithObj(deliveryService.getMenuItems());
                            System.out.println("CLIENT");
                            clientView.open();
                        } else if(u.getTypeUser().equals("employee")){
                            employeeView.open();
                            /*for(User us : deliveryService.users){
                                if(us.getTypeUser().equals("employee")) {
                                    for(Order or : us.orders){
                                        employeeView.write("Order nr : " + or.hashCode() + " price : " + or.getPrice() + "$ made by user: " + or.getUser() + " date : " + or.getDate().toString());
                                    }
                                }
                            }*/

                            for(Order or : u.orders){
                                employeeView.write("Order nr : " + or.hashCode() + " price : " + or.getPrice() + "$ made by user: " + or.getUser().getUsername() + " date : " + or.getDate().toString());
                            }
                            System.out.println("EMPLOYEEE");
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class CreateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                serializator.doUserDeserialization();

                deliveryService.users = (ArrayList<User>) serializator.getUsersRepository().getAll(); //???
                deliveryService.addObservable();  // ???

                User user = new User(logInView.getUsername(), logInView.getPassword(), logInView.getTypeUser());

                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                System.out.println(user.getTypeUser());

                usersRepository.add(user);
                serializator.doUsersSerialization();

                //trebuie sa il salvez

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ImportCSVListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //fasjfkaf
                JFileChooser jFileChooser = new JFileChooser();
                int result = jFileChooser.showOpenDialog(adminView);
                File f = jFileChooser.getSelectedFile();
                String p = f.getPath();
                //dhjasjfbhjqwf

                List<List<String>> listOfLists = dataCSV.getDataCSV();
                deliveryService.removeAllProducts();
                deliveryService.createMenuFromString(listOfLists);
                adminView.populateTable(listOfLists);
                serializator.doMenuSerialization();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<String> list = adminView.getDataFromJTextField();
                deliveryService.addMenuItemToMenu(deliveryService.createProductFromString(list));
                serializator.doMenuSerialization();
                adminView.populateTableWithObj(deliveryService.getMenuItems());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ModifyProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int index = adminView.getIndexOfSelectedRow();
                List<String> list = adminView.getDataFromJTextField();
                deliveryService.modifyProduct(index, list);
                serializator.doMenuSerialization();
                adminView.populateTableWithObj(deliveryService.getMenuItems());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class RemoveProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int index = adminView.getIndexOfSelectedRow();
                deliveryService.removeProduct(index);
                serializator.doMenuSerialization();
                adminView.populateTableWithObj(deliveryService.getMenuItems());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class CreateCompositeProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<Integer> index = adminView.getSelectedIndex();
                String title = adminView.getTitle();
                deliveryService.addMenuItemToMenu(deliveryService.createCompositeProduct(index, title));

                serializator.doMenuSerialization();

                adminView.populateTableWithObj(deliveryService.getMenuItems());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class ReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                reportView.open();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    //Client
    class FilterProducts implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                ArrayList<MenuItem> list = deliveryService.getFilteredList(clientView.getTitle(), clientView.getCaloriesRange(), clientView.getProteinRange(),
                        clientView.getFatRange(), clientView.getSodiumRange(), clientView.getRatingRange(), clientView.getPriceRange());
                listOfProducts = list;
                clientView.populateTableWithObj(list);
                //clientView.populateTableWithObj(listOfProducts);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class CreateOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                ArrayList<Integer> index = clientView.getSelectedIndex();
                ArrayList<MenuItem> selectedProducts = deliveryService.getSelectedProductsFromList(listOfProducts, index);
                deliveryService.addNewOrder(selectedProducts, actualUser);
                serializator.doOrdersSerialization();
                serializator.doMenuSerialization();
                serializator.doUsersSerialization();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    //REPORT

    class Report1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<Integer> start = reportView.getStartDate();
                ArrayList<Integer> finish = reportView.getFinishDate();
                //System.out.println(start + "\n" + finish);
                List<List<String>> listOflists = deliveryService.getReport1Data(start, finish);
                reportView.populateTable1(listOflists);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class Report2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int nr = reportView.getReport2Data();

                ArrayList<MenuItem> p = deliveryService.getMenuItems();
                /*for(MenuItem i : p){
                    System.out.println(i.getContorOrder());
                }*/

                ArrayList<MenuItem> productsList = deliveryService.getReport2Products(nr);
                reportView.populateTableReport2(productsList);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class Report3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList<Integer> listInteger = reportView.getReport3Data();

                List<List<String>> listOfLists = deliveryService.getReport3Data(listInteger);
                reportView.populateTable3(listOfLists);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    class Report4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String str = reportView.getDate4();
                List<List<String>> listOflists = deliveryService.getReport4Data(str);
                reportView.populateTable4(listOflists);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}

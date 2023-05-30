package dataAccess;

import business.MenuItem;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DataCSV {

    Path path;

    public DataCSV(){
        path = Paths.get("C:\\PT2022_30221_Ciupe_David_Robert_Assignment_4\\products.csv");
    }

    public List<List<String>> getDataCSV(){
        System.out.println("CSV imported");


        List<List<String>> listOfLists = new ArrayList<>();

        if(Files.exists(path)){

            try{
              Stream<String> stream = Files.lines(path);
              //stream.skip(1).limit(10).forEach(System.out::println); //trebuie sa scot limita
              //ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
              //ArrayList<String> line = stream.skip(1).limit(10).collect(Collectors.toCollection(ArrayList::new));

              stream.skip(1).forEach(line -> {
                  List<String> innerList = new ArrayList<>(Arrays.asList(line.split(",")));

                  boolean seRepeta = false;
                  for(List<String> ss : listOfLists){
                      if(ss.get(0).equals(innerList.get(0)))
                          seRepeta = true;
                  }

                  if(seRepeta == false)
                     listOfLists.add(innerList);
              });

            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
        }

        return listOfLists;
    }

}

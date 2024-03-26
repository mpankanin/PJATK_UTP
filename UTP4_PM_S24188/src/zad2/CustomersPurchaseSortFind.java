/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {

    List<Purchase> database = new ArrayList<>();

    public void readFile(String path){

        try {
            Files.readAllLines(Paths.get(path)).forEach(s -> database.add(new Purchase(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String s){

        switch (s) {
            case "Nazwiska" -> {
                List<Purchase> tmp = new ArrayList<>(database);
                sortByNames(tmp);
                print("Nazwiska", tmp);
            }
            case "Koszty" -> {
                List<Purchase> tmp = new ArrayList<>(database);
                sortByPrices(tmp);
                print("Koszty", tmp);
            }
        }

    }

    public void showPurchaseFor(String id){
        print("Klient " + id, database.stream().filter(p -> p.getIdCustomer().equals(id)).collect(Collectors.toList()));
    }

    public void sortByNames(){
        database.sort(Comparator.comparing(Purchase::getName).thenComparing(Purchase::getIdCustomer));
    }

    public void sortByNames(List<Purchase> list){
        list.sort(Comparator.comparing(Purchase::getName).thenComparing(Purchase::getIdCustomer));
    }

    public void sortByPrices(){
        database.sort(Comparator.comparingDouble(Purchase::getDoubleSum).reversed().thenComparing(Purchase::getIdCustomer));
    }

    public void sortByPrices(List<Purchase> list){
        list.sort(Comparator.comparingDouble(Purchase::getDoubleSum).reversed().thenComparing(Purchase::getIdCustomer));
    }

    public void print(String str){
        System.out.println(str);

        if(str.equals("Koszty"))
            for(Purchase p : database)
                System.out.println(p.toString() + " (koszt: " + p.getSum() + ')');
        else
            for(Purchase p : database)
                System.out.println(p.toString());

    }

    public void print(String str, List<Purchase> list){
        System.out.println('\n' + str);

        /*
        for(Purchase p : list)
            System.out.println(p.toString());

         */

        if(str.equals("Koszty"))
            for(Purchase p : list)
                System.out.println(p.toString() + " (koszt: " + p.getSum() + ')');
        else
            for(Purchase p : list)
                System.out.println(p.toString());


    }
}

/**
 *
 *  @author Pankanin Miłosz S24188
 *
 */

package zad2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Purchase {

    private final String idCustomer, name, productName, price, amount, sum;

    public List<String> details = new ArrayList<>();

    public Purchase(String s){
        details.addAll(Arrays.asList(s.split(";")));
        idCustomer = details.get(0);
        name = details.get(1);
        productName = details.get(2);
        price = details.get(3);
        amount = details.get(4);
        sum = String.valueOf(Float.parseFloat(amount) * Float.parseFloat(price));
    }

    public Purchase(String idCustomer, String name, String productName, String price, String amount){
        this.idCustomer = idCustomer;
        this.name = name;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.sum = String.valueOf(Float.parseFloat(amount) * Float.parseFloat(price));
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getName() {
        return name;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getAmount() {
        return amount;
    }

    public String getSum(){
        return sum;
    }

    public double getDoubleSum(){
        return Double.parseDouble(sum);
    }

    @Override
    public String toString() {
        return idCustomer + ';' + name + ';' + productName + ';' + price + ';' + amount;
    }
}

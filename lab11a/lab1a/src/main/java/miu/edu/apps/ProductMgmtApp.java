package miu.edu.apps;


import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMgmtApp {
    public static void main(String[] args) {
        Product products [] = Product.getData();
        printProducts(products);

    }

    static void printProducts(Product[] products){
        List<Product> productList = Arrays.stream(products)
                        .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
        printJSON(productList);
        printXML(productList);
        printCSV(productList);
    }

    public static void printJSON(List<Product> products){
        System.out.println("JSON format:\n"+new Gson().toJson(products));
    }

    public static void printCSV(List<Product> products){
        System.out.println("CSV format\n");
        for (Product product : products) {
            System.out.printf("%s, %s, %s, %s, %s \n", product.getProductId(),product.getName(), product.getDateSupplied(), product.getQuantityInStock(), product.getUnitPrice());
        }
    }

    public static void printXML(List<Product> products){
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<Products>\n\t");
        for (Product product : products) {
            xmlBuilder.append("<Product>\n\t");
            xmlBuilder.append(String.format("<productId>%s</productId/>", product.getProductId()));
            xmlBuilder.append(String.format("<name>%s</name/>", product.getName()));
            xmlBuilder.append(String.format("<dateSupplied>%s</dateSupplied/>", product.getDateSupplied()));
            xmlBuilder.append(String.format("<quantityInStock>%s</quantityInStock/>", product.getQuantityInStock()));
            xmlBuilder.append(String.format("<unitPrice>%s</unitPrice/>\n", product.getUnitPrice()));
            xmlBuilder.append("</Product>\n\t");
        }
        xmlBuilder.append("</Products>");
        System.out.println("XML format:\n"+xmlBuilder.toString());
    }

}

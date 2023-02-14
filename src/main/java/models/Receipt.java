package models;

import java.util.List;

public class Receipt {
    private String name;
    private boolean domestic;
    private double price;
    private String weight;
    private String description;

    public Receipt() {
        this.weight="N/A";
    }

    public Receipt(String name, boolean domestic, double price, String weight, String description) {
        this.name = name;
        this.domestic = domestic;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWeight() {
        if (weight.equals("N/A"))
            return weight;
        else
            return weight +"g";
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        if (description.length()<=10)
            return description;
        else
            return description.substring(0,10) + "...";

    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", domestic=" + domestic +
                ", price=" + price +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                '}';
    }
    public static String printDetailsDomestic(List<Receipt> receiptList){
        StringBuilder sb=new StringBuilder();
        sb.append(". Domestic").append("\n");
        return getItems(receiptList, sb);
    }
    public static String printDetailsImported(List<Receipt> receiptList){
        StringBuilder sb=new StringBuilder();
        sb.append(". Imported").append("\n");
        return getItems(receiptList, sb);
    }

    private static String getItems(List<Receipt> receiptList, StringBuilder sb) {
        for (Receipt r: receiptList) {
            sb.append("... ").append(r.getName()).append("\n")
                    .append("    Price: ").append("$").append(r.getPrice()).append("\n")
                    .append("    ").append(r.getDescription()).append("\n")
                    .append("    Weight: ").append(r.getWeight()).append("\n");
        }
        return sb.toString();
    }
    public static String getCost(List<Receipt> receiptList){
        double sum= receiptList.stream().mapToDouble(Receipt::getPrice).sum();
        return String.format("%.1f",sum);
    }
}

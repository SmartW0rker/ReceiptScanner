import com.fasterxml.jackson.databind.ObjectMapper;
import models.Receipt;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Receipt> receiptList=getReceipts();

        List<Receipt> domesticReceipt=new ArrayList<>();
        List<Receipt> importedReceipt=new ArrayList<>();

        splitReceiptsIntoDomesticAndImported(domesticReceipt,importedReceipt,receiptList);

        printDetails(domesticReceipt,importedReceipt);

    }

    private static void printDetails(List<Receipt> domesticReceipt, List<Receipt> importedReceipt) {

        System.out.print(Receipt.printDetailsDomestic(domesticReceipt));

        System.out.print(Receipt.printDetailsImported(importedReceipt));

        System.out.println("Domestic cost: $"+Receipt.getCost(domesticReceipt));
        System.out.println("Imported cost: $"+Receipt.getCost(importedReceipt));

        System.out.println("Domestic count: "+domesticReceipt.size());
        System.out.println("Imported count: "+importedReceipt.size());

    }

    private static void splitReceiptsIntoDomesticAndImported(List<Receipt> domesticReceipt, List<Receipt> importedReceipt, List<Receipt> receiptList) {
        for (Receipt receipt:receiptList) {
            if (receipt.isDomestic())
                domesticReceipt.add(receipt);
            else
                importedReceipt.add(receipt);
        }
        domesticReceipt.sort(Comparator.comparing(Receipt::getName));
        importedReceipt.sort(Comparator.comparing(Receipt::getName));

    }

    private static List<Receipt> getReceipts() throws IOException {

        Receipt[] receipts = new ObjectMapper().readValue(new URL("https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1"),Receipt[].class);
        return List.of(receipts);
    }
}

package dojo.supermarket;

import dojo.supermarket.model.*;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class ReceiptPrinterTest {

    Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
    Product apples = new Product("apples", ProductUnit.KILO);
    Receipt receipt = new Receipt();

    @Test
    public void oneLineItem() {
        receipt.receiptItems.addProduct(toothbrush, 1, 0.99, 0.99);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void quantityTwo() {
        receipt.receiptItems.addProduct(toothbrush, 2, 0.99,0.99 * 2);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void looseWeight() {
        receipt.receiptItems.addProduct(apples, 2.3, 1.99,1.99 * 2.3);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void total() {

        receipt.receiptItems.addProduct(toothbrush, 1, 0.99, 2*0.99);
        receipt.receiptItems.addProduct(apples, 0.75, 1.99, 1.99*0.75);
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void discounts() {
        receipt.discounts.addDiscount(new Discount(apples, "3 for 2", -0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

    @Test
    public void printWholeReceipt() {
        receipt.receiptItems.addProduct(toothbrush, 1, 0.99, 0.99);
        receipt.receiptItems.addProduct(toothbrush, 2, 0.99, 2*0.99);
        receipt.receiptItems.addProduct(apples, 0.75, 1.99, 1.99*0.75);
        receipt.discounts.addDiscount(new Discount(toothbrush, "3 for 2", -0.99));
        Approvals.verify(new ReceiptPrinter(40).printReceipt(receipt));
    }

}

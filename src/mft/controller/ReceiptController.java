package mft.controller;

import mft.model.entity.Receipt;
import mft.model.service.ReceiptService;

import java.util.regex.Pattern;

public class ReceiptController {

    public static void save(int id, int amount, String description) {
        System.out.println("ReceiptController - Save");
        try {
            if (ValidatorReceipt.amountValidator(amount) &&
                    (Pattern.matches("^[a-zA-Z]+$", description))
            ) {
                Receipt receipt =
                        Receipt
                                .builder()
                                .id(id)
                                .amount(amount)
                                .description(description)
                                .build();
                ReceiptService.save(receipt);
                System.out.println("Receipt Saved : " + receipt.toString());
            } else {
                System.out.println("Invalid Data");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }



    }

}





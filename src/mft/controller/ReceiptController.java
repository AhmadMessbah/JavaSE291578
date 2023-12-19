package mft.controller;

import mft.model.entity.Receipt;
import mft.model.repository.ReceiptDa;

import java.util.regex.Pattern;

public class ReceiptController {

    public static Receipt save(String amount, String description) {
        try {
            if (Pattern.matches("^[1-9]$",amount) &&
                    (Pattern.matches("^[a-zA-Z]$", description))) {
                Receipt recepit = Receipt.builder().amount(amount).description(description).build();

ReceiptDa receiptDa = new ReceiptDa();
receiptDa.save(recepit);
                return recepit;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    return save(amount, description);
    }
    }





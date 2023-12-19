package mft.model.service;

import mft.model.entity.Receipt;
import mft.model.repository.ReceiptRepository;

public class ReceiptService {

    public static void  save(Receipt receipt) throws Exception {
        System.out.println("ReceiptService - Save");
        ReceiptRepository repository = new ReceiptRepository();
        if (repository.findByDescription(receipt.getDescription()) == null) {
            //      if nationalCode Unique
            repository.save(receipt);
    } else {

            throw new Exception();
    }
        }
    }



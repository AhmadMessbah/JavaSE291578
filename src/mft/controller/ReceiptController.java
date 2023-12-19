package mft.controller;

import mft.model.entity.Receipt;
import mft.model.service.ReceiptService;

import java.util.List;

public class ReceiptController {

    private static ReceiptController controller = new ReceiptController();

    private ReceiptController() {
    }

    public static ReceiptController getController() {
        return controller;
    }


    public Receipt save(int amount, String description) {
        try {
            Receipt receipt =
                    Receipt
                            .builder()
                            .amount(amount)
                            .description(description)
                            .build();
            System.out.println(receipt);
            ReceiptService.getService().save(receipt);
            return receipt;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Receipt edit(Integer id, int amount, String description) {
        try {
            Receipt receipt =
                    Receipt
                            .builder()
                            .id(id)
                            .amount(amount)
                            .description(description)
                            .build();
            System.out.println(receipt);
            ReceiptService.getService().edit(receipt);
            return receipt;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Receipt remove(Integer id) {
        try {
            Receipt receipt = ReceiptService.getService().findById(id);
            ReceiptService.getService().remove(id);
            return receipt;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Receipt> findAll() {
        try {
            return ReceiptService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Receipt findById(Integer id) {
        try {
            return ReceiptService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;

    }

    public Receipt findByAmount(Integer amount) {
        try {
            return ReceiptService.getService().findByAmount(amount);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }
        public Receipt findByDescription (String description){
            try {
                return ReceiptService.getService().findByDescription(description);
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
            return null;
        }
    }








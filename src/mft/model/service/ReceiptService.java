package mft.model.service;




import lombok.Getter;
import mft.controller.exception.DuplicateDescriptionException;
import mft.model.entity.Receipt;
import mft.model.repository.ReceiptRepository;


public class ReceiptService {
    @Getter
    private static final ReceiptService service = new ReceiptService();

    private ReceiptService() {
    }

    public  void  save(Receipt receipt) throws Exception, DuplicateDescriptionException {
        System.out.println("ReceiptService - Save");
        ReceiptRepository repository = new ReceiptRepository();
        if (repository.findByDescription(receipt.getDescription()) == null) {
            //      if nationalCode Unique
            repository.save(receipt);
        } else {

            throw new DuplicateDescriptionException();
        }
    }
}


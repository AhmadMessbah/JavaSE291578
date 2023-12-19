package mft.model.service;
import lombok.Getter;
import mft.controller.exception.DuplicateDescriptionException;
import mft.model.entity.Receipt;
import mft.model.repository.ReceiptRepository;

import java.util.List;

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
        }        }
        public Receipt edit(Receipt receipt) throws Exception {
            try (ReceiptRepository repository = new ReceiptRepository()) {
                return  repository.edit(receipt);
            }
        }

        public Receipt remove(int id) throws Exception {
            try (ReceiptRepository repository = new ReceiptRepository()) {
                return  repository.remove(id);            }
        }

        public List<Receipt> findAll() throws Exception {
            try (ReceiptRepository repository = new ReceiptRepository()) {
                return  repository.findAll();            }
        }

        public Receipt findById(int id) throws Exception {
            try (ReceiptRepository repository = new ReceiptRepository()) {
                return repository.findById(id)            ;
            }
        }

    public Receipt findByAmount(int amount) throws Exception {
        try (ReceiptRepository repository = new ReceiptRepository()) {
            return repository.findByAmount(amount) ;
        }
    }

    public Receipt findByDescription(String description) throws Exception {
        try (ReceiptRepository repository = new ReceiptRepository()) {
            return repository.findByDescription(description);
        }
    }
}



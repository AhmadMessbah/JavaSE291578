package mft.model.service;

import mft.model.entity.Borrow;
import mft.model.repository.BorrowDa;

import java.util.List;

public class BorrowService {
    private static  BorrowService service = new BorrowService();

    private BorrowService() {
    }

    public static BorrowService getService() {
        return service;
    }

    public Borrow save(Borrow borrow) throws Exception{
        try(BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.save(borrow);
        }
    }

    public Borrow edit(Borrow borrow) throws Exception{
        try(BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.edit(borrow);
        }
    }

    public Borrow remove(int id) throws Exception{
        try(BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.remove(id);
        }
    }

    public List<Borrow> findAll(Borrow borrow) throws Exception{
        try(BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findAll();
        }
    }

    public Borrow findById(int id) throws Exception{
        try(BorrowDa borrowDa = new BorrowDa()) {
            return borrowDa.findById(id);
        }
    }
}

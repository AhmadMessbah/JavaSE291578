package mft.model.service;

import mft.model.entity.Borrow;
import mft.model.repository.BorrowDa;

public class BorrowService {
    public static void save(Borrow borrow) throws Exception{
        BorrowDa borrowDa = new BorrowDa();
        borrowDa.save(borrow);
    }
}

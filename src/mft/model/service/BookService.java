package mft.model.service;

import mft.model.entity.Book;
import mft.model.entity.User;
import mft.model.repository.BookDa;
import mft.model.repository.UserDa;

public class BookService {
        public static void save(Book book) throws Exception {
            System.out.println("UserService - Save");
            BookDa bookDa= new BookDa();
            if (bookDa.findById(book.getId())==null){
                bookDa.save(book);
            }else {
                throw new Exception();
            }

        }


}

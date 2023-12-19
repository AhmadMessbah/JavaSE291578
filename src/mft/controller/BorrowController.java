package mft.controller;

import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Person;
import mft.model.service.BorrowService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BorrowController {
    private static BorrowController borrowController = new BorrowController();

    private BorrowController() {
    }

    public static BorrowController getBorrowController() {
        return borrowController;
    }

    public Borrow save(Person person, Book book, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) {
        try {
            Borrow borrow = Borrow
                    .builder()
                    .person(Person
                            .builder()
                            .id(person.getId())
                            .name(person.getName())
                            .family(person.getFamily())
                            .birthDate(person.getBirthDate())
                            .gender(person.getGender())
                            .active(person.isActive())
                            .build())
                    .book(Book
                            .builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .build())
                    .borrowTimeStamp(Timestamp.valueOf(borrowTimeStamp).toLocalDateTime())
                    .returnTimeStamp(Timestamp.valueOf(returnTimeStamp).toLocalDateTime())
                    .build();
            System.out.println("controller");
            BorrowService.getService().save(borrow);
            return borrow;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Borrow edit(Integer id, Person person, Book book, LocalDateTime borrowTimeStamp, LocalDateTime returnTimeStamp) {
        try {
            Borrow borrow = Borrow
                    .builder()
                    .id(id)
                    .person(Person
                            .builder()
                            .id(person.getId())
                            .name(person.getName())
                            .family(person.getFamily())
                            .birthDate(person.getBirthDate())
                            .gender(person.getGender())
                            .active(person.isActive())
                            .build())
                    .book(Book
                            .builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .build())
                    .borrowTimeStamp(Timestamp.valueOf(borrowTimeStamp).toLocalDateTime())
                    .returnTimeStamp(Timestamp.valueOf(returnTimeStamp).toLocalDateTime())
                    .build();
            BorrowService.getService().edit(borrow);
            return borrow;
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Borrow remove(int id) {
        try {
            BorrowService.getService().findById(id);
            return BorrowService.getService().remove(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Borrow> findAll() {
        try {
            return BorrowService.getService().findAll();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Borrow findById(int id) {
        try {
            return BorrowService.getService().findById(id);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Borrow> findByPersonId(int personId) {
        try {
            return BorrowService.getService().findByPersonId(personId);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }
}

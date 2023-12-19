package mft.controller.exception;

import java.sql.SQLException;

public class ExceptionWrapper {
        public static String getMessage(Exception e) {
            if (e instanceof SQLException) {
                return "Error : in database connection\n" + e.getMessage();
            } else if (e instanceof ArithmeticException) {
                return "Error : in calculations\n" + e.getMessage();
            } else if (e instanceof NullPointerException) {
                return "Error : null value\n" + e.getMessage();
            } else if (e instanceof DuplicateDescriptionException) {
                    return "Error : Duplicate Description";
            }else {
                return "Error : Unknown error - call admin";
            }
        }
    }



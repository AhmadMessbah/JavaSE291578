package mft.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString

public class Borrow {
    private int id;
    private Person person;
    private Book book;
    private LocalDateTime borrowTimeStamp;
    private LocalDateTime returnTimeStamp;

}

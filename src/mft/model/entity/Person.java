package mft.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.Gender;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString


public class Person {
    private int id;
    private String name;
    private String family;
    private LocalDate birthDate;
    private Gender gender;
    private boolean active;
}

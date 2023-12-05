package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString

public class User {
    private int id;

    @Setter(AccessLevel.NONE)
    private String username;

    private String password;

    private boolean active;
}

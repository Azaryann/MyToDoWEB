package am.azaryan.mytodoweb.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
}


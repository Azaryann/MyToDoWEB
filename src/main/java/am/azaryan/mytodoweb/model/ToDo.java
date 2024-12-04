package am.azaryan.mytodoweb.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    private int id;
    private String title;
    private Date createdDate;
    private Date finishDate;
    private User user;
    private ToDoStatus status;
}

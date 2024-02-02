package sem2.crud.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode
@Component
public class User {
    private int id;
    private String firstName;
    private String lastName;
}

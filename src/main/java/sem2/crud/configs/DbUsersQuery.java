package sem2.crud.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "db.users")
public class DbUsersQuery {
    private  String findAll;
    private  String findUserById;
    private  String save;
    private  String update;
    private  String delete;

}

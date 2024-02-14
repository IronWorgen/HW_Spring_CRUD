package sem2.crud.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "db.archive")
public class DbArchiveQuery {
    private String saveUser;
    private String createTableUsers;
}

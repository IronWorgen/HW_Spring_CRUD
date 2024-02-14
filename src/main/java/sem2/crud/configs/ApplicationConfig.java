package sem2.crud.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "my.app")
@Component
@Getter
@Setter
public class ApplicationConfig {

    private boolean enableArchiveDB ;



}

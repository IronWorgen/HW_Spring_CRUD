package sem2.crud.repositories;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import sem2.crud.model.User;

import javax.sql.DataSource;
import java.net.ConnectException;
import java.sql.SQLException;

/**
 * репозиторий позволяющий работать с базой данных MYSQL
 */
@Repository
public class ArchiveRepository {

    private final JdbcTemplate jdbcTemplate;

    public ArchiveRepository() {

        DataSource dataSource = DataSourceBuilder.create().username("root")
                .password("password")
                .url("jdbc:mysql://localhost:8086/Users")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * сохранить пользователя в базе данных MySQL
     *
     * @param user - пользователь
     */
    public void saveUser(User user) {
        String sql = "INSERT INTO users (firstName, lastname) values(?, ?)";
        try {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
        } catch (RuntimeException e) {
            try {
                createTableUsers();
                jdbcTemplate.update(sql, user.getFirstName(), user.getLastName());
            } catch (RuntimeException t) {
                System.out.println("не удается подключиться к базе данных MySql");
            }
        }
    }


    /**
     * если в базе данных нет таблицы `users` создать ее
     */
    private void createTableUsers() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "    id INT AUTO_INCREMENT PRIMARY KEY," +
                "    firstName varchar(50) NOT NULL," +
                "    lastName varchar(50) NOT NULL" +
                ");";
        jdbcTemplate.update(sql);
    }
}

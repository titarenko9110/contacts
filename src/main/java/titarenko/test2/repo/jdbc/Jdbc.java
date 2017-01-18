package titarenko.test2.repo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.Connection;

import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;
import titarenko.test2.domain.Contact;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MyMac on 10.10.16.
 */
@Repository
public class Jdbc extends JdbcDaoSupport {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<Contact> filter(String regex, List<Contact> fromDb) {
        return fromDb.stream().filter(s -> !s.getName().matches(regex)).collect(Collectors.toList());
    }

    public List<Contact> findByCustomerId(String regex) {
        List<Contact> output = new ArrayList<>();
        String sql = "select id, name FROM contacts";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            st.setFetchSize(10000);
            ResultSet rs = st.executeQuery(sql);
            System.out.println(rs.getRow());
            long t1 = System.currentTimeMillis();
            while (rs.next()) {
                Contact contact = new Contact();
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                if (!name.matches(regex)) {
                    contact.setId(id);
                    contact.setName(name);
                    output.add(contact);
                }
            }

            System.out.println("Time to iterate ResultSet -> " + (System.currentTimeMillis() - t1));
            rs.close();
            st.close();
            return output;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}

package titarenko.test2.repo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import titarenko.test2.domain.Contact;
import titarenko.test2.repo.ContactRepo;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by MyMac on 28.09.16.
 */
@Repository
public class JdbcContactRepoImpl extends JdbcDaoSupport implements ContactRepo {

    private static final BeanPropertyRowMapper<Contact> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Contact.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Integer getCount() {
        String sql = "SELECT Count(*) FROM contacts";
        Integer total = getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }

    @Override
    public List<Contact> getContactsPart(Integer from, Integer to) {
        return getJdbcTemplate().query("select id, name FROM contacts where id > ? and id < ?", ROW_MAPPER, from, to);
    }



}

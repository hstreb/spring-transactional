package org.sample.dao;

import org.sample.entity.AddressEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AddressDao {

    private static final Logger log = LoggerFactory.getLogger(AddressDao.class);
    private static final String TABLE_NAME = "address";
    private static final String COLUMN_PERSON_ID = "person_id";
    private static final String COLUMN_STREET = "street";
    private static final String COLUMN_NUMBER = "number";
    private static final String COLUMN_ADDON = "add_on";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
        jdbcTemplate.execute("DROP TABLE address IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE address(person_id bigint PRIMARY KEY not null, street VARCHAR(255) not null, " +
                "number int, add_on VARCHAR(255))");
    }

    public AddressEntity save(AddressEntity address) {
        log.info("Saving {}", address);
        int rows = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(COLUMN_PERSON_ID, COLUMN_STREET, COLUMN_NUMBER, COLUMN_ADDON)
                .execute(getParams(address));
        log.info("{} row(s) afected", rows);
        return address;
    }

    private Map<String, Object> getParams(AddressEntity address) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(COLUMN_PERSON_ID, address.getPersonId());
        parameters.put(COLUMN_STREET, address.getStreet());
        parameters.put(COLUMN_NUMBER, address.getNumber());
        parameters.put(COLUMN_ADDON, address.getAddOn().orElse(null));
        return parameters;
    }

    public List<AddressEntity> list(Long personId) {
        String sql = "select person_id, street, number, add_on from address where person_id = ?";
        return jdbcTemplate.query(sql,
                new Object[]{personId},
                (rs, rowNum) -> new AddressEntity(rs.getLong(COLUMN_PERSON_ID),
                        rs.getString(COLUMN_STREET),
                        rs.getInt(COLUMN_NUMBER),
                        Optional.ofNullable(rs.getString(COLUMN_ADDON))));
    }
}

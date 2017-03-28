package org.sample.dao;

import org.sample.entity.PhoneEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PhoneDao {

    private static final Logger log = LoggerFactory.getLogger(PhoneDao.class);
    private static final String TABLE_NAME = "phone";
    private static final String COLUMN_PERSON_ID = "person_id";
    private static final String COLUMN_NUMBER = "number";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
        jdbcTemplate.execute("DROP TABLE phone IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE phone(person_id bigint PRIMARY KEY not null, number VARCHAR(255) not null)");
    }

    public PhoneEntity save(PhoneEntity phone) {
        log.info("Saving {}", phone);
        int rows = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(COLUMN_PERSON_ID, COLUMN_NUMBER)
                .execute(getParams(phone));
        log.info("{} row(s) afected", rows);
        return phone;
    }

    private Map<String, Object> getParams(PhoneEntity phone) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(COLUMN_PERSON_ID, phone.getPersonId());
        parameters.put(COLUMN_NUMBER, phone.getNumber());
        return parameters;
    }

    public List<PhoneEntity> list(Long personId) {
        String sql = "select person_id, number from phone where person_id = ?";
        return jdbcTemplate.query(sql,
                new Object[]{personId},
                (rs, rowNum) -> new PhoneEntity(rs.getLong(COLUMN_PERSON_ID), rs.getString(COLUMN_NUMBER)));
    }
}

package org.sample.dao;

import org.sample.entity.EmailEntity;
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
public class EmailDao {

    private static final Logger log = LoggerFactory.getLogger(EmailDao.class);
    private static final String TABLE_NAME = "email";
    private static final String COLUMN_PERSON_ID = "person_id";
    private static final String COLUMN_EMAIL = "email";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
        jdbcTemplate.execute("DROP TABLE email IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE email(person_id bigint PRIMARY KEY not null, email VARCHAR(255) not null)");
    }

    public EmailEntity save(EmailEntity email) {
        log.info("Saving {}", email);
        int rows = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(COLUMN_PERSON_ID, COLUMN_EMAIL)
                .execute(getParams(email));
        log.info("{} row(s) afected", rows);
        return email;
    }

    private Map<String, Object> getParams(EmailEntity email) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(COLUMN_PERSON_ID, email.getPersonId());
        parameters.put(COLUMN_EMAIL, email.getEmail());
        return parameters;
    }

    public List<EmailEntity> list(Long personId) {
        String sql = "select person_id, email from email where person_id = ?";
        return jdbcTemplate.query(sql,
                new Object[]{personId},
                (rs, rowNum) -> new EmailEntity(rs.getLong(COLUMN_PERSON_ID), rs.getString(COLUMN_EMAIL)));
    }
}

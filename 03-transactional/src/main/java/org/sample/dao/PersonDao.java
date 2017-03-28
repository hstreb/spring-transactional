package org.sample.dao;

import org.sample.entity.PersonEntity;
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
public class PersonDao {

    private static final Logger log = LoggerFactory.getLogger(PersonDao.class);
    private static final String TABLE_NAME = "person";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
        jdbcTemplate.execute("DROP TABLE person IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE person(id SERIAL PRIMARY KEY not null, name VARCHAR(255) not null)");
    }

    public PersonEntity save(PersonEntity person) {
        log.info("Saving {}", person);
        Number id = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(COLUMN_NAME)
                .usingGeneratedKeyColumns(COLUMN_ID)
                .executeAndReturnKey(getParams(person));
        person.setId(id.longValue());
        log.info("{} saved", person);
        return person;
    }

    private Map<String, Object> getParams(PersonEntity person) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(COLUMN_NAME, person.getName());
        return parameters;
    }

    public List<PersonEntity> list() {
        return jdbcTemplate.query("select * from person",
                (rs, rowNum) -> new PersonEntity(rs.getLong(COLUMN_ID), rs.getString(COLUMN_NAME)));
    }
}

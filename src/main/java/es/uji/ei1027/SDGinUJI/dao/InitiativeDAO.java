package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.Initiative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InitiativeDAO {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void addInitiative(Initiative initiative) {
        Integer maxId = jdbcTemplate.queryForObject("SELECT MAX(id_initiative) FROM initiative", Integer.class);

        if (maxId == null) {
            maxId = 0;
        }

        int nextId = maxId + 1;
        initiative.setId(nextId);

        initiative.setState("Pending");


        jdbcTemplate.update("INSERT INTO initiative VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                initiative.getId(), initiative.getName(), initiative.getDescription(),
                initiative.getState(), initiative.getUrl(), initiative.getStartDate(),
                initiative.getFinishDate(), initiative.getResults(),
                initiative.getGoal(), initiative.getIdSDG());
    }



    public void deleteInitiative(int id) {
        jdbcTemplate.update("DELETE FROM initiative WHERE id_initiative = ? ", id);
    }

    public void approveInitiative(int id) {
        jdbcTemplate.update("UPDATE initiative SET state = 'Approved' WHERE id_initiative = ?", id);
    }

    public void rejectInitiative(int id) {
        jdbcTemplate.update("UPDATE initiative SET state = 'Denied' WHERE id_initiative = ?", id);
    }

    public void updateInitiative(Initiative initiative) {
        jdbcTemplate.update("UPDATE initiative SET name=?, description=?, state=?, URL=?,initial_date=?, final_date=?, expected_results=?, motivation=?, num_SDG=? WHERE id_initiative=?",
                initiative.getName(), initiative.getDescription(),
                initiative.getState(), initiative.getUrl(), initiative.getStartDate(),
                initiative.getFinishDate(), initiative.getResults(),
                initiative.getGoal(), initiative.getIdSDG(),initiative.getId());
    }

    public Initiative getInitiative(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM initiative WHERE id_initiative=?",  new InitiativeRowMapper(), id);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Initiative> getInitiatives() {
        try {
            return jdbcTemplate.query("SELECT * FROM initiative", new InitiativeRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Initiative>();
        }
    }


}

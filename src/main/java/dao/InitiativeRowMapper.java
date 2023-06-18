package dao;

import model.Initiative;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class InitiativeRowMapper implements RowMapper<Initiative> {
    @Override
    public Initiative mapRow(ResultSet rs, int rowNum) throws SQLException {
        Initiative initiative = new Initiative();
        initiative.setId(rs.getInt("id_initiative"));
        initiative.setName(rs.getString("name"));
        initiative.setDescription(rs.getString("description"));
        initiative.setState(rs.getString("state"));
        initiative.setUrl(rs.getString("url"));
        initiative.setStartDate(rs.getObject("initial_date", LocalDate.class));
        initiative.setFinishDate(rs.getObject("final_date", LocalDate.class));
        initiative.setResults(rs.getString("expected_results"));
        initiative.setGoal(rs.getString("motivation"));
        initiative.setIdSDG(rs.getInt("num_SDG"));
        return initiative;
    }
}


package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.UJIMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UJIMemberDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addMember(UJIMember ujiMember) {
        jdbcTemplate.update("INSERT INTO UJIMember VALUES(?, ?, ?)", ujiMember.getName(), ujiMember.getType(), ujiMember.getEmail());
    }

    public void deleteUJIMember(String email) {
        jdbcTemplate.update("DELETE FROM UJIMember WHERE email =?", email);
    }

    public void updateUJIMember(UJIMember ujiMember) {
        jdbcTemplate.update("UPDATE UJIMember SET name=?,type=? WHERE email=?", ujiMember.getName(), ujiMember.getType(), ujiMember.getEmail());
    }

    public List<UJIMember> getUJIMembers() {
        try {
            return jdbcTemplate.query("SELECT * FROM UJIMember",
                    new UJIMemberRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<UJIMember>();
        }
    }

    public UJIMember getUJIMember(String email) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM UJIMember WHERE email=?", new UJIMemberRowMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

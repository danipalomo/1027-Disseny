package dao;

import model.SDG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SdgDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addSDG(SDG sdg) {
        jdbcTemplate.update("INSERT into sdg VALUES(?, ?, ?, ?)", sdg.getIdSDG(), sdg.getName(), sdg.getDescription(), sdg.getUrlImage());
    }

    public void deleteSDG(int num_SDG) {
        jdbcTemplate.update("DELETE FROM sdg WHERE num_SDG =?", num_SDG);
    }

    public void updateSDG(SDG sdg) {
        jdbcTemplate.update("UPDATE sdg SET name=?,description=?, foto_url=? WHERE num_SDG=?", sdg.getName(), sdg.getDescription(), sdg.getUrlImage(), sdg.getIdSDG());
    }

    public List<SDG> getSDG() {
        try {
            return jdbcTemplate.query("SELECT * FROM sdg", new SdgRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<SDG>();
        }
    }

    public SDG getSDG(int idSdg) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM sdg WHERE num_SDG=?", new SdgRowMapper(), idSdg);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

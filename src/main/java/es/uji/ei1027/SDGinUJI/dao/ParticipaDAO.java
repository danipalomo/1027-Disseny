package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.Participa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipaDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addParticipa(String ujiMemberId, int idInitiative, boolean isResponsible) {
        jdbcTemplate.update("INSERT INTO participa VALUES(?, ?, ?)",
                ujiMemberId, idInitiative, isResponsible);
    }

    public void deleteParticipa(String ujiMemberId, int idInitiative) {
        jdbcTemplate.update("DELETE FROM participa WHERE ujimember_email = ? AND id_initiative = ?",
                ujiMemberId, idInitiative);
    }
    public List<Participa> getAllParticipa() {
        try {
            return jdbcTemplate.query("SELECT * FROM participa",new ParticipaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Participa>();
        }
    }


    public boolean isResponsible(String ujiMemberId, int idInitiative) {
        List<Participa> participaciones = getAllParticipa();
        for (Participa participa: participaciones) {
            if (participa.getUjiMemberId().equalsIgnoreCase(ujiMemberId) && idInitiative==participa.getIdInitiative()){
                return participa.isResponsible();
            }
        }
        return false;
    }

    public Participa getParticipa(String getUjiMemberId, int idInitiative) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM participa WHERE ujimember_email=? and id_initiative=?", new ParticipaRowMapper(), getUjiMemberId, idInitiative);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}




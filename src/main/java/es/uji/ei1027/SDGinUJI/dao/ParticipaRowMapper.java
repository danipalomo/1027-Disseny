package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.Participa;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipaRowMapper implements RowMapper<Participa> {
    public Participa mapRow(ResultSet rs, int rowNum) throws SQLException {
        Participa participa = new Participa();
        participa.setUjiMemberId(rs.getString("ujimember_email"));
        participa.setIdInitiative(rs.getInt("id_initiative"));
        participa.setResponsible(rs.getBoolean("responsable"));
        return participa;
    }
}


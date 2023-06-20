package es.uji.ei1027.SDGinUJI.dao;


import es.uji.ei1027.SDGinUJI.model.SDG;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SdgRowMapper implements RowMapper<SDG> {
    public SDG mapRow(ResultSet rs, int rowNum) throws SQLException {
        SDG sdg = new SDG();
        sdg.setIdSDG(rs.getInt("num_SDG"));
        sdg.setName(rs.getString("name"));
        sdg.setDescription(rs.getString("description"));
        sdg.setUrlImage(rs.getString("foto_url"));
        return sdg;
    }
}
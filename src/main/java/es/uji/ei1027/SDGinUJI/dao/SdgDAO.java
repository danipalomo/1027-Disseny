package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.SDG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void deleteSDG(int IdSDG) {
        jdbcTemplate.update("DELETE FROM sdg WHERE num_SDG =?", IdSDG);
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

    public Map<Integer, String> getIdNameMap() {
        String sql = "SELECT num_sdg, name FROM sdg";
        List<Map<Integer, String>> resultList = jdbcTemplate.query(sql, new RowMapper<Map<Integer, String>>() {
            @Override
            public Map<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<Integer, String> idNameMap = new HashMap<>();
                int id = rs.getInt("num_sdg");
                String name = rs.getString("name");
                idNameMap.put(id, name);
                return idNameMap;
            }
        });

// Crear un solo mapa con todos los resultados
        Map<Integer, String> resultMap = new HashMap<>();
        for (Map<Integer, String> map : resultList) {
            resultMap.putAll(map);
        }
        return resultMap;

    }

}

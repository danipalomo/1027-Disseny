package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.UJIMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UJIMemberRowMapper implements RowMapper<UJIMember> {
    @Override
    public UJIMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        UJIMember ujimember = new UJIMember();
        ujimember.setName(rs.getString("name"));
        ujimember.setEmail(rs.getString("email"));
        ujimember.setType(rs.getString("type"));
        return ujimember;
    }
}
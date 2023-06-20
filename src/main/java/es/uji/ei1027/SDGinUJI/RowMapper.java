package es.uji.ei1027.SDGinUJI;

public interface RowMapper<T> {
        T mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException;
}

package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.UserDetails;

public interface UserDAO {
    UserDetails loadUserByUsername(String username, String password);
}
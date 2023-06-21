package es.uji.ei1027.SDGinUJI.dao;

import es.uji.ei1027.SDGinUJI.model.TypeUser;
import es.uji.ei1027.SDGinUJI.model.UserDetails;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOManagerImpl implements UserDAO{

    final Map<String, UserDetails> knownUsers = new HashMap<>();  //mapa para mejorar el acceso a un usuario
    private final int NUM_UJI_MEMBERS_GENERATOR = 2;
    private final int NUM_OCDS_GENERATOR = 2;

    private final PasswordEncryptor passwordEncryptor;

    public UserDAOManagerImpl(){
        passwordEncryptor = new StrongPasswordEncryptor();
        generateUsers();
    }

    private void generateUsers() {
        for (int i = 0; i < NUM_UJI_MEMBERS_GENERATOR; i++) {
            String username = generateUsername();
            String password = "123456";
            TypeUser typeUser = TypeUser.UJI_MEMBER;

            addUser(username, password, typeUser);
        }

        for (int i = 0; i < NUM_OCDS_GENERATOR; i++) {
            String username = generateUsername();
            String password = "123456";
            TypeUser typeUser = TypeUser.OCDS;

            addUser(username, password, typeUser);
        }
    }

    private String generateUsername() {
        String baseUsername = "UsuarioDesarrollo";
        int count = 1;
        while (knownUsers.containsKey(baseUsername + count)) {
            count++;
        }

        return baseUsername + count;
    }


    private void addUser(String username, String password, TypeUser typeUser){
        UserDetails user = new UserDetails();
        user.setUsername(username);
        user.setPassword(passwordEncryptor.encryptPassword(password));
        user.setTypeUser(typeUser);

        knownUsers.put(username, user);
    }


    @Override
    public UserDetails loadUserByUsername(String username, String password) {
        if(knownUsers.containsKey(username)){
            UserDetails user = knownUsers.get(username);
            if (passwordEncryptor.checkPassword(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}

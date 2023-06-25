package es.uji.ei1027.SDGinUJI.controller;

import es.uji.ei1027.SDGinUJI.dao.UserDAO;
import es.uji.ei1027.SDGinUJI.model.TypeUser;
import es.uji.ei1027.SDGinUJI.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final String BASE_REDIRECT = "redirect:/";
    private static final String INDEX_STUDENT = "index_UJIMember";
    private static final String INDEX_OCDS = "index_OCDS";

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new UserDetails());
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String validateLogin(@ModelAttribute("user") UserDetails user, HttpSession session, Model model) {

        user = userDao.loadUserByUsername(user.getUsername(), user.getPassword());

        if (user == null) {
            model.addAttribute("errorMsg", "Credenciales incorrectas");
            return "/login/login";
        }

        model.addAttribute("user", user);
        session.setAttribute("user", user);
        session.setAttribute("typeUser", user.getTypeUser().name());
        return getRedirectByUserTypePostLogin(user.getTypeUser());
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return BASE_REDIRECT;
    }

    private String getRedirectByUserTypePostLogin(TypeUser type){
        switch (type){
            case UJI_MEMBER:
                return BASE_REDIRECT + INDEX_STUDENT;
            case OCDS:
                return INDEX_OCDS;
            default:
                return BASE_REDIRECT;
        }
    }

    @Component
    public class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return UserDetails.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            UserDetails user = (UserDetails) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required", "El nombre de usuario es obligatorio");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required", "La contraseña es obligatoria");
            if (user.getPassword().length() < 6) {
                errors.rejectValue("password", "required","La contraseña debe tener al menos 6 carácteres");
            }
        }
    }

}

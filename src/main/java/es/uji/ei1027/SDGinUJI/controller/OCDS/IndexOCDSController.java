package es.uji.ei1027.SDGinUJI.controller.OCDS;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexOCDSController {

    @RequestMapping(value = "/index_OCDS", method = RequestMethod.GET)
    public String redirect(HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "index_OCDS";
    }
}

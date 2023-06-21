package es.uji.ei1027.SDGinUJI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexUJIMemberController {

    @RequestMapping(value = "/index_UJIMember", method = RequestMethod.GET)
    public String redirect(HttpSession session) {
        if (session == null) {
            return "redirect:/login";
        }
        return "/index_UJIMember";
    }
}
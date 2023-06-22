package es.uji.ei1027.SDGinUJI.controller.UJIMember;

import es.uji.ei1027.SDGinUJI.dao.InitiativeDAO;
import es.uji.ei1027.SDGinUJI.dao.ParticipaDAO;
import es.uji.ei1027.SDGinUJI.dao.SdgDAO;
import es.uji.ei1027.SDGinUJI.model.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei1027.SDGinUJI.model.Initiative;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.http.HttpSession;
import java.util.*;

import java.util.List;


@Controller
@RequestMapping("/ujiMember/initiative")
public class InitiativeUJIMemberController {

    @Autowired
    private InitiativeDAO initiativeDao;

    @Autowired
    private ParticipaDAO participaDAO;

    @Autowired
    private SdgDAO sdgDAO;

    @Autowired
    public void setInitiativeDao(InitiativeDAO initiativeDao) {
        this.initiativeDao = initiativeDao;
    }

    @RequestMapping(value="/add")
    public String addInitiative(Model model){
        model.addAttribute("initiative", new Initiative());
        List<SDG> sdgs = sdgDAO.getSDG();
        Collections.sort(sdgs);
        model.addAttribute("sdgs", sdgs);
        return "ujiMember/initiative/add";
    }


    @RequestMapping(value="/list", method= RequestMethod.GET)
    public String listInitiative(Model model, HttpSession session) {
        List<Initiative> initiativeList = initiativeDao.getInitiatives();
        initiativeList.sort(Comparator.comparingInt(Initiative::getId));
        UserDetails user = (UserDetails) session.getAttribute("user");
        if (user == null ) { //por si algun pillin intenta acceder desde la barra de busqueda
            return "redirect:/login";
        }
        List<Integer> initiativeResponsibleList = new ArrayList<>();
        for (Participa participa: participaDAO.getAllParticipa()) {
            if (participa.getUjiMemberId().equalsIgnoreCase(user.getEmail()) && participa.isResponsible()){
                initiativeResponsibleList.add(initiativeDao.getInitiative(participa.getIdInitiative()).getId());
            }
        }

        model.addAttribute("initiativeResponsibleList", initiativeResponsibleList);
        model.addAttribute("initiativeList", initiativeList);
        model.addAttribute("sdg", sdgDAO.getIdNameMap());
        return "ujiMember/initiative/list";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("initiative") Initiative initiative, BindingResult bindingResult, HttpSession session) {
        AddInitiativeValidator initiativeValidator = new AddInitiativeValidator();
        initiativeValidator.validate(initiative, bindingResult);

        if (session == null) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            return "ujiMember/initiative/add";
        }

        initiativeDao.addInitiative(initiative);
        participaDAO.addParticipa(((UserDetails) Objects.requireNonNull(session.getAttribute("user"))), initiative.getId(), true);
        return "redirect:/ujiMember/initiative/list";
    }

    @RequestMapping("/update/{id}")
    public String updateInitiative(@PathVariable int id, Model model, HttpSession session) {

        if (session == null) {
            return "redirect:/login";
        }

        Initiative initiative = initiativeDao.getInitiative(id);
        model.addAttribute("initiative", initiative);
        model.addAttribute("sdgs", sdgDAO.getSDG());
        return "ujiMember/initiative/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("initiative") Initiative initiative, BindingResult bindingResult, HttpSession session) {
        AddInitiativeValidator initiativeValidator = new AddInitiativeValidator();
        initiativeValidator.validate(initiative, bindingResult);

        if (session == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "loginUs/initiative/update";
        }

        initiativeDao.updateInitiative(initiative);
        return "redirect:/ujiMember/initiative/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteInitiative(@PathVariable int id, HttpSession session) {
        if (session == null) {
            return "redirect:/login";
        }
        initiativeDao.deleteInitiative(id);
        return "redirect:/ujiMember/initiative/list";
    }

    public class AddInitiativeValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Initiative.class.isAssignableFrom(clazz);
        }


        @Override
        public void validate(Object obj, Errors errors) {
            Initiative initiative = (Initiative) obj;

            if (initiative.getStartDate() == null)
                errors.rejectValue("startDate", "required", "La fecha inicial es obligatoria");

            if (initiative.getFinishDate() == null)
                errors.rejectValue("finishDate", "required", "La fecha final es obligatoria");

            if(initiative.getStartDate() != null && initiative.getFinishDate() != null){
                if (initiative.getFinishDate().isBefore(initiative.getStartDate()))
                    errors.rejectValue("finishDate", "fecha incorrecta", "La fecha final no puede ser anterior a la fecha inicial");
            }
        }
    }

}
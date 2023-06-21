package es.uji.ei1027.SDGinUJI.controller.UJIMember;

import es.uji.ei1027.SDGinUJI.dao.InitiativeDAO;
import es.uji.ei1027.SDGinUJI.dao.SdgDAO;
import es.uji.ei1027.SDGinUJI.model.Initiative;
import es.uji.ei1027.SDGinUJI.model.SDG;
import es.uji.ei1027.SDGinUJI.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.uji.ei1027.SDGinUJI.model.Initiative;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;

import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/ujiMember/initiative")
public class InitiativeUJIMemberController {

    @Autowired
    private InitiativeDAO initiativeDao;

    @Autowired
    public void setInitiativeDao(InitiativeDAO initiativeDao) {
        this.initiativeDao = initiativeDao;
    }

    @RequestMapping(value="/add")
    public String addInitiative(Model model){
        model.addAttribute("initiative", new Initiative());
        return "ujiMember/initiative/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("initiative") Initiative initiative, BindingResult bindingResult) {
        AddInitiativeValidator initiativeValidator = new AddInitiativeValidator();
        initiativeValidator.validate(initiative, bindingResult);

        if (bindingResult.hasErrors()) {
            return "ujiMember/initiative/add";
        }
        initiativeDao.addInitiative(initiative);
        return "redirect:/initiative/list";
    }

    @RequestMapping("/update")
    public String updateInitiative(Model model) {
        List<Initiative> initiativeList = initiativeDao.getInitiatives();
        initiativeList.sort(Comparator.comparingInt(Initiative::getId));
        model.addAttribute("initiative", initiativeList);
        return "ujiMember/initiative/list";
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
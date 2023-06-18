package controller;

import dao.UJIMemberDAO;
import model.UJIMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ujimember")
public class UJIMemberController {

    private UJIMemberDAO ujImemberDao;

    @Autowired
    public void setUjiMemberDao(UJIMemberDAO ujImemberDao) {
        this.ujImemberDao = ujImemberDao;
    }

    @RequestMapping("/list")
    public String listMembers(Model model) {
        model.addAttribute("ujimember", ujImemberDao.getUJIMembers());
        return "ujimember/list";
    }

    @RequestMapping(value="/add")
    public String addMember(Model model){
        model.addAttribute("ujimember",new UJIMember());
        return "ujimember/add";
    }

    /*@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("ujimember") UJImember ujImember,
                                   BindingResult bindingResult) {
        UjiMemberValidator ujiMemberValidator = new UjiMemberValidator();
        ujiMemberValidator.validate(ujImember, bindingResult);
        System.out.println();
        if (bindingResult.hasErrors())
            return "ujimember/add";
        try{
            ujImemberDao.addMember(ujImember);
        } catch (DuplicateKeyException e) {
            throw new SDGinUJIException(
                    "Ya existe un miembro con el email " + ujImember.getEmail(), "CPduplicada");
        } catch (DataAccessException e) {
            throw new SDGinUJIException(
                    "Error en el acceso a la base de datos", "ErrorAccedintDades");
        } catch (Exception e) {
            throw new SDGinUJIException(
                    "Error no esperado", "ErrorNoEsperat");
        }
        return "redirect:list";
    }*/


    @RequestMapping(value = "/update/{email}", method = RequestMethod.GET)
    public String editMember(Model model, @PathVariable String email){
        model.addAttribute("ujimember", ujImemberDao.getUJIMember(email));
        List<String> typeList = Arrays.asList("Student", "PAS","PDI");
        model.addAttribute("typeList", typeList);
        return "ujimember/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("ujimember") UJIMember ujImember,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "ujimember/update";
        ujImemberDao.updateUJIMember(ujImember);
        return "redirect:list";
    }


    @RequestMapping(value = "/delete/{email}")
    public String processDelete(@PathVariable String email){
        ujImemberDao.deleteUJIMember(email);
        return "redirect:../list";
    }

}

package es.uji.ei1027.SDGinUJI.controller.OCDS;


import es.uji.ei1027.SDGinUJI.dao.InitiativeDAO;
import es.uji.ei1027.SDGinUJI.model.Initiative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/ocds/initiative")
public class InitiativeOCDSController {

    @Autowired
    private InitiativeDAO initiativeDao;

    @Autowired
    public void setInitiativeDao(InitiativeDAO initiativeDao) {
        this.initiativeDao = initiativeDao;
    }


    @RequestMapping("/list")
    public String listInitiative(Model model) {
        List<Initiative> allInitiatives = initiativeDao.getInitiatives();
        List<Initiative> onlyPending = new ArrayList<>();
        for(Initiative initiative: allInitiatives){
            if (initiative.getState().equalsIgnoreCase("Pending")){
                onlyPending.add(initiative);
            }
        }
        onlyPending.sort(Comparator.comparingInt(Initiative::getId));
        model.addAttribute("initiativesOCDS", onlyPending);
        return "ocds/initiative/list";
    }

    @RequestMapping("/approve/{id}")
    public String approveInitiative(@PathVariable int id) {
        List<Initiative> allInitiatives = initiativeDao.getInitiatives();
        for(Initiative initiative: allInitiatives){
            if (initiative.getId() == id){
                initiative.setState("Approved");
            }
        }
        initiativeDao.approveInitiative(id);
        return "redirect:/ocds/initiative/list";
    }

    //WIP: FALTA QUE FUNCIONE EL REDIRECCIONAMIENTO AL LIST
    @RequestMapping("/reject/{id}")
    public String rejectInitiative(@PathVariable int id) {
        List<Initiative> allInitiatives = initiativeDao.getInitiatives();
        for(Initiative initiative: allInitiatives){
            if (initiative.getId() == id){
                initiative.setState("Denied");
            }
        }
        initiativeDao.rejectInitiative(id);
        return "redirect:/ocds/initiative/list";
    }
}
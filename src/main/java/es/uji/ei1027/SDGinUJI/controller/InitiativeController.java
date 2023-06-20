package es.uji.ei1027.SDGinUJI.controller;

import es.uji.ei1027.SDGinUJI.dao.InitiativeDAO;
import es.uji.ei1027.SDGinUJI.dao.SdgDAO;
import es.uji.ei1027.SDGinUJI.model.Initiative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/initiative")

public class InitiativeController {

    private InitiativeDAO initiativeDao;

    @Autowired
    private SdgDAO sdgDao;

    @Autowired
    public void setInitiativeDao(InitiativeDAO initiativeDao){
        this.initiativeDao=initiativeDao;
    }

    @RequestMapping("/list")
    public String listInitiative(Model model) {
        List<Initiative> initiativeList = initiativeDao.getInitiatives();
        initiativeList.sort(Comparator.comparingInt(Initiative::getId));
        model.addAttribute("initiative", initiativeList);
        return "initiative/list";
    }

    @RequestMapping(value="/add")
    public String addInitiative(Model model){
        model.addAttribute("initiative", new Initiative());
        model.addAttribute("sdgs", sdgDao.getSDG());

        return "initiative/add";
    }

    @RequestMapping(value="/update/{id_initiative}", method = RequestMethod.GET)
    public String editInitiative(Model model, @PathVariable int id_initiative) {
        model.addAttribute("initiative", initiativeDao.getInitiative(id_initiative));
        return "initiative/update";
    }

    @RequestMapping(value="/delete/{id_initiative}")
    public String processDelete(@PathVariable int id_initiative) {
        initiativeDao.deleteInitiative(id_initiative);
        return "redirect:../list";
    }
}


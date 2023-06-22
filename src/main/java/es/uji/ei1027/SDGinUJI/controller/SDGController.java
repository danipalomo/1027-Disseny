package es.uji.ei1027.SDGinUJI.controller;

import es.uji.ei1027.SDGinUJI.dao.SdgDAO;

import es.uji.ei1027.SDGinUJI.model.SDG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/sdg")
public class SDGController {

    @Autowired
    private SdgDAO sdgDao;

    @Autowired
    public void setSDGDao(SdgDAO sdgDao) {
        this.sdgDao = sdgDao;
    }

    @RequestMapping(value = "/list")
    public String listSDGs(Model model) {
        List<SDG> sdgList = sdgDao.getSDG();
        sdgList.sort(Comparator.comparingInt(SDG::getIdSDG));
        model.addAttribute("sdg", sdgList);
        return "sdg/list";
    }
}


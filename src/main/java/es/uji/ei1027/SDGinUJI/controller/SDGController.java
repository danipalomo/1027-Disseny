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
    private SdgDAO sdgDao;

    @Autowired
    public void setSDGDao(SdgDAO sdgDao) {
        this.sdgDao = sdgDao;
    }

/*    @RequestMapping("/list")
    public String listSDGs(Model es.uji.ei1027.SDGinUJI.model) {
        es.uji.ei1027.SDGinUJI.model.addAttribute("sdg", sdgDao.getSDG());
        return "sdg/list";
    }*/

    @RequestMapping("/add")
    public String addSDG(Model model) {
        model.addAttribute("sdg", new SDG());
        return "sdg/add";
    }
/*
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("sdg") SDG sdg,
                                   BindingResult bindingResult) {
        SDGValidator sdgValidator = new SDGValidator();
        sdgValidator.validate(sdg,bindingResult);
        if (bindingResult.hasErrors())
            return "sdg/add";
        try {
            sdgDao.addSDG(sdg);
        } catch (DuplicateKeyException e) {
            throw new SDGinUJIException(
                    "Ya existe un SDG con el número " + sdg.getNum_SDG(), "CPduplicada");
        } catch (DataAccessException e) {
            throw new SDGinUJIException(
                    "Error en el acceso a la base de datos", "ErrorAccedintDades");
        } catch (Exception e) {
            throw new SDGinUJIException(
                    "Error no esperado en el registro del SDG", "ErrorRegistreSDG");
        }

        return "redirect:list";
    }
*/

    @RequestMapping(value = "/update/{num_SDG}", method = RequestMethod.GET)
    public String editSDG(Model model, @PathVariable int num_SDG) {
        model.addAttribute("sdg", sdgDao.getSDG(num_SDG));
        return "sdg/update";
    }
/*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("sdg") SDG sdg,
            BindingResult bindingResult) {
        SDGValidator sdgValidator = new SDGValidator();
        sdgValidator.validate(sdg,bindingResult);
        if (bindingResult.hasErrors())
            return "sdg/update";
        sdgDao.updateSDG(sdg);
        return "redirect:list";
    }
*/

    @RequestMapping(value = "/delete/{num_SDG}")
    public String processDelete(@PathVariable int num_SDG) {
        sdgDao.deleteSDG(num_SDG);
        return "redirect:../list";
    }
    @RequestMapping(value = "/list")
    public String listSDGs(Model model) {
        List<SDG> sdgList = sdgDao.getSDG();
        sdgList.sort(Comparator.comparingInt(SDG::getIdSDG));
        model.addAttribute("sdg", sdgList);
        return "sdg/list";
    }
}


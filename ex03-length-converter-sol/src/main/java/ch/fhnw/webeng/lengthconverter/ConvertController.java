package ch.fhnw.webeng.lengthconverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConvertController {

    @GetMapping("/convert")
    public ModelAndView convert(int feet, int inches) {
        var cm = 30.48 * feet + 2.54 * inches;
        var cmPart = (int) cm;
        var mmPart = (int) (cm * 10 % 10);

        var model = new ModelMap();
        model.addAttribute("feet", feet);
        model.addAttribute("inches", inches);
        model.addAttribute("cmPart", cmPart);
        model.addAttribute("mmPart", mmPart);
        return new ModelAndView("convert", model);
    }
}

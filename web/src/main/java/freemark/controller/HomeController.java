package freemark.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/11/21.
 */
@Controller
public class HomeController {


    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        logger.info("Welcome home!");
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("name", "Marcio !");
        mav.addObject("user", "Big Joe");
        mav.addObject("title", "Testing initial page, using FreeMarker!");

        return mav;
    }
}

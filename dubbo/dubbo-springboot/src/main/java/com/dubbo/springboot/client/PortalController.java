package com.dubbo.springboot.client;


import com.dubbo.springboot.common.AccountManager;
import com.dubbo.springboot.common.UicTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public class PortalController {

    @Autowired
    private UicTemplate uicTemplate;
    @Autowired
    private AccountManager accountManager;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("user", uicTemplate.findById(1L));
        return "index";
    }


}

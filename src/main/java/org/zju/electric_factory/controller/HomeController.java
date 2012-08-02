package org.zju.electric_factory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Web controller used when loading the home page.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public void viewHome(Model model) {
    }

}

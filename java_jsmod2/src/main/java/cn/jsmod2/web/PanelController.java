package cn.jsmod2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PanelController {

    @Autowired
    public PanelService service;

    @RequestMapping("/api/{message}")
    public Object api(@PathVariable("message") String message){
        return service.api(message);
    }
}

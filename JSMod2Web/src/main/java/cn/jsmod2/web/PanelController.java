package cn.jsmod2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PanelController {

    @Autowired
    public PanelService service;

    @RequestMapping("/api/{message}")
    public Object api(@PathVariable("message") String message){
        Object o = service.api(message);
        return o;
    }

    @RequestMapping("/api")
    public Object apiParam(String message){
        return service.api(message);
    }
}

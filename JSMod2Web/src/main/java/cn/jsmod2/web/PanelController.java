package cn.jsmod2.web;

import cn.jsmod2.ResponseValue;
import cn.jsmod2.core.ex.ServerRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @RequestMapping("/multi/{message}")
    public Object multiApi(@PathVariable("message")String message){
        try{
            return new ResponseValue<>("success",service.multiApi(message),200);
        }catch (Exception e){
            return new ResponseValue<>(e.getMessage(),e,500);
        }

    }

    @RequestMapping("/api")
    public Object apiParam(String message){
        return service.api(message);
    }
}

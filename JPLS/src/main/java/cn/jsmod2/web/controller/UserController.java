package cn.jsmod2.web.controller;


import cn.jsmod2.ResponseValue;
import cn.jsmod2.web.ex.LoginFailedException;
import cn.jsmod2.web.ex.LoginSuccessException;
import cn.jsmod2.web.ex.ServiceException;
import cn.jsmod2.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController {

    @Autowired
    public IUserService service;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseValue<String> login(@RequestBody HashMap<String,String> map, HttpSession session){
        try {
            service.login(map.get("name"),map.get("pass"),session);
        }catch (ServiceException e){
            if(e instanceof LoginSuccessException){
                return new ResponseValue<>(e.getMessage(),"dashboard.html",200);
            }
            if(e instanceof LoginFailedException){
                return new ResponseValue<>(e.getMessage(),"index.html",201);
            }
        }
        return null;
    }
}

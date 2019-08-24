package cn.jsmod2.web.service;

import cn.jsmod2.web.ex.LoginFailedException;
import cn.jsmod2.web.ex.LoginSuccessException;
import cn.jsmod2.web.ex.ServiceException;
import cn.jsmod2.web.utils.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService implements IUserService{

    @Override
    public void login(String name, String password, HttpSession session) throws ServiceException {
        if(PasswordUtil.equalPassword(name,password)){
            session.setAttribute("registered","true");
            throw new LoginSuccessException("login success");
        }
        throw new LoginFailedException("login failed");
    }
}

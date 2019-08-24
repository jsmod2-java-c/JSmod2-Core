package cn.jsmod2.web.service;

import cn.jsmod2.web.ex.ServiceException;

import javax.servlet.http.HttpSession;

public interface IUserService {

    /** 登陆，注册和修改密码在控制台设置 */
    void login(String name, String password, HttpSession session) throws ServiceException;





}

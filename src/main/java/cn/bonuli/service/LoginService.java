package cn.bonuli.service;

import cn.bonuli.databaseValues.User;
import cn.bonuli.mappers.UserMapper;
import cn.bonuli.values.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * LoginService
 *
 * @author D.jin
 * @date 2018/10/19
 */

@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;


    public boolean login(LoginUser loginUser, HttpSession session) {
        User user = userMapper.queryByUserName(loginUser.getUserName(), loginUser.getPassword());
        if (user != null) {
            loginUser.setCompany(user.getCompanyId());
            session.setAttribute("LoginUser", loginUser);
            return true;
        }
        return false;
    }

  /*  public boolean logout(LoginUser loginUser, HttpSession session) {
        session.removeAttribute("LoginUser");
    }*/
}

package cn.bonuli.controllers;

import cn.bonuli.service.LoginService;
import cn.bonuli.values.LoginUser;
import cn.bonuli.values.result.ResultData;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Map;

/**
 * LoginController
 *
 * @author D.jin
 * @date 2018/10/19
 */

@Controller
@RequestMapping("/user")
public class LoginController {
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @PostMapping("/login-in")
    @ResponseBody
    public ResultData login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            test();
            logger.info("username:" + username);
            logger.info("password:" + password);
            LoginUser loginUser = new LoginUser(username, password, null);
            if (loginService.login(loginUser, request.getSession())) {
                Map<String, String> loginMap = Maps.newHashMap();
                //loginMap.put("access_token", "123456");
                return ResultData.succed(0, loginMap, "success");
            }
            return ResultData.failed("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.failed(e.getMessage());
        }
    }


    private void test(){
        File file = new File("E:/pdf.pdf");
        logger.info("username:" + file.getPath());
        if (file.isFile() && file.exists()) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferReader = new BufferedReader(inputStreamReader);
                String lineStr = null;
                int count = 0;
                try {
                    while ((lineStr = bufferReader.readLine()) != null) {
                        logger.info("lineStr"+lineStr);
                        byte[] pdf = Base64.decodeBase64(lineStr);
                        FileUtils.writeByteArrayToFile(new File("E:/pdfC.pdf"), pdf);
                    }
                    System.out.println("count:" + count);
                    bufferReader.close();
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    System.out.println("file read error!");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("file is not a file or file is not existing!");
        }

    }


   /* @GetMapping("/login")
    public String  login(){
        return "login";
    }*/

}

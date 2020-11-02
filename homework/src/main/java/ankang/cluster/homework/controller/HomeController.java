package ankang.cluster.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-01
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username , @RequestParam("password") String password , HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();

        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("isAuth" , true);
            modelAndView.setViewName("home");
        } else {
            session.setAttribute("isAuth" , false);
            modelAndView.addObject("msg" , "用户名或密码错误，请重新登录！");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    /**
     * 当请求没有登录时，跳转到这里，然后重新登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

}


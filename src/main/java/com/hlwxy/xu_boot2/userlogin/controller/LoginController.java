package com.hlwxy.xu_boot2.userlogin.controller;

import com.hlwxy.xu_boot2.common.comtroller.BaseController;
import com.hlwxy.xu_boot2.common.utils.MD5Utils;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.common.utils.Tree;
import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import com.hlwxy.xu_boot2.system.service.PeopleService;
import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;
import com.hlwxy.xu_boot2.userlogin.service.MenuService;
import com.hlwxy.xu_boot2.userlogin.service.UserRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @author shkstart
 * @create 2019-10-16-9:49
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private PeopleService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/login")
    public String login() {
        return "index";
    }

    @RequestMapping("/toPageMain")
    public String toPageMain(Model  model) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        return "calendar";
    }

    @RequestMapping("/main")
    @ResponseBody
    public R findByUsername(PeopleDO userDO) {
//       userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userDO.getPassword()));
        UsernamePasswordToken token = new UsernamePasswordToken(userDO.getUsername(), userDO.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            PeopleDO u = (PeopleDO) subject.getPrincipal();
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 登出页面
     *
     */
    @RequestMapping(value="/logout",method= RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return "index";
    }

//    /**
//     * 用户注册
//     */
//    @RequestMapping("/reg")
//    @ResponseBody
//    public R reg(UserDo userDO) {
//        Map<String,Object> map=new HashMap<>(1);
//        map.put("username",userDO.getUsername());
//        List<UserDo> list = userService.list(map);
//        if (list.size() >0){
//            return R.error();
//        }else {
//            userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userDO.getPassword()));
//            if (userService.save(userDO) > 0) {
//                userRoleService.save(new UserRoleDO(userDO.getId(), Integer.valueOf(userDO.getBmId())));
//            }
//            return R.ok();
//        }
//    }



}
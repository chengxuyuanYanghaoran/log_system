package com.hlwxy.xu_boot2.userlogin.controller;

import com.hlwxy.xu_boot2.common.comtroller.BaseController;
import com.hlwxy.xu_boot2.common.utils.R;
import com.hlwxy.xu_boot2.common.utils.Tree;
import com.hlwxy.xu_boot2.sup.domain.SupDO;
import com.hlwxy.xu_boot2.system.domain.*;
import com.hlwxy.xu_boot2.system.service.*;
import com.hlwxy.xu_boot2.userlogin.domain.MenuDO;
import com.hlwxy.xu_boot2.userlogin.service.MenuService;
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
import java.util.*;
/**
 * @author shkstart
 * @create 2019-10-16-9:49
 */
@Controller
public class LoginController extends BaseController{
    @Autowired
    private DayPlanService dayPlanService;

    @Autowired
    private DaySummaryService daySummaryService;

    @Autowired
    private WeekPlanService weekPlanService;

    @Autowired
    private WeekSummaryService weekSummaryService;

    @Autowired
    private MonthlyPlanService monthlyPlanService;

    @Autowired
    private MonthlySummaryService monthlySummaryService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/login")
    public String login() {
        return "index";
    }

    @RequestMapping("/toPageMain")
    public String toPageMain(Model  model) {
        //遍历菜单树
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);

      //日计划
        List<SupDO> supDOS=new ArrayList<>();
        Map<String,Object> map=new HashMap<>(1);
        map.put("peo_id",getUserId());
        List<DayPlanExtendDO> list = dayPlanService.list(map);
        if(list.size()>0){
            DayPlanExtendDO dayPlanExtendDO = list.get(0);
            if(dayPlanExtendDO.getDay_plan_state()==3){
                SupDO supDO = new SupDO();
                supDO.setType("1");
                supDO.setId(dayPlanExtendDO.getId().toString());
                supDO.setContent(dayPlanExtendDO.getDay_plan_content());
                supDO.setNewdata(dayPlanExtendDO.getDay_plan_time());
                supDOS.add(supDO);
            }
        }
        model.addAttribute("struts",supDOS);

        //日总结

        Map<String,Object> map1=new HashMap<>(1);
        map1.put("peo_id",getUserId());
        List<DaySummaryExtendDO> list1 = daySummaryService.list(map1);
        if (list1.size()>0){
            DaySummaryExtendDO daySummaryExtendDO = list1.get(0);
            if (daySummaryExtendDO.getDay_summary_state()==3){
                SupDO supDO = new SupDO();
                supDO.setType("2");
                supDO.setId(daySummaryExtendDO.getId().toString());
                supDO.setContent(daySummaryExtendDO.getDay_summary_content());
                supDO.setNewdata(daySummaryExtendDO.getDay_summary_time());
                supDOS.add(supDO);
            }
        }
        model.addAttribute("struts",supDOS);

        //周计划
        Map<String,Object> map2=new HashMap<>(1);
        map2.put("peo_id",getUserId());
        List<WeekPlanExtendDO> list2 = weekPlanService.list(map2);
        if (list2.size()>0){
            WeekPlanExtendDO weekPlanExtendDO = list2.get(0);
            if (weekPlanExtendDO.getWeek_plan_state()==3){
                SupDO supDO = new SupDO();
                supDO.setType("3");
                supDO.setId(weekPlanExtendDO.getId().toString());
                supDO.setContent(weekPlanExtendDO.getWeek_plan_content());
                supDO.setNewdata(weekPlanExtendDO.getWeek_plan_time());
                supDOS.add(supDO);
            }
        }
        model.addAttribute("struts",supDOS);

                //周总结
        Map<String,Object> map3=new HashMap<>(1);
        map3.put("peo_id",getUserId());
        List<WeekSummaryExtendDO> list3 = weekSummaryService.list(map3);
        if (list3.size()>0){
            WeekSummaryExtendDO weekSummaryExtendDO = list3.get(0);
            if (weekSummaryExtendDO.getWeek_summary_state()==3){
                SupDO supDO = new SupDO();
                supDO.setType("4");
                supDO.setId(weekSummaryExtendDO.getId().toString());
                supDO.setContent(weekSummaryExtendDO.getWeek_summary_content());
                supDO.setNewdata(weekSummaryExtendDO.getWeek_summary_time());
                supDOS.add(supDO);
            }
        }
        model.addAttribute("struts",supDOS);

        //月计划
        Map<String,Object> map4=new HashMap<>(1);
        map4.put("peo_id",getUserId());
        List<MonthlyPlanExtendDo> list4 = monthlyPlanService.list(map4);
        if (list4.size()>0){
            MonthlyPlanExtendDo monthlyPlanExtendDo = list4.get(0);
            if (monthlyPlanExtendDo.getMonthly_plan_state()==3){
                SupDO supDO = new SupDO();
                supDO.setType("5");
                supDO.setId(monthlyPlanExtendDo.getId().toString());
                supDO.setContent(monthlyPlanExtendDo.getMonthly_plan_content());
                supDO.setNewdata(monthlyPlanExtendDo.getMonthly_plan_time());
                supDOS.add(supDO);
            }
        }
        model.addAttribute("struts",supDOS);

        //月总结
        Map<String,Object> map5=new HashMap<>(1);
        map5.put("peo_id",getUserId());
        List<MonthlySummaryExtendDO> list5 = monthlySummaryService.list(map5);
        if (list5.size()>0){
            MonthlySummaryExtendDO monthlySummaryExtendDO = list5.get(0);
            if (monthlySummaryExtendDO.getMonthly_summary_state()==3){
                SupDO supDO = new SupDO();
                supDO.setType("6");
                supDO.setId(monthlySummaryExtendDO.getId().toString());
                supDO.setContent(monthlySummaryExtendDO.getMonthly_summary_content());
                supDO.setNewdata(monthlySummaryExtendDO.getMonthly_summary_time());
                supDOS.add(supDO);
            }
        }
        model.addAttribute("struts",supDOS);
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
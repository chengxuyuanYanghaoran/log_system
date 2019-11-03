package com.hlwxy.xu_boot2.system.service.impl;

import com.hlwxy.xu_boot2.common.utils.DateTool;
import com.hlwxy.xu_boot2.common.utils.EmailUtil;
import com.hlwxy.xu_boot2.system.dao.EmailDao;
import com.hlwxy.xu_boot2.system.domain.DayPlanDO;
import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import com.hlwxy.xu_boot2.system.service.EmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {
	@Resource
	private EmailDao emailDao;

	/**
	 *  1.查询所有人
	 *  2.看每个人是否有日计划
	 *      2.1、没有日计划，发送邮件
	 *      2.2、有日计划，在次判断
	 *          2.2.1、该日计划是今天的计划，不发送邮件
	 *          2.2.2、该计划不是今天的计划，发送邮件
	 */

	@Override
	public void sendEmail() {
		//获取所有人员
		List<com.hlwxy.xu_boot2.system.domain.PeopleDO> peopleDOS=emailDao.findPeopleDO();
		DateTool dateTool=new DateTool();
		EmailUtil emailUtil=new EmailUtil();
		//遍历每个人
		for (com.hlwxy.xu_boot2.system.domain.PeopleDO people:peopleDOS) {
//			//获取每个人的账号和邮箱
			PeopleDO userDO=emailDao.getUser(people);
			//判断这个人有没有日计划
			if (people.getDayPlanId()==null||people.getDayPlanId().equals("")){ //没有日计划
				if(userDO.getEmail()!=null&&!userDO.getEmail().equals("")){ //邮箱不为空
					emailUtil.send(userDO.getEmail());//给未提交的发送邮件
				}
			}else {  //有日计划
				//获取每个人最新的日计划
				DayPlanDO dayPlanDO=emailDao.gerDdyPlan(people);
				//判断该计划是否为今天的计划
				if (dayPlanDO.getDay_plan_time().equals(dateTool.adyAndDay())){ //是今天的日计划，不发送
					//判断该计划是否已提交
					if (dayPlanDO.getDay_plan_state()!=2||dayPlanDO.getDay_plan_state()!=4){ //保存了但未提交，或者提交了但审核不通过
						if(userDO.getEmail()!=null&&!userDO.getEmail().equals("")){  //邮箱不为空
							emailUtil.send(userDO.getEmail());//给未提交的发送邮件
						}
					}
				}else {  //不是今天的日计划
					if(userDO.getEmail()!=null&&!userDO.getEmail().equals("")){  //邮箱不为空
						emailUtil.send(userDO.getEmail());//给未提交的发送邮件
					}
				}
			}
		}


	}
}

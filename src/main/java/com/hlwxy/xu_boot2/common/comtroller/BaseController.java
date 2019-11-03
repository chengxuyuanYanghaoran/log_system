package com.hlwxy.xu_boot2.common.comtroller;

import com.hlwxy.xu_boot2.common.utils.ShiroUtils;
import com.hlwxy.xu_boot2.system.domain.PeopleDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
	public PeopleDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return Long.valueOf(getUser().getId());
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}
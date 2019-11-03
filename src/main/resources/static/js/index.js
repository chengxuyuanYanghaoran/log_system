/**********************************************************登        录*******************************************************/
//验证用户名
function loginame() {
	//获取登录用户名
	var logiName = document.getElementById("user_name").value;
	//正则验证
	var regName = /^\w{3,10}$/;
	if(!regName.test(logiName)) {
		document.getElementById("span6").innerHTML = "嗨，用户名是由字母，数字，下划线组成的3-10位字符!";
		return false;
	}
	document.getElementById("span6").innerHTML = "OK 开始下一步!";
	return true;
}

//验证密码
function loginpwd() {
	//获取密码
	var logiNpwd = document.getElementById("password").value;
	//正则验证
	var regPwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;
	if(!regPwd.test(logiNpwd)) {
		document.getElementById("span7").innerHTML = "嗨，密码至少包含 数字和英文组成的6-12位字符!";
		return false;
	}
	document.getElementById("span7").innerHTML = "OK 开始下一步吧!";
	return true;
}

function btn01() {
	if(loginame() && loginpwd()) {
		return true;
	}
	document.getElementById("span8").style.display = "block";
	document.getElementById("span8").innerHTML = "输入有误! 请重新输入";
	return false;
}

/******************************************************************注                  册********************************************************************/
//用户名验证
function names() {
	//获取用户名
	var RUSname = document.getElementById("r_user_name").value;
	//验证的正则
	var regname = /^\w{3,10}$/;
	if(!regname.test(RUSname)) {
		document.getElementById("span1").innerHTML = "嗨，用户名是由字母，数字，下划线组成的3-10位字符!";
		return false;
	}
	document.getElementById("span1").innerHTML = "OK 开始下一步吧!";
	return true;
};

//密码验证
function pwd() {
	//获取密码
	var pwd = document.getElementById("r_password").value;
	//验证的正则
	var regpwd = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;
	if(!regpwd.test(pwd)) {
		document.getElementById("span2").innerHTML = "嗨，密码至少包含 数字和英文组成的6-12位字符!";
		return false;
	} else if(pwd.trim() == "") {
		document.getElementById("span2").innerHTML = "密码不能为空!";
		return false;
	};
	document.getElementById("span2").innerHTML = "OK 开始下一步吧!"
	return true;
}

//确认密码验证
function pwds() {
	var pwd = document.getElementById("r_password").value;
	//获取确认密码验证
	var pwds = document.getElementById("r_passwords").value;
	if(pwd != pwds) {
		document.getElementById("span3").innerHTML = "输入的密码不一致噢!";
		return false;
	} else if(pwds.trim() == "") {
		document.getElementById("span3").innerHTML = "确认密码不能为空!";
		return false;
	};
	document.getElementById("span3").innerHTML = "OK 开始下一步吧!";
	return true;
}

//电子邮件验证
function mail() {
	//获取邮箱
	var mail = document.getElementById("r_mail").value;
	//邮箱验证
	var regmail = /^\w{3,}(\.\w+)*@[A-z0-9]+(\.[A-z]{2,5}){1,2}$/;
	if(!regmail.test(mail)) {
		document.getElementById("span4").innerHTML = "请输入正确的邮箱哦!";
		return false;
	}
	document.getElementById("span4").innerHTML = "OK 圆满结束!";
	return true;
}

function btn02() {
	if(names() && pwd() && pwds() && mail()) {
		return true;
	}
	document.getElementById("span5").style.display = "block";
	document.getElementById("span5").innerHTML = "输入有误! 请重新输入";
	return false;
}
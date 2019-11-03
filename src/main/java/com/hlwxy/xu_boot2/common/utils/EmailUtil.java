package com.hlwxy.xu_boot2.common.utils;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

    public void send(String ema){
        //发邮件
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("smtp.qq.com");// 设置使用发电子邮件的邮件服务器，这里以qq邮箱为例（其它例如：【smtp.163.com】，【smtp.sohu.com】）
        try {
            // 收件人邮箱
            htmlEmail.addTo(ema);
            // 邮箱服务器身份验证
            htmlEmail.setCharset("UTF-8");
            //前面的参数是发件人邮箱，后面的参数是生成的授权码
            htmlEmail.setAuthentication("1812582971@qq.com", "livhgxvgzqrzechf");
            // 发件人邮箱
            htmlEmail.setFrom("1812582971@qq.com");
            // 邮件主题
            htmlEmail.setSubject("日志提示");
            // 邮件内容
            htmlEmail.setHtmlMsg("您还没有提交今天的日报，请尽快提交！");
            // 发送邮件
            htmlEmail.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
        }
    }
}

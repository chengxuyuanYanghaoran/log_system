package com.hlwxy.xu_boot2.system.controller;


import com.hlwxy.xu_boot2.system.service.DataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @date 2019-09-26 22:35:53
 */

@Controller
@RequestMapping("/system/data")
public class DataController {
	@Autowired
	private DataService dataService;

	//数据备份
	@ResponseBody
	@GetMapping("/backups")
	@RequiresPermissions("system:data:backups")
	public void list(HttpServletResponse response) {
		//备份文件
		String filePath="C:\\beifen";//备份文件的路径
		String dbName="log_system233"+(new Date().getTime());//备份文件的名称
		try {
			Process process = Runtime.getRuntime().exec(
					"cmd /c mysqldump -u root -proot " + "log_system233" + " > "
							+ filePath + "/" + dbName
							+ ".sql");
			//备份的数据库名字为log_system，数据库连接和密码均为root

		} catch (Exception e) {
			e.printStackTrace();
		}

		//下载备份的文件
		String filename = "C:\\beifen";
		String filenameZip = filename + dbName + ".sql";//获取文件的路径
		try {
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String((dbName+".sql").getBytes(), "UTF-8"));
			// 读取文件
			InputStream in = new FileInputStream(filenameZip);
			ServletOutputStream outputStream = response.getOutputStream();
			// 写文件
			int b;
			while ((b = in.read()) != -1) {
				outputStream.write(b);
			}
			in.close();
			outputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		//结束
	}


	//数据还原，也就是上传sql文件并调用sql命令执行
	@RequestMapping("/reduction")
	@ResponseBody
	@RequiresPermissions("system:data:reduction")
	public Map<String,Object> reduction(@RequestParam("file") MultipartFile file) throws IOException {
		Map<String,Object> map=new HashMap<>();
		try {
			//上传文件
			System.out.println("fileName："+file.getOriginalFilename());
			String path="C:\\beifen"+new Date().getTime()+file.getOriginalFilename();
			File newFile=new File(path);
			//通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);

			//数据库还原
			String stmt2 = "cmd /c mysql"+" -uroot"+" -proot"+" "+"log_system233"+" < " + path;
			Runtime.getRuntime().exec(stmt2);//执行sql命令
			map.put("res",0);
			map.put("code","还原成功");
		}catch (Exception e){
			map.put("res",-1);
			map.put("code","系统异常");
		}
		return map;
	}

//数据初始化，清空数据库所有的表
@RequestMapping("/del")
@ResponseBody
public Map<String, Object> del() {
	Map<String, Object> map = new HashMap<>();

	try {
		dataService.del();
		map.put("res", 0);
		map.put("code", "初始化成功！");
	} catch (Exception e) {
		map.put("res", -1);
		map.put("code", "初始化失败！");
	}

	return map;
}

}

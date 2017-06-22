package com.mulhyac.zimg.client;

import java.io.File;
import java.io.InputStream;

import org.nutz.http.Request;
import org.nutz.http.Request.METHOD;
import org.nutz.http.Response;

import com.mulhyac.zimg.client.bean.ZimgFile;

/**
 * zimg客户端 使用方法如下： ZimgClient zimgClient = new
 * ZimgClient("http://192.168.2.199:4869"); ZimgInfo upload =
 * zimgClient.upload("d:\\tmp\\timg.jpg");
 * 
 * @author mulhayc
 */
public class ZimgClient {

	private String baseUrl;

	public ZimgClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public ZimgFile upload(File file) {
		Request req = Request.create(baseUrl + "/upload", METHOD.POST);
		req.getParams().put("file", file);
		return upload(req);
	}
	
	public ZimgFile upload(Request req){
		ZimgFilePostSender sender = new ZimgFilePostSender(req);
		Response upload = sender.send();
		String content = upload.getContent();
		if (content.indexOf("Successfully") > -1) {
			String zimgName = content.substring(content.indexOf("<h1>") + 9, content.indexOf("</h1>")).trim();
			content = content.substring(content.indexOf("http"), content.indexOf("</body>")).trim();
			content = content.replace("http://yourhostname:4869", baseUrl);
			ZimgFile zimgInfo = new ZimgFile();
			zimgInfo.setZimgPath(content);
			zimgInfo.setZimgName(zimgName);
			return zimgInfo;
		}
		return null;
	}

	public ZimgFile upload(String file) {
		return upload(new File(file));
	}

	public ZimgFile upload(InputStream inputStream) {
		Request req = Request.create(baseUrl + "/upload", METHOD.POST);
		req.getParams().put("file", inputStream);
		return upload(req);
	}

}

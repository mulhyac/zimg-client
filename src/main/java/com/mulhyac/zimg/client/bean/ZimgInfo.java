package com.mulhyac.zimg.client.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * zimg info
 * 文件进行更名，请使用{@link ZimgFile}
 * @author mulhayc
 */
@Deprecated 
public class ZimgInfo implements Serializable {

	private static final long serialVersionUID = 5887741972760354399L;

	private String zimgName;

	private String zimgPath;

	/**
	 * zimg info
	 * 文件进行更名，请使用{@link ZimgFile}
	 * @author mulhayc
	 */
	public ZimgInfo() {
		super();
	}

	/**
	 * zimg info
	 * 文件进行更名，请使用{@link ZimgFile}
	 * @author mulhayc
	 */
	public ZimgInfo(String zimgName, String zimgPath) {
		super();
		this.zimgName = zimgName;
		this.zimgPath = zimgPath;
	}

	public String getZimgPath() {
		return zimgPath;
	}

	public void setZimgPath(String zimgPath) {
		this.zimgPath = zimgPath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getZimgName() {
		return zimgName;
	}

	public void setZimgName(String zimgName) {
		this.zimgName = zimgName;
	}

	/**
	 * 获取文件md5值
	 * 
	 * @param file
	 * @return
	 */
	public static String getMd5(File file) {
		String md5 = null;
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, length);
			}
			BigInteger bigInt = new BigInteger(1, md.digest());
			md5 = bigInt.toString(16);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return md5;
	}

	/**
	 * 根据指定文件获取ZimgFile对象
	 * 
	 * @param file
	 * @return
	 */
	public static ZimgInfo get(File file) {
		String md5 = getMd5(file);
		return get(md5);
	}

	/**
	 * 根据指定md5值获取ZimgFile对象
	 * 
	 * @param md5
	 * @return
	 */
	public static ZimgInfo get(String md5) {
		BigInteger bigInt = new BigInteger(md5);
		String one = bigInt.toString(16).substring(0, 3);
		String two = bigInt.toString(16).substring(3, 6);
		return new ZimgInfo(md5, Integer.parseInt(new BigInteger(one).toString()) / 4 + "/" + Integer.parseInt(new BigInteger(two).toString()) + "/" + md5 + "/0*0");
	}

}

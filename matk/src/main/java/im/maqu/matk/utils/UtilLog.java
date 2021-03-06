/*

 */

package im.maqu.matk.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import im.maqu.matk.constant.CstBase;
import im.maqu.matk.constant.CstCharset;
import im.maqu.matk.constant.CstFile;
import im.maqu.matk.constant.CstHttp;
import im.maqu.matk.constant.CstSize;


public class UtilLog {
	//日志文件最大值
	private static final long LOG_MAX_SIZE = 100 * CstSize.KB; //100K
	
	private static final String BOUNDARY = "---------------------------ae0Ij5GI3KM7gL6Ef1cH2GI3Ef1gL6";
	
	private static final ExecutorService mLogCachedThreadPool = UtilThreadPool.getCachedThreadPool();
	
	/**
	 * Verbose Log, 输出大于或等于Verbose日志级别的信息
	 * @param msg
	 */
	public static void v(String msg) {
		if(CstBase.DEBUG) {
			Log.v(CstBase.PROJECT, UtilString.nullToString(msg));
			//saveLog(msg);
		}
	}

	/**
	 * Debug Log, 输出大于或等于Debug日志级别的信息
	 * @param msg
	 */
	public static void d(String msg) {
		if(CstBase.DEBUG) {
			Log.d(CstBase.PROJECT, UtilString.nullToString(msg));
			//saveLog(msg);
		}
	}

	/**
	 * Info Log,输出大于或等于Info日志级别的信息
	 * @param msg
	 */
	public static void i(String msg) {
		if(CstBase.DEBUG) {
			Log.i(CstBase.PROJECT, UtilString.nullToString(msg));
			//saveLog(msg);
		}
	}

	/**
	 * Warn Log,输出大于或等于Warn日志级别的信息
	 * @param msg
	 */
	public static void w(String msg) {
		if(CstBase.DEBUG) {
			Log.w(CstBase.PROJECT, UtilString.nullToString(msg));
			//saveLog(msg);
		}
	}

	/**
	 * Error Log, 仅输出Error日志级别的信息.
	 * @param msg
	 */
	public static void e(String msg) {
		if(CstBase.DEBUG) {
			Log.e(CstBase.PROJECT, UtilString.nullToString(msg));
			//saveLog(msg);
		}
	}

	/**
	 *
	 * @param msg
	 */
	private static void saveLog(final String msg) {
		mLogCachedThreadPool.execute(new Runnable() {

			@Override
			public void run() {
				String fileName = new SimpleDateFormat("yyyyMMdd_HH")
										.format(new Date())+ CstFile.SUFFIX_LOG;
				String filePath = UtilStorage.createFilePath(null, fileName);
				if(UtilString.isNotBlank(filePath)) {
					File file = new File(filePath);
					writeLogFile(file, msg);
					uploadLogFile(file, msg);
				}
			}
		});
	}

	/**
	 * 将日志写入文件
	 * @param file
	 * @param msg
	 */
	private static void writeLogFile(File file, String msg) {
		//日志内容
		StringBuilder log = new StringBuilder()
				.append(new SimpleDateFormat("yyyyMMdd-hh:mm:ss:SSS").format(new Date()))
				.append(":")
				.append(CstBase.PROJECT)
				.append(":")
				.append(msg)
				.append("\n");
		UtilFile.writeFile(file, log.toString(), true);
	}

	/**
	 * 上传日志文件到服务器
	 * @param file
	 * @param msg
	 */
	private static void uploadLogFile(File file, String msg) {
		if(UtilFile.getFileSize(file) < LOG_MAX_SIZE) {
			return;
		}

		try {
			String url = "http://upload9.163disk.com/?ac=upload&temp=0.8839586193207651";

			HashMap<String, String> contents = new HashMap<String, String>();
	        contents.put("Filename",  file.getName());
	        contents.put("up_folder", "0");
	        contents.put("up_server", "9");
	        contents.put("up_key", 	  "bbb127a4d091297f7de2b3839e2d0740");
	        contents.put("up_list",   "8");
	        contents.put("up_share",  "0");
	        contents.put("up_ip",     "202.101.166.113");
	        contents.put("up_user",   "395871");
	        contents.put("Upload",    "Submit Query");

			FileInputStream in = new FileInputStream(file);
	        byte[] data = UtilStream.toByteArray(in);
	        FormUploadFile uploadFile = new FormUploadFile("Filedata", file.getName(), data);
	        FormUploadFile[] uploadFiles = {uploadFile};
	        httpUploadFile(url, contents, uploadFiles);
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}

	/**
	 * 基于http的文件上传
	 * @param url
	 * @param contents
	 * @param files
	 */
	private static void httpUploadFile(String url, HashMap<String, String> contents, FormUploadFile[] files) {
		HttpURLConnection conn = null;
		DataOutputStream dataOutStream = null;
		BufferedInputStream bufferInStream = null;
		ByteArrayOutputStream arrayOutStream = null;

		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(CstHttp.CONNECTION_TIMEOUT);
			conn.setReadTimeout(CstHttp.READ_TIMEOUT);
			conn.setDoInput(true);// 允许输入
			conn.setDoOutput(true);// 允许输出
			conn.setUseCaches(false);// 不使用Cache
			conn.setRequestMethod(CstHttp.POST);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", CstCharset.UTF_8);
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();
			// 上传的表单参数部分，格式请参考文章
			for (Map.Entry<String, String> entry : contents.entrySet()) {// 构建表单字段内容
				sb.append("--");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}

			dataOutStream = new DataOutputStream(conn.getOutputStream());
			byte[] data = sb.toString().getBytes();
			dataOutStream.write(data);// 发送表单字段数据

			// 上传的文件部分，格式请参考文章
			for (FormUploadFile file : files){
				StringBuilder split = new StringBuilder();
				split.append("--");
				split.append(BOUNDARY);
				split.append("\r\n");
				split.append("Content-Disposition: form-data;name=\"" + file.field + "\";filename=\"" + file.fileName + "\"\r\n");
				split.append("Content-Type: " + file.contentType + "\r\n\r\n");
				dataOutStream.write(split.toString().getBytes());
				dataOutStream.write(file.data, 0, file.data.length);
				dataOutStream.write("\r\n".getBytes());
			}

			byte[] end = ("--" + BOUNDARY + "--\r\n").getBytes();// 数据结束标志
			dataOutStream.write(end);
			dataOutStream.flush();

			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				byte[] buffer = new byte[8192];
				int length = -1;	 //每次读取数据的长度

				bufferInStream = new BufferedInputStream(conn.getInputStream());
				arrayOutStream = new ByteArrayOutputStream();

				while((length = bufferInStream.read(buffer)) != -1){
					arrayOutStream.write(buffer, 0, length);
				}
			}  else { //请求失败，下载失败
				throw new IllegalStateException("HTTP RESPONSE ERROR!!!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//-------------释放资源-----------------
			if(conn != null) conn.disconnect();
			UtilStream.closeInputStream(bufferInStream);
			UtilStream.closeOutputStream(dataOutStream);
			UtilStream.closeOutputStream(arrayOutStream);
		}
 	}
	
	
	/**
	 * @author : Maqu
	 * @date   : 2013-9-19
	 * @desc   :
	 */
	private static class FormUploadFile {
		//上传文件的数据 
		public byte[] data;
		//文件名称 
		public String fileName;
		//请求参数名称
		public String field;
		//内容类型
		public String contentType = CstHttp.APPLICATION_OCTET_STREAM;

		public FormUploadFile() {
			
		}
		
		public FormUploadFile(String field, String fileName, byte[] data) {
			this.field = field;
			this.fileName = fileName;
			this.data = data;
		}
		
		public FormUploadFile(String field, String fileName, byte[] data, String contentType) {
			this.field = field;
			this.fileName = fileName;
			this.data = data;
			this.contentType = contentType;
		}
	}  
	
}

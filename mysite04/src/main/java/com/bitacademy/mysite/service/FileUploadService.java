package com.bitacademy.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.exception.FileUploadServiceException;

@Service
public class FileUploadService {
	@Autowired
	private Environment env;	
	
	public String restore(MultipartFile multipartFile) throws FileUploadServiceException {
		
		String url = null;
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			File restoreDirectory = new File(env.getProperty("fileupload.resourceMapping"));
			if(!restoreDirectory.exists()) {
				restoreDirectory.mkdirs();
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
			String restoreFilename = generateSaveFilename(extName);
			Long fileSize = multipartFile.getSize();
			
			byte[] data = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(env.getProperty("fileupload.resourceMapping") + "/" + restoreFilename);
			os.write(data);
			os.close();
			
			url = env.getProperty("fileupload.uploadLocation") + "/" + restoreFilename;
		} catch (IOException e) {
			throw new FileUploadServiceException(e.toString());
		}
		
		return url;
		
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

}
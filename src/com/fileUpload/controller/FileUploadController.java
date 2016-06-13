package com.fileUpload.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fileUpload.constants.AccessConstants;
import com.fileUpload.model.FileUpload;
import com.fileUpload.util.UnZipFile;
 
@Controller
public class FileUploadController {

	@RequestMapping(value = "/fileUpload/", method = RequestMethod.POST)
	@ResponseBody
	public String continueFileUpload(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest mRequest;
		try {
			mRequest = (MultipartHttpServletRequest) request;
			mRequest.getParameterMap();

			Iterator<String> itr = mRequest.getFileNames();
			while (itr.hasNext()) {
				FileUpload mFile = new FileUpload(mRequest.getFile(itr.next()));
				File uploadedFile = new File(AccessConstants.UPLOAD_LOCATION + mFile.getFile().getOriginalFilename());
				
				// Creating upload folder
				if(!uploadedFile.getParentFile().exists())
					uploadedFile.getParentFile().mkdirs();
				
				// Copying file to upload location
				FileCopyUtils.copy(mFile.getFile().getBytes(), uploadedFile);
				String fileName = mFile.getOriginalFilename();
				System.out.println(fileName);
				UnZipFile uzf = new UnZipFile();
				uzf.unZipIt(uploadedFile, AccessConstants.UNZIP_LOCATION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
        
}
package com.fileUpload.model;

import org.springframework.web.multipart.MultipartFile;
 
public class FileUpload {
 
    MultipartFile file;
    
    public FileUpload(MultipartFile file){
    	this.file = file;
    }
     
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    public String getOriginalFilename(){
    	return file.getOriginalFilename();
    }
}
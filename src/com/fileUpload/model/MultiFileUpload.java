package com.fileUpload.model;

import java.util.ArrayList;
import java.util.List;
 
public class MultiFileUpload {
 
    List<FileUpload> files = new ArrayList<FileUpload>();
     
    public MultiFileUpload(){
        files.add(new FileUpload());
        files.add(new FileUpload());
        files.add(new FileUpload());
    }
     
    public List<FileUpload> getFiles() {
        return files;
    }
 
    public void setFiles(List<FileUpload> files) {
        this.files = files;
    }
}
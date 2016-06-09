package com.fileUpload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.fileUpload.model.FileUpload;
import com.fileUpload.model.MultiFileUpload;
import com.fileUpload.util.FileValidator;
import com.fileUpload.util.MultiFileValidator;
 
@Controller
public class FileUploadController {
 
    private static String UPLOAD_LOCATION="C:/mytemp/";
     
    @Autowired
    FileValidator fileValidator;
     
 
    @Autowired
    MultiFileValidator multiFileValidator;
 
     
    @InitBinder("fileUpload")
    protected void initBinderFileUpload(WebDataBinder binder) {
       binder.setValidator(fileValidator);
    }
 
 
    @InitBinder("multiFileUpload")
    protected void initBinderMultiFileUpload(WebDataBinder binder) {
       binder.setValidator(multiFileValidator);
    }
 
     
    @RequestMapping(value={"/","/welcome"}, method = RequestMethod.GET)
    public String getHomePage(ModelMap model) {
        return "welcome";
    }
 
    @RequestMapping(value="/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileUpload fileModel = new FileUpload();
        model.addAttribute("fileUpload", fileModel);
        return "singleFileUploader";
    }
 
    @RequestMapping(value="/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileUpload fileUpload, BindingResult result, ModelMap model) throws IOException {
 
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "singleFileUploader";
        } else {            
            System.out.println("Fetching file");
            MultipartFile multipartFile = fileUpload.getFile();
 
            //Now do something with file...
            FileCopyUtils.copy(fileUpload.getFile().getBytes(), new File(UPLOAD_LOCATION + fileUpload.getFile().getOriginalFilename()));
             
            String fileName = multipartFile.getOriginalFilename();
            model.addAttribute("fileName", fileName);
            return "success";
        }
    }
 
     
    @RequestMapping(value="/multiUpload", method = RequestMethod.GET)
    public String getMultiUploadPage(ModelMap model) {
        MultiFileUpload filesModel = new MultiFileUpload();
        model.addAttribute("multiFileUpload", filesModel);
        return "multiFileUploader";
    }
 
    @RequestMapping(value="/multiUpload", method = RequestMethod.POST)
    public String multiFileUpload(@Valid MultiFileUpload multiFileUpload, BindingResult result, ModelMap model) throws IOException {
 
         
        if (result.hasErrors()) {
            System.out.println("validation errors in multi upload");
            return "multiFileUploader";
        } else {            
            System.out.println("Fetching files");
            List<String> fileNames= new ArrayList<String>();
             
            //Now do something with file...
            for(FileUpload Upload : multiFileUpload.getFiles()){
                FileCopyUtils.copy(Upload.getFile().getBytes(), new File(UPLOAD_LOCATION + Upload.getFile().getOriginalFilename()));
                fileNames.add(Upload.getFile().getOriginalFilename());
            }
             
            model.addAttribute("fileNames", fileNames);
            return "multiSuccess";
        }
    }
     
     
     
}
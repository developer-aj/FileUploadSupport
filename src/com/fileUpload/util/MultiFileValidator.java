package com.fileUpload.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fileUpload.model.FileUpload;
import com.fileUpload.model.MultiFileUpload;
 
@Component
public class MultiFileValidator implements Validator {
     
    public boolean supports(Class<?> clazz) {
        return MultiFileUpload.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        MultiFileUpload multiUpload = (MultiFileUpload) obj;
         
        int index=0;
         
        for(FileUpload file : multiUpload.getFiles()){
            if(file.getFile()!=null){
                if (file.getFile().getSize() == 0) {
                    errors.rejectValue("files["+index+"].file", "missing.file");
                }
            }
            index++;
        }
         
    }
}
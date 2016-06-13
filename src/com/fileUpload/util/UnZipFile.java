package com.fileUpload.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipFile {
	List<String> fileList;
	
	public void unZipIt(String zipFile, String outputFolder){
		byte[] buffer = new byte[1024];
		
		try{
			// Creating output directory
			File folder = new File(outputFolder);
			if(!folder.exists())
				folder.mkdir();
			
			// Get the zip file content
			ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
			
			// Get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();
			
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator + fileName);
				System.out.println("file unzip : " + newFile.getAbsoluteFile());
				
				// Create all non existing folders otherwise FileNotFound Exception would be thrown
				new File(newFile.getParent()).mkdirs();
				
				FileOutputStream fos = new FileOutputStream(newFile);
				
				int len;
				while((len = zis.read(buffer)) > 0){
					fos.write(buffer, 0, len);
				}
				
				fos.close();
				ze = zis.getNextEntry();
			}
			
			zis.closeEntry();
			zis.close();
			
			System.out.println("Done !");
			
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}

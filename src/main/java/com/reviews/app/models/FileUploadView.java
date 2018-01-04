package com.reviews.app.models;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.reviews.app.util.AmazonSESSample;
 
@ManagedBean(name = "fileUploadView")
@SessionScoped
public class FileUploadView {
 
    public void handleFileUpload(FileUploadEvent event) {
    	
    	 UploadedFile uploadFile=event.getFile();
    	 
         String filePath="/home/ec2-user/files/";
         byte[] bytes=null;
  
             if (null!=uploadFile) {
                 bytes = uploadFile.getContents();
                 String filename = FilenameUtils.getName(uploadFile.getFileName());
                 BufferedOutputStream stream = null;
				try {
					stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+filename)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 try {
					stream.write(bytes);
					 stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
             }
  
      /*   FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_INFO,"Your Photo (File Name "+ uploadFile.getFileName()+ " with size "+
         uploadFile.getSize()+ ")  Uploaded Successfully", ""));*/
             
             try {
				AmazonSESSample.sendEmail();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         FacesMessage message = new FacesMessage("Succesful", "Your file " +event.getFile().getFileName() + " is uploaded.");
         FacesContext.getCurrentInstance().addMessage(null, message);
     }
    	
       
   
}
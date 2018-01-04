package com.reviews.app.primefaces.sentinel;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean(name="fileUploadBean")
@RequestScoped
 
public class FileUploadBean implements Serializable{
 
/**
     *
     */
    private static final long serialVersionUID = 1L;
 
    private String name;
    private UploadedFile resume;
 
    public UploadedFile getResume() {
        return resume;
    }
 
    public void setResume(UploadedFile resume) {
        this.resume = resume;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String uploadResume() throws IOException{
 
        UploadedFile uploadedPhoto=getResume();
        //System.out.println("Name " + getName());
        //System.out.println("tmp directory" System.getProperty("java.io.tmpdir"));
        //System.out.println("File Name " + uploadedPhoto.getFileName());
        //System.out.println("Size " + uploadedPhoto.getSize());
        String filePath="c:/temp/kk/";
        byte[] bytes=null;
 
            if (null!=uploadedPhoto) {
                bytes = uploadedPhoto.getContents();
                String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+filename)));
                stream.write(bytes);
                stream.close();
            }
 
        return "success";
    }
 
    /*  The above code is for file upload using simple mode. */
 
    //This below code is for file upload with advanced mode.
 
    public void uploadPhoto(FileUploadEvent e) throws IOException{
 
        UploadedFile uploadedPhoto=e.getFile();
 
        String filePath="c:/temp/kk/";
        byte[] bytes=null;
 
            if (null!=uploadedPhoto) {
                bytes = uploadedPhoto.getContents();
                String filename = FilenameUtils.getName(uploadedPhoto.getFileName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+filename)));
                stream.write(bytes);
                stream.close();
            }
 
        FacesContext.getCurrentInstance().addMessage("messages",new FacesMessage(FacesMessage.SEVERITY_INFO,"Your Photo (File Name "+ uploadedPhoto.getFileName()+ " with size "+ uploadedPhoto.getSize()+ ")  Uploaded Successfully", ""));
    }
 
}
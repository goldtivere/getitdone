/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.git.dbcon.LoadPPTfile;
import com.git.imgUpload.UploadImagesX;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Gold
 */
@ManagedBean
@ViewScoped
public class AddService implements Serializable{

    private UploadedFile passport_file;
    private String passport_url;
    private String ref_number;
    private String imageLocation;
    private String messangerOfTruth;
    private AddServiceModel model= new AddServiceModel();

    public AddService() {
        ref_number = generateRefNo();
    }

    public String generateRefNo() {

        try {

            String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());

            int rnd = new Random().nextInt(99999753);
            String temp_val = String.valueOf(rnd).concat(timeStamp);
            return temp_val;

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;

        }

    }//end generateRefNo(...)

    public void handleFileUpload(FileUploadEvent event) {

        setPassport_file(event.getFile());
        setPassport_url("");
        setImageLocation("");

        //byte fileNameByte[] = getFile().getContents();
        //System.out.println("fileNameByte:" + fileNameByte);
        FacesMessage message;
        UploadImagesX uploadImagesX = new UploadImagesX();

        try {

            if (!(uploadImagesX.uploadImg(getPassport_file(), "pix".concat(String.valueOf(getRef_number()))))) {

                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, uploadImagesX.getMessangerOfTruth(), uploadImagesX.getMessangerOfTruth());
                FacesContext.getCurrentInstance().addMessage(null, message);

                //value.setPst_url(null);
                return;

            }

            message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            setPassport_url(uploadImagesX.getPst_url());
            setImageLocation(uploadImagesX.getPst_loc());
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception ex) {

            ex.printStackTrace();
            message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }

    }

    public void clearPix() {

        LoadPPTfile loadPPTfile = new LoadPPTfile();

        try {

            String file_ = "pix".concat(String.valueOf(getRef_number())).concat(".jpg");

            if (loadPPTfile.ImagePath().isEmpty() || loadPPTfile.ImagePath() == null) {
                setMessangerOfTruth("Cannot load configuration file...");
                setMessangerOfTruth("Operation failed");
                return;
            }
            //
            Properties ppt = loadPPTfile.getPptFile();
            String url = loadPPTfile.ImagePath();

            File file = new File(url + "".concat(file_));
            file.delete();
            //
            setPassport_file(null);
            passport_file = null;
            setPassport_url("");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public UploadedFile getPassport_file() {
        return passport_file;
    }

    public void setPassport_file(UploadedFile passport_file) {
        this.passport_file = passport_file;
    }

    public String getPassport_url() {
        return passport_url;
    }

    public void setPassport_url(String passport_url) {
        this.passport_url = passport_url;
    }

    public String getRef_number() {
        return ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getMessangerOfTruth() {
        return messangerOfTruth;
    }

    public void setMessangerOfTruth(String messangerOfTruth) {
        this.messangerOfTruth = messangerOfTruth;
    }

    public AddServiceModel getModel() {
        return model;
    }

    public void setModel(AddServiceModel model) {
        this.model = model;
    }

}

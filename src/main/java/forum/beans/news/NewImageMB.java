package forum.beans.news;

import forum.model.GalleryImage;
import forum.services.MaterialsService;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by Maxym on 11/8/2014.
 */
@ManagedBean(name = "newImageMB")
@RequestScoped
public class NewImageMB {

    private String name;

    private String description;

    private UploadedFile file;
    private boolean validation(){
       if(file.getFileName().indexOf(".jpg")>0 ||file.getFileName().indexOf(".png")>0 || file.getFileName().indexOf(".jpeg")>0 || file.getFileName().indexOf(".gif")>0){
        }
        else {
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
                   "Please choose good format", null));
           return false;
       }
        if(file.getFileName().equals("")){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Please choose image", null));
            return false;
        }
        return true;
    }
    public String upload(){
        MaterialsService materialsService=new MaterialsService();
        // Do what you want with the file
        if(validation()){
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String absoluteWebPath = externalContext.getRealPath("/");
                materialsService.copyFile(file.getFileName(), file.getInputstream(), absoluteWebPath + "/resources/gallery/");
                materialsService.copyFile(file.getFileName(), file.getInputstream(), "E:/Projects/forum/max/web/resources/gallery/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            GalleryImage galleryImage= new GalleryImage();
            galleryImage.setAdress(file.getFileName());
            galleryImage.setName(name);
            galleryImage.setDesc(description);
            try{
                materialsService.getGalleryDAO().save(galleryImage);
            }catch (RuntimeException e){
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Image exist"));
            }
        }
        return "/content/gallery.xhtml?image=1";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}

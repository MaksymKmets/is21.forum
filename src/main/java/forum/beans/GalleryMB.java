package forum.beans;


        import forum.model.GalleryImage;
        import forum.model.Message;
        import forum.model.User;
        import forum.services.MaterialsService;
        import java.io.*;
        import javax.enterprise.context.RequestScoped;
        import javax.faces.bean.ManagedBean;
        import javax.faces.context.ExternalContext;
        import javax.faces.context.FacesContext;


/**
 * Created by Maxym on 10/24/2014.
 */
@ManagedBean (name = "galleryMB")
@RequestScoped
public class GalleryMB extends Topic implements Serializable,Commentable{
    private MaterialsService materialsService = new MaterialsService();
    private int countGalleryImages = materialsService.getCountImage();
    private GalleryImage image;
    private int imageNumber;

    public String delete(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        image=getMaterialsService().getGalleryDAO().find(imageNumber);
        String absoluteWebPath = externalContext.getRealPath("/");
        materialsService.deleteFile("E:/Projects/forum/max/web/resources/gallery/" + image.getAdress());
        absoluteWebPath=absoluteWebPath+ "/resources/gallery/"+image.getAdress();
        materialsService.deleteFile(absoluteWebPath);
       // for(image.getMessages();
        materialsService.getGalleryDAO().delete(image);
        materialsService.getGalleryDAO().normalizeGallery();
        countGalleryImages--;
        return back();
    }
    public String next() {
        imageNumber++;
        if (imageNumber < 0) {
            imageNumber = countGalleryImages;
        } else if (imageNumber > countGalleryImages) {
            imageNumber = 1;
        }
        return "gallery?faces-redirect=true&amp;includeViewParams=true";
    }
    public String back() {
        imageNumber--;
        if (imageNumber <= 0) {
            imageNumber = countGalleryImages;
        } else if (imageNumber > countGalleryImages) {
            imageNumber = 1;
        }
        return "gallery?faces-redirect=true&amp;includeViewParams=true";
    }
    public GalleryImage getImage() {
        if (image == null) {
            image = materialsService.getImage(imageNumber);
        }
        return image;
    }

    public void setImage(GalleryImage image) {
        this.image = image;
    }

    public void sendMessage(User user) {
        messageService.setMessage(message);
        Message newm = messageService.createMessage(user);
        getImage().getMessages().add(newm);
        messageService.addMessage();
        materialsService.getGalleryDAO().update(image);
        message = new Message();
    }









    public MaterialsService getMaterialsService() {
        return materialsService;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public int getCountGalleryImages() {

        return countGalleryImages;
    }

    public void setCountGalleryImages(int countGalleryImages) {
        this.countGalleryImages = countGalleryImages;
    }
}

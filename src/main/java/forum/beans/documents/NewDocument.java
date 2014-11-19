package forum.beans.documents;

import forum.model.Document;
import forum.services.MaterialsService;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by Maxym on 11/10/2014.
 */
@ManagedBean(name = "newDocument")
@RequestScoped
public class NewDocument {
    private Document document;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String upload()
    {
        MaterialsService materialsService = new MaterialsService();
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            String absoluteWebPath = externalContext.getRealPath("/");
            materialsService.copyFile(file.getFileName(), file.getInputstream(), absoluteWebPath + "/resources/documents/");
            materialsService.copyFile(file.getFileName(), file.getInputstream(), "E:/Projects/forum/max/web/resources/documents/");
            document.setAdress(file.getFileName());
            materialsService.getDocumentsDAO().save(document);
        }catch(RuntimeException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Document exist"));
            return null;
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed creating"));
            return null;
        }
        return "/content/documents/documents.xhtml?faces-redirect=true";
    }
    public Document getDocument() {
        if(document==null){
            document=new Document();
        }
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}

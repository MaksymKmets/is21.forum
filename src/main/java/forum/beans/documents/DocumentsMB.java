package forum.beans.documents;

import forum.model.Document;
import forum.services.MaterialsService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Maxym on 10/30/2014.
 */
@ManagedBean(name="documentsMB")
@RequestScoped
public class DocumentsMB {
    private UploadedFile file;
    private List<Document> documents;
    private MaterialsService materialsService;
    private DefaultStreamedContent download;

    public DefaultStreamedContent getDownload() {
        return download;
    }

    public void setDownload(DefaultStreamedContent download) {
        this.download = download;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String deleteDocument(Document document){

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String absoluteWebPath = externalContext.getRealPath("/");
        materialsService.deleteFile("E:/Projects/forum/max/web/resources/documents/" + document.getAdress());
        absoluteWebPath=absoluteWebPath+ "/resources/documents/"+document.getAdress();
        materialsService.deleteFile(absoluteWebPath);
        getMaterialsService().getDocumentsDAO().delete(document);
        return "documents.xhtml?faces-redirect=true";
    }
    public String upload(){
        return "uploadDocument.xhtml?faces-redirect=true";
    }
    public void downloadFile(Document document) throws Exception {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        InputStream input =externalContext.getResourceAsStream("/resources/documents/"+document.getAdress());
        setDownload(new DefaultStreamedContent(input,document.getAdress()));
        download.setName(document.getAdress());
    }
    public DocumentsMB(){
        materialsService= new MaterialsService();
        documents=materialsService.getDocuments();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public MaterialsService getMaterialsService() {
        return materialsService;
    }

    public void setMaterialsService(MaterialsService materialsService) {
        this.materialsService = materialsService;
    }

}

package forum.services;

import forum.dao.impl.DocumentsDAO;
import forum.dao.impl.GalleryDAO;
import forum.model.Document;
import forum.model.GalleryImage;

import java.io.*;
import java.util.List;

/**
 * Created by Maxym on 10/24/2014.
 */
public class MaterialsService {

    GalleryDAO galleryDAO;         //work with gallery model
    DocumentsDAO documentsDAO;      //work with documents model

    public GalleryImage getImage(Integer id){       //find image in db by id
       return getGalleryDAO().find(id);
    }
    public List<GalleryImage> getGalleryImages(){  //find all images on gallery

        return getGalleryDAO().getAll();
    }

    public int getCountImage(){                     //get count all images
        return getGalleryDAO().getCountImages();
    }
    public List<Document> getDocuments(){           //get All documents
        return  getDocumentsDAO().getAll();
    }
    public void copyFile(String fileName, InputStream in,String destination) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFile(String path){
        try{

            File file = new File(path);
            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }

        }catch(Exception e){

            e.printStackTrace();

        }
    }



    public GalleryDAO getGalleryDAO() {
        if(galleryDAO==null){
            galleryDAO=new GalleryDAO();
        }
        return galleryDAO;
    }
    public DocumentsDAO getDocumentsDAO() {
        if(documentsDAO==null){
            documentsDAO=new DocumentsDAO();
        }
        return documentsDAO;
    }







    //==========================================Getters Setters===============================================

}

package forum.dao.impl;

import forum.model.GalleryImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

/**
 * Created by Maxym on 10/24/2014.
 */
public class GalleryDAO extends GenericDAO<GalleryImage>{
    final static Logger loger = LogManager.getLogger(MessageDAO.class.getName());
    public GalleryDAO() {
        super(GalleryImage.class);
    }
    public int getCountImages(){
        beginTransaction();
        Criteria cr=session.createCriteria(GalleryImage.class);
        cr.setProjection(Projections.rowCount());
        Number result=(Number)cr.uniqueResult();
        int count=result.intValue();
        commitAndCloseTransaction();
        return count;
    }
    public void normalizeGallery(){
        beginTransaction();
        Query query = session.createSQLQuery(
                "{CALL normalizeGallery()}");
        query.executeUpdate();
        commitAndCloseTransaction();
    }
}

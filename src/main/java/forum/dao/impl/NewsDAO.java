package forum.dao.impl;

import forum.model.GalleryImage;
import forum.model.News;

/**
 * Created by Maxym on 11/7/2014.
 */
public class NewsDAO extends GenericDAO<News> {
    public NewsDAO() {
        super(News.class);
    }
}

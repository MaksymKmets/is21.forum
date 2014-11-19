package forum.services;

import forum.beans.Topic;
import forum.dao.impl.EventDAO;
import forum.dao.impl.NewsDAO;
import forum.dao.impl.OptionDAO;
import forum.dao.impl.SurveyDAO;
import forum.model.News;
import sun.net.www.content.text.Generic;

/**
 * Created by Maxym on 11/7/2014.
 */
public class TopicsService  {

    private NewsDAO newsDAO;
    private EventDAO eventDAO;
    private SurveyDAO surveyDAO;
    private OptionDAO optionDAO;

    public NewsDAO getNewsDAO() {
        if(newsDAO==null){
            newsDAO=new NewsDAO();
        }
        return newsDAO;
    }

    public EventDAO getEventDAO() {
        if(eventDAO==null){
            eventDAO=new EventDAO();
        }
        return eventDAO;
    }

    public SurveyDAO getSurveyDAO() {
        if(surveyDAO==null){
            surveyDAO=new SurveyDAO();
        }
        return surveyDAO;
    }

    public OptionDAO getOptionDAO() {
        if(optionDAO==null){
            optionDAO=new OptionDAO();
        }
        return optionDAO;
    }
}

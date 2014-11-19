package forum.beans.news;

import forum.beans.Commentable;
import forum.beans.Topic;
import forum.model.Message;
import forum.model.News;
import forum.model.User;
import forum.services.MessageService;
import forum.services.TopicsService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Maxym on 10/25/2014.
 */
@ManagedBean(name="newsMB")
@RequestScoped
public class NewsMB extends Topic implements Serializable,Commentable {
    private TopicsService topicsService;
    private List<News> allNews;
    private News newInfo;
    @ManagedProperty("#{(param.news)}")
    private int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void init(){
        newInfo=getTopicsService().getNewsDAO().find(id);
    }

    public String chooseNews(){
        return "currentNews.xhtml?faces-redirect=true&amp;includeViewParams=true";
    }
    public String addNewInfo(){
        return "addNews.xhtml?faces-redirect=true";
    }
    public String editInfo(){
        return "addNews.xhtml?faces-redirect=true&amp;includeViewParams=true";
    }

    public String deleteInfo(News news){
        getTopicsService().getNewsDAO().delete(news);
        for(Message item:news.getMessages()){
            messageService.deleteMessage(item);
        }
        return "news.xhtml?faces-redirect=true";
    }

    public String save(){
        if(id==0){
            newInfo.setDate(getTime());
            getTopicsService().getNewsDAO().save(newInfo);
        }else {
            String content=newInfo.getContent();
            String theme=newInfo.getTheme();
            newInfo=getTopicsService().getNewsDAO().find(id);
            newInfo.setContent(content);
            newInfo.setTheme(theme);
            newInfo.setIdnews(id);
            getTopicsService().getNewsDAO().update(newInfo);
        }
        return "/content/news/news.xhtml?faces-redirect=true";
    }
    public List<News> getAllNews() {
        if(allNews==null){
           allNews= getTopicsService().getNewsDAO().getAllDesc();
        }
        return allNews;
    }

    public void setAllNews(List<News> allNews) {
        this.allNews = allNews;
    }

    public News getNewInfo() {
        if(newInfo==null){
            newInfo=new News();
        }
        return newInfo;
    }

    public void setNewInfo(News newInfo) {
        this.newInfo = newInfo;
    }

    public TopicsService getTopicsService() {
        if(topicsService==null){
            topicsService=new TopicsService();
        }
        return topicsService;
    }

    public void setTopicsService(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @Override
    public void sendMessage(User user) {
        messageService.setMessage(message);
        Message newm = messageService.createMessage(user);
        newInfo=getTopicsService().getNewsDAO().find(id);
        getNewInfo().getMessages().add(newm);
        messageService.addMessage();
        getTopicsService().getNewsDAO().update(newInfo);
        message = new Message();
    }
}

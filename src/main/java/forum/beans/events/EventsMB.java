package forum.beans.events;

import forum.beans.Commentable;
import forum.beans.Topic;
import forum.dao.impl.UserDAO;
import forum.model.Event;
import forum.model.News;
import forum.model.User;
import forum.services.TopicsService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxym on 10/25/2014.
 */
@ManagedBean(name = "eventsMB")
@RequestScoped
public class EventsMB implements Serializable {
    private TopicsService topicsService;
    private List<Event> events;
    private Event event;

    @ManagedProperty("#{(param.event)}")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void init(){ System.out.println();
        System.out.println("LLSAD");
        System.out.println();
        System.out.println(id);
        System.out.println();
        System.out.println();
        event=getTopicsService().getEventDAO().find(id);
        if(event==null){
            System.out.println("safsafasafsas");
            event=new Event();
        }
        else {
            System.out.println(event.getDate());
            System.out.println(event.getHeader());
            System.out.println(event.getIdevent());
            System.out.println(event.getDate());
        }
    }
    public String add(){
        return "addEvent.xhtml?faces-redirect=true";
    }
    public String saveEvent() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(id);
        System.out.println();
        System.out.println();
        if(id==0){
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            dateFormat.format(date);
            event.setDate(new Timestamp(date.getTime()));
        getTopicsService().getEventDAO().save(event);
    }else {
        String content=event.getInfo();
        String theme=event.getHeader();
        event=getTopicsService().getEventDAO().find(id);
        event.setInfo(content);
        event.setHeader(theme);
        event.setIdevent(id);
        getTopicsService().getEventDAO().update(event);
    }
        return "/content/events/events.xhtml?faces-redirect=true";
    }
    public String delete(Event event){
        topicsService.getEventDAO().delete(event);
        return "events.xhtml?faces-redirect=true";
    }
    public String edit(){
        return "addEvent.xhtml?faces-redirect=true&amp;includeViewParams=true";
    }
    public String participate(User user,Event event){
        getTopicsService().getEventDAO().insertUserToEvent(user.getIduser(),event.getIdevent());
        return "events.xhtml?faces-redirect=true";
    }
    public void decline(User user,Event event){
        event.getUsers().remove(user);
        getTopicsService().getEventDAO().update(event);
    }
    public boolean state(User user,Event event){
        if(event.getUsers().contains(user)){
            return false;
        } else {
            return true;
        }
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

    public List<Event> getEvents() {
        if(events==null){
            events=getTopicsService().getEventDAO().getAllDesc();
        }
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Event getEvent() {
        if(event==null){
            event=new Event();
            System.out.println("lol");
        }
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    public int getCount(List<User> list){
       return list.size();
    }


}

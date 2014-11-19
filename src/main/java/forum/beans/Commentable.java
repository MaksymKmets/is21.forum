package forum.beans;

import forum.model.User;

/**
 * Created by Maxym on 10/26/2014.
 */
public interface Commentable {
    public void sendMessage(User user);
      /*  if(messageService==null){
            messageService=new MessageService();
        }
        loger.info(message.getContent());
        messageService.setMessage(message);
        loger.info(message.getContent());
        messageService.sendMessage(user);*/
}

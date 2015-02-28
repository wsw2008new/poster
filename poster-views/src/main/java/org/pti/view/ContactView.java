package org.pti.view;

import org.pti.model.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Produces({MediaType.APPLICATION_JSON})
public class ContactView {
    private final Contact contact;

    public ContactView(String nickName, String name, String number) {
        this.contact = new Contact(nickName, name, number);
    }

    @GET
    @Path("name")
    public String getName(){
        return contact.getName();
    }
    
    @GET
    @Path("number")
    public String getNumber(){
        return contact.getNumber();
    }
    
    @GET
    @Path("nickname")
    public String getNickName(){
        return contact.getNickName();
    }
    
    @GET
    @Path("/")
    public Contact getContact(){
        return contact;
        
    }
}

package com.phonebook.rest;

import com.phonebook.view.ContactView;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path(value = "hello")
public class ContactsService {

    @Path("/")
    public ContactView getMsg(@PathParam("key") String key) {
        return new ContactView(key, "kozubal", "068-017-17-57");
    }
}

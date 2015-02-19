package org.pti.rest;

import org.pti.view.ContactView;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//hello
@Path(value = "hello")
public class ContactsService {

    @Path("/")
    public ContactView getMsg(@PathParam("key") String key) {
        return new ContactView(key, "kozubal", "068-017-17-57");
    }
}

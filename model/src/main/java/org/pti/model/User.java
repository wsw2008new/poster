package org.pti.model;


import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class User {
    private  long userId;
    private String userName;
}

package com.phonebook;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data @AllArgsConstructor
public class Contact implements Serializable{
    private String nickName;
    private String name;
    private String number;
}

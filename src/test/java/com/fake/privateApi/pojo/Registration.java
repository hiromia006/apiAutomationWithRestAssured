package com.fake.privateApi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Registration {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private Integer password;

    public Registration(String name, String email, Integer password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Registration(String email, Integer password) {
        this.email = email;
        this.password = password;
    }
}
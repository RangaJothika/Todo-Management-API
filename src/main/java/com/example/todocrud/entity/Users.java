package com.example.todocrud.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    
    /**
    
     FetchType.EAGER in JPA means that when you retrieve an object (let's say, an author) from the database,
     you also want to eagerly retrieve and load all the related objects (like books written by that author)
     associated with it.

     Cascade types JPA:- https://www.baeldung.com/jpa-cascade-types
     Fetchtype JPA:- https://www.baeldung.com/hibernate-lazy-eager-loading
     
     **/

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval=true)
    @JsonManagedReference
    private List<Todo> todoList = new ArrayList<>();
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}

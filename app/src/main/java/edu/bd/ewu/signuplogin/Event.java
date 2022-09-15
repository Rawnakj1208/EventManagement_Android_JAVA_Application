package edu.bd.ewu.signuplogin;
public class Event {
    String key = "";
    String name = "";
    String datetime = "";
    String place = "";
    String email = "";
    String capacity = "";
    String budget = "";
    String phone = "";
    String description = "";


    public Event(String key, String name, String datetime,String place, String email,String capacity,String budget,String phone,String description){
        this.key = key;
        this.name = name;
        this.datetime = datetime;
        this.place = place;
        this.email = email;
        this.capacity = capacity;
        this.budget = budget;

        this.phone = phone;
        this.description = description;

    }
}

package com.example.abdullahrao.bitgoapp.Store;

public class Hero {
    String image;
    String name,team;


    public Hero(String image, String name, String team) {
        this.image = image;
        this.name = name;
        this.team = team;
    }

    public String getImage() {
        return image;
        //return Picasso.with(this).;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}

package com.mng.chat.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String email;

    private String nickName;
    private String introduction;
    private String givenName;
    private String familyName;
    private String pictureUrl;

    @ManyToMany
    private Set<Room> managedRooms;

    @OneToMany(mappedBy = "owner")
    private Set<Message> messages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}

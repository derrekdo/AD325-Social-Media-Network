package SocialMediaPackage;

import java.util.HashSet;

public class Profile {
    private String name;
    private String status;
    private HashSet<String> friendsList = new HashSet<>();
    private String gender;
    private int age;
    private String occupation;

    //Default constructor
    public Profile(){

    }

    //Creates user profile
    public Profile(String name, String gender, int age, String occupation){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
    }

    //Add friend to friend list
    public void addFriend(String user){
        friendsList.add(user);
    }

    //setters for User data
    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setStatusOnline(){
        status = "Online";
    }

    public void setStatusOffline(){
        status = "Offline";
    }

    public void setStatusDoNotDisturb(){
        status = "Do not Disturb";
    }

    public void setStatusAway(){
        status = "Away";
    }

    public String getName(){
        return name;
    }
}

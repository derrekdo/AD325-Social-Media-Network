package SocialMediaPackage;

import java.util.HashSet;

public class Profile {
    private String name = null;
    private String status = "Offline";
    private HashSet<String> friendsList = new HashSet<>();
    private String gender = null;
    private int age = 0;
    private String occupation = null;

    //Default constructor
    public Profile(){

    }

    //Creates user profile with only a name
    public Profile(String name){
        this.name = name;
    }

    //Creates user profile
    public Profile(String name, String gender, int age, String occupation, String status){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
        this.status = status;
    }

    //Add friend to friend list
    public void addFriend(String user){
        friendsList.add(user);
    }

    /**
     * remove a user from the friends list
     * @param user is the friend removed
     */
    public void removeFriend(String user){
        friendsList.remove(user);
    }

    //getters and setters for User data
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

    /**
     * Displays the friends list
     */
    public void getFriendsList(){
        if(friendsList.isEmpty()){
            System.out.println("No Friends");
        }else{
            System.out.println("Friends: " + friendsList);
        }
    }

    /**
     * Displays the profile data fields in string format
     * @return string format of the Profile
     */
    public String toString(){
        StringBuilder profileData = new StringBuilder("\n\t\t" + name);
        profileData.append("\n------------------------");
        profileData.append("\nStatus: " + status);
        profileData.append("\nAge: " + age);
        profileData.append("\nGender: " + gender);
        profileData.append("\nOccupation: " + occupation);
        profileData.append("\nFriends: " + friendsList);

        return profileData + "";
    }
}

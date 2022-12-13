package SocialMediaPackage;

public class Main {
    public static void main(String[] args) {
        ProfileManager socialNetwork = new ProfileManager();
        createNetwork(socialNetwork);
        socialNetwork.startApp();

    }


    public static void createNetwork(ProfileManager socialNetwork){
        users(socialNetwork);
        friendships(socialNetwork);
    }

    public static void users(ProfileManager socialNetwork){
        socialNetwork.createUser("Luffy", "Male", 19, "Captain", "Online");
        socialNetwork.createUser("Zoro", "Male", 21, "Swordsman", "Do Not Disturb");
        socialNetwork.createUser("Nami", "Female", 21, "Navigator", "Online");
        socialNetwork.createUser("Ussop", "Male", 19, "Sniper", "Idle");
        socialNetwork.createUser("Sanji", "Male", 21, "Chef", "Idle");
        socialNetwork.createUser("Chopper", "Male", 17, "Doctor", "Idle");
        socialNetwork.createUser("Robin", "Female", 30, "Archaeologist", "Offline");
        socialNetwork.createUser("Franky", "Male", 36, "Shipwright", "Online");
        socialNetwork.createUser("Brook", "Male", 90, "Musician", "Offline");
        socialNetwork.createUser("Jinbe", "Male", 46, "Helmsman", "Offline");

    }

    public static void friendships(ProfileManager socialNetwork){
        socialNetwork.addFriend("Luffy", "Zoro");
        socialNetwork.addFriend("Luffy", "Ussop");
        socialNetwork.addFriend("Luffy", "Nami");
        socialNetwork.addFriend("Luffy", "Sanji");
        socialNetwork.addFriend("Luffy", "Robin");
        socialNetwork.addFriend("Luffy", "Franky");
        socialNetwork.addFriend("Luffy", "Brook");
        socialNetwork.addFriend("Luffy", "Chopper");
        socialNetwork.addFriend("Luffy", "Jinbe");

        socialNetwork.addFriend("Sanji", "Nami");
        socialNetwork.addFriend("Sanji", "Robin");
        socialNetwork.addFriend("Sanji", "Chopper");

        socialNetwork.addFriend("Robin", "Brook");
        socialNetwork.addFriend("Robin", "Chopper");
        socialNetwork.addFriend("Robin", "Nami");
        socialNetwork.addFriend("Robin", "Zoro");

        socialNetwork.addFriend("Chopper", "Zoro");
        socialNetwork.addFriend("Chopper", "Ussop");
        socialNetwork.addFriend("Chopper", "Nami");
        socialNetwork.addFriend("Chopper", "Franky");

        socialNetwork.addFriend("Ussop", "Nami");
        socialNetwork.addFriend("Ussop", "Franky");
        socialNetwork.addFriend("Ussop", "Brook");
    }
}

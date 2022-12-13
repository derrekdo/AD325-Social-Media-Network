package SocialMediaPackage;

import ADTPackage.QueueInterface;
import GraphPackage.UndirectedGraph;

import java.util.*;

public class ProfileManager {
    private UndirectedGraph<Profile> network = new UndirectedGraph<>();
    private ArrayList<Profile> profiles = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private boolean loggedIn = false;
    private String currentUser = null;
    private boolean networkOpened = false;
    public ProfileManager(){

    }

    /**
     * Creates the user Profile, adding to the list of users and graph
     * @param name what the user is called
     * @param gender what the user identifies as
     * @param age how old the user is
     * @param occupation what career the user has
     * @param status the users activity on the social network
     */
    public void createUser(String name, String gender, int age, String occupation, String status){
        users.add(name);
        profiles.add(new Profile(name,gender,age,occupation,status));
        network.addVertex(profiles.get(findUser(name)));

    }

    /**
     * Creates a user profile
     * @param scanner
     */
    private void createUser(Scanner scanner){
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();

        users.add(name);
        profiles.add(new Profile(name));
        network.addVertex(profiles.get(findUser(name)));
        currentUser = name;
    }

    /**
     * Adds a gender to the user profile
     * @param scanner takes in user input
     */
    private void setGender(Scanner scanner){
        System.out.println("Enter Gender: ");
        String input = scanner.nextLine();
        profiles.get(findUser(currentUser)).setGender(input);
    }

    private void setAge(Scanner scanner){
        System.out.println("Enter Age: ");
        int input = scanner.nextInt();
        scanner.nextLine();
        profiles.get(findUser(currentUser)).setAge(input);
    }

    /**
     * Adds an occupation to the profile from user input
     * @param scanner takes in user input
     */
    private void setOccupation(Scanner scanner){
        System.out.println("Enter Occupation: ");
        String input = scanner.nextLine();
        profiles.get(findUser(currentUser)).setOccupation(input);
    }

    /**
     * Changes the status of the user
     * @param scanner takes in console input from user
     */
    private void setStatus(Scanner scanner){
        String input = scanner.nextLine();
        System.out.println("(1) Online\n(2) Away\n(3) Do Not Disturb\n(4) Offline");

        if(Objects.equals(input, "Online") || Objects.equals(input, "1")){
            profiles.get(findUser(currentUser)).setStatusOnline();
        }else if(Objects.equals(input, "Away") || Objects.equals(input, "2")){
            profiles.get(findUser(currentUser)).setStatusAway();
        }else if(Objects.equals(input, "Do Not Disturb") || Objects.equals(input, "3")){
            profiles.get(findUser(currentUser)).setStatusDoNotDisturb();
        }else if(Objects.equals(input, "Offline") || Objects.equals(input, "4")){
            profiles.get(findUser(currentUser)).setStatusOffline();
        }
    }

    /**
     * Removes the user from the network
     * @param name the Profile to be removed
     */
    public void removeUser(String name){
        users.remove(name);
        network.removeVertex(profiles.get(findUser(name)));

        //Removes the user from all friends lists
        //Runtime O(N)
        for(int i = 0; i < profiles.size(); i++){
            profiles.get(i).removeFriend(name);
        }
        profiles.remove(profiles.get(findUser(name)));
    }

    /**
     * Creates a connection between two profiles on the network
     * @param user1 The current user
     * @param user2 The user that is being added
     */
    public void addFriend(String user1, String user2){
        int profile1Index = findUser(user1);
        int profile2Index = findUser(user2);

        //Creates an edge(connection) between two profiles in the graph
        network.addEdge(profiles.get(profile1Index), profiles.get(profile2Index));
        //Each profile will add the other profile to their friend list
        profiles.get(profile1Index).addFriend(user2);
        profiles.get(profile2Index).addFriend(user1);
    }

    /**
     * Private helper method to find the index of the profile in the array list
     * @param name the profile whose index wanted to be found
     * @return the index of the profile
     */
    private int findUser(String name){
        int index;
        //iterates through the array list to find the index of the profile passed
        //Runtime O(N)
        for(int i = 0; i < profiles.size(); i++){
            if(Objects.equals(profiles.get(i).getName(), name)){
                index = i;
                return index;
            }
        }
        return -1;
    }

    /**
     * Displays the friends list of the user passed
     * @param name of the user profile
     */
    public void displayFriendsList(String name){
        profiles.get(findUser(name)).getFriendsList();
    }

    /**
     * Displays an array list of all the users in the social network
     */
    public void displayUsers(){
        System.out.println(users);
    }

    /**
     * Displays the profile data
     * @param name the selected users profile
     */
    public void displayProfile(String name){
        System.out.println(profiles.get(findUser(name)));
    }

    /**
     * Performs breadth first search starting from the profile entered
     * @param name of the profile origin
     */
    public void bfs(String name){
        bfs1(name);
        QueueInterface<Profile> que = network.getBreadthFirstTraversal(profiles.get(findUser(name)));

        //Runtime O(1)
        while(!que.isEmpty()){
            System.out.println(que.dequeue() + " ");
        }
    }

    /**
     * private helper method for breath first search(bfs)
     * is used in case a profile(vertex) is removed so it no longer appears in the social network(graph)
     * @param name is the origin/starting point for bfs
     */
    private void bfs1(String name){
        QueueInterface<Profile> que = network.getBreadthFirstTraversal(profiles.get(findUser(name)));
    }

    /**
     * Starts the process of creating and using the account on the social network
     * Any profiles added,changed, or removed exist only during the call of this method
     */
    public void startApp(){
        networkOpened = true;
        Scanner scanner = new Scanner(System.in);
        //checks if the app has been opened
        while(networkOpened) {
            String prompt = "\nWhat Would You Like To Do:";
            String options = "(1) Log in\n(2) Create New Account\n(3) Close";

            System.out.println(prompt);
            System.out.println(options);
            //takes in user input
            String input = scanner.nextLine();

            //Based on user input, the program will create or log on to an account
            //The user may also exit the app, thus ending the program
            if (Objects.equals(input, "Create New Account") || Objects.equals(input, "2")) {
                signUp(scanner);
            }else if (Objects.equals(input, "Log in") || Objects.equals(input, "1")) {
                logIn(scanner,prompt);
            }else{
                System.out.println("Now Exiting . . .");
                //the program ends
                networkOpened = false;
            }
        }
    }

    /**
     * Walks the user through the process of creating a new profile
     * @param scanner takes in user input
     */
    private void signUp(Scanner scanner){
        createUser(scanner);
        setAge(scanner);
        setGender(scanner);
        setOccupation(scanner);
        //sets current user to null after profile creation is finished
        currentUser = null;
    }

    /**
     * Logs in to the user selected
     * @param scanner takes input from the console the user entered
     * @param prompt asks what the user would like to do
     */
    private void logIn(Scanner scanner, String prompt){
        //Options available when logged in
        String options = """
                (1) Add Friend
                (2) Display Profile
                (3) Display All Profiles
                (4) Display All Users
                (5) View Friends
                (6) Delete Account
                (7) Log Out""";

        System.out.println("Log on to Which Account?");
        System.out.println(users);
        //Takes in user input (account selected)
        String input = scanner.nextLine();
        System.out.println("Logged On: " + input);

        //current user will change to the user logged in
        //the user will also be logged in
        currentUser = input;
        loggedIn = true;

        //while logged in, the user will be provided option for the account
        //Runtime O(1)
        while(loggedIn){
            System.out.println(prompt);
            System.out.println(options);
            options(scanner);
        }
    }

    /**
     * logs the user out
     */
    private void logOut(){
        loggedIn = false;
        currentUser = null;
    }

    /**
     * Takes in the option selected and runs the method corresponding to it
     * @param scanner takes in user input
     */
    private void options(Scanner scanner){
        String input = scanner.nextLine();

        if(Objects.equals(input, "Add Friend") || Objects.equals(input, "1")){
            System.out.println("Who would you like to add?\n" + users);
            input = scanner.nextLine();
            addFriend(currentUser, input);
        }else if(Objects.equals(input, "Display Profile") || Objects.equals(input, "2")){
            System.out.println("Display which profile:\n" + users);
            input = scanner.nextLine();
            displayProfile(input);
        }else if(Objects.equals(input, "Display All Profiles") || Objects.equals(input, "3")){
            bfs(currentUser);
        }else if(Objects.equals(input, "Display All Users") || Objects.equals(input, "4")){
            displayUsers();
        }else if(Objects.equals(input, "View Friends") || Objects.equals(input, "5")){
            displayFriendsList(currentUser);
        }else if(input == "Change Status" || input == "6"){
            setStatus(scanner);
        }else if(Objects.equals(input, "Delete Account") || Objects.equals(input, "7")){
            removeUser(currentUser);
            logOut();
        }else if(Objects.equals(input, "Log Out") || Objects.equals(input, "8")){
            logOut();
        }
    }
}

package au.edu.unsw.infs3634.gamifiedlearning;

public class User {

    public String fullName, age, email;

    //Constructor #1 - Allows you just to access these variables so it is empty
    public User(){}

    //Constructor #2  - Accepts arguments
    public User(String fullName, String age, String email){
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

}

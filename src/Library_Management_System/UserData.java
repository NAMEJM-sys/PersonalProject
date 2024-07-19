package Library_Management_System;

public class UserData {
    String name;
    String age;
    String email;
    String address;
    String ID;
    String PW;
    String bookName;
    void setID(String ID){
        this.ID = ID;
    }
    void setPW(String PW){
        this.PW = PW;
    }
    void setName(String name){
        this.name = name;
    }
    void setAge(String age){
        this.age = age;
    }
    void setEmail(String email){
        this.email = email;
    }
    void setAddress(String address){
        this.address = address;
    }
    void setRental(String bookName){
        this.bookName = bookName;
    }
}

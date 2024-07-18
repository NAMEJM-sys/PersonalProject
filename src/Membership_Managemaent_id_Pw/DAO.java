package Membership_Managemaent_id_Pw;

// 데이터 저장
public class DAO {
    String name;
    String age;
    String email;
    String address;
    String ID;
    String PW;
    void setPW(String PW){
        this.PW = PW;
    }
    void setID(String ID){
        this.ID = ID;
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
}

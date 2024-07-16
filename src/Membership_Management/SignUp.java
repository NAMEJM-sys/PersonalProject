package Membership_Management;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;


public class SignUp {
    String name;
    String age;
    String email;
    String address;
    Scanner sc = new Scanner(System.in);
    public static List<SignUp> MemberInfo = new ArrayList<SignUp>();

    public SignUp() {}
    public SignUp(String name, String age, String email, String address){
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public void InputInformation() {
        try {

        } catch (Exception e){

        }
        InputName();
        while (true) {
            if (ValidateName()) { break;
            } else {
                System.out.println("옳바른 이름이 아닙니다.");
                InputName();
            }
        }

        try {
            InputAge();
            while (true) {
                if (ValidateAge() && this.age.charAt(0) != '0') { break;
                } else {
                    System.out.println("옳바른 나이가 아닙니다.");
                    InputAge();
                }
            }
        }catch (Exception e){
            System.out.println("숫자 쓰라고 시발");
            InputAge();
        }

        InputEmail();
        while (true) {
            if (ValidateEmail()) { break;
            } else {
                System.out.println("옳바른 이메일이 아닙니다.");
                InputEmail();
            }
        }

        InputAddress();
        while (true) {
            if (ValidateAddress() && this.address.charAt(this.address.length()-1) == '구') { break;
            } else {
                System.out.println("옳바른 주소가 아닙니다.");
                InputAge();
            }
        }

        MemberInfo.add(new SignUp(this.name, this.age, this.email, this.address));
        System.out.println("\n회원가입이 완료 되었습니다.");
        SelectMenu.print();

    }


    public void InputName() {
        System.out.print("이름을 입력하시오: ");
        this.name = sc.nextLine();
    }

    public void InputAge() {
        System.out.print("나이를 입력하시오: ");
        this.age = sc.nextLine();
        // try-catch
    }

    public void InputEmail() {
        System.out.print("E-mail 을(를) 입력하시오: ");
        this.email = sc.nextLine();
    }

    public void InputAddress() {
        System.out.print("주소(거주 구)를 입력하시오: ");
        this.address = sc.nextLine();
    }




    public boolean ValidateName() {
        Pattern pattern = Pattern.compile("^[가-힣]{2,3}$");
        Matcher matcher = pattern.matcher(this.name);
        return matcher.matches();
    }

    public boolean ValidateAge() {
        Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
        Matcher matcher = pattern.matcher(this.age);
        return Integer.parseInt(this.age) < 100 && matcher.matches();
    }

    public boolean ValidateEmail() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

    public boolean ValidateAddress() {
        Pattern pattern = Pattern.compile("^[가-힣]{2,5}$");
        Matcher matcher = pattern.matcher(this.address);
        return matcher.matches();
    }
}

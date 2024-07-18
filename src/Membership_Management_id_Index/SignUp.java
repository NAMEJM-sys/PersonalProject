package Membership_Management_id_Index;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class SignUp {
    String name;
    String age;
    String email;
    String address;
    int id;
    Scanner sc = new Scanner(System.in);
    public static LinkedHashMap<Integer, SignUp> MemberInfo = new LinkedHashMap<>();

    public SignUp() {}
    public SignUp(String name, String age, String email, String address){
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public void InputInformation() {
        SignUp signUp = new SignUp();
        InputID();
        InputName();
        InputAge();
        InputEmail();
        InputAddress();
        addInfo(this.id, new SignUp(this.name, this.age, this.email, this.address));

        System.out.println("회원가입이 완료 되었습니다.");
        SelectMenu.print();
    }

    public void revision() {
        System.out.print("수정할 아이디를 입력하시오: ");
        int id = sc.nextInt();
        sc.nextLine();
        try {
            if (MemberInfo.containsKey(id)) {
                System.out.println("\n수정할 정보를 입력해 주세요.");
                InputName();
                MemberInfo.get(id).name = this.name;
                InputAge();
                MemberInfo.get(id).age = this.age;
                InputEmail();
                MemberInfo.get(id).email = this.email;
                InputAddress();
                MemberInfo.get(id).address = this.address;

                System.out.println("\n회원 정보 수정이 완료 되었습니다.\n");
                SelectMenu.print();
            } else {
                System.out.println("\n존재하지 않는 ID 입니다.\n");
                revision();
            }
        }catch (Exception e){
            System.out.println("\n존재하지 않는 ID 입니다.\n");
            revision();
        }
    }


    // LinkedHashMap을 활용하기 위한 저장 참조 메서드
    public void addInfo(int id, SignUp sign){
        MemberInfo.put(id, sign);
    }

    // 회원 정보 확인
    public void CheckInfo() {
        System.out.print("확인하려는 회원 정보의 ID를 입력하시오: ");
        int num = sc.nextInt();
        try {
            if (MemberInfo.containsKey(num)) {
                System.out.printf("이름: %s\t나이: %s%n이메일: %s\t주소: %s%n", MemberInfo.get(num).name, MemberInfo.get(num).age,
                        MemberInfo.get(num).email, MemberInfo.get(num).address);
            }else{
                System.out.println("존재하지 않는 ID입니다.");
                CheckInfo();
            }
        }catch (Exception e){
            System.out.println("존재하지 않는 ID입니다.");
            CheckInfo();
        }

        SelectMenu.print();
    }

    // 회원 탈퇴
    public void DeleteInfo() {
        System.out.print("삭제할 아이디를 입력하시오: ");
        int id = sc.nextInt();
        sc.nextLine();

        try {
            if (MemberInfo.containsKey(id)) {
                System.out.print("삭제할 아이디와 일치하는 이름을 입력하시오: ");
                String name = sc.nextLine();

                if (MemberInfo.get(id).name.equals(name)) {
                    MemberInfo.remove(id);
                    System.out.println("회원 정보가 삭제되었습니다.");
                    SelectMenu.print();
                } else {
                    System.out.println("아이디의 이름 정보가 일치하지 않습니다.");
                    DeleteInfo();
                }
            } else {
                System.out.println("존재하지 않는 ID 입니다.");
                SelectMenu.print();
            }
        }catch (Exception e) {
            System.out.println("존재하지 않는 ID 입니다.");
            SelectMenu.print();
        }
    }

    // 회원 정보 입력 및 유효성 검사 결과에 따른 반복
    public void InputName() {
        System.out.print("이름을 입력하시오: ");
        this.name = sc.nextLine();

        while (true) {
            if (ValidateName()) { break;
            } else {
                System.out.println("옳바른 이름이 아닙니다.");
                InputName();
            }
        }
    }

    public void InputID() {
        System.out.print("아이디을 입력하시오(1~100): ");
        this.id = sc.nextInt();
        sc.nextLine();
    }

    public void InputAge() {
        System.out.print("나이를 입력하시오: ");
        this.age = sc.nextLine();

        try {
            while (true) {
                if (ValidateAge() && this.age.charAt(0) != '0') { break;
                } else {
                    System.out.println("옳바른 나이가 아닙니다.");
                    InputAge();
                }
            }
        }catch (Exception e){
            System.out.println("예외 발생");
            InputAge();
        }

    }

    public void InputEmail() {
        System.out.print("E-mail 을(를) 입력하시오: ");
        this.email = sc.nextLine();

        while (true) {
            if (ValidateEmail()) { break;
            } else {
                System.out.println("옳바른 이메일이 아닙니다.");
                InputEmail();
            }
        }
    }

    public void InputAddress() {
        System.out.print("주소(거주 구)를 입력하시오: ");
        this.address = sc.nextLine();

        while (true) {
            if (ValidateAddress() && this.address.charAt(this.address.length()-1) == '구') { break;
            } else {
                System.out.println("옳바른 주소가 아닙니다.");
                InputAddress();
            }
        }
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

    void initializeConsole() {
        System.out.print("\n".repeat(50));
    }
}


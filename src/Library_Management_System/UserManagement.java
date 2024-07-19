package Library_Management_System;

import Membership_Managemaent_id_Pw.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class UserManagement {
    Scanner sc = new Scanner(System.in);
    List<UserData> userData = new ArrayList<>();
    int index = 0;
    BookManagement BM;

    // 관리자 Index 0 으로 프로그램 실행 마다 설정
    public UserManagement(){
        BM = new BookManagement(this);
        Administrator();
    }

    // 관리자 정보 입력
    void Administrator() {
        UserData ud = new UserData();
        ud.setID("admin");
        ud.setPW("12345");
        ud.setName("관리자");
        userData.add(ud);
    }

    // 메인 메뉴
    void SelectMenu() throws InterruptedException {
        while (true) {
            System.out.println("==================================================");
            System.out.println("진행할 메뉴를 선택하세요\n");
            System.out.println("[1] 회원 가입\t[2] 로그인\t[3] 프로그램 종료");
            System.out.println("==================================================");
            String selectNum = sc.nextLine();

            switch (selectNum) {
                case "1":
                    SingUP();
                    break;
                case "2":
                    Login();
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    Thread.sleep(500);
                    initializeConsole();
                    break;
            }
        }
    }

    // 로그인 메뉴(유저 or 관리자)
    void SelectMenu2(String name) throws InterruptedException {
        if(index == 0) {
            AdministratorMenu(name);
        }else UserMenu(name);
    }

    // 로그인(유저) -> 메뉴
    void UserMenu(String name) throws InterruptedException{
        initializeConsole();
        System.out.println("==================================================");
        System.out.printf("환영합니다 \"%s\" 유저님%n", name);
        System.out.println("==================================================");
        System.out.println("진행할 메뉴를 선택하세요\n");
        System.out.println("도서 -> [1] 도서 대여 - 미구현   [2] 도서 반납 - 미구현   [3] 대여 확인 - 미구현\n프로필 -> [4]개인 정보 확인   [5]개인 정보 수정   [6]회원 탈퇴");
        System.out.println("로그 아웃 -> [0] 메인 메뉴");
        System.out.println("==================================================");
        int selectNum = sc.nextInt();
        sc.nextLine();

        switch (selectNum) {
            case 1:
                break;
            case 4:
                CheckInfo(index);
                break;
            case 5:
                ChangeInfo(index);
                break;
            case 6:
                DeleteInfo(index);
                break;
            case 0:
                SelectMenu();
                break;
            default:
        }
    }

    // 로그인(관리자) -> 메뉴
    void AdministratorMenu(String name) throws InterruptedException{
        while(true) {
            initializeConsole();
            System.out.println("==================================================");
            System.out.printf("환영합니다 \"%s\"님%n", name);
            System.out.println("==================================================");
            System.out.println("진행할 메뉴를 선택하세요\n");
            System.out.println("도서 관리 -> [1] 도서 확인   [2] 도서 추가   [3] 도서 파기 및 분실 처리   [4] 대여 관리(전체) - 미구현 \n유저 관리 -> [5] 유저 정보 확인(전체)   [6]유저 정지 - 미구현");
            System.out.println("로그 아웃 -> [0] 메인 메뉴");
            System.out.println("==================================================");
            String selectNum = sc.nextLine();

            switch (selectNum) {
                case "1":
                    BM.CheckBookList(index);
                    break;
                case "2":
                    BM.AddBook(index);
                    break;
                case "3":
                    BM.DeleteBook(index);
                    break;
                case "4":
                    break;
                case "5":
                    CheckInfoAll(index);
                    break;
                case"0":
                    SelectMenu();
                    break;
                default:
                    System.out.println("똑바로 쓰라고 시발");
                    Thread.sleep(500);
                    initializeConsole();
                    break;
            }
        }
    }

    // 회원 가입
    public void SingUP() throws InterruptedException {
        initializeConsole();
        System.out.println("==================================================");
        System.out.println("ID, PW를 입력하세요");
        System.out.println("==================================================");
        UserData ud = new UserData();

        ud.setID(InputID());
        ud.setPW(InputPW());

        initializeConsole();
        System.out.println("==================================================");
        System.out.println("ID, PW가 정상적으로 입력 되었습니다.");
        System.out.println("==================================================");
        Thread.sleep(1000);

        initializeConsole();
        System.out.println("==================================================");
        System.out.println("고객 정보를 입력하세요 (이름, 나이, 이메일, 주소)");
        System.out.println("==================================================");
        ud.setName(InputName());
        ud.setAge(InputAge());
        ud.setEmail(InputEmail());
        ud.setAddress(InputAddress());

        userData.add(ud);
        initializeConsole();
        System.out.println("==================================================");
        System.out.println("회원 가입이 완료되었습니다.");
        System.out.println("==================================================");
        Thread.sleep(1000);
        initializeConsole();
        SelectMenu();
    }

    // 로그인
    public void Login() throws InterruptedException {
        initializeConsole();
        System.out.println("==================================================");
        System.out.println("로그인을 시도합니다.");
        System.out.println("==================================================");
        UserData ud = new UserData();

        String a = LoginID();
        ud.setPW(LoginPW());

        System.out.println("==================================================");
        System.out.println("로그인을 성공했습니다.");
        System.out.println("==================================================\n");
        Thread.sleep(500);
        initializeConsole();

        for (int i = 0; i < userData.size(); i++) {
            if (a.equals(userData.get(i).ID)) {
                index = i;
                break;
            }
        }
        SelectMenu2(userData.get(index).name);
    }

    // 관리자 - 회원 정보 전체 확인
    public void CheckInfoAll(int index) throws InterruptedException {
        if(userData.size()>1) {
            for (int i = 1; i < userData.size(); i++) {
                System.out.println("==================================================");
                System.out.printf("%d번째 유저\n",i);
                System.out.printf("ID: %s%n이름: %s%n나이: %s%nE-mail:%s%n주소: %s%n", userData.get(i).ID, userData.get(i).name, userData.get(i).age,
                        userData.get(i).email, userData.get(i).address);
                System.out.println("==================================================\n");
            }
        }else{
            System.out.println("==================================================");
            System.out.println("일반 유저가 존재하지 않습니다.");
            System.out.println("==================================================");
            Thread.sleep(500);
            initializeConsole();
        }
        while (true){
            System.out.println("이전 메뉴로 돌아가려면 Y or y를 눌러주세요");
            System.out.print(">");
            String commend = sc.nextLine();

            if(commend.equals("Y")|| commend.equals("y")) break;
            else {
                initializeConsole();
                System.out.println("잘못 입력하셨습니다.");
            }
        }
        SelectMenu2(userData.get(index).name);
    }

    // 유처 - 회원 정보 확인
    public void CheckInfo(int Index) throws InterruptedException {
        initializeConsole();
        while (true) {
            if (LoginPW().equals(userData.get(Index).PW)) {
                System.out.println("비밀번호 인증에 성공하였습니다.");
                Thread.sleep(500);
                initializeConsole();
                break;
            }
        }
        initializeConsole();
        System.out.println("==================================================");
        System.out.printf("ID: %s%n이름: %s%n나이: %s%nE-mail:%s%n주소: %s%n", userData.get(Index).ID, userData.get(Index).name, userData.get(Index).age, userData.get(Index).email, userData.get(Index).address);
        System.out.println("==================================================\n");

        while (true){
            System.out.println("이전 메뉴로 돌아가려면 Y or y를 눌러주세요");
            System.out.print(">");
            String commend = sc.nextLine();

            if(commend.equals("Y")|| commend.equals("y")) break;
            else {
                initializeConsole();
                System.out.println("잘못 입력하셨습니다.");
            }
        }

        SelectMenu2(userData.get(Index).name);
    }

    // 유처 - 회원 정보 수정
    public void ChangeInfo(int Index) throws InterruptedException {
        while (true) {
            if (LoginPW().equals(userData.get(Index).PW)) break;
            else
                System.out.println("비밀번호 인증에 실패했습니다.");
        }

        boolean dataChanged = false;
        while (true) {
            System.out.println("==================================================");
            System.out.println("수정할 고객 정보를 선택하세요 ([1]이름, [2]나이, [3]이메일, [4]주소)");
            System.out.println("[5] 이전 메뉴]");
            System.out.println("==================================================");
            int select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1:
                    ChangeName(Index);
                    break;
                case 2:
                    ChangeAge(Index);
                    break;
                case 3:
                    ChangeEmail(Index);
                    break;
                case 4:
                    ChangeAddress(Index);
                    break;
                case 5:
                    SelectMenu2(userData.get(Index).name);
                    return;
                default:

            }
        }
    }

    // 유저 - 회원 탈퇴
    void DeleteInfo(int Index) throws InterruptedException {
        while (true) {
            if (LoginPW().equals(userData.get(Index).PW)) break;
            else
                System.out.println("비밀번호 인증에 실패했습니다.");
        }
        System.out.printf("%s 회원 탈퇴되었습니다.%n", userData.get(Index).name);
        userData.remove(Index);
        SelectMenu();
    }

    // 유저 - 회원 정보 수정 관련 메서드
    void ChangeName(int Index) {
        System.out.print("수정할 ");
        userData.get(Index).name = InputName();
    }

    void ChangeAge(int Index) {
        System.out.print("수정할 ");
        userData.get(Index).age = InputAge();
    }

    void ChangeEmail(int Index) {
        System.out.print("수정할 ");
        userData.get(Index).email = InputEmail();
    }

    void ChangeAddress(int Index) {
        System.out.print("수정할 ");
        userData.get(Index).address = InputAddress();
    }

    // 로그인 관련 입력
    public String LoginID() {
        while (true) {
            System.out.println("아이디를 입력하시오 (영문 & 숫자, 4 ~ 15자)");
            System.out.print(">");
            String id = sc.nextLine();

            boolean check = false;
            if (ValidateID(id)) {
                for (int i = 0; i < userData.size(); i++) {
                    if (id.equals(userData.get(i).ID)) {
                        check = true;
                        break;
                    }
                }
                if (check) return id;
                else {
                    System.out.println("존재하지 않는 ID입니다.");
                }
            } else {
                System.out.println("옳바르지 않는 ID 형식입니다.");
            }
        }
    }

    public String LoginPW() throws InterruptedException {
        while (true) {
            System.out.println("비밀번호를 입력하시오 (영문 & 숫자, 4 ~ 15자)");
            System.out.print(">");
            String pw = sc.nextLine();
            boolean check = false;
            if (ValidateID(pw)) {
                for (int i = 0; i < userData.size(); i++) {
                    if (pw.equals(userData.get(i).PW)) {
                        check = true;
                        break;
                    }
                }
                if (check) return pw;
                else {
                    System.out.println("비밀번호 인증에 실패하였습니다.");
                    Thread.sleep(500);
                    initializeConsole();
                }
            } else {
                System.out.println("옳바르지 않는 PW 형식입니다.");
                Thread.sleep(500);
                initializeConsole();
            }
        }
    }

    // 회원 가입 관련 입력
    String InputID() {
        while (true) {
            System.out.print("아이디를 입력하시오 (영문 & 숫자, 4 ~ 15자): ");
            String id = sc.nextLine();

            if (ValidateID(id)) {
                return id;
            } else {
                System.out.println("올바른 아이디 형식이 아닙니다. 다시 입력해주세요.");
            }
        }
    }

    String InputPW() {
        String pw;
        while (true) {
            System.out.print("비밀번호를 입력하시오 (영문 & 숫자, 4 ~ 15자): ");
            pw = sc.nextLine().trim(); // 사용자 입력 받기

            if (ValidatePW(pw)) {
                break; // 유효한 ID가 입력되면 반복문 탈출
            } else {
                System.out.println("올바른 비밀번호 형식이 아닙니다. 다시 입력해주세요.");
            }
        }
        return pw;
    }

    String InputName() {
        while (true) {
            System.out.print("이름을 입력하시오: ");
            String name = sc.nextLine();

            if (ValidateName(name)) {
                return name;
            } else {
                System.out.println("올바른 이름의 형식이 아닙니다. 다시 입력해주세요.");
            }
        }
    }

    String InputAge() {
        while (true) {
            try {
                System.out.print("나이를 입력하시오: ");
                String age = sc.nextLine();

                if (ValidateAge(age) && age.charAt(0) != '0') {
                    return age;
                } else {
                    System.out.println("올바른 나이의 형식이 아닙니다. 다시 입력해주세요.");
                }
            } catch (Exception e) {
                System.out.println("올바른 나이의 형식이 아닙니다. 다시 입력해주세요.");
            }
        }
    }

    String InputEmail() {
        while (true) {
            System.out.print("E-mail 을 입력하시오: ");
            String email = sc.nextLine();

            if (ValidateEmail(email)) {
                return email;
            } else {
                System.out.println("올바른 E-mail 형식이 아닙니다. 다시 입력해주세요.");
            }
        }
    }

    String InputAddress() {
        while (true) {
            System.out.print("거주지을 입력하시오(- 구): ");
            String address = sc.nextLine();

            if (ValidateAddress(address)) {
                return address;
            } else {
                System.out.println("올바른 거주지 형식이 아닙니다. 다시 입력해주세요.");
            }
        }
    }

    // 정보 입력 유효성 검사
    boolean ValidateID(String ID) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,15}$");
        Matcher matcher = pattern.matcher(ID);
        return matcher.matches();
    }

    boolean ValidatePW(String PW) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,15}$");
        Matcher matcher = pattern.matcher(PW);
        return matcher.matches();
    }

    boolean ValidateName(String name) {
        Pattern pattern = Pattern.compile("^[가-힣]{2,3}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean ValidateAge(String age) {
        Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
        Matcher matcher = pattern.matcher(age);
        return Integer.parseInt(age) < 100 && matcher.matches();
    }

    public boolean ValidateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean ValidateAddress(String address) {
        Pattern pattern = Pattern.compile("^[가-힣]{2,5}$");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    void initializeConsole() {
        System.out.print("\n".repeat(50));
    }

}

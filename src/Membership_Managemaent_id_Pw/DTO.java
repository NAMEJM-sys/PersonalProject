package Membership_Managemaent_id_Pw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class DTO {
    Scanner sc = new Scanner(System.in);
    List<DAO> Membership = new ArrayList<>();
    int index = 0;


    // 로그인, 회원가입 메서드
    void SelectMenu() throws InterruptedException {
        while (true) {
            System.out.println("==================================================");
            System.out.println("진행할 메뉴를 선택하세요\n");
            System.out.println("[1] 회원 가입\t[2] 로그인\t[3] 프로그램 종료");
            System.out.println("==================================================");
            String selectNum = sc.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();

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

    // 로그인이 가능할 경우 활성화 되는 메서드
    void SelectMenu2(String name) throws InterruptedException {
        System.out.println("==================================================");
        System.out.printf("환영합니다 %s 님%n",name);
        System.out.println("==================================================");
        System.out.println("진행할 메뉴를 선택하세요\n");
        System.out.println("[1] 회원 정보 확인\t[2] 회원 정보 수정\n[3] 회원 탈퇴\n[4] 로그 아웃");
        System.out.println("==================================================");
        int selectNum = sc.nextInt();
        sc.nextLine();

        switch (selectNum){
            case 1:
                CheckInfo(index);
                break;
            case 2:
                ChangeInfo(index);
                break;
            case 3:
                DeleteInfo(index);
                break;
            case 4:
                SelectMenu();
                break;
            default:
        }
    }

    // 회원 가입
    public void SingUP() throws InterruptedException {
        initializeConsole();
        System.out.println("==================================================");
        System.out.println("ID, PW를 입력하세요");
        System.out.println("==================================================");
        DAO dao = new DAO();

        dao.setID(InputID());
        dao.setPW(InputPW());

        initializeConsole();
        System.out.println("==================================================");
        System.out.println("ID, PW가 정상적으로 입력 되었습니다.");
        System.out.println("==================================================");
        Thread.sleep(1000);

        initializeConsole();
        System.out.println("==================================================");
        System.out.println("고객 정보를 입력하세요 (이름, 나이, 이메일, 주소)");
        System.out.println("==================================================");
        dao.setName(InputName());
        dao.setAge(InputAge());
        dao.setEmail(InputEmail());
        dao.setAddress(InputAddress());

        Membership.add(dao);
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
        System.out.println("==================================================");
        System.out.println("로그인을 시도합니다.");
        System.out.println("==================================================");
        DAO dao = new DAO();

        String a = LoginID();
        dao.setPW(LoginPW());

        System.out.println("==================================================");
        System.out.println("로그인을 성공했습니다.");
        System.out.println("==================================================\n");

        for(int i=0; i<Membership.size(); i++) {
            if(a.equals(Membership.get(i).ID)) {
                index = i;
                break;
            }
        }
        SelectMenu2(Membership.get(index).name);
    }

    // 회원 정보 체크
    public void CheckInfo(int Index) throws InterruptedException {
        while (true) {
            if (LoginPW().equals(Membership.get(Index).PW)) break;
            else
                System.out.println("비밀번호 인증에 실패했습니다.");
        }
        System.out.printf("ID: %s%n이름: %s%n나이: %s%nE-mail:%s%n주소: %s%n",Membership.get(Index).ID, Membership.get(Index).name, Membership.get(Index).age, Membership.get(Index).email, Membership.get(Index).address);
        SelectMenu2(Membership.get(Index).name);
    }

    // 회원 정보 수정
    public void ChangeInfo(int Index) throws InterruptedException {
        while (true) {
            if (LoginPW().equals(Membership.get(Index).PW)) break;
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
                    SelectMenu2(Membership.get(Index).name);
                    return;
                default:

            }
        }
    }

    // 회원 정보 삭제 관련 메서드
    void DeleteInfo(int Index) throws InterruptedException {
        while (true) {
            if (LoginPW().equals(Membership.get(Index).PW)) break;
            else
                System.out.println("비밀번호 인증에 실패했습니다.");
        }
        System.out.printf("%s 회원 탈퇴되었습니다.%n",Membership.get(Index).name);
        Membership.remove(Index);
        SelectMenu();
    }

    // 회원 정보 수정 관련 메서드
    void ChangeName(int Index){
        System.out.print("수정할 ");
        Membership.get(Index).name = InputName();
    }
    void ChangeAge(int Index){
        System.out.print("수정할 ");
        Membership.get(Index).age = InputAge();
    }
    void ChangeEmail(int Index){
        System.out.print("수정할 ");
        Membership.get(Index).email = InputEmail();
    }
    void ChangeAddress(int Index){
        System.out.print("수정할 ");
        Membership.get(Index).address = InputAddress();
    }

    // 로그인 관련 입력
    public String LoginID(){
        while (true) {
            System.out.println("아이디를 입력하시오 (영문 & 숫자, 4 ~ 15자)");
            System.out.print(">");
            String id = sc.nextLine();

            boolean check = false;
            if(ValidateID(id)) {
                for (int i = 0; i < Membership.size(); i++) {
                    if (id.equals(Membership.get(i).ID)) {
                        check = true;
                        break;
                    }
                }
                if (check) return id;
                else {
                    System.out.println("존재하지 않는 ID입니다.");
                }
            }else{
                System.out.println("옳바르지 않는 ID 형식입니다.");
            }
        }
    }
    public String LoginPW(){
        while (true) {
            System.out.println("비밀번호를 입력하시오 (영문 & 숫자, 4 ~ 15자)");
            System.out.print(">");
            String pw = sc.nextLine();
            boolean check = false;
            if(ValidateID(pw)) {
                for (int i = 0; i < Membership.size(); i++) {
                    if (pw.equals(Membership.get(i).PW)) {
                        check = true;
                        break;
                    }
                }
                if (check) return pw;
                else {
                    System.out.println("존재하지 않는 PW 입니다.");
                }
            }else{
                System.out.println("옳바르지 않는 PW 형식입니다.");
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
    String InputName(){
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
    String InputAge(){
        while (true) {
            try {
                System.out.print("나이를 입력하시오: ");
                String age = sc.nextLine();

                if (ValidateAge(age) && age.charAt(0) != '0') {
                    return age;
                } else {
                    System.out.println("올바른 나이의 형식이 아닙니다. 다시 입력해주세요.");
                }
            }catch (Exception e){
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
    boolean ValidateID(String ID){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,15}$");
        Matcher matcher = pattern.matcher(ID);
        return matcher.matches();
    }
    boolean ValidatePW(String PW){
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
        System.out.print("\n".repeat(10));
    }
}

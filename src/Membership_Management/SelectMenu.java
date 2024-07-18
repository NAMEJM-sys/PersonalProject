package Membership_Management;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class SelectMenu {
    static void print(){
        System.out.println("==================================================");
        System.out.println("진행할 메뉴를 선택하세요\n");
        System.out.println("[1] 로그인\t[2] 회원 가입\t[3] 회원 정보 확인\n[4] 회원 정보 수정\t[5] 회원 탈퇴");
        System.out.println("==================================================");
        Scanner sc = new Scanner(System.in);
        int selectNum = sc.nextInt();

        SignUp signUp = new SignUp();

        switch (selectNum) {
            case 1 : break;
            case 2 :
                signUp.initializeConsole();
                signUp.InputInformation();
                break;
            case 3 :
                signUp.CheckInfo();
                break;
            case 4 :
                signUp.revision();
                break;
            case 5 :
                signUp.DeleteInfo();
                break;

            default:
        }
    }
}

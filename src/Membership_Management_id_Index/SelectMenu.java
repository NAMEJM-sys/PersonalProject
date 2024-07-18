package Membership_Management_id_Index;
import java.util.Scanner;


public class SelectMenu {
    static void print(){
        System.out.println("==================================================");
        System.out.println("진행할 메뉴를 선택하세요\n");
        System.out.println("[1] 회원 가입\t[2] 회원 정보 확인\n[3] 회원 정보 수정\t[4] 회원 탈퇴");
        System.out.println("==================================================");
        Scanner sc = new Scanner(System.in);
        int selectNum = sc.nextInt();

        SignUp signUp = new SignUp();

        switch (selectNum) {
            case 1 :
                signUp.initializeConsole();
                signUp.InputInformation();
                break;
            case 2 :
                signUp.CheckInfo();
                break;
            case 3 :
                signUp.revision();
                break;
            case 4 :
                signUp.DeleteInfo();
                break;

            default:
        }
    }
}

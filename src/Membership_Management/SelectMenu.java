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
                signUp.InputInformation();
                break;
            case 3 :
                System.out.print("확인하려는 회원 정보의 ID를 입력하시오: ");
                int num = sc.nextInt();
                System.out.printf("이름: %s\t나이: %s%n이메일: %s\t주소: %s%n",SignUp.MemberInfo.get(num).name,SignUp.MemberInfo.get(num).age,
                        SignUp.MemberInfo.get(num).email,SignUp.MemberInfo.get(num).address);
                break;
            case 4 : break;
            case 5 : break;
            default:
        }
    }
}

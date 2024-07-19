package Library_Management_System;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookManagement {
    Scanner sc = new Scanner(System.in);
    UserManagement UM;
    List<BookData> BookList = new ArrayList<>();
    List<String> BookName = new ArrayList<>();
    List<String> removeDuplicate;
    List<Integer> duplicateCounts;


    // UserManagement 연동 관련 생성자
    public BookManagement(UserManagement UM) {
        this.UM = UM;
    }

    // 중복 제거 계산 메서드
    public void processDuplicates() {
        removeDuplicate = BookName.stream()
                .distinct()
                .collect(Collectors.toList());

        duplicateCounts = new ArrayList<>();
        for (String book : removeDuplicate) {
            int count = 0;
            for (String name : BookName) {
                if (book.equals(name)) {
                    count++;
                }
            }
            duplicateCounts.add(count);
        }
    }

    // 변경 된 책 정보 저장 메서드
    public void SaveBookCount() {
        processDuplicates(); // 중복 처리

        BookList.clear(); // 기존 리스트를 지워 중복을 피함

        for (int i = 0; i < removeDuplicate.size(); i++) {
            BookList.add(new BookData(removeDuplicate.get(i), duplicateCounts.get(i)));
        }
    }

    // 관리자 - 책 정보 확인
    public void CheckBookList(int num) throws InterruptedException {
        while (true) {
            UM.initializeConsole();
            System.out.println("==================================================");
            for (BookData book : BookList) {
                System.out.printf("책 제목: %s  수량: %d%n", book.bookName, book.bookCount);
            }
            System.out.println("==================================================");

            System.out.println("\n이전 메뉴로 돌아가려면 Y or y 를 입력하시오");
            System.out.print(">");
            String commend = sc.nextLine();

            if(commend.equals("Y")|| commend.equals("y")) break;
            else {
                System.out.println("잘못 입력하셨습니다.");
                Thread.sleep(500);
                UM.initializeConsole();
            }
        }
        UM.AdministratorMenu(UM.userData.get(num).name);
    }

    // 관리자 - 책 추가
    public void AddBook(int num) throws InterruptedException {
        UM.initializeConsole();
        System.out.println("입고된 책의 이름과 수량을 입력하시오");
        System.out.print("> (이름): ");
        String bookName = sc.nextLine();
        System.out.print("> (갯수): ");
        int bookCount = sc.nextInt();
        sc.nextLine(); // 개행 문자 소비

        for (int i = 0; i < bookCount; i++) {
            BookName.add(bookName);
        }

        SaveBookCount();
        UM.AdministratorMenu(UM.userData.get(num).name);
    }

    // 관리자 - 책 분실 및 파기
    public void DeleteBook(int num) throws InterruptedException {
        String bookName="";
        while (true){
            int a =0;
            UM.initializeConsole();
            System.out.println("==================================================");
            for (BookData book : BookList) {
                System.out.printf("책 제목: %s  수량: %d%n", book.bookName, book.bookCount);
            }
            System.out.println("==================================================\n");

            System.out.println("파기 및 분실 처리할 책의 이름과 수량을 입력하시오");
            System.out.print("> (이름): ");
            bookName = sc.nextLine();

            if(bookName.equals(removeDuplicate.get(a))){
                break;
            }else {
                System.out.println("\n입력한 책은 존재하지 않습니다.");
                Thread.sleep(500);
            }
        }

        System.out.print("> (갯수): ");
        int bookCount = sc.nextInt();
        sc.nextLine(); // 개행 문자 소비

        int countRemoved = 0;
        for (int i = 0; i < BookName.size(); i++) {
            if (bookName.equals(BookName.get(i))) {
                BookName.remove(i);
                i--;
                countRemoved++;
                if (countRemoved == bookCount) break;
            }
        }

        SaveBookCount();
        UM.AdministratorMenu(UM.userData.get(num).name);
    }

}

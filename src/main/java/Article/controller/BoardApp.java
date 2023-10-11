package Article.controller;

import java.util.Scanner;

public class BoardApp {

    ArticleController articleController = new ArticleController();

    public void start() {

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.print("메뉴를 입력해 주세요 : ");
            String cmd = scan.next();

            if (cmd.equals("add")) {
                articleController.add();
            } else if (cmd.equals("list")) {
                articleController.list();
            } else if (cmd.equals("update")) {
                articleController.update();
            } else if (cmd.equals("delete")) {
                articleController.delete();
            } else if (cmd.equals("search")) {
                articleController.search();
            } else if (cmd.equals("exit")) {
                System.out.println("프로그램 종료.");
                break;
            }
        }
    }
}

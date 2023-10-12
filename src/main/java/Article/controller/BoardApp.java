package Article.controller;

import Article.model.Member;

import java.util.Scanner;

public class BoardApp {

    ArticleController articleController = new ArticleController();

    MemberController memberController = new MemberController();

    public void start() {

        Scanner scan = new Scanner(System.in);

        while (true) {

            Member loginedmember = memberController.loginedMember();

            if (loginedmember == null) {
                System.out.print("명령어: ");
            } else {
                System.out.printf("명령어[%s(%s)]: ", loginedmember.getMemberId(), loginedmember.getMemberNickname());
            }

            String cmd = scan.next();

            if (cmd.equals("add")) {
                articleController.add(loginedmember);
            } else if (cmd.equals("list")) {
                articleController.list();
            } else if (cmd.equals("sort")) {
                articleController.sort();
            } else if (cmd.equals("page")) {
                articleController.page();
            } else if (cmd.equals("detail")) {
                articleController.detail(loginedmember);
            } else if (cmd.equals("search")) {
                articleController.search();
            } else if (cmd.equals("signup")) {
                memberController.signup();
            } else if (cmd.equals("signin")) {
                memberController.login();
            }else if (cmd.equals("exit")) {
                System.out.println("프로그램 종료.");
                break;
            }
        }
    }
}

package Article.controller;

import Article.model.Member;
import Article.model.MemberDao;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {

    MemberDao memberDao = new MemberDao();

    Scanner scan = new Scanner(System.in);

    Member sessioninfo = null; // 인스턴스 변수 : 세션정보로 활용

    public void signup() {

        System.out.println("==== 회원 가입을 진행합니다. ====");
        System.out.print("이름을 입력해주세요 : ");
        String inputName = scan.nextLine();
        System.out.print("아이디를 입력해주세요 : ");
        String inputId = scan.nextLine();
        System.out.print("비밀번호를 입력해주세요 : ");
        String inputPass = scan.nextLine();
        System.out.print("닉네임을 입력해주세요 : ");
        String inputNick = scan.nextLine();

        memberDao.signup(inputName, inputId, inputPass, inputNick);;
    }

    public Member login() {
        System.out.println("==== 로그인을 진행합니다. ====");
        System.out.print("아이디 : ");
        String loginId = scan.nextLine();
        System.out.print("비밀번호 : ");
        String loginPass = scan.nextLine();

        sessioninfo = memberDao.signin(loginId, loginPass);

        return sessioninfo;
    }

    public Member loginedMember() {
        return sessioninfo;
    }
}

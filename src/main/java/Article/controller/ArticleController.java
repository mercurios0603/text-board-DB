package Article.controller;

import Article.model.*;
import Article.view.ArticleView;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {

    Scanner scan = new Scanner(System.in);
    ArticleDao articleDao = new ArticleDao();
    ArticleView articleView = new ArticleView();

    public void add(Member membersession) {

        // 클래스를 어떻게 연결하느냐에 따라서 변수 라이프사이클 주기와 연결방식이 달라짐

        if (membersession == null) {
            System.out.println("게시물은 회원만 작성할 수 있습니다.");
        } else {
            System.out.print("게시물 제목을 입력해주세요 : ");
            String title = scan.nextLine();
            System.out.println("당신이 입력한 제목은 : " + title);

            System.out.print("게시물 내용을 입력해주세요 : ");
            String contents = scan.nextLine();
            System.out.println("당신이 입력한 내용은 : " + contents);

            articleDao.insert(membersession.getMemberId(), title, contents);
        }
    }

    public void list() {
        ArrayList<Article> articlelist = articleDao.findAllArticles();
        articleView.printArticles(articlelist);
        articlelist.clear();
    }

    public void update() {

        System.out.print("수정할 게시글 번호를 입력해주세요 : ");

        int articleIdx = getParamInt(scan.nextLine(), -1);

        System.out.print("수정할 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("수정할 내용을 입력해주세요 : ");
        String content = scan.nextLine();

        articleDao.modify(articleIdx, title, content);

    }

    public void delete() {

        System.out.print("몇 번 주소록을 삭제하시겠습니까? : ");

        int articleIdx = getParamInt(scan.nextLine(), -1);
        Article article = articleDao.findById(articleIdx);

        articleDao.delete(article);

    }

    public void search() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scan.nextLine();

        ArrayList<Article> articlelist = articleDao.findByKeyword(keyword);

        articleView.printArticles(articlelist);
        articlelist.clear();
    }

    public void detail(Member sessionmember) {

        if (sessionmember == null) {
            System.out.println("상세보기는 회원만 가능합니다. 로그인 해주세요.");

        } else {

            System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");

            int articleIdx = getParamInt(scan.nextLine(), -1);
            Article article = articleDao.findById(articleIdx);

            // 상세보기 할 때 조회수 증가
//                int checkcount = article.getCount();
//                checkcount++;
//                article.setCount(checkcount);
//
//                Member member = memberDao.getMemberById(sessionmember.getMemberId());
//                ArticleView.printArticleDetail(article, member);

            // DetailOption(article, member, comments);

        }
    }

    public void sort() {
    }

    public void page() {
    }

    public int getParamInt(String input, int defaultvalue) {

        try {
            int num = Integer.parseInt(input);
            return num;
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다.");
        }
        return defaultvalue;
    }

}

package Article.controller;

import Article.model.Article;
import Article.model.ArticleDao;
import Article.view.ArticleView;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {

    Scanner scan = new Scanner(System.in);
    ArticleDao articleDao = new ArticleDao();
    ArticleView articleView = new ArticleView();

    public void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();

        System.out.print("게시물 내용을 입력해주세요 : ");
        String contents = scan.nextLine();

        articleDao.insert(title, contents);
    }

    public void list() {
        ArrayList<Article> articlelist = articleDao.findAllArticles();
        articleView.printArticles(articlelist);
        articlelist.clear();
    }

    public void update() {

        System.out.print("수정할 게시글 번호를 입력해주세요 : ");

        int articleIdx = getParamInt(scan.nextLine(), -1);
        int targetid = articleDao.findById(articleIdx);

        if (targetid == -1) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
        } else {
            System.out.print("수정할 제목을 입력해주세요 : ");
            String title = scan.nextLine();

            System.out.print("수정할 내용을 입력해주세요 : ");
            String content = scan.nextLine();

            articleDao.modify(targetid, title, content);;
        }
    }

    public void delete() {

        System.out.print("몇 번 주소록을 삭제하시겠습니까? : ");

        int articleIdx = getParamInt(scan.nextLine(), -1);
        int targetid = articleDao.findById(articleIdx);

        if (targetid == -1) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
        } else {
            articleDao.delete(targetid);
        }
    }

    public void search() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scan.nextLine();

        ArrayList<Article> articlelist = articleDao.findByKeyword(keyword);

        articleView.printArticles(articlelist);
        articlelist.clear();
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

package Article.view;

import Article.model.Article;
import Article.model.Comment;
import Article.model.Like;
import Article.model.Member;

import java.util.ArrayList;

public class ArticleView {

    public void printArticles(ArrayList<Article> list)  {

        System.out.println("==================");
        for (int i = 0; i < list.size(); i++) {

            Article article = list.get(i);

            System.out.println(article.getArticleIndex() + "번 게시물의 내용은 다음과 같습니다.");
            System.out.println("작성자 : " + article.getMemberId());
            System.out.println("번호 : " + article.getArticleIndex());
            System.out.println("제목 : " + article.getTitle());
            System.out.println("등록날짜 : " + article.getCreatetime());
            System.out.println("수정날짜 : " + article.getModifytime());
            System.out.println("==================");
        }
    }

    public void printArticleDetail(Article article, Member member) {
        System.out.println("===================");
        System.out.printf("번호 : %d\n", article.getArticleIndex());
        System.out.printf("제목 : %s\n", article.getTitle());
        System.out.printf("내용 : %s\n", article.getContent());
        System.out.printf("작성자 : %s\n", member.getMemberId());
        System.out.printf("조회수 : %d\n", article.getCount());
        System.out.println("===================");

    }
}

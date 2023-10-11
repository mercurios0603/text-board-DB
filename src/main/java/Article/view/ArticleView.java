package Article.view;

import Article.model.Article;

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
}

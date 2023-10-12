package Article.view;

import Article.controller.Pagination;
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

    public void printArticleDetail(Article article, Member member, ArrayList<Comment> replies, int likeCount, Like like) {
        System.out.println("===================");
        System.out.printf("번호 : %d\n", article.getArticleIndex());
        System.out.printf("제목 : %s\n", article.getTitle());
        System.out.printf("내용 : %s\n", article.getContent());
        System.out.printf("작성자 : %s\n", member.getMemberId());
        if(like == null && likeCount == 0) {
            System.out.printf("좋아요 : ♡ %d\n", likeCount);
        } else {
            System.out.printf("좋아요 : ♥ %d\n", likeCount);
        }
        System.out.printf("조회수 : %d\n", article.getCount());
        System.out.println("===================");
        printCommentview(replies);
    }

    public void printCommentview (ArrayList<Comment> aaa) {

        for (int i = 0; i < aaa.size(); i++) {

            Comment bbb = aaa.get(i);
            System.out.println("댓글의 글번호 : " + bbb.getArticleid());
            System.out.println("댓글 번호 : " + bbb.getCommentid());
            System.out.println("댓글 작성자 : " + bbb.getCommentUser());
            System.out.println("댓글 : " + bbb.getReplyContent());
            System.out.println("==================");
        }
    }

    public void printPagedArticles(ArrayList<Article> list, Pagination pagination)  {

        System.out.println("==================");
        for (int i = pagination.getStartIdx(); i < pagination.getEndIdx(); i++) {

            Article article = list.get(i);

            System.out.println(article.getArticleIndex() + "번 게시물의 내용은 다음과 같습니다.");
            System.out.println("작성자 : " + article.getMemberId());
            System.out.println("번호 : " + article.getArticleIndex());
            System.out.println("제목 : " + article.getTitle());
            System.out.println("등록날짜 : " + article.getCreatetime());
            System.out.println("수정날짜 : " + article.getModifytime());
            System.out.println("==================");
        }

        // 현재 선택한 페이지를 []로 보여주기 위한 for 반복문

        if(pagination.hasPrevBlock())
            System.out.print("<< ");

        for(int i = pagination.getStartPageNo(); i <= pagination.getEndPageNo(); i++) {
            if (i == pagination.getCurrentPageNo()) {
                System.out.print("[" + i +"]" + " ");
            } else {
                System.out.print(i + " ");
            }
        }

        if(pagination.hasNextBlock())
            System.out.printf(">>");
    }
}

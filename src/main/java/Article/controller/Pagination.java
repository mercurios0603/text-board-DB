package Article.controller;

import Article.model.Article;
import Article.model.ArticleDao;

import java.util.ArrayList;

public class Pagination {

    ArticleDao articleDao = new ArticleDao();

    ArrayList<Article> articles = articleDao.findAllArticles();

    private int currentPageNo = 1;
    private int itemsCountPerPage = 3; // 한 페이지당 게시글 갯수 : 서버 관리자 지정값
    private int pageCntPerBlock = 5; // 페이지 묶음의 최대값 pageSet(화면당 보여지는 페이지 묶음 단위) : 서버 관리자 지정값

    public int getStartIdx() {
        return itemsCountPerPage * (currentPageNo - 1); // 지정 페이지의 시작 게시물의 배열 인덱스 (ArticleView에서 사용)
    }

    public int getEndIdx() {
        return Math.min(getStartIdx() + itemsCountPerPage, articles.size()); // 지정 페이지의 종료 게시물의 배열 인덱스 (ArticleView에서 사용)
    }

    public int getPageBlockNo() {
        return (int)Math.ceil((double)currentPageNo /pageCntPerBlock); // 페이지들이 저장된 페이지 묶음 배열 = pageArray
    }

    public int getTotalPageNo() {
        return (int) Math.ceil((double) articles.size() / itemsCountPerPage);
    }

    // getStartPageNo()와 getEndPageNo()는 페이지를 출력하는데 사용되는 메서드이다.

    public int getStartPageNo() {
        return pageCntPerBlock * (getPageBlockNo() - 1) + 1; // 시작 페이지 번호
    }

    public int getEndPageNo() {
        return Math.min(pageCntPerBlock * getPageBlockNo(), getTotalPageNo()); // 마지막 페이지 번호
        // 위의 최소값 구문이 없다면 게시글이 5페이지를 꽉 채우지 않더라도 (현재기준은 5*3=15개) 5페이지까지 표시될 것
        // totalPageNo가 있어야 예를 들어 게시글이 7개라면 7/3=2.3에서 올림을 하여 3페이지까지 생성될 것이고, 이게 마지막 페이지가 된다.
    }

    public int getCurrentPageNo() { // 초기 출력화면에 사용하기 위한 첫번째 페이지 번호 (1로 지정되어 있음)
        return currentPageNo;
    }

    public void prevPage() {
        int prevPageNo = getCurrentPageNo() - 1;
        if (prevPageNo < 1) {
            System.out.println("처음 페이지입니다");
            currentPageNo = getCurrentPageNo();
        } else {
            currentPageNo = prevPageNo;
        }
    }
    public void nextPage() {
        int nextPageNo = getCurrentPageNo() + 1;
        if (nextPageNo > getTotalPageNo()) {
            System.out.println("마지막 페이지입니다");
            currentPageNo = getCurrentPageNo();
        } else {
            currentPageNo = nextPageNo;
        }
    }
    public void selectPage(int pageNo) {
        if(pageNo >= 1 && pageNo <= getEndPageNo()) { // 페이지의 작동범위를 제한하기 위한 조건문
            currentPageNo = pageNo;                   // 사용자가 입력한 번호가 곧 현재 페이지 (보고 싶어하는 페이지가 된다)
        }
    }

    public int getLastPageBlockNo() {
        return (int)(Math.ceil((double)getTotalPageNo() / pageCntPerBlock));
    }

    public boolean hasNextBlock() {
        if(getPageBlockNo() < getLastPageBlockNo()) {
            return true;
        }
        return false;
    }

    public boolean hasPrevBlock() {
        if(getPageBlockNo() > 1) {
            return true;
        }
        return false;
    }
}


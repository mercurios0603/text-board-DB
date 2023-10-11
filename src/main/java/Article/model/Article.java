package Article.model;

public class Article {

    // 기사 인덱스
    private int articleindex;
    // 작성자 아이디
    private String memberid;
    // 제목
    private String title;
    // 내용
    private String content;

    // 작성일
    private String createtime;
    // 수정일
    private String modifytime;
    // 조회수
    private int cnt;

    public Article(int articleindex, String memberid, String title, String content, String createtime, String modifytime, int cnt) {
        this.articleindex = articleindex;
        this.memberid = memberid;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.cnt = cnt;
    }

    // 아래는 생성된 객체가 사용할 수 있는 메서드이다 (ArrayList의 기능을 대체하는 것으로 보여진다)

    public int getArticleIndex() {
        return articleindex;
    }

    public void setArticleIndex(int articleindex) {
        this.articleindex = articleindex;
    }
    public String getMemberId() {
        return memberid;
    }

    public void setMemberId(String memberid) {
        this.memberid = memberid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public int getCount() {
        return cnt;
    }

    public void setCount(int cnt) {
        this.cnt = cnt;
    }
}
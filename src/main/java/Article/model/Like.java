package Article.model;

public class Like {

    private int likeindex;

    private int articleid;

    private String likeuser;

    public Like(int likeindex, int articleid, String likeuser) {
        this.likeindex = likeindex;
        this.articleid = articleid;
        this.likeuser = likeuser;
    }

    public int getLikeIndex() {
        return likeindex;
    }

    public void setLikeIndexd(int likeindex) {
        this.likeindex = likeindex;
    }

    public int getArticleId() {
        return articleid;
    }

    public void setArticleId(int articleid) {
        this.articleid = articleid;
    }

    public String getLikeUser() {
        return likeuser;
    }

    public void setLikeUser(String likeuser) {
        this.likeuser = likeuser;
    }


}
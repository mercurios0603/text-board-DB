package Article.model;

import util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LikeDao {

    public ArrayList<Like> likes = new ArrayList<>();

    public Connection loginDBServer() {

        Connection conn = null; // DB 접속하는 객체

        String url = "jdbc:mysql://localhost:3306/textboard?serverTimezone=UTC";
        String user = "root";
        String pass = "";

        try {
            // 1. 드라이버 세팅
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connection 획득
            conn = DriverManager.getConnection(url, user, pass);

            System.out.println("DB 접속 완료 - Likes");

//            rs.next(); // 화살표를 한칸 내림.
//            rs.next(); // 다음 행이 있으면 true, 없으면 false 반환 (즉 while 반복문에서 false가 되면 종료됨)

        } catch (Exception e) {
            System.out.println("접속 시도중 문제 발생!!");
            e.printStackTrace();
        }

        return conn;
    }

    public Like getLikeByArticleIdAndMemberId(int articleindex, String likeuser) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // SQL 결과를 받는 객체 (받아야 할 객체가 있을 경우에만)

        Like likes = null;

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("SELECT * FROM likes WHERE articleindex LIKE %d AND likeuserid LIKE '%s'", articleindex, likeuser);

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int likeindex = rs.getInt("likeindex");
                int articleidx = rs.getInt("articleindex");
                String likeuserid = rs.getString("likeuserid");
                likes = new Like(likeindex, articleidx, likeuserid);
            }


        } catch (Exception e) {
            System.out.println("SQL 실행 중 오류 발생!!");
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return likes;
        }
    }

    public void insert(int articleId, String userId) {
        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // SQL 결과를 받는 객체 (받아야 할 객체가 있을 경우에만)

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO likes (articleindex, likeuserid) VALUES (%d, '%s')", articleId, userId);
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("SQL 실행 중 오류 발생!!");
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int articleId, String userId) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("DELETE FROM likes WHERE articleindex = %d AND likeuserid = '%s'", articleId, userId);
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("SQL 실행 중 오류 발생!!");
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public int getCountOfLikeByArticleId(int articleId) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int count = 0;

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            // 좋아요 테이블에서 매개변수로 입력받은 articleId 가지고 있는 행들의 합 count() 쿼리.

            String sql = String.format("SELECT COUNT(*) AS like_count FROM likes WHERE articleindex = %d", articleId);
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                count = rs.getInt("like_count");
            }

        } catch (Exception e) {
            System.out.println("SQL 실행 중 오류 발생!!");
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
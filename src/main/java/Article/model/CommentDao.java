package Article.model;

import util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentDao {
    ArrayList<Comment> comments = new ArrayList<>();

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

            System.out.println("DB 접속 완료 - Comments");

//            rs.next(); // 화살표를 한칸 내림.
//            rs.next(); // 다음 행이 있으면 true, 없으면 false 반환 (즉 while 반복문에서 false가 되면 종료됨)

        } catch (Exception e) {
            System.out.println("접속 시도중 문제 발생!! 코멘트");
            e.printStackTrace();
        }

        return conn;
    }


    public void insert(int articleId, String commentuser, String content) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // SQL 결과를 받는 객체 (받아야 할 객체가 있을 경우에만)

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO comments (articleid, commentuser, replycontent) VALUES (%d, '%s', '%s')", articleId, commentuser, content);
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

    public ArrayList<Comment> findcommentById(int postidx) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        ArrayList<Comment> searchedReplies = new ArrayList<>();

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("SELECT * FROM comments WHERE articleid = %d", postidx);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int cid = rs.getInt("cid");
                int articleid = rs.getInt("articleid");
                String commentuser = rs.getString("commentuser");
                String replycontent = rs.getString("replycontent");
                Comment comments = new Comment(cid, articleid, commentuser, replycontent);
                searchedReplies.add(comments);
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
        return searchedReplies;
    }
}

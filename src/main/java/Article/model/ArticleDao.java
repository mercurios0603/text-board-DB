package Article.model;

import util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.*;
import java.util.Comparator;

public class ArticleDao {

    ArrayList<Article> articles = new ArrayList<>();

    public ArticleDao() {
    }

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

            System.out.println("DB 접속 완료");

//            rs.next(); // 화살표를 한칸 내림.
//            rs.next(); // 다음 행이 있으면 true, 없으면 false 반환 (즉 while 반복문에서 false가 되면 종료됨)

        } catch (Exception e) {
            System.out.println("접속 시도중 문제 발생!!");
            e.printStackTrace();
        }

        return conn;
    }

    public void insert(String title, String content) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // SQL 결과를 받는 객체 (받아야 할 객체가 있을 경우에만)

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();

            // 유저 아이디는 임시정보 입니다. 향후 교체되어야함 //
            String memberid = "tester";

            String sql = String.format("INSERT INTO article (memberid, title, content, createtime, modifytime, cnt) " +
                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%d' )", memberid, title, content, Util.getCurrentTime(), Util.getCurrentTime(), 0);
            stmt.executeUpdate(sql);

            System.out.println("게시글이 등록되었습니다");


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

    public ArrayList<Article> findAllArticles() {
        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();

            //4. SQL 처리하고 결과 ResultSet에 받아오기
            String sql = String.format("SELECT * FROM article");
            rs = stmt.executeQuery(sql);
            // 이 메서드는 SELECT 문을 실행하고 그 결과로 ResultSet 객체를 반환합니다.
            // 이 때 ResultSet은 조회 결과 집합을 나타내며, 조회된 데이터를 검색하는 데 사용됩니다.

            while (rs.next()) {
                int listid = rs.getInt("articleindex");
                String memberid = rs.getString("memberid");
                String title = rs.getString("title");
                String contents = rs.getString("content");
                String createtime = rs.getString("createtime");
                String modifytime = rs.getString("modifytime");
                int count = rs.getInt("cnt");

                Article article = new Article(listid, memberid, title, contents, createtime, modifytime, count);
                articles.add(article);
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

        return articles;

    }

    public int findById(int postidx) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int targetidx = -1;

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();

            //4. SQL 처리하고 결과 ResultSet에 받아오기
            String sql = String.format("SELECT * FROM article WHERE articleindex = %d", postidx);
            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                return targetidx;
            }
            targetidx = postidx;


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

        return targetidx;
    }

    public void modify(int targetid, String title, String content) {
        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("UPDATE article SET title = '%s', content = '%s', modifytime = '%s' WHERE articleindex = '%d'",
                    title, content, Util.getCurrentTime(), targetid);
            stmt.executeUpdate(sql);
            // 이 메서드는 INSERT, UPDATE 또는 DELETE 문과 같은 데이터베이스를 수정하는 SQL 문을 실행하고
            // 변경된 행의 수를 반환합니다.

            System.out.println("게시글이 수정되었습니다");

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

    public void delete(int targetid) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();


            String sql = String.format("DELETE FROM article WHERE articleindex='%d'", targetid);
            stmt.executeUpdate(sql);

            System.out.println(targetid + "번 주소록이 삭제되었습니다.");

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

    public ArrayList<Article> findByKeyword(String targetkeyword) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // 결과 받아오는 객체

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("SELECT * FROM article WHERE title LIKE '%%%s%%' OR content LIKE '%%%s%%'", targetkeyword, targetkeyword);
            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("키워드에 해당하는 게시글이 없습니다.");
            } else {
                while (true) {
                    int listid = rs.getInt("articleindex");
                    String memberid = rs.getString("memberid");
                    String title = rs.getString("title");
                    String contents = rs.getString("content");
                    String createtime = rs.getString("createtime");
                    String modifytime = rs.getString("modifytime");
                    int count = rs.getInt("cnt");
                    Article article = new Article(listid, memberid, title, contents, createtime, modifytime, count);
                    articles.add(article);

                    if (!rs.next()) {
                        break;
                    }
                }
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

        return articles;
    }
}

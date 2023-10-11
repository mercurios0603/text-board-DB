package Article.model;

import util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDao {

    ArrayList<Member> members = new ArrayList<>();

    public MemberDao() {
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

    public void signup(String username, String userid, String userpass, String usernick) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // SQL 결과를 받는 객체 (받아야 할 객체가 있을 경우에만)

        try {

            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO member (membername, memberid, memberpassword, membernickname) " +
                    "VALUES ('%s', '%s', '%s', '%s' )", username, userid, userpass, usernick);
            stmt.executeUpdate(sql);

            System.out.println("==== 회원가입이 완료되었습니다. ====");


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

    public Member signin(String loginid, String loginpass) {

        Connection conn = null;
        Statement stmt = null; // SQL 전송하는 객체
        ResultSet rs = null; // SQL 결과를 받는 객체 (받아야 할 객체가 있을 경우에만)

        Member sessions = null;

        try {
            conn = loginDBServer();
            stmt = conn.createStatement();

            String sql = String.format("SELECT * FROM member WHERE memberid LIKE '%s' AND memberpassword LIKE '%s'", loginid, loginpass);
            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("로그인 정보가 정확하지 않습니다.");
            } else {
                while (true) {
                    int memberindex = rs.getInt("memberindex");
                    String membername = rs.getString("membername");
                    String memberid= rs.getString("memberid");
                    String memberpassword = rs.getString("memberpassword");
                    String membernickname = rs.getString("membernickname");

                    sessions = new Member(memberindex, membername, memberid, memberpassword, membernickname); // sessions 객체를 생성

                    System.out.println(sessions.getMemberNickname() + "님 환영합니다.");

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
        return sessions;
    }
}
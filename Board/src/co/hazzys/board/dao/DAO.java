package co.hazzys.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public Connection conn;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hazzys";
	private String password = "dhgPwl7#";

	public DAO() {
		try {
			Class.forName(driver); // 드라이버 호출
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("게시판 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("게시판 연결 실패");
		}
	}
}

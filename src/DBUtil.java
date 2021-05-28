

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 오라클에 접속하기 위한 환경설정 클래스이다.
 * @author sist50
 *
 */
public class DBUtil {
	
	
	private static Connection conn = null;
	
	//오라클 접속 > Connection 객체 생성 + 반환
	/**
	 * 오라클에 접속하기 위한 메소드이다.
	 * @return 접속된 DB객체
	 */
	public static Connection open() {
		
		String url = "jdbc:oracle:thin:@211.63.89.48:1521:xe";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "project";
		String pw = "java1234";
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil.open() : " + e.toString());
		}
		
		return null;
		
	}
	
	/**
	 * 사용자가 직접 ip주소,id,password를 입력해주는 오버로딩 메소드이다.
	 * @param server ip주소
	 * @param id	서버이름
	 * @param pw	서버 비밀번호
	 * @return 접속된 DB객체
	 */
	public static Connection open(String server, String id, String pw) {
		
		String url = "jdbc:oracle:thin:@" + server + ":1521:xe";
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			return conn;
			
		} catch (Exception e) {
			System.out.println("DBUtil.open() : " + e.toString());
		}
		
		return null;
		
	}
	/**
	 * 오라클 접속을 종료할때 사용되는 메소드이다.
	 */
	public static void close() {
		
		try {

			conn.close();

		} catch (Exception e) {
			System.out.println("DBUtil.close() : " + e.toString());
		}
	}


}

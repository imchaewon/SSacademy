import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

/**
 * 개설 과목과 관련한 기능을 담당하는 클래스이다.
 * 전체 개설 과정 조회, 개설 과정 등록, 개설 과정 정보 수정, 개설 과정 과정 삭제 기능을 포함한다.
 * @author 전수희
 *
 */
public class OpenSubjectDAO {

	private Connection conn;
	private Statement stat;
	private CallableStatement cstat;
	private ResultSet rs;

	/**
	 * 개설 과목 DAO 클래스의 기본 생성자이다.
	 */
	public OpenSubjectDAO() {

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.OpenSubjectDAO() : " + e.toString());
		}
	}

	/**
	 * 전체 개설 과목 정보를 배열로 반환하는 메소드이다.
	 * 개설 과목의 정보에는 개설 과목 번호, 과목 이름, 교재, 개설 과목 시작 날짜, 개설 과목 종료 날짜가 포함된다.
	 * @return 전체 개설 과목 정보
	 */
	// 전체 개설 과목 목록 조회
	public ArrayList<OpenSubjectDTO> openSubjectList() {

		try {

			String sql = "{ call proc_openSubjectList(?) }";

			cstat = conn.prepareCall(sql);
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(1);

			ArrayList<OpenSubjectDTO> result = new ArrayList<OpenSubjectDTO>();

			while (rs.next()) {

				OpenSubjectDTO osdto = new OpenSubjectDTO();

				osdto.setSeq(rs.getString("seq")); // 개설과목번호
				osdto.setTitle(rs.getString("title")); // 개설과목명
				osdto.setBook(rs.getString("book")); // 교재
				osdto.setStartDate(rs.getString("startDate")); // 과목시작날짜
				osdto.setEndDate(rs.getString("endDate")); // 과목종료날짜

				result.add(osdto);

			}
			rs.close();

			return result;

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.allOpenSubjectList() : " + e.toString());
		}

		return null;
	}

	/**
	 * 새 개설 과목을 등록하는 메소드이다.
	 * 과목 번호, 교재 번호, 과목 시작 날짜, 과목 종료 날짜가 담긴 개설 과목 데이터 정보를 받아 개설 과목을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param osdto 개설 과목 데이터 정보
	 * @return 성공 여부
	 */
	// 개설 과목 등록
	public int addOpenSubject(OpenSubjectDTO osdto) {

		String sql = "{ call proc_addOpenSubject(?, ?, ?, ?) }";

		try {

			cstat = conn.prepareCall(sql);
			cstat.setString(1, osdto.getTitle()); // 과목명
			cstat.setString(2, osdto.getBook()); // 교재
			cstat.setString(3, osdto.getStartDate()); // 과목시작날짜
			cstat.setString(4, osdto.getEndDate()); // 과목종료날짜

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.addOpenSubject() : " + e.toString());
		}

		return 0;
	}
	

	/**
	 * 수정할 개설 과목 번호와 수정할 개설 과목의 시작 날짜, 수정할 개설 과목의 종료 날짜, 수정할 개설 과목의 교재를 받아와 기존 개설 과목의 정보를 수정하는 메소드이다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param openSubjectSeq 개설 과목 번호
	 * @param openSubjectStartDate 개설 과목의 시작 날짜
	 * @param openSubjectEndDate 개설 과목의 종료 날짜
	 * @param openSubjectBook 교재 번호
	 * @return 성공 여부
	 */
	// 개설 과목 수정
	public int updateOpenSubject(String openSubjectSeq, String openSubjectStartDate, String openSubjectEndDate, String openSubjectBook) {

		//원래 값
		String startDate = "";
		String endDate = "";
		String book = "";

		try {

			String sql = "SELECT to_char(startDate, 'yyyy-mm-dd') AS startDate, to_char(endDate, 'yyyy-mm-dd') AS endDate, book_seq AS book FROM tblOpenSubject WHERE seq = " + openSubjectSeq;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				startDate = rs.getString("startdate");
				endDate = rs.getString("enddate");
				book = rs.getString("book");
			}

			rs.close();

			// 개설 과목 시작날짜 수정
			if (!openSubjectStartDate.equals("")) {
				startDate = openSubjectStartDate;
			}
			
			// 개설 과목 종료날짜 수정
			if (!openSubjectEndDate.equals("")) {
				endDate = openSubjectEndDate;
			}
			
			// 개설 과목 교재 수정
			if (!openSubjectBook.equals("")) {
				book = openSubjectBook;
			}

			sql = "{ call proc_updateOpenSubject(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, startDate);
			cstat.setString(3, endDate);
			cstat.setString(4, book);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.updateOpenSubjectStartDate() : " + e.toString());
		}

		return 0;
	}
	

	/**
	 * 개설 과목을 삭제하는 메소드이다.
	 * 삭제할 개설 과목 번호를 받아 개설된 개설 과목을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 성공 여부
	 */
	// 개설 과목 삭제
	public int deleteOpenSubject(String openSubjectSeq) {

		try {

			String sql = "{ call proc_deleteOpenSubject(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenSubjectDAO.deleteOpenSubject() : " + e.toString());
		}

		return 0;
	}

}
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

/**
 * 강의스케줄 관리 클래스 
 * @author 전수희
 */
public class LectureScheduleDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;

	/**
	 * 강의 스케줄 DB연결 메소드
	 */
	public LectureScheduleDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}

	}

	/**
	 * 특정 개설 과정의 번호을 받아 개설 과목의 목록을 가져오는 메소드이다.
	 * 과목번호, 과목명, 과목 시작 날짜, 과목 종료 날짜, 교재명을 가져온다.
	 * @param openProcessSeq 개설과정번호
	 * @return 특정 개설과정의 개설과목 목록 배열
	 */
	public ArrayList<OpenSubjectDTO> openSubjectList(String openProcessSeq) {

		ArrayList<OpenSubjectDTO> result = new ArrayList<OpenSubjectDTO>();

		try {

			String sql = "{ call proc_openSubjectListOfOP(?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openProcessSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(2);

			while (rs.next()) {

				OpenSubjectDTO osdto = new OpenSubjectDTO();

				osdto.setSeq(rs.getString("seq"));
				osdto.setTitle(rs.getString("title"));
				osdto.setStartDate(rs.getString("startDate"));
				osdto.setEndDate(rs.getString("endDate"));
				osdto.setBook(rs.getString("book"));

				result.add(osdto);
			}

			return result;

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return null;
	}

	/**
	 * 특정 개설 과정 번호로 해당 과정을 수강하는 교육생 목록을 출력하는 메소드이다.
	 * 교육생번호, 교육생명, 교육생 주민번호 뒷자리, 핸드폰번호, 등록일, 수료여부
	 * @param openProcessSeq 개설과정번호
	 * @return 특정 개설 과정의 교육생 목록 배열
	 */
	public ArrayList<StudentDTO> studentList(String openProcessSeq) {

		ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();

		try {

			String sql = "{ call proc_studentListOfOP(?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openProcessSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(2);

			while (rs.next()) {
				
				StudentDTO sdto = new StudentDTO();
				
				sdto.setSeq(rs.getString("seq"));
				sdto.setName(rs.getString("name"));
				sdto.setRegidentRegistrationNumber(rs.getString("regidentRegistrationNumber"));
				sdto.setPhone(rs.getString("phone"));
				sdto.setRegdate(rs.getString("regdate"));
				sdto.setFinishStatus(rs.getString("finishStatus"));

				result.add(sdto);
			}

			return result;

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.studentList() : " + e.toString());
		}

		return null;
	}
	
	
	

}

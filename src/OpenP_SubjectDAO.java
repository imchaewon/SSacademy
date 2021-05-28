import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

/**
 * 개설 과정 과목 클래스 
 * @author 박민선
 */
public class OpenP_SubjectDAO {

	private Connection conn;
	private CallableStatement cstat;
	private ResultSet rs;
	
	/**
	 * 개설 과정 DB연결 메소드
	 */
	public OpenP_SubjectDAO() {
		try {

			conn = DBUtil.open();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}
	
	/**
	 * 학생이 선택한 과정의 과목별 성적 목록을 출력하는 메소드이다.
	 * 과목번호, 과목명, 과목 시작 날짜, 과목 종료 날짜, 필기시험배점, 실기시험배점, 출결배점, 필기시험점수, 실기시험점수, 출결점수
	 * @param sel 개설과정번호
	 * @return 선택한 과정의 과목별 성적 배열
	 */
	public ArrayList<OpenP_SubjectDTO> registP_Subjects(String sel) {

		String studentSeq = Sequence.StudentSeq;
		System.out.println(sel);
		System.out.println(studentSeq);
		   try {

				String sql = "{ call proc_subjectScore(?, ?, ?) }";
				
				cstat = conn.prepareCall(sql);
				
				cstat.setString(1, sel);
				cstat.setString(2, studentSeq);
				
				cstat.registerOutParameter(3, OracleTypes.CURSOR);
				
				cstat.executeQuery();
				
				rs = (ResultSet)cstat.getObject(3);
				
				ArrayList<OpenP_SubjectDTO> result = new ArrayList<OpenP_SubjectDTO>();
				
				while (rs.next()) {
					
					OpenP_SubjectDTO tccdto = new OpenP_SubjectDTO();
					
					tccdto.setSeq(rs.getString("sSeq"));				
					tccdto.setSubjectName(rs.getString("sTitle"));
					tccdto.setSubStartDate(rs.getString("osStartdate"));
					tccdto.setSubEndDate(rs.getString("osEnddate"));
					tccdto.setHandWritingTestPoint(rs.getString("emHandwritingTestPoint"));
					tccdto.setPracticalTestPoint(rs.getString("emPracticalTestPoint"));
					tccdto.setAttendancePoint(rs.getString("emAttendancePoint"));
					tccdto.setHandWritingTestScore(rs.getString("handwritingtestscore"));
					tccdto.setPracticalTestScore(rs.getString("practicaltestscore"));
					tccdto.setAttendanceScore(rs.getString("attendancescore"));
					
					result.add(tccdto);
				}
				
				return result;

			} catch (Exception e) {
				System.out.println("TeacherDAO.teacherCourseList() : " + e.toString());
			}
		
		return null;
	}
	
}


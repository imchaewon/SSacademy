import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

/**
 * 교육생 계정 관리 기능을 담당하는 클래스이다.
 * @author 전수희
 *
 */
public class StudentDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;

	/**
	 * 교육생 DAO의 기본 생성자이다.
	 * DB 연결을 담당한다.
	 */
	public StudentDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}

	}

	/**
	 * 전체 교육생 목록을 출력하는 메소드이다.
	 * 학생번호, 학생명, 주민번호 뒷자리, 핸드폰번호, 등록일
	 * @return 모든 교육생 정보 배열
	 */
	public ArrayList<StudentDTO> studentList() {

		ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();

		try {

			String sql = "SELECT seq, name, regidentRegistrationNumber, phone, regdate FROM vw_studentList";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				StudentDTO stdto = new StudentDTO();
				stdto.setSeq(rs.getString("seq"));
				stdto.setName(rs.getString("name"));
				stdto.setRegidentRegistrationNumber(rs.getString("regidentRegistrationNumber"));
				stdto.setPhone(rs.getString("phone"));
				stdto.setRegdate(rs.getString("regdate"));

				result.add(stdto);
			}

			return result;

		} catch (Exception e) {
			System.out.println("StudentDAO.studentList() : " + e.toString());
		}
		return null;
	}

	/**
	 * 새로운 교육생 정보를 받아 교육생을 새로 등록하는 메소드이다.
	 * 교육생명, 주민번호뒷자리, 핸드폰 번호 입력받아 등록한다.
	 * @param stdto 등록할 교육생의 정보를 담은 배열(상자)
	 * @return 등록 성공시 1 반환, 실패시 0 반환
	 */
	public int addStudent(StudentDTO stdto) {

		try {

			String sql = "{ call proc_addStudent(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, stdto.getName());
			cstat.setString(2, stdto.getRegidentRegistrationNumber());
			cstat.setString(3, stdto.getPhone());
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("StudentDAO.addStudent() : " + e.toString());
		}
		return 0;
	}
	
	/**
	 * 수정할 정보를 받아 기존의 교육생 정보를 수정하는 메소드이다.
	 * @param studentSeq 수정할 교육생 번호
	 * @param studentName 수정할 교육생 이름
	 * @param studentRegidentRegistrationNumber 수정할 교육생 주민번호 뒷자리
	 * @param studentPhone 수정할 교육생전화번호
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	public int updateStudent(String studentSeq, String studentName, String studentRegidentRegistrationNumber, String studentPhone) {
		
		//원래 값
		String name = "";
		String regidentRegistrationNumber = "";
		String phone = "";
		
		try {

			String sql = "SELECT name, regidentRegistrationNumber, phone FROM tblStudent WHERE seq = " + studentSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("name");
				regidentRegistrationNumber = rs.getString("regidentRegistrationNumber");
				phone = rs.getString("phone");
			}
			
			if(!studentName.equals("")) {
				name = studentName;
			}
			
			if (!studentRegidentRegistrationNumber.equals("")) {
				regidentRegistrationNumber = studentRegidentRegistrationNumber;
			}
			
			if (!studentPhone.equals("")) {
				phone = studentPhone;
			}
			
			sql = "{ call proc_updateStudent(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentSeq);
			cstat.setString(2, name);
			cstat.setString(3, regidentRegistrationNumber);
			cstat.setString(4, phone);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.enclosing_method() : " + e.toString());
		}
		return 0;
	}
	
	/**
	 * 기존의 교육생 등록일을 수정하는 메소드이다.
	 * @param studentSeq 수정할 교육생 번호
	 * @param studentRegdate 수정할 교육생 등록일
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	
	public int updateStudentRegdate(String studentSeq, String studentRegdate) {
		
		//원래 값
		String regdate = "";
		
		try {

			String sql = "SELECT regdate FROM tblStudentRegdate WHERE seq = " + studentSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				regdate = rs.getString("regdate");
			}
			
			if (!studentRegdate.equals("")) {
				regdate = studentRegdate;
			}
			
			sql = "{ call proc_updateStudentRegdate(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentSeq);
			cstat.setString(2, regdate);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.updateStudentRegdate() : " + e.toString());
		}
		
		
		return 0;
	}

	/**
	 * 삭제할 교육생 번호를 받아 기존의 교육생을 삭제하는 메소드이다.
	 * @param studentSeq 삭제할 교육생
	 * @return 삭제 성공시 1 반환, 실패시 0 반환
	 */
	public int deleteStudent(String studentSeq) {

		try {
			
			String sql = "{ call proc_deleteStudent(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("StudentDAO.deleteStudent() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 특정 교육생을 선택하여 해당 교육생의 정보를 출력하는 메소드이다.
	 * 수강 번호, 과정명, 과정 시작 날짜, 과정 종료 날짜, 강의실, 수료 여부, 수료 날짜, 수강 상태
	 * @param studentSeq 조회할 특정 교육생 번호
	 * @return 특정 교육생 정보 배열
	 */
	public ArrayList<StudentCourseDTO> studentCourseList(String studentSeq) {
		
		ArrayList<StudentCourseDTO> result = new ArrayList<StudentCourseDTO>();
		
		try {

			String sql = "{ call proc_studentCourseList(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			while (rs.next()) {
				StudentCourseDTO stdto = new StudentCourseDTO();
				stdto.setRegistrationSeq(rs.getString("seq"));
				stdto.setOpenProcessTitle(rs.getString("ptitle"));
				stdto.setOpenProcessStartDate(rs.getString("startDate"));
				stdto.setOpenProcessEndDate(rs.getString("endDate"));
				stdto.setLectureRoom(rs.getString("lectureRoom"));
				stdto.setFinishStatus(rs.getString("finishStatus"));
				stdto.setFinishDate(rs.getString("finishDate"));
				stdto.setStatus(rs.getString("status"));
				
				result.add(stdto);
			}
			
			return result;

		} catch (Exception e) {
			System.out.println("StudentDAO.enclosing_method() : " + e.toString());
		}
		
		return null;
	}

	/**
	 * 특정 교육생의 번호를 입력받아 해당 학생의 수료 여부를 수정하는 메소드이다.
	 * @param registrationSeq 수강번호
	 * @param regiStrationFinishStatus 수정할 수료 및 중도탈락 여부
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	public int updateStudentFinishStatus(String registrationSeq, String regiStrationFinishStatus) {
		
		//원래 값
		String finishStatus = "";
		
		try {
			
			String sql = "SELECT finishStatus FROM tblRegistrationCourse WHERE seq = " + registrationSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				finishStatus = rs.getString("finishStatus");
			}
			
			if (!regiStrationFinishStatus.equals("")) {
				finishStatus = regiStrationFinishStatus;
			}
			

			sql = "{ call proc_updateFinishStatus(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, finishStatus);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.updateStudentFinishStatus() : " + e.toString());
		}
		return 0;
	}
	
	/**
	 * 특정 교육생의 번호를 입력받아 해당 교육생의 수료 날짜를 수정하는 메소드이다.
	 * @param registrationSeq 수강번호
	 * @param registrationFinishDate 수정할 수료날짜
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	public int updateStudentFinishDate(String registrationSeq, String registrationFinishDate) {
		
		//원래 값
		String finishDate = "";
		
		try {
			
			String sql = "SELECT finishDate FROM tblRegistrationCourse WHERE seq = " + registrationSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				finishDate = rs.getString("finishDate");
			}
			
			if (!registrationFinishDate.equals(finishDate)) {
				finishDate = registrationFinishDate;
			}
			

			sql = "{ call proc_updateFinishDate(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, finishDate);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("StudentDAO.updateStudentFinishDate() : " + e.toString());
		}
		
		return 0;
	}


}
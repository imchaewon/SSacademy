import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

/**
 * 교사 계정과 관련한 기능을 담당하는 클래스이다.
 * @author 전수희
 *
 */
public class TeacherDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 교사 DAO의 기본 생성자이다.
	 * DB 연결을 담당한다.
	 */
	public TeacherDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}

	/**
	 * 가지고 있는 전체 교사 목록을 출력하는 메소드이다.
	 * 교사번호, 교사명, 주민번호 뒷자리, 핸드폰번호
	 * @return 모든 교사 목록 배열
	 */
	public ArrayList<TeacherDTO> allTeacherList() {
		
		ArrayList<TeacherDTO> result = new ArrayList<TeacherDTO>();
		
		try {

			String sql = "SELECT seq, name, regidentRegistrationNumber, phone FROM tblTeacher order by seq asc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				
				TeacherDTO tcdto = new TeacherDTO();
				
				tcdto.setSeq(rs.getString("seq"));
				tcdto.setName(rs.getString("name"));
				tcdto.setRegidentRegistrationNumber(rs.getString("regidentRegistrationNumber"));
				tcdto.setPhone(rs.getString("phone"));
				
				result.add(tcdto);
			}
			
			return result;

		} catch (Exception e) {
			System.out.println("TeacherDAO.allTeacherList() : " + e.toString());
		}
		
		return null;
	}

	/**
	 * 새로운 교사 정보를 입력받아 새로 등록하는 메소드이다.
	 * @param tcdto TeacherDTO에 있는 등록할 교사 정보를 담은 배열
	 * @param teacherAvailableSubjects 과목목록에서 선택한 강의 가능한 과목 배열
	 * @return 등록 성공시 1 반환, 실패시 0 반환
	 */
	public int addTeacher(TeacherDTO tcdto , String[] teacherAvailableSubjects) {
		
		//등록한 교사의 seq
		String seq = ""; 

		try {
			
			//자동 Commit 끄기
			// - 교사 추가 후 강의 가능 과목까지 추가한 다음 commit
			conn.setAutoCommit(false);

			//교사 추가
			String sql = "{ call proc_addTeacher(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, tcdto.getName());
			cstat.setString(2, tcdto.getRegidentRegistrationNumber());
			cstat.setString(3, tcdto.getPhone());
			cstat.registerOutParameter(4, OracleTypes.NUMBER); //교사 번호
			
			cstat.executeUpdate();
			seq = cstat.getString(4);
			
			
			//교사 강의 가능 과목 추가
			boolean loop = true;
			for (int i=0; i<teacherAvailableSubjects.length; i++) {
				sql = "{ call proc_addAvailableSubject(?, ?) }";
				
				cstat = conn.prepareCall(sql);
				
				cstat.setString(1, seq);
				cstat.setString(2, teacherAvailableSubjects[i]);
				int result = cstat.executeUpdate();
				
				if (result != 1) {
					loop = false;
					break;
				}
			}
			
			if (loop) {
				conn.commit();
				return 1;
			}
			
			
		} catch (Exception e) {
			
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println("TeacherDAO.addTeacher() : " + e2.toString());
			}
			
			System.out.println("TeacherDAO.addTeacher() : " + e.toString());
		}
		
		return 0;
	}
	
	/**
	 * 기존의 교사 정보 중 수정할 정보를 선택 후 수정하기위한 메소드이다.
	 * 교사명, 주민번호뒷자리, 핸드폰번호
	 * @param teacherSeq 수정할 교사 번호
	 * @param teacherName 수정할 교사 이름
	 * @param teacherRegidentRegistrationNumber 수정할 교사 주민번호 뒷자리
	 * @param teacherPhone 수정할 교사 핸드폰 번호
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	public int updateTeacher(String teacherSeq, String teacherName, String teacherRegidentRegistrationNumber, String teacherPhone) {
		
		//원래 값
		String name = "";
		String regidentRegistrationNumber = "";
		String phone = "";
		
		try {
			
			String sql = "SELECT name, regidentRegistrationNumber, phone FROM tblTeacher WHERE seq = " + teacherSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("name");
				regidentRegistrationNumber = rs.getString("regidentRegistrationNumber");
				phone = rs.getString("phone");
			}
			
			//이름 수정
			if (!teacherName.equals("")) {
				name = teacherName;
			}
			
			//주민번호 뒷자리(비밀번호) 수정
			if (!teacherRegidentRegistrationNumber.equals("")) {
				regidentRegistrationNumber = teacherRegidentRegistrationNumber;
			}
			
			//전화번호 수정
			if (!teacherPhone.equals("")) {
				phone = teacherPhone;
			}
			
			
			
			sql = "{ call proc_updateTeacher(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			cstat.setString(2, name);
			cstat.setString(3, regidentRegistrationNumber);
			cstat.setString(4, phone);
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherDAO.updateTeacher() : " + e.toString());
		}
		
		return 0;
	}
	
	/**
	 * 특정 교사를 선택하여 해당 교사의 강의 가능한 과목 목록를 출력하는 메소드이다.
	 * 과목번호, 과목명, 과목기간
	 * @param teacherSeq 조회할 특정 교사 번호
	 * @return 선택한 특정교사의 강의 가능한 과목 목록 배열
	 */
	public ArrayList<TeacherAvailableSubjectDTO> availableSubjectList(String teacherSeq) {
		
		ArrayList<TeacherAvailableSubjectDTO> result = new ArrayList<TeacherAvailableSubjectDTO>();
		
		try {

			String sql = "{ call proc_availableSubjectList(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			while(rs.next()) {
				TeacherAvailableSubjectDTO asdto = new TeacherAvailableSubjectDTO();
				asdto.setSeq(rs.getString("seq"));
				asdto.setTitle(rs.getString("title"));
				asdto.setDays(rs.getString("days"));
				
				result.add(asdto);
			}

			return result;

		} catch (Exception e) {
			System.out.println("TeacherDAO.availableSubjectList() : " + e.toString());
		}
		
		return null;
	}
	
	/**
	 * 특정 교사의 강의가능과목을 다중선택한 후 수정하기 위한 메소드이다.
	 * @param teacherSeq 수정할 교사번호
	 * @param subject1 수정 전 과목번호 
	 * @param subject2 수정 후 과목번호(수정할 번호)
	 * @return 수정 성공시 1 반환, 실패시 0 반환
	 */
	public int updateAvailableSubject(String teacherSeq, String subject1, String subject2) {
		
		//원래값
		String subject_seq = "";
		
		try {
			String sql = "SELECT seq, teacher_seq, subject_seq FROM tblAvailableSubject WHERE teacher_seq = " + teacherSeq + "AND subject_seq = " + subject1;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				subject_seq = rs.getString("subject_seq");
			}
			
			if(!subject2.equals("")) {
				subject_seq = subject2;
			}
			
			sql = "{ call proc_updateAvailableSubject(?, ?, ?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			cstat.setString(2, subject1);
			cstat.setString(3, subject_seq);
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherDAO.updateAvailableSubject() : " + e.toString());
		}
		
		return 0;
		
	}

	
	/**
	 * 기존의 교사를 교사목록에서 삭제하기위한 메소드이다.
	 * @param teacherSeq 삭제할 교사 번호
	 * @return 삭제 성공시 1 반환, 실패시 0 반환
	 */
	public int deleteTeacher(String teacherSeq) {
		
		try {

			String sql = "{ call proc_deleteTeacher(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("TeacherDAO.deleteTeacher() : " + e.toString());
		}
		return 0;
	}


	/**
	 * 특정 교사를 선택하여 해당 교사가 담당하는 개설 과정 목록을 출력하는 메소드이다.
	 * 과정번호, 과정명, 과정 시작 날짜, 과정 종료 날짜, 강의실, 수강상태
	 * @param teacherSeq 조회할 특정 교사 번호
	 * @return 선택한 특정 교사가 담당하는 개설 과정 목록 배열
	 */
	public ArrayList<TeacherCourseDTO> teacherCourseList(String teacherSeq) {
		
		ArrayList<TeacherCourseDTO> result = new ArrayList<TeacherCourseDTO>();
		try {

			String sql = "{ call proc_teacherCourse(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, teacherSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			while (rs.next()) {
				
				TeacherCourseDTO tccdto = new TeacherCourseDTO();
				
				tccdto.setOpenProcessSeq(rs.getString("seq"));
				tccdto.setOpenProcessTitle(rs.getString("title"));
				tccdto.setOpenProcessStartDate(rs.getString("startDate"));
				tccdto.setOpenProcessEndDate(rs.getString("endDate"));
				tccdto.setLectureRoom(rs.getString("lectureRoom"));
				tccdto.setLectureStatus(rs.getString("lectureStatus"));
				
				result.add(tccdto);
			}
			
			return result;

		} catch (Exception e) {
			System.out.println("TeacherDAO.teacherCourseList() : " + e.toString());
		}
		return null;
		
	}

}

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

/**
 * 개설 과정과 관련한 기능을 담당하는 클래스이다.
 * 전체 개설 과정 조회, 개설 과정 등록, 개설 과정 정보 수정, 개설 과정 과정 삭제, 특정 개설 과정의 개설 과목 목록 조회, 개설 과정에 개설 과목 등록, 개설 과정에 개설 과목 삭제, 특정 개설 과정의 교육생 목록 조회, 개설 과정에 교육생 등록, 개설 과정에서 교육생 삭제 기능을 포함한다.
 * @author 전수희
 *
 */
public class OpenProcessDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;

	/**
	 * 개설 과정 DAO의 기본 생성자이다.
	 */
	public OpenProcessDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}

	}

	/**
	 * 개설 과정 정보를 배열로 반환하는 메소드이다.
	 * 개설 과정의 정보에는 개설 과정 번호, 과정 이름, 개설 과정 시작 날짜, 개설 과정 종료 날짜, 강의실, 등록 인원, 개설 과목 등록 여부, 수료 상태가 포함된다.
	 * @return 전체 개설 과정 정보
	 */
	// 전체 개설 과정 목록 조회
	public ArrayList<OpenProcessDTO> openProcessList() {

		ArrayList<OpenProcessDTO> result = new ArrayList<OpenProcessDTO>();

		try {

			String sql = "SELECT seq, title, startDate, endDate, lectureRoom, personnel, existStatus, status FROM vw_openProcessList order by seq";
			rs = stat.executeQuery(sql);

			while (rs.next()) {

				OpenProcessDTO opdto = new OpenProcessDTO();
				opdto.setSeq(rs.getString("seq"));
				opdto.setTitle(rs.getString("title"));
				opdto.setStartDate(rs.getString("startDate"));
				opdto.setEndDate(rs.getString("endDate"));
				opdto.setLectureRoom(rs.getString("lectureRoom"));
				opdto.setPersonnel(rs.getString("personnel"));
				opdto.setExistStatus(rs.getString("existStatus"));
				opdto.setStatus(rs.getString("status"));

				result.add(opdto);
			}

			return result;

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openProcessList() : " + e.toString());
		}
		return null;
	}

	/**
	 * 새 개설 과정을 등록하는 메소드이다.
	 * 개설 과정의 제목, 강의실 번호, 등록 인원, 개설 과정 시작 날짜, 개설 과정 종료 날짜, 수료 상태, 개설 과목 등록 여부가 담긴 개설 과정 데이터 정보와 교사 번호를 받아 개설 과정을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param opdto 개설 과정 데이터 정보
	 * @param teacherSeq 교사 번호
	 * @return 성공 여부
	 */
	// 개설 과정 등록
	public int addOpenProcess(OpenProcessDTO opdto, String teacherSeq) {

		try {

			String sql = "{ call proc_addOpenProcess(?, ?, ?, ?, ?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, opdto.getTitle());
			cstat.setString(2, opdto.getLectureRoom());
			cstat.setString(3, opdto.getPersonnel());
			cstat.setString(4, opdto.getStartDate());
			cstat.setString(5, opdto.getEndDate());
			cstat.setString(6, opdto.getStatus());
			cstat.setString(7, opdto.getExistStatus());
			cstat.setString(8, teacherSeq);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.addOpenProcess() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 수정할 개설 과정 번호와 수정할 개설 과정의 시작 날짜, 수정할 개설 과정의 종료 날짜, 수정할 개설 과정의 강의실을 받아와 기존 개설 과정의 정보를 수정하는 메소드이다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param openProcessSeq 개설 과정 번호
	 * @param openProcessStartDate 개설 과정의 시작 날짜
	 * @param openProcessEndDate 개설 과정의 종료 날짜
	 * @param openProcessLectureRoom 수정된 개설 과정의 개수
	 * @return 성공 여부
	 */
	// 개설 과정 정보 수정
	public int updateOpenProcess(String openProcessSeq, String openProcessStartDate, String openProcessEndDate,
			String openProcessLectureRoom) {

		// 원래 값
		String startDate = "";
		String endDate = "";
		String lectureRoom = "";

		try {

			String sql = "SELECT startDate, endDate, lecture_room_seq AS lectureRoom FROM tblOpenProcess WHERE seq = "
					+ openProcessSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				startDate = rs.getString("startDate");
				endDate = rs.getString("endDate");
				lectureRoom = rs.getString("lectureRoom");
			}
			rs.close();

			// 시작 날짜 수정
			if (!openProcessStartDate.equals("")) {
				startDate = openProcessStartDate;
			}

			// 종료 날짜 수정
			if (!openProcessEndDate.equals("")) {
				endDate = openProcessEndDate;
			}

			// 강의실 수정
			if (!openProcessLectureRoom.equals("")) {
				lectureRoom = openProcessLectureRoom;
			}

			sql = "{ call proc_updateOpenProcess(?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openProcessSeq);
			cstat.setString(2, startDate);
			cstat.setString(3, endDate);
			cstat.setString(4, lectureRoom);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.updateOpenProcess() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 수정할 개설 과정 번호와 수정할 수강의 수료 날짜를 받아 특정 개설과정의 수료 날짜를 수정하는 메소드이다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param openProcessSeq 개설 과정 번호
	 * @param openProcessFinishDate 개설 과정의 수료 날짜
	 * @return 성공 여부
	 */
	// 수료 날짜 수정
	public int updateFinishDate(String openProcessSeq, String openProcessFinishDate) {
		// 원래 값
		String finishDate = "";

		try {

			String sql = "SELECT finishDate FROM tblRegistrationCourse WHERE open_process_seq = " + openProcessSeq;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				finishDate = rs.getString("finishDate");
			}
			rs.close();

			// 시작 날짜 수정
			if (!openProcessFinishDate.equals("")) {
				finishDate = openProcessFinishDate;
			}

			sql = "{ call proc_updateFinishDate(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, openProcessSeq);
			cstat.setString(2, finishDate);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.updateFinishDate() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 특정 개설 과정의 담당 교사를 변경하는 메소드이다.
	 * 담당 교사를 변경할 개설 과정의 번호와 새 교사의 교사 번호를 받아 개설 과정의 담당 교사를 변경한다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param openProcessSeq 개설 과정 번호
	 * @param openProcessteacherSeq 새 담당 교사 번호
	 * @return 성공 여부
	 */
	// 교사의 담당 과정 수정
	public int updateTeacherCourse(String openProcessSeq, String openProcessteacherSeq) {

		// 원래 값
		String teacherSeq = "";

		try {

			String sql = "SELECT teacher_seq FROM tblTeacherCourse WHERE openProcess_seq = " + openProcessSeq;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				teacherSeq = rs.getString("teacher_seq");
			}
			rs.close();

			sql = "{ call proc_updateTeacherCourse(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, openProcessSeq);
			cstat.setString(2, teacherSeq);
			cstat.setString(3, openProcessteacherSeq);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.updateTeacherCourse() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 개설된 개설 과정을 삭제하는 메소드이다.
	 * 개설 과정을 삭제하면 해당 개설 과정에 개설된 개설 과목과 시험 정보, 성적 등이 삭제된다.
	 * 또한 개설 과정을 들었던 수강 정보(출결 및 교사평가)가 삭제된다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param openProcessSeq 개설 과정 번호
	 * @return 성공 여부
	 */
	// 개설 과정 삭제
	public int deleteOpenProcess(String openProcessSeq) {

		try {
			String sql = "{ call proc_deleteOpenProcess(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, openProcessSeq);
			
			return cstat.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("OpenProcessDAO.deleteOpenProcess() : " + e.toString());
		}
		return 0;
	}

	/**
	 * 특정 개설 과정에 등록된 개설 과목들을 배열로 반환하는 메소드이다.
	 * 개설 과목의 정보에는 개설 과목 번호, 과목 이름, 개설 과목 시작 날짜, 개설 과목 종료 날짜, 교재가 포함되어 있다.
	 * @param openProcessSeq 개설 과정 번호
	 * @return 개설 과목 정보 배열
	 */
	// 특정 개설 과정의 개설 과목 목록 조회
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
	 * 개설 과정에 등록할 수 있는 개설 과목의 목록을 보여주는 메소드이다.
	 * 개설 과목의 정보에는 개설 과목 번호, 과목 이름, 교재, 개설 과목 시작 날짜, 개설 과목 종료 날짜가 포함된다.
	 * @return 개설 과목 정보 배열
	 */
	// 개설 과정이 NULL인 개설 과목 조회
	public ArrayList<OpenSubjectDTO> openSubjectListOfNull() {
		
		ArrayList<OpenSubjectDTO> result = new ArrayList<OpenSubjectDTO>();

		try {

			String sql = "{ call proc_openSubjectListOfNull(?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(1);
			
			while (rs.next()) {
				
				OpenSubjectDTO osdto = new OpenSubjectDTO();
				
				osdto.setSeq(rs.getString("seq"));
				osdto.setTitle(rs.getString("title"));
				osdto.setBook(rs.getString("book"));
				osdto.setStartDate(rs.getString("startDate"));
				osdto.setEndDate(rs.getString("endDate"));
				
				result.add(osdto);
				
			}
			
			return result;

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectListOfNull() : " + e.toString());
		}
		
		return null;
	}

	/**
	 * 개설 과정에 개설 과목을 등록하는 메소드이다.
	 * 개설 과목을 등록할 개설 과정의 번호와 개설 과목 번호 여러 개를 배열로 받아 개설 과목들을 개설 과정에 등록한다.
	 * 모든 개설 과목을 개설 과정에 등록하면 성공 여부를 반환한다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeqs 개설 과목 번호 배열
	 * @return 개설 과목 등록 성공 여부
	 */
	// 특정 개설 과정에 개설 과목 추가 (개설 과목 번호 NULL > 업데이트)
	public int addOpenSubjectToOP(String openProcessSeq, String[] openSubjectSeqs) {

		try {
			
			//자동 Commit 끄기
			// - 선택한 모든 개설 과목 추가 후 COMMIT
			conn.setAutoCommit(false);
			
			boolean loop = true;

			for (int i=0; i<openSubjectSeqs.length; i++) {
				String sql = "{ call proc_addOpenSubjectToOP(?, ?) }";
				cstat = conn.prepareCall(sql);
				cstat.setString(1, openProcessSeq);
				cstat.setString(2, openSubjectSeqs[i]);
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
				System.out.println("OpenProcessDAO.addOpenSubjectToOP() : " + e2.toString());
			}
			
			System.out.println("OpenProcessDAO.addOpenSubjectToOP() : " + e.toString());
		}
		return 0;

	}

	/**
	 * 개설 과정에 등록된 개설 과목을 삭제하는 메소드이다.
	 * 개설 과목을 삭제할 개설 과정의 번호와 삭제할 개설 과목의 번호를 받아 개설 과정에서 개설 과목을 삭제한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 성공 여부
	 */
	// 특정 개설 과정에서 개설 과목 삭제 (개설 과목 번호 NULL)
	public int deleteOpenSubject(String openProcessSeq, String openSubjectSeq) {

		try {

			String sql = "{ call proc_deleteOpenSubjectOfOP(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, openProcessSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.deleteOpenSubject() : " + e.toString());
		}
		return 0;
	}

	/**
	 * 특정 개설 과정에 등록된 교육생들을 배열로 반환하는 메소드이다.
	 * 교육생의 정보에는 교육생 번호, 교육생 이름, 교육생 전화번호, 교육생 등록일, 개설 과정 수료 및 중도탈락 여부가 포함되어 있다.
	 * @param openProcessSeq 개설 과정 번호
	 * @return 교육생 데이터 정보 배열
	 */
	// 특정 개설 과정의 교육생 목록 조회
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

	/**
	 * 현재 수강중이지 않은 교육생들을 배열로 반환하는 메소드이다.
	 * 교육생의 정보에는 교육생 번호, 교육생 이름, 교육생 주민번호 뒷자리, 교육생 전화번호, 교육생 등록일이 포함된다.
	 * @return 교육생 정보 배열
	 */
	// 전체 교육생 목록 조회
	// 수강중이지 않은 교육생들
	public ArrayList<StudentDTO> studentListNotRC() {
		
		ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();
		
		try {
			
			String sql = "{ call proc_studentListNotRC(?) }";
			cstat = conn.prepareCall(sql);
			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			rs = (ResultSet)cstat.getObject(1);
			
			while (rs.next()) {
				
				StudentDTO sdto = new StudentDTO();
				
				sdto.setSeq(rs.getString("seq"));
				sdto.setName(rs.getString("name"));
				sdto.setRegidentRegistrationNumber(rs.getString("regidentRegistrationNumber"));
				sdto.setPhone(rs.getString("phone"));
				sdto.setRegdate(rs.getString("regdate"));
				
				result.add(sdto);
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println("OpenProcessDAO.studentList() : " + e.toString());
		}
		
		return null;
	}
	

	/**
	 * 개설 과정에 교육생을 등록하는 메소드이다.
	 * 교육생을 등록할 개설 과정의 번호와 교육생 번호 여러 개를 배열로 받아 교육생들을 개설 과정에 등록한다.
	 * 모든 교육생을 개설 과정에 등록하면 성공 여부를 반환한다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param studentSeqs 교육생 번호 배열
	 * @return 성공 여부
	 */
	// 특정 개설 과정에 교육생 추가 (수강 테이블에 INSERT)
	public int addStudent(String openProcessSeq, String[] studentSeqs) {

		try {
			
			//자동 Commit 끄기
			// - 선택한 교육생들을 모두 추가했을 때 COMMIT
			conn.setAutoCommit(false);
			
			
			//개설과정에 교육생들 추가
			boolean loop = true;
			for (int i=0; i<studentSeqs.length; i++) {
				
				String sql = "{ call proc_addStudentToOP(?, ?) }";
				
				cstat = conn.prepareCall(sql);
				cstat.setString(1, openProcessSeq);
				cstat.setString(2, studentSeqs[i]);
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
				System.out.println("OpenProcessDAO.addStudent() : " + e2.toString());
			}
			
			System.out.println("OpenProcessDAO.addStudent() : " + e.toString());
		}
		return 0;
	}
	

	/**
	 * 개설 과정에 등록된 교육생을 삭제하는 메소드이다.
	 * 교육생을 삭제할 개설 과정의 번호와 삭제할 교육생 번호를 받아 개설 과정에서 교육생을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param openProcessSeq 개설 과정 번호
	 * @param studentSeq 교육생 번호
	 * @return 성공 여부
	 */
	// 특정 개설 과정에서 교육생 삭제
	public int deleteStudent(String openProcessSeq, String studentSeq) {

		try {

			String sql = "{ call proc_deleteStudentOfOP(?, ?) }";
			
			cstat = conn.prepareCall(sql);
			cstat.setString(1, openProcessSeq);
			cstat.setString(2, studentSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.deleteStudent() : " + e.toString());
		}
		return 0;
	}

	
	
	
	
	
	
	/**
	 * 로그인 한 교육생의 수강한 개설 과정들을 배열로 반환하는 메소드이다.
	 * 개설 과정의 정보에는 개설 과정 번호, 과정 이름, 개설 과정 시작 날짜, 개설 과정 종료 날짜, 강의실, 개설 과정 등록 인원, 개설 과목 등록 여부가 포함된다.
	 * @return 개설 과정 정보 배열
	 */
	// 학생성적전용 수강한 과정 조회
	public ArrayList<OpenProcessDTO> registProcessList() {

		String studentSeq = Sequence.StudentSeq;
		try {

			String sql = "{ call prod_listStudentProcess(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(2);

			ArrayList<OpenProcessDTO> result = new ArrayList<OpenProcessDTO>();

			while (rs.next()) {

				OpenProcessDTO tccdto = new OpenProcessDTO();

				tccdto.setSeq(rs.getString("pseq"));
				tccdto.setTitle(rs.getString("pTitle"));
				tccdto.setStartDate(rs.getString("opStartdate"));
				tccdto.setEndDate(rs.getString("opEnddate"));
				tccdto.setLectureRoom(rs.getString("lrName"));
				tccdto.setPersonnel(rs.getString("opPersonnel"));
				tccdto.setExistStatus(rs.getString("opExistsatus"));
				result.add(tccdto);
			}

			return result;

		} catch (Exception e) {
			System.out.println("TeacherDAO.teacherCourseList() : " + e.toString());
		}

		return null;
	}

}

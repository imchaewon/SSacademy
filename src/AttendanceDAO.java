import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;


/**
 * 출결 관련(등록,수정,삭제,조회) 모든 프로시져 관리를 위한 DAO이다.
 * @author 이진수, 이홍진
 */
public class AttendanceDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private ResultSet rs2;
	private CallableStatement cstat;
	private CallableStatement cstat2;
	private String studentNum;

	/**
	 * 기본 생성자 Connection과 Statement를 생성한다.
	 */
	public AttendanceDAO() {

		try {
			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("AttendanceDAO.AttendanceDAO() : " + e.toString());
		}
	}

	
	/**
	 * 전체 과정의 목록을 출력하기 위한 메소드이다.
	 * 과정번호, 과정명, 과정시작일, 과정종료일, 강의실명, 수강인원수, 등록여부를 출력한다.
	 * @return  전체 과정 목록 정보 배열
	 */
	public ArrayList<OpenProcessDTO> openProcessList() {

		try {

			ArrayList<OpenProcessDTO> result = new ArrayList<OpenProcessDTO>();

			String sql = "{call proc_ProcessList(?)}";

			cstat = conn.prepareCall(sql);

			cstat.registerOutParameter(1, OracleTypes.CURSOR);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(1);
			while (rs.next()) {

				OpenProcessDTO dto = new OpenProcessDTO();

				dto.setSeq(rs.getString("opSeq")); // 과정번호
				dto.setTitle(rs.getString("pTitle")); // 과정명
				dto.setStartDate(rs.getString("opStartDate")); // 과정시작일
				dto.setEndDate(rs.getString("opEndDate")); // 과정종료일
				dto.setLectureRoom(rs.getString("lrName")); // 강의실명
				dto.setPersonnel(rs.getString("opPersonnel")); // 수강인원수
				dto.setExistStatus(rs.getString("opExistsatus")); // 등록여부

				result.add(dto);
			}
			return result;
		} catch (Exception e) {
			System.out.println("AttendanceDAO.openProcessList() : " + e.toString());
		}

		return null;
	}

	/**
	 * 해당 과정을 수강하는 교육생들의 정보를 출력하는 메소드이다.
	 * 학생번호, 학생명, 학생주민번호뒷자리, 학생전화번호, 학생등록일 , 학생 수료여부를 출력한다.
	 * @param openProcessSeq 개설 과정 번호 
	 * @return 교육생 정보 배열
	 */
	public ArrayList<StudentDTO> student(String openProcessSeq) {

		try {
			ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();

			String sql = "{call proc_student(?,?) }";

			cstat = conn.prepareCall(sql);

			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setString(2, openProcessSeq);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(1);

			if (rs.next()) {
				while (rs.next()) {

					StudentDTO dto = new StudentDTO();

					dto.setSeq(rs.getString("rcStudentSeq")); // 학생번호
					dto.setName(rs.getString("stName"));// 학생명
					dto.setRegidentRegistrationNumber(rs.getString("stRegidentregistrationNumber"));// 학생주민번호뒷자리
					dto.setPhone(rs.getString("stPhone")); // 학생전화번호
					dto.setRegdate(rs.getString("srRegdate")); // 학생등록일
					dto.setFinishStatus(rs.getString("rcFinishStatus")); // 수료여부

					result.add(dto);

				}

				return result;
			} else {
				System.out.println("데이터가 없습니다.");
				AdminController ac = new AdminController();
				ac.attendanceManagement();
			}
		} catch (Exception e) {
			System.out.println("AttendanceDAO.student() : " + e.toString());
		}
		return null;
	}

	/**
	 * 해당 과정을 수강하는 교육생들의 기간별 출결 조회를 하는 메소드이다.
	 * 교육생의 출결 상태를 출력한다.
	 * @param registrationSeq	수강 번호
	 * @param startDate			과정 시작날짜
	 * @param endDate			과정 종료날자
	 * @param openProcessSeq	과정 번호
	 * @return	기간 내 출결 데이터 정보 배열
	 */
	public ArrayList<AttendanceDTO> searchPeriod(String registrationSeq, String startDate, String endDate,
			String openProcessSeq) {

		try {
			ArrayList<AttendanceDTO> result = new ArrayList<AttendanceDTO>();
			studentNum = registrationSeq;
			String sql = "{call proc_searchPeriod(?,?,?,?,?)}";

			cstat = conn.prepareCall(sql);

			cstat.registerOutParameter(1, OracleTypes.CURSOR);
			cstat.setString(2, openProcessSeq);
			cstat.setString(3, registrationSeq);
			cstat.setString(4, startDate);
			cstat.setString(5, endDate);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(1);

			String substr = "";
			String seq = "1";
			while (rs.next()) {

				AttendanceDTO dto = new AttendanceDTO();

				dto.setSeq(seq);
				checkDate(substr, dto, startDate);

				dto.setAttendanceStatus(rs.getString("aStatus"));

				result.add(dto);
				Sequence.chul.add(dto);
				substr = dto.getAttendanceTime();
				seq = String.format("%s", Integer.parseInt(seq) + 1);

			}

			return result;
		} catch (Exception e) {
			System.out.println("AttendanceDAO.searchPeriod() : " + e.toString());
		}
		return null;
	}

	
	/**
	 * 받아온 출력 정보의 null값을 제거하기위해 만든 메소드 checkDate이다.
	 * @param substr		날짜
	 * @param dto			출결정보 dto
	 * @param startDate		시작날짜
	 * @throws SQLException
	 */
	private void checkDate(String substr, AttendanceDTO dto, String startDate) throws SQLException {
		String a = "";
		String b = "";
		String c = "";

		if (rs.getString("aData") == null) {
			if (substr.equals("")) {
				dto.setAttendanceTime(startDate);
			} else {
				a = substr.substring(substr.length() - 5, substr.length() - 3); // 월
				b = substr.substring(substr.length() - 2, substr.length()); // 일
				c = substr.substring(0, 4);

				switch (a) {
				case "01":
				case "03":
				case "05":
				case "07":
				case "08":
				case "10":
					if (b.equals("31")) {
						b = "01";
						int month = Integer.parseInt(a) + 1;
						a = String.format("%s", month);
					} else {
						int date = Integer.parseInt(b) + 1;
						if (date >= 10) {
							b = String.format("%s", date);
						} else {
							b = String.format("0%s", date);
						}
					}
					break;
				case "04":
				case "06":
				case "09":
				case "11":
					if (b.equals("30")) {
						b = "01";
						int month = Integer.parseInt(a) + 1;
						a = String.format("%s", month);

					} else {
						int date = Integer.parseInt(b) + 1;
						if (date >= 10) {
							b = String.format("%s", date);
						} else {
							b = String.format("0%s", date);
						}
					}
					break;
				case "12":
					if (b.equals("31")) {
						b = "01";
						a = "01";
						int year = Integer.parseInt(c) + 1;
						c = String.format("%s", year);
					} else {
						int date = Integer.parseInt(b) + 1;
						if (date >= 10) {
							b = String.format("%s", date);
						} else {
							b = String.format("0%s", date);
						}
					}
					break;
				case "02":
					if (b.equals("28") || b.equals("29")) {
						b = "01";
						a = "03";
					} else {
						int date = Integer.parseInt(b) + 1;
						if (date >= 10) {
							b = String.format("%s", date);
						} else {
							b = String.format("0%s", date);
						}
					}
					break;
				}

				String resultDate = "";
				resultDate = String.format("%s-%s-%s", c, a, b);
				dto.setAttendanceTime(resultDate);
			}
		} else {
			dto.setAttendanceTime(rs.getString("aData"));
		}
	}

	
	
	/**
	 * 특정 개설과정의 특정 교육생의 특정 날짜의 시간 수정을 위해 조작되는 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param attendanceSeq	해당 교육생의 번호
	 * @return 수정 성공 여부
	 */
	public ArrayList<AttendanceDTO> updateAttendance(String attendanceSeq) {
		try {

			ArrayList<AttendanceDTO> result = new ArrayList<AttendanceDTO>();

			String dc = "";
			for (int i = 0; i < Sequence.chul.size(); i++) {
				if (attendanceSeq.equals(Sequence.chul.get(i).getSeq())) {
					dc = (Sequence.chul.get(i).getAttendanceTime());
					break;
				}

			}
			// System.out.println("학생번호 : " + Sequence.StudentSeq+" 검색하고자하는 날짜 : "+dc);

			String sql = "{call proc_fasd(?,?,?)}";

			cstat = conn.prepareCall(sql);
			cstat.setString(1, Sequence.StudentSeq);
			cstat.setString(2, dc);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(3);

			while (rs.next()) {
				AttendanceDTO dto = new AttendanceDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setRcseq(rs.getString("registration_course_seq"));
				dto.setAttendanceTime(rs.getString("time"));
				result.add(dto);
			}
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 수강한 과정의 전체 출결 조회를 위한 메소드이다.
	 * @param registrationSeq 수강한 과정의 번호
	 * @return 과정 전체 출결 목록 정보 배열
	 */
	public ArrayList<AttendanceDTO> attendanceList(String registrationSeq) {
		String opseq = "";
		ArrayList<String> op = new ArrayList<String>();
		ArrayList<AttendanceDTO> result = new ArrayList<AttendanceDTO>();
		try {
			// 선택한 년, 월에 해당하는 과정 출결 목록

			String sql = "{call proc_studentProcess(?,?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, Sequence.StudentSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);

			int n = 0;
			while (rs.next()) {
				if (rs.getString("open_process_seq") != null) {
					op.add(n, rs.getString("open_process_seq"));
					n++;
				}
			}

		} catch (Exception e) {
			System.out.println("AttendanceDAO.monthAttendanceList() : " + e.toString());
		}

		try {

			String sql = "{call proc_allAttendanceList(?,?,?)}"; // 교육생이 수강한 과정 전체 출결 조회목록

			cstat2 = conn.prepareCall(sql);

			for (int i = 0; i < op.size(); i++) {
				cstat2.registerOutParameter(1, OracleTypes.CURSOR);
				cstat2.setString(2, op.get(i));
				cstat2.setString(3, Sequence.StudentSeq);
				cstat2.executeQuery();
				rs2 = (ResultSet) cstat2.getObject(1);

				while (rs2.next()) {

					AttendanceDTO atdto = new AttendanceDTO();

					atdto.setOpenProcessName(rs2.getString("pTitle")); // 과정명
					atdto.setAttendanceTime(rs2.getString("aDate")); // 시간 (월)
					atdto.setAttendanceTime2(rs2.getString("aTime")); // 시간 (일)
					atdto.setAttendanceStatus(rs2.getString("aStatus")); // 출결상태

					result.add(atdto);

				}
			}

			return result;
		} catch (Exception e) {
			System.out.println("AttendanceDAO.allProcessAttendanceList() : " + e.toString());
		}

		return null;
	}

	/**
	 * 
	 * 해당 교육생의 월별 조회를 위하여 만들어진 메소드이다.
	 * 교육생의 출결 정보를 월별로 출력한다.
	 * @param year	조회하고 싶은 년도
	 * @param month 조회하고 싶은 월	
	 * @return	월별 출결 정보 배열
	 */
	public ArrayList<AttendanceDTO> monthAttendanceList(String year, String month) {
		String opseq = "";
		ArrayList<AttendanceDTO> result = new ArrayList<AttendanceDTO>();
		ArrayList<String> op = new ArrayList<String>();
		try {
			// 선택한 년, 월에 해당하는 과정 출결 목록

			String sql = "{call proc_studentProcess(?,?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, Sequence.StudentSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			cstat.executeQuery();
			rs = (ResultSet) cstat.getObject(2);

			int n = 0;
			while (rs.next()) {
				if (rs.getString("open_process_seq") != null) {
					op.add(n, rs.getString("open_process_seq"));
					n++;
				}
			}

		} catch (Exception e) {
			System.out.println("AttendanceDAO.monthAttendanceList() : " + e.toString());
		}

		try {
			String date = String.format("%s-%s", year, month);

			String sql = "{call proc_studentSearchPeriod(?,?,?,?)}";
			CallableStatement cstat2 = null;
			cstat2 = conn.prepareCall(sql);

			for (int i = 0; i < op.size(); i++) {
				cstat2.registerOutParameter(1, OracleTypes.CURSOR);
				cstat2.setString(2, op.get(i));
				cstat2.setString(3, Sequence.StudentSeq);
				cstat2.setString(4, date);
				cstat2.executeQuery();
				ResultSet rs2 = (ResultSet) cstat2.getObject(1);

				while (rs2.next()) {
					AttendanceDTO dto = new AttendanceDTO();
					dto.setAttendanceTime(rs2.getString("aData"));
					dto.setAttendanceStatus(rs2.getString("state"));
					result.add(dto);
				}

			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	
	/**
	 * 해당 교육생의 출석시간을 수정하기 위한 메소드 이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param seqNum	해당 교육생 번호
	 * @param time		해당 교육생의 출석시간
	 * @return 수정 완료 여부
	 */
	public int updateAttendance2(String seqNum, String time) {

		try {
			String sql = "{call proc_updateAttendance(?,?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, time);
			cstat.setString(2, seqNum);
			return cstat.executeUpdate();

		} catch (Exception e) {
		}

		return 0;
	}

	/**
	 * 교육생의 출석체크를 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @return 출석 체크 성공 여부
	 */
	public int AttendanceCheck2() {

		try {

			String sql = "{call proc_addAttendance(?)}";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, Sequence.StudentSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;

	}

}

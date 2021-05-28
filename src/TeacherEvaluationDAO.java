import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
/**
 * 교사 평가 관리를 위한 클래스이다.
 * 교사 평가 점수 등록,수정,삭제,조회 실행 메소드가 작성되어있다.
 * @author 노형준
 * 
 */
public class TeacherEvaluationDAO {

	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	
	/**
	 * 기본 생성자, Connection 과 Statement를 만들어준다.
	 */
	public TeacherEvaluationDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}
	
	//교육생이 수강하는 개설 과정들 + 평가 점수 평균
	/**
	 * 형준
	 * 교사 평가 전체 목록을 조회하기 위한 메소드이다.
	 * 교사번호,과정명,과정시작날짜,과정 종료 날짜, 과정 수료 여부,교사명
	 * 강의계획서 이행점수, 강의내용 전달 및 이해점수, 소통 점수,  유익성 점수, 전반적인 만족도 점수를 출력한다.
	 * @param studentSeq	교육생 번호
	 * @return 교사 평가 전체 정보 배열
	 */
	public ArrayList<TeacherEvaluationDTO> teacherEvaluationList(String studentSeq) {

		try {
			String sql = "{ call proc_listTeacherEv(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, studentSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<TeacherEvaluationDTO> result = new ArrayList<TeacherEvaluationDTO>();
			
			while(rs.next()) {
				
				TeacherEvaluationDTO tedto = new TeacherEvaluationDTO();
				
				tedto.setSeq(rs.getString("teSeq")); //교사번호
				tedto.setProcessName(rs.getString("pTitle")); //과정명
				tedto.setStartDate(rs.getString("opStartdate")); //과정시작날짜
				tedto.setEndDate(rs.getString("opEnddate")); //과정종료날짜
				tedto.setFinishStatus(rs.getString("rFinishStatus")); //과정수료여부
				tedto.setTeacherName(rs.getString("tName")); //교사명
				tedto.setProcessScore(rs.getString("teProcessScore")); //평가 - 강의계획서 이행점수
				tedto.setUnderstandScore(rs.getString("teUnderstandScore")); //평가 - 강의내용 전달 및 이해점수
				tedto.setCommunicationScore(rs.getString("teCommunicationScore")); //평가 - 소통 점수
				tedto.setUsefulScore(rs.getString("teUsefulScore")); //평가 - 유익성 점수
				tedto.setSatisfactionScore(rs.getString("teSatisfactionScore")); //평가 - 전반적인 만족도 점수
				
				result.add(tedto);
				
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println("TeacherEvaluationDAO.teacherEvaluationList() : " + e.toString());
		}
		
		return null;
	}
	
	/**
	 * 형준
	 * 
	 * 교육생이 실시한 교사 평가를 조회하기 위한 메소드이다.
	 * 교사번호,과정명,과정시작날짜,과정 종료 날짜, 과정 수료 여부,교사명
	 * 강의계획서 이행점수, 강의내용 전달 및 이해점수, 소통 점수,  유익성 점수, 전반적인 만족도 점수를 출력한다.
	 * @param studentSeq 해당 교육생의 번호
	 * @return 교육생이 실시한 교사 평가 정보 배열
	 */
	//학생전용 교사 평가 조회
	public ArrayList<TeacherEvaluationDTO> studentEvaluationList(String studentSeq) {

		try {
			
			String sql = "{ call proc_listTeacherEv(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, studentSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<TeacherEvaluationDTO> dto = new ArrayList<TeacherEvaluationDTO>();
			
			while(rs.next()) {

				TeacherEvaluationDTO tedto = new TeacherEvaluationDTO();
				
				tedto.setSeq(rs.getString("teSeq")); //교사번호
				tedto.setProcessName(rs.getString("pTitle")); //과정명
				tedto.setStartDate(rs.getString("opStartdate")); //과정시작날짜
				tedto.setEndDate(rs.getString("opEnddate")); //과정종료날짜
				tedto.setFinishStatus(rs.getString("rFinishStatus")); //과정수료여부
				tedto.setTeacherName(rs.getString("tName")); //교사명
				tedto.setProcessScore(rs.getString("teProcessScore")); //평가 - 강의계획서 이행점수
				tedto.setUnderstandScore(rs.getString("teUnderstandScore")); //평가 - 강의내용 전달 및 이해점수
				tedto.setCommunicationScore(rs.getString("teCommunicationScore")); //평가 - 소통 점수
				tedto.setUsefulScore(rs.getString("teUsefulScore")); //평가 - 유익성 점수
				tedto.setSatisfactionScore(rs.getString("teSatisfactionScore")); //평가 - 전반적인 만족도 점수
				
				dto.add(tedto);
				
			}
			
			return dto;
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 형준
	 * 교육생이 교사 평가를 등록하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq		수강 번호
	 * @param processScore			과정 번호
	 * @param understandScore		이해도 점수
	 * @param communicationScore	소통 점수
	 * @param usefulScore			유익성 점수
	 * @param satisfactionScore		만족도 점수
	 * @return 교사평가 등록 성공 여부
	 */
	//3-1. 교사평가등록
	public int addTeacherEvaluation(String registrationSeq, String processScore, String understandScore, String communicationScore, String usefulScore, String satisfactionScore) {

		try {
			
			String sql = "{ call proc_addTeacherEvaluation(?, ?, ?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, processScore); //강의계획서 이행점수
			cstat.setString(3, understandScore); //강의내용 전달 및 이해점수
			cstat.setString(4, communicationScore); //소통 점수
			cstat.setString(5, usefulScore); //유익성 점수
			cstat.setString(6, satisfactionScore); //전반적인 만족도 점수
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherEvaluationDAO.addTeacherEvaluation() : " + e.toString());
		}
		
		return 0;
	}
	
	/**
	 * 형준
	 * 교육생이 평가한 이행 점수를 수정하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq	수강 번호
	 * @param processScore		이행 점수
	 * @return	교사 평가 이행점수 수정 성공 여부
	 */
	//3-2 교사 평가 이행점수 수정
	public int updateProcessScore(String registrationSeq, String processScore) {

		try {
			
			String sql = "{ call proc_updateProcessscore(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, processScore);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	

	//3-2 교사 평가 이해점수 수정
	/**
	 * 교육생이 평가한 이해 점수를 수정하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq	수강 번호
	 * @param understandScore	이해점수
	 * @return 교사 평가 이해점수 수정 성공 여부
	 */
	public int updateUnderstandScore(String registrationSeq, String understandScore) {

		try {
			
			String sql = "{ call proc_updateUnderstandscore(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, understandScore);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	
	//3-2 교사 평가 소통 점수 수정
	/**
	 * 교육생이 평가한 소통 점수를 수정하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq		수강번호
	 * @param communicationScore	소통 점수
	 * @return 교사 평가 소통 점수 수정 성공 여부
	 */
	public int updateCommunicationScore(String registrationSeq, String communicationScore) {

		try {
			
			String sql = "{ call proc_updateCommunicationScore(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, communicationScore);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교육생이 평가한 유익성 점수를 수정하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq 수강번호
	 * @param usefulScore	유익성 정보
	 * @return	교사 평가 유익성 점수 수정 성공 여부
	 */
	//3-2 교사 평가 유익성 점수 수정
	public int updateUsefulScore(String registrationSeq, String usefulScore) {

		try {
			
			String sql = "{ call proc_updateUsefulScore(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, usefulScore);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교육생이 평가한 만족도 점수를 수정하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq	수강 번호
	 * @param satisfactionScore		만족도 점수
	 * @return	교사 평가 만족도 점수 수정 성공 여부
	 */
	//3-2 교사 평가 만족도 점수 수정
	public int updateSatisfactionScore(String registrationSeq, String satisfactionScore) {

		try {
			
			String sql = "{ call proc_updateSatisfactionScore(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, satisfactionScore);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}	
	
	/**
	 * 교육생이 평가한 점수들을 삭제하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq 수강 번호
	 * @return 교사 평가 점수 삭제 성공 여부
	 */
	//3-3 교사 평가 점수 삭제
	public int deleteProcessScore(String registrationSeq) {

		try {
			
			String sql = "{ call proc_deleteProcessScore(?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * 교사 평가 점수를 삭제하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq 수강 번호
	 * @param str 프로시져 명
	 * @return 교사 평가 점수 삭제 성공 여부
	 */
	//교사 평가 점수 삭제(통합)
	public int deleteEvaluations(String registrationSeq, String str) {

		try {
			
			String sql = "{ call " + str + "(?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 특정 교사의 개설과정에 관한 평가를 조회하기 위한 메소드이다.
	 * @param teacherSeq 교사 번호
	 * @return 특정 교사의 개설과정에 관한 평가 정보 배열
	 */
	//특정 교사의 (개설과정 + 평가) 조회
	public ArrayList<OpenProcessEvaluationDTO> teacherCourse(String teacherSeq) {

		try {
			
			String sql = "{ call proc_listOPTeacherEvaluation(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, teacherSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			ResultSet rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<OpenProcessEvaluationDTO> dto = new ArrayList<OpenProcessEvaluationDTO>();
			
			while(rs.next()) {
				
				OpenProcessEvaluationDTO op = new OpenProcessEvaluationDTO();
				
				op.setSeq(rs.getString(1));
				op.setTitle(rs.getString(2));
				op.setStartDate(rs.getString(3));
				op.setEndDate(rs.getString(4));
				op.setPersonnel(rs.getString(5));
				op.setEvaluationPersonnel(rs.getString(6));
				op.setFinishStatus(rs.getString(7));
				op.setProcessScore(rs.getString(8));
				op.setUnderstandScore(rs.getString(9));
				op.setCommunicationScore(rs.getString(10));
				op.setUsefulScore(rs.getString(11));
				op.setSatisfactionScore(rs.getString(12));
				
				dto.add(op);
				
			}
			
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 특정 개설 과정의 평가점수를 조회할수 있는 메소드이다.
	 * 교사명,교사 평가 점수들을 출력한다.
	 * @param openProcessSeq	개설 과정 번호
	 * @return 특정 개설 과정 평가점수 정보 배열
	 */
	//특정 개설 과정의 평가 점수
	public ArrayList<EvaluationScoreDTO> scoreList(String openProcessSeq) {

try {
			
			String sql = "{ call proc_listTeacherEvaluation(?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, openProcessSeq);
			cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstat.executeQuery();
			
			ResultSet rs = (ResultSet)cstat.getObject(2);
			
			ArrayList<EvaluationScoreDTO> dto = new ArrayList<EvaluationScoreDTO>();
			
			while(rs.next()) {
				
				EvaluationScoreDTO op = new EvaluationScoreDTO();
				
//				op.setRegistrationSeq(rs.getString(1));
//				op.setStudentName(rs.getString(2));
//				op.setProcessScore(rs.getString(3));
//				op.setUnderstandScore(rs.getString(4));
//				op.setCommunicationScore(rs.getString(5));
//				op.setUsefulScore(rs.getString(6));
//				op.setSatisfactionScore(rs.getString(7));
				op.setRegistrationSeq(rs.getString("rcSeq"));
				op.setStudentName(rs.getString("sname"));
				op.setProcessScore(rs.getString("teProcessscore"));
				op.setUnderstandScore(rs.getString("teUnderstandScore"));
				op.setCommunicationScore(rs.getString("teCommunicationscore"));
				op.setUsefulScore(rs.getString("teUsefulscore"));
				op.setSatisfactionScore(rs.getString("teSatisfactionscore"));
				
				dto.add(op);
				
			}
			
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 교육생이 해당 교사평가의 모든 내용을 수정하고 싶을때 실행되는 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param registrationSeq		수강 번호
	 * @param processScore			이행 점수
	 * @param understandScore		이해 점수
	 * @param communicationScore	소통 점수
	 * @param usefulScore			유익성 점수
	 * @param satisfactionScore		만족도 점수
	 * @return 교사평가 전체 수정 성공 여부
	 */
	//교육생전용 교사평가 전체 수정
	public int updateTeacherEvaluation(String registrationSeq, String processScore, String understandScore, String communicationScore,
			String usefulScore, String satisfactionScore) {

		try {
			
			String sql = "{ call proc_updateTeacherEvaluation(?, ?, ?, ?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, registrationSeq);
			cstat.setString(2, processScore); //강의계획서 이행점수
			cstat.setString(3, understandScore); //강의내용 전달 및 이해점수
			cstat.setString(4, communicationScore); //소통 점수
			cstat.setString(5, usefulScore); //유익성 점수
			cstat.setString(6, satisfactionScore); //전반적인 만족도 점수
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherEvaluationDAO.addTeacherEvaluation() : " + e.toString());
		}
		
		return 0;
	}

	/**
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param pnum 해당 교사평가자료 번호
	 * @return 교사평가 전체 삭제 성공 여부
	 */
	//교육생전용 교사평가 전체 삭제
	public int deleteTeacherEvaluation(String pnum) {
		
		try {
			
			String sql = "{ call proc_deleteTeacherEvaluation(?) }";
			cstat = conn.prepareCall(sql);
			
			cstat.setString(1, pnum);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("TeacherEvaluationDAO.addTeacherEvaluation() : " + e.toString());
		}
		
		return 0;
	}

	
}//TeacherEvaluationDAO

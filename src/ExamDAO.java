import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;
/**
 * 
 * @author 이진수, 노형준
 * 시험 관련 메소드를 관리하기 위한 클래스이다.
 * 시험의 등록,수정,삭제,조회를 위한 실행 메소드가 작성되어있다.
 */
public class ExamDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	ResultSet rs;
	CallableStatement cstat;

	/**
	 * 기본 생성자, Connection 과 Statement 가 생성되어있다.
	 */
	public ExamDAO() {
		try {
			conn = DBUtil.open();
			stat = conn.createStatement();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 전체 개설 과정 목록을 조회하기위한 메소드
	 * 개설 과정의 번호, 이름, 시작날짜, 끝날짜, 강의실이름, 수강인원, 등록여부를 출력한다.
	 * @return 전체 개설 과정 목록 정보 배열
	 */
	   public ArrayList<OpenProcessDTO> listAllOpenProcess() {
	         
	         ArrayList<OpenProcessDTO> result = new ArrayList<OpenProcessDTO>();

	         try {

	            String sql = "SELECT opSeq, pTitle, opStartDate, opEndDAte, lrName, opPersonnel, opExiststatus FROM vw_listAllOpenProcess";
	            rs = stat.executeQuery(sql);
	            
	            while (rs.next()) {
	               
	               OpenProcessDTO opdto = new OpenProcessDTO();
	               opdto.setSeq(rs.getString("opSeq"));
	               opdto.setTitle(rs.getString("pTitle"));
	               opdto.setStartDate(rs.getString("opStartDate"));
	               opdto.setEndDate(rs.getString("opEndDAte"));
	               opdto.setLectureRoom(rs.getString("lrName"));
	               opdto.setPersonnel(rs.getString("opPersonnel"));
	               opdto.setExistStatus(rs.getString("opExiststatus"));
	               
	               result.add(opdto);
	            }
	            
	            return result;
	            
	         } catch (Exception e) {
	            System.out.println("OpenProcessDAO.openProcessList() : " + e.toString());
	         }
	         return null;
	      }
	
	  
	   /**
	    * 특정 개설 과정의 개설 과목 목록 조회를 위한  메소드이다
	    * 특정 개설 과정의 개설 과목인 과목번호, 과목이름, 과목 시작일, 과목 종료일, 과목 교재를 출력한다.
	    * @param openProcessSeq 과정 번호
	    * @return 특정 개설 과정의 개설 과목 정보 배열
	    */
	   public ArrayList<OpenSubjectDTO> openSubejct(String openProcessSeq) {

		      ArrayList<OpenSubjectDTO> result = new ArrayList<OpenSubjectDTO>();
		         
		         try {
		            
		            String sql = "{ call proc_listOpenProcessSubject(?, ?) }";
		            cstat = conn.prepareCall(sql);
		            
		            cstat.setString(1, openProcessSeq);
		            cstat.registerOutParameter(2, OracleTypes.CURSOR);
		            
		            cstat.executeQuery();
		            
		            rs = (ResultSet)cstat.getObject(2);
		            
		            while (rs.next()) {
		               
		               OpenSubjectDTO osdto = new OpenSubjectDTO();
		               
		               osdto.setSeq(rs.getString("seq"));
		               osdto.setTitle(rs.getString("title"));
		               osdto.setStartDate(rs.getString("startDate"));
		               osdto.setEndDate(rs.getString("endDate"));
		               osdto.setBook(rs.getString("book"));
		               
		               result.add(osdto);
		            }

		            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		            
		            return result;
		            

		         } catch (Exception e) {
		            System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		         }
		      
		      return null;
		   }

	   /**
	    * 특정 개설 과정을 수강하는 교육생을 조회하기 위해 만들어진  메소드이다.
	    * @param openProcessSeq 개설 과정 번호
	    * @return 특정 개설 과정의 교육생 정보 배열
	    */
	public ArrayList<StudentDTO> student(String openProcessSeq) {
		ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();
		try {
				String sql ="{call prod_listStudent(?,?)}";
				
				cstat=conn.prepareCall(sql);
				cstat.setString(1, openProcessSeq);
				cstat.registerOutParameter(2, OracleTypes.CURSOR);
			
				rs = (ResultSet) cstat.getObject(2);	
				
				while(rs.next()) {
					StudentDTO dto = new StudentDTO();
					
					dto.setSeq(rs.getString("sSeq"));
					dto.setName(rs.getString("sName"));
					dto.setRegidentRegistrationNumber(rs.getString("sRegidentregistrationnumber"));
					dto.setPhone(rs.getString("sPhone"));
					dto.setRegdate(rs.getString("sRegdate"));
				
					
					result.add(dto);
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 
	 * 해당 학생의 필기 점수를 등록하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @param score				필기 점수
	 * @return 필기 점수 등록 성공 여부
	 */
	// 필기 점수 등록
	public int addHandwritingScore(String openSubjectSeq, String studentSeq, String score) {

		try {

			String sql = "{ call proc_addHDStudentscore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);
			cstat.setString(3, score);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 필기 점수를 수정하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @param score				필기 점수
	 * @return 필기 점수 수정 성공 여부
	 */
	// 필기 점수 수정
	public int updateHandwritingScore(String openSubjectSeq, String studentSeq, String score) {

		try {

			String sql = "{ call proc_updateHDStudentscore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);
			cstat.setString(3, score);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 필기 점수를 삭제하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @return	필기 점수 삭제 성공 여부
	 */
	// 필기 점수 삭제
	public int deleteHandwritingScore(String openSubjectSeq, String studentSeq) {

		try {

			String sql = "{ call proc_deleteHDStudentscore(?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 실기 점수를 등록하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @param score				실기 점수
	 * @return 실기 점수 등록 성공 여부
	 */
	// 실기 점수 등록
	public int addPracticalScore(String openSubjectSeq, String studentSeq, String score) {

		try {

			String sql = "{ call proc_addPCStudentscore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);
			cstat.setString(3, score);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 실기 점수를 수정하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @param score				실기 점수
	 * @return 실기 점수 수정 성공 여부
	 */
	// 실기 점수 수정
	public int updatePracticalScore(String openSubjectSeq, String studentSeq, String score) {

		try {

			String sql = "{ call proc_updatePCStudentscore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);
			cstat.setString(3, score);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 실기 점수를 삭제하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @return	실기 점수 삭제 성공 여부
	 */
	// 실기 점수 삭제
	public int deletePracticalScore(String openSubjectSeq, String studentSeq) {

		try {

			String sql = "{ call proc_deletePCStudentscore(?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 출결 점수를 등록하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @param score				출결 점수
	 * @return 출결 점수 등록 성공 여부
	 */
	// 출결 점수 등록
	public int addAttendanceScore(String openSubjectSeq, String studentSeq, String score) {

		try {

			String sql = "{ call proc_addATStudentscore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);
			cstat.setString(3, score);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 출석 점수를 수정하기 위하여 만들어진  메소드이다.
	 * 
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @param score				출결 점수
	 * @return 출결 점수 수정 성공 여부
	 */
	// 출결 점수 수정
	public int updateAttendanceScore(String openSubjectSeq, String studentSeq, String score) {

		try {

			String sql = "{ call proc_updateATStudentscore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);
			cstat.setString(3, score);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}

	/**
	 * 
	 * 해당 학생의 출석 점수를 삭제하기 위하여 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openSubjectSeq	개설 과목 번호
	 * @param studentSeq		교육생 번호
	 * @return	출결 점수 삭제 성공 여부
	 */
	// 출결 점수 삭제
	public int deleteAttendanceScore(String openSubjectSeq, String studentSeq) {

		try {

			String sql = "{ call proc_deleteATStudentscore(?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, studentSeq);

			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return 0;
	}
	
	
	
	
	
	
	
	
	//특정 개설 과정의 특정 개설 과목의 시험 정보 조회
	//필기 배점, 실기 배점, 출결 배점, 시험 날짜, 시험 문제(파일) 등록 여부
	
	
	
	/**
	 * 특점 개설 과정의 특정 개설 과목의 시험 배점 정보를 조회 하기 위한 메소드이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 개설 과정의 배점 정보 배열
	 * 
	 */
	public ArrayList<ExamDTO> examInformation(String openProcessSeq, String openSubjectSeq) {

		try {
			String sql ="{call prod_listPoint(?,?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);
			cstat.executeQuery();
			ArrayList<ExamDTO> result = new ArrayList<ExamDTO>();
			rs=(ResultSet) cstat.getObject(3);
			while(rs.next()) {
				ExamDTO dto = new ExamDTO();

				dto.setStitle(rs.getString("sTitle"));
				dto.setHpoint(rs.getString("exmHandwritingtestpoint"));
				dto.setPpoint(rs.getString("exmPracticaltestpoint"));
				dto.setApoint(rs.getString("exmAttendancepoint"));
				dto.setExamDate(rs.getString("examdate"));
				dto.setExamFileStatus(rs.getString("filecheck"));
				
				result.add(dto);
				
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
		
		
		
		return null;
	}

	
	
	/**
	 * 특정 개설 과정의 특정 개설 과목의 시험 배점 정보를 등록하기 위한 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param edto	시험 정보 dto
	 * @return 배점 정보 등록 성공 여부
	 */
	//배점 및 시험 정보 등록
	public int addExamInformation(String openProcessSeq, String openSubjectSeq ,ExamDTO edto) {

		try {
			String sql = "{call prod_addtestPoint(?,?,?,?,?,?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, edto.getHpoint());
			cstat.setString(3, edto.getPpoint());
			cstat.setString(4, edto.getApoint());
			cstat.setString(5, edto.getExamFileStatus());
			cstat.setString(6, edto.getExamDate());
			cstat.setString(7, openProcessSeq);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	
	
	//필기 배점 수정
	/**
	 *  특정 개설 과정의 특정 개설 과목의 필기 배점을 수정하기 위해서 만들어진 메소드이다.
	 *  성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param hpoint	필기 점수
	 * @return	필기 배점 수정 성공 여부
	 */
	public int updateHandwritingPoint(String openProcessSeq, String openSubjectSeq , String hpoint) {
		
		try {
				String sql = "{call pord_uphPoint(?,?,?)}";
				cstat=conn.prepareCall(sql);
				cstat.setString(1, openSubjectSeq);
				cstat.setString(2, openProcessSeq);
				cstat.setString(3, hpoint);
				
				return cstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//실기 배점 수정
	/**
	 * 특정 개설 과정의 특정 개설 과목의 실기 배점을 수정하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param ppoint	실기 점수
	 * @return	실기 배점 수정 성공 여부
	 */
	public int updatePracticalPoint(String openProcessSeq, String openSubjectSeq, String ppoint) {
		
		try {
			String sql = "{call pord_uppPoint(?,?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
			cstat.setString(3, ppoint);
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return 0;
	}
	
	//출결 배점 수정
	/**
	 * 특정 개설 과정의 특정 개설 과목의 출결 배점을 수정하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param ppoint	출결 점수
	 * @return	출결 배점 수정 성공 여부
	 */
	public int updateAttendancePoint(String openProcessSeq, String openSubjectSeq , String ppoint) {

		try {
			String sql = "{call pord_upaPoint(?,?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
			cstat.setString(3, ppoint);
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return 0;
	}

	//시험 날짜 수정
	/**
	 * 특정 개설 과정의 특정 개설 과목의 시험 날짜를 수정하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param examDate	수정 날짜
	 * @return 시험 날짜 수정 성공 여부
	 */
	public int updateExamDate(String openProcessSeq, String openSubjectSeq, String examDate) {
		
		try {
			String sql = "{call pord_upExamDate(?,?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
			cstat.setString(3, examDate);
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		
		return 0;
	}
	
	//시험 문제 등록 여부 수정
	/**
	 * 특정 개설 과정의 특정 개설 과목의 시험 문제 등록여부를 수정하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param examFile 시험 문제등록 여부
	 * @return 시험 문제 등록 여부 수정 성공 여부
	 */
	public int updateExamFileStatus(String openProcessSeq, String openSubjectSeq, String examFile) {

		
		try {
			String sql = "{call pord_upExamFile(?,?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
			cstat.setString(3, examFile);
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return 0;
	}
	

	//필기 배점 삭제
	/**
	 * 특정 개설 과정의 특정 개설 과목의 필기 배점을 삭제하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 필기 배점 삭제 성공 여부
	 */
	public int deleteHandwritingPoint(String openProcessSeq, String openSubjectSeq) {
		try {
			String sql = "{call pord_delhPoint(?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
		
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return 0;
	}

	//실기 배점 삭제
	/**
	 * 특정 개설 과정의 특정 개설 과목의 실기 배점을 삭제하기 위해서 만들어진 메소드이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 실기 배점 삭제 성공 여부
	 */
	public int deletePracticalPoint(String openProcessSeq, String openSubjectSeq) {
		try {
			String sql = "{call pord_delpPoint(?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
		
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return 0;
	}

	//출결 배점 삭제
	/**
	 * 특정 개설 과정의 특정 개설 과목의 출결 배점을 삭제하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 출결 배점 삭제 성공 여부
	 */
	public int deleteAttendancePoint(String openProcessSeq, String openSubjectSeq) {
		try {
			String sql = "{call pord_delaPoint(?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
		
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return 0;
	}
	
	//시험 날짜 삭제
	/**
	 * 특정 개설 과정의 특정 개설 과목의 시험 날짜를 삭제하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 시험 날짜 삭제 성공 여부
	 */
	public int deleteExamDate(String openProcessSeq, String openSubjectSeq) {
		try {
			String sql = "{call pord_delExamDate(?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
		
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return 0;
	}
		
	
	//시험 문제 등록 여부 삭제
	/**
	 * 특정 개설 과정의 특정 개설 과목의 문제 등록 여부를 삭제하기 위해서 만들어진 메소드이다.
	 * 성공 여부는 1(성공) 또는 0(실패)이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 시험 문제 등록 여부 삭제 성공 여부
	 */
	public int deleteExamFileStatus(String openProcessSeq, String openSubjectSeq) {
		try {
			String sql = "{call pord_delExamFile(?,?)}";
			cstat=conn.prepareCall(sql);
			cstat.setString(1, openSubjectSeq);
			cstat.setString(2, openProcessSeq);
		
			
			return cstat.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return 0;
	}

	
	
	
	
	
	
	

	
	/**
	 * 특정 개설 과정의 특정 개설 과목의 전체 교육생 성적을 조회하는 메소드이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @return 전체 교육생 성적 정보 배열
	 */
	//특정 개설 과정의 특정 개설 과목의 전체 교육생 성적 조회
	public ArrayList<ScoreDTO> studentScore(String openProcessSeq, String openSubjectSeq) {

		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();

		try {

			String sql = "{ call proc_listAllStudentScore(?, ?, ?) }";
			cstat = conn.prepareCall(sql);

			cstat.setString(1, openProcessSeq);
			cstat.setString(2, openSubjectSeq);
			cstat.registerOutParameter(3, OracleTypes.CURSOR);

			cstat.executeQuery();

			rs = (ResultSet) cstat.getObject(3);

			while (rs.next()) {

				ScoreDTO osdto = new ScoreDTO();

				osdto.setStudentSeq(rs.getString("seq"));
				osdto.setStudentName(rs.getString("stName"));
				osdto.setTitle(rs.getString("sTitle"));
				osdto.setStartdate(rs.getString("osStart"));
				osdto.setEnddate(rs.getString("osEnddate"));
				osdto.setScoreInputStatus(rs.getString("scoreInputStatus"));

				result.add(osdto);
			}

			System.out.println(
					"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			return result;

		} catch (Exception e) {
			System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
		}

		return null;
	}

	/**
	 * 특정 개설 과정의 특정 교육생의 성적을 조회하는 메소드이다.
	 * @param openProcessSeq 개설 과정 번호
	 * @param openSubjectSeq 개설 과목 번호
	 * @param studentSeq 교육생 번호
	 * @return 특정 교육생 성적 정보 배열
	 */
	//특정 개설 과정의 특정 개설 과목의 특정 교육생 성적 조회
	public ArrayList<ScoreDTO> score(String openProcessSeq, String openSubjectSeq, String studentSeq) {

		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
	      
	      try {
	         
	         String sql = "{ call proc_listOneStudentScoreManage(?, ?, ?, ?) }";
	         cstat = conn.prepareCall(sql);
	         
	         
	         cstat.setString(1, openProcessSeq);
	         cstat.setString(2, openSubjectSeq);
	         cstat.setString(3, studentSeq);
	         cstat.registerOutParameter(4, OracleTypes.CURSOR);
	         
	         cstat.executeQuery();
	         
	         rs = (ResultSet)cstat.getObject(4);
	         
	         if (rs.next()) {
	            
	        	 ScoreDTO osdto = new ScoreDTO();
	            
	        	osdto.setStudentName(rs.getString("stName"));
	        	osdto.setPhone(rs.getString("stPhone"));
	            osdto.setTitle(rs.getString("sTitle"));
	            osdto.setStartdate(rs.getString("osStart"));
	            osdto.setEnddate(rs.getString("osEnddate"));
	            osdto.setFinishStatus(rs.getString("rcFinishStatus"));
	            osdto.setHandwritingPoint(rs.getString("exmHandwritingTestPoint"));
	            osdto.setPracticalPoint(rs.getString("exmPracticalTestPoint"));
	            osdto.setAttendancePoint(rs.getString("exmAttendancePoint"));
	            osdto.setHandwritingScore(rs.getString("eHandwritingtestscore"));
	            osdto.setPracticalScore(rs.getString("ePracticaltestscore"));
	            osdto.setAttendanceScore(rs.getString("eAttendancescore"));
	            
	            result.add(osdto);
	         }

	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         return result;
	         

	      } catch (Exception e) {
	         System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
	      }
		
		return null;
	}

	/**
	 * 특정 과목별 교육생들의 전체 성적을 조회하는 메소드이다.
	 * @return 특정 과목 교육생 전체 성적 정보 배열
	 */
	public ArrayList<ScoreDTO> allScoreBySubject() {

		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
	      
	      try {
	         
	         String sql = "{ call proc_listAllScoreBySubject(?, ?) }";
	         cstat = conn.prepareCall(sql);
	         
	         
	         cstat.setString(1, Sequence.teacherSeq);
	         cstat.registerOutParameter(2, OracleTypes.CURSOR);
	         
	         cstat.executeQuery();
	         
	         rs = (ResultSet)cstat.getObject(2);
	         
	         if (rs.next()) {
	            
	        	 ScoreDTO osdto = new ScoreDTO();
	            
	            osdto.setOpenSubject(rs.getString("osseq"));
	            osdto.setOpenProcessName(rs.getString("ptitle"));
	            osdto.setpStartdate(rs.getString("opStartdate"));
	            osdto.setpEnddate(rs.getString("opEnddate"));
	            osdto.setLectureRoom(rs.getString("lectureName"));
	            osdto.setTitle(rs.getString("sTitle"));
	            osdto.setStartdate(rs.getString("osStartdate"));
	            osdto.setEnddate(rs.getString("osEnddate"));
	            osdto.setBookName(rs.getString("bookName"));
	            osdto.setHandwritingPoint(rs.getString("emHandwritingTestPoint"));
	            osdto.setPracticalPoint(rs.getString("emPracticalTestPoint"));
	            osdto.setAttendancePoint(rs.getString("emAttendancePoint"));
	            osdto.setScoreInputStatus(rs.getString("scoreStatus"));
	            
	            result.add(osdto);
	         }

	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         return result;
	         

	      } catch (Exception e) {
	         System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
	      }
		
		return null;
	}

	/**
	 * 특정 교사의 특정 담당 과목 교육생의 성적 정보를 조회하는 메소드이다.
	 * @param openSubjectSeq 개설 과목 번호
	 * @return result 특정 담당 과목 교육생 성적 정보 배열
	 */
	//특정교사의 특정 담당 과목 교육생 성적 정보 조회
	public ArrayList<ScoreDTO> listAllScoreByOneSubject(String openSubjectSeq) {

		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
	      
	      try {
	         
	         String sql = "{ call proc_listAllScoreByOneSubject(?, ?) }";
	         cstat = conn.prepareCall(sql);
	         
	         
	         cstat.setString(1, openSubjectSeq);
	         cstat.registerOutParameter(2, OracleTypes.CURSOR);
	         
	         cstat.executeQuery();
	         
	         rs = (ResultSet)cstat.getObject(2);
	         
	         while (rs.next()) {
	            
	        	 ScoreDTO osdto = new ScoreDTO();
	            
	        	osdto.setStudentSeq(rs.getString("sSeq"));
	        	osdto.setStudentName(rs.getString("sName"));
	        	osdto.setPhone(rs.getString("sPhone"));
	            osdto.setFinishDate(rs.getString("rcFinishdate"));
	            osdto.setFinishStatus(rs.getString("rcFinishstatus"));
	            osdto.setHandwritingScore(rs.getString("eHandwritingTestScore"));
	            osdto.setPracticalScore(rs.getString("ePracticalTestScore"));
	            osdto.setAttendanceScore(rs.getString("atsAttendanceScore"));
	            
	            result.add(osdto);
	         }

	         System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	         return result;
	         

	      } catch (Exception e) {
	         System.out.println("OpenProcessDAO.openSubjectList() : " + e.toString());
	      }
		return null;
	}
	

}

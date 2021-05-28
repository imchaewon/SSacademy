import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 과목과 관련한 기능을 담당하는 클래스이다.
 * 전체 과목 조회, 새 과목 등록, 과목 정보 수정, 과목 삭제 기능을 포함한다.
 * @author 전수희
 *
 */
public class SubjectDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 과목 DAO의 기본 생성자이다.
	 */
	public SubjectDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}

	

	/**
	 * 전체 과목 정보를 배열로 반환하는 메소드이다.
	 * 과목의 정보에는 과목 번호, 과목 이름, 과목 기간(일)이 포함된다.
	 * @return 과목 정보 배열
	 */
	//전체 과목 조회
	public ArrayList<SubjectDTO> subjectList() {
		
		ResultSet rs = null;
		
		try {

			conn = DBUtil.open();
			String sql = "select seq, title, days from tblSubject order by seq asc";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<SubjectDTO> result = new ArrayList<SubjectDTO>();
			
			while(rs.next()) {
				
				SubjectDTO sdto = new SubjectDTO();
				sdto.setSeq(rs.getString("seq"));
				sdto.setTitle(rs.getString("title"));
				sdto.setDays(rs.getString("days"));
				
				result.add(sdto);
			}
			rs.close();
			
			return result;

		} catch (Exception e) {
			System.out.println("AddressDAO.list() : " + e.toString());
		}
		
		return null;
	}

	/**
	 * 새 과목을 등록하는 메소드이다. 
	 * 과목 이름과 기간(일)이 담긴 과목 데이터 정보를 받아 과목을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param sdto 과목 데이터 정보
	 * @return 성공 여부
	 */
	//과목 등록
	public int addSubject(SubjectDTO sdto) {
		
		try {

			String sql = "{ call proc_addSubject(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, sdto.getTitle());
			cstat.setString(2, sdto.getDays());
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SubjectDAO.addSubject() : " + e.toString());
		}

		return 0;
	}	

	/**
	 * 수정할 과목 번호와 수정할 과목 이름 또는 과목 기간(일)을 받아와 기존 과목의 정보를 수정하는 메소드이다. 
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param subjectSeq 과목 번호 
	 * @param subjectTitle 과목 이름
	 * @param subjectDays 과목 기간(일)
	 * @return 수정된 과목의 수 
	 */
	//과목 수정
	public int updateSubject(String subjectSeq, String subjectTitle, String subjectDays) {
		
		//원래 값
		String title = "";
		String days = "";
		
		try {
			
			String sql = "SELECT seq, title, days FROM tblSubject where seq = " + subjectSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				title = rs.getString("title");
				days = rs.getString("days");
			}
			
			//과목명 수정
			if (!subjectTitle.equals("")) {
				title = subjectTitle;
			}
			
			//기간(일) 수정
			if (!subjectDays.equals("")) {
				days = subjectDays;
			}
			
			sql = "{ call proc_updateSubject(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, subjectSeq);
			cstat.setString(2, title);
			cstat.setString(3, days);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("SubjectDAO.updateSubject() : " + e.toString());
		}
		
		
		return 0;
	}	
	
	/**
	 * 과목을 삭제하는 메소드이다.
	 * 삭제할 과목 번호를 받아 과목을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param subjectSeq 과목 번호 
	 * @return 성공 여부
	 */
	//과목 삭제
	public int deleteSubject(String subjectSeq) {
		
		try {

			String sql = "{ call proc_deleteSubject(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, subjectSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SubjectDAO.deleteSubject() : " + e.toString());
		}
		return 0;
	}


}

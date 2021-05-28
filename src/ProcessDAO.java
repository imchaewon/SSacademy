import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 과정과 관련한 기능을 담당하는 클래스이다.
 * 전체 과정 조회, 새 과정 등록, 과정 정보 수정, 과정 삭제 기능을 포함한다.
 * @author 전수희
 *
 */
public class ProcessDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 과정DAO의 기본 생성자이다.
	 */
	public ProcessDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}

	/**
	 * 전체 과정 정보를 배열로 반환하는 메소드이다.
	 * 과정의 정보에는 과정 번호, 과정 이름, 과정 기간(일)이 포함된다.
	 * @return 과정 정보 배열
	 */
	//전체 과정 목록 조회
	public ArrayList<ProcessDTO> processList() {
		
		try {

			String sql = "select * from tblProcess order by seq asc";
			rs = stat.executeQuery(sql);
			
			ArrayList<ProcessDTO> result = new ArrayList<ProcessDTO>();
			while(rs.next()) {
				
				ProcessDTO dto = new ProcessDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setDays(rs.getString("days"));
				
				result.add(dto);
			}
			rs.close();
			
			return result;
			

		} catch (Exception e) {
			System.out.println("ProcessDAO.processList() : " + e.toString());
		}
		
		return null;
	}
	
	/**
	 * 새 과정을 등록하는 메소드이다.
	 * 과정 이름과 기간(일)이 담긴 과정 데이터 정보를 받아 과정을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param pdto 과정 데이터 정보
	 * @return 성공 여부
	 */
	//과정 등록
	public int addProcess(ProcessDTO pdto) {
		
		String sql = "{ call proc_addProcess(?, ?) }";
		
		try {
			cstat = conn.prepareCall(sql);
			cstat.setString(1, pdto.getTitle());//과정명
			cstat.setString(2, pdto.getDays());//기간
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("ProcessDAO.addProcess() : " + e.toString());
		}
		return 0;
	}
	
	/**
	 * 과정의 정보를 수정하는 메소드이다.
	 * 수정할 과정 번호와 수정할 과정 이름 또는 과정 기간(일)을 받아와 기존 과정의 정보를 수정한다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param processSeq 과정 번호
	 * @param processTitle 과정 이름
	 * @param processDays 과정 기간(일)
	 * @return 성공 여부
	 */
	//과정 수정
	public int updateProcess(String processSeq, String processTitle, String processDays) {
		
		//원래 값
		String title = "";
		String days = "";
		
		try {

			String sql = "SELECT title, days FROM tblProcess WHERE seq = " + processSeq;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				title = rs.getString("title");
				days = rs.getString("days");
			}
			rs.close();
			
			//과정명 수정
			if (!processTitle.equals("")) {
				title = processTitle;
			}
			
			//기간 수정
			if (!processDays.equals("")) {
				days = processDays;
			}
			
			
			sql = "{ call proc_updateProcess(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, processSeq);
			cstat.setString(2, title);
			cstat.setString(3, days);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ProcessDAO.updateProcess() : " + e.toString());
		}
		return 0;
	}

	/**
	 * 과정을 삭제하는 메소드이다.
	 * 삭제할 과정 번호를 받아 과정을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param processSeq 과정 번호
	 * @return 성공 여부
	 */
	//과정 삭제
	public int deleteProcess(String processSeq) {
		
		try {

			String sql = "{ call proc_deleteProcess(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, processSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("ProcessDAO.deleteProcess() : " + e.toString());
		}
		return 0;
	}

	
}

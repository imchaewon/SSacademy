import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 강의실과 관련한 기능을 담당하는 클래스이다.
 * 전체 강의실 조회, 새 강의실 등록, 강의실 정보 수정, 강의실 삭제 기능을 포함한다.
 * @author 전수희
 *
 */

public class LectureRoomDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	/**
	 * 강의실DAO의 기본 생성자이다.
	 */
	public LectureRoomDAO() {
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("ProcessDAO.ProcessDAO() : " + e.toString());
		}
		
	}

	/**
	 * 전체 강의실 정보를 배열로 반환하는 메소드이다.
	 * 한 강의실 정보에는 강의실 번호, 강의실 이름, 수용 인원이 포함되어있다.
	 * @return 강의실 정보 배열
	 */
	//전체 강의실 목록 조회
	public ArrayList<LectureRoomDTO> allLectureRoomList() {
		
		ArrayList<LectureRoomDTO> result = new ArrayList<LectureRoomDTO>();
		
		try {

			String sql = "SELECT seq, name, acceptablepersonnel FROM tblLectureRoom order by seq asc";
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				LectureRoomDTO lrdto = new LectureRoomDTO();
				lrdto.setSeq(rs.getString("seq"));
				lrdto.setName(rs.getString("name"));
				lrdto.setAcceptablePersonnel(rs.getString("acceptablepersonnel"));
				
				result.add(lrdto);
			}
			return result;

		} catch (Exception e) {
			System.out.println("LectureRoomDAO.allLectureRoomList() : " + e.toString());
		}
		return null;
	}

	/**
	 * 새 강의실을 등록하는 메소드이다.
	 * 강의실 이름과 수용 인원이 담긴 강의실 데이터 정보를 받아 강의실을 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param lrdto 강의실 데이터 정보
	 * @return 성공 여부
	 */
	//강의실 등록
	public int addLecture(LectureRoomDTO lrdto) {
		
		try {

			String sql = "{ call proc_addLecture(?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, lrdto.getName());	//강의실명
			cstat.setString(2, lrdto.getAcceptablePersonnel()); //수용인원
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("LectureRoomDAO.addLecture() : " + e.toString());
		}
		return 0;
	}

	/**
	 * 수정할 강의실 번호와 수정할 이름 또는 수용 인원을 받아와 기존 강의실의 정보를 수정하는 메소드이다. 
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param lectureRoomSeq 강의실 번호
	 * @param lectureRoomName 강의실 이름
	 * @param lectureRoomAcceptablePersonnel 강의실 수용 인원
	 * @return 수정된 강의실의 수 
	 */
	//강의실 수정
	public int updateLectureRoom(String lectureRoomSeq, String lectureRoomName,
			String lectureRoomAcceptablePersonnel) {
		
		//원래 값
		String name = "";
		String acceptablePersonnel = "";
		
		try {

			String sql = "SELECT seq, name, acceptablePersonnel FROM tblLectureRoom WHERE seq = " + lectureRoomSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				name = rs.getString("name");
				acceptablePersonnel = rs.getString("acceptablePersonnel");
			}
			
			if (!lectureRoomName.equals("")) {
				name = lectureRoomName;
			}
			
			if (!lectureRoomAcceptablePersonnel.equals("")) {
				acceptablePersonnel = lectureRoomAcceptablePersonnel;
			}
			
			sql = "{ call proc_updateLectureRoom(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, lectureRoomSeq);
			cstat.setString(2, name);
			cstat.setString(3, acceptablePersonnel);
			
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("LectureRoomDAO.updateLectureRoom() : " + e.toString());
		}
		
		return 0;
	}
	
	/**
	 * 강의실을 삭제하는 메소드이다.
	 * 삭제할 강의실 번호를 받아 강의실을 삭제한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param lectureRoomSeq 강의실 번호 
	 * @return 성공 여부
	 */
	//강의실 삭제
	public int deleteLectureRoom(String lectureRoomSeq) {
		
		try {

			String sql = "{ call proc_deleteLectureRoom(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, lectureRoomSeq);
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("LectureRoomDAO.deleteLectureRoom() : " + e.toString());
		}
		return 0;
	}


}

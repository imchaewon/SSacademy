/**
 * 
 * 강의실 정보를 저장하는 DTO 클래스이다.
 * seq = 강의실 번호
 * name = 강의실 이름
 * acceptablePersonnel = 강의실 수용인원수
 * @author sist37
 *
 */
public class LectureRoomDTO {
	
	private String seq;
	private String name;
	private String acceptablePersonnel;
	
	/**
	 * 강의실 번호를 내보내는 메소드이다.
	 * @return 강의실 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 강의실 번호를 받는 메소드이다.
	 * @param seq 강의실 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 강의실명을 내보내는 메소드이다.
	 * @return 강의실명
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 강의실명을 받는 메소드이다.
	 * @param name 강의실명
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 강의실 수용인원에 대한 데이터를 내보내는 메소드이다.
	 * @return 강의실 수용인원
	 */
	public String getAcceptablePersonnel() {
		return acceptablePersonnel;
	}
	
	/**
	 * 강의실 수용인원에 대한 데이터를 받는 메소드이다.
	 * @param acceptablePersonnel 강의실 수용인원
	 */
	public void setAcceptablePersonnel(String acceptablePersonnel) {
		this.acceptablePersonnel = acceptablePersonnel;
	}
	

}

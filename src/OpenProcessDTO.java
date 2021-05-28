/**
 * 
 * 개설 과정 정보를 저장하는 DTO 클래스이다.
 * seq 개설과정번호 
 * title 개설과정명
 * lectureRoom 강의실
 * personnel 등록인원수
 * startDate 시작날짜
 * endDate 종료날짜
 * status 상태
 * existStatus 개설과목등록여부
 * @author 전수희
 */
public class OpenProcessDTO {
	
	private String seq;	//개설과정번호 
	private String title; //개설과정명
	private String lectureRoom; //강의실
	private String personnel; //등록인원수
	private String startDate; //시작날짜
	private String endDate; //종료날짜
	private String status; //상태
	private String existStatus; //개설과목등록여부
	
	/**
	 * 개설과정번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 개설과정번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 개설과정번호에 대한 데이터를 받는 메소드이다.
	 * @param seq 개설과정번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 개설과정명에 대한 데이터를 내보내는 메소드이다.
	 * @return 개설과정명
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 개설과정명에 대한 데이터를 받는 메소드이다.
	 * @param title 개설과정명
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 강의실에 대한 데이터를 내보내는 메소드이다.
	 * @return 강의실
	 */
	public String getLectureRoom() {
		return lectureRoom;
	}
	
	/**
	 * 강의실에 대한 데이터를 받는 메소드이다.
	 * @param lectureRoom 강의실
	 */
	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}
	
	/**
	 * 등록인원수에 대한 데이터를 내보내는 메소드이다.
	 * @return 등록인원수
	 */
	public String getPersonnel() {
		return personnel;
	}
	
	/**
	 * 등록인원수에 대한 데이터를 받는 메소드이다.
	 * @param personnel 등록인원수
	 */
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}
	
	/**
	 * 시작날짜에 대한 데이터를 내보내는 메소드이다.
	 * @return 시작날짜
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * 시작날짜에 대한 데이터를 받는 메소드이다.
	 * @param startDate 시작날짜
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 종료날짜에 대한 데이터를 내보내는는 메소드이다.
	 * @return 종료날짜
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * 종료날짜에 대한 데이터를 받는 메소드이다.
	 * @param endDate 종료날짜
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 상태에 대한 데이터를 내보내는 메소드이다.
	 * @return 상태
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 상태에 대한 데이터를 받는 메소드이다.
	 * @param status 상태
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 개설과목등록여부에 대한 데이터를 내보내는 메소드이다.
	 * @return 개설과목등록여부
	 */
	public String getExistStatus() {
		return existStatus;
	}
	
	/**
	 * 개설과목등록여부에 대한 데이터를 받는 메소드이다.
	 * @param existStatus 개설과목등록여부
	 */
	public void setExistStatus(String existStatus) {
		this.existStatus = existStatus;
	}
	
	
}

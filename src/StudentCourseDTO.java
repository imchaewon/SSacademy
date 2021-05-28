/**
 * 
 * @author 전수희
 *
 */

public class StudentCourseDTO {
	
	private String registrationSeq;	//수강 번호
	private String openProcessTitle; //과정명
	private String openProcessStartDate; //과정 시작날짜
	private String openProcessEndDate; //과정 종료날짜
	private String lectureRoom; //강의실
	private String finishStatus; //수료 여부
	private String finishDate; //수료 날짜
	private String status; //상태
	
	/**
	 * 수강 번호를 내보내는 메소드이다.
	 * @return 수강 번호
	 */
	public String getRegistrationSeq() {
		return registrationSeq;
	}
	
	/**
	 * 수강 번호를 받아오는 메소드이다.
	 * @param registrationSeq 수강 번호
	 */
	public void setRegistrationSeq(String registrationSeq) {
		this.registrationSeq = registrationSeq;
	}
	
	/**
	 * 과정명을 내보내는 메소드이다.
	 * @return 과정명
	 */
	public String getOpenProcessTitle() {
		return openProcessTitle;
	}
	
	/**
	 * 과정명을 받아오는 메소드이다.
	 * @param openProcessTitle 과정명
	 */
	public void setOpenProcessTitle(String openProcessTitle) {
		this.openProcessTitle = openProcessTitle;
	}
	
	/**
	 * 과정 시작날짜를 내보내는 메소드이다.
	 * @return 과정 시작날짜
	 */
	public String getOpenProcessStartDate() {
		return openProcessStartDate;
	}
	
	/**
	 * 과정 시작날짜를 받아오는 메소드이다.
	 * @param openProcessStartDate 과정 시작날짜
	 */
	public void setOpenProcessStartDate(String openProcessStartDate) {
		this.openProcessStartDate = openProcessStartDate;
	}
	
	/**
	 * 과정 종료날짜를 내보내는 메소드이다.
	 * @return 과정 종료날짜
	 */
	public String getOpenProcessEndDate() {
		return openProcessEndDate;
	}
	
	/**
	 * 과정 종료날짜를 받아오는 메소드이다.
	 * @param openProcessEndDate 과정 종료날짜
	 */
	public void setOpenProcessEndDate(String openProcessEndDate) {
		this.openProcessEndDate = openProcessEndDate;
	}
	
	/**
	 * 강의실명을 내보내는 메소드이다.
	 * @return 강의실명
	 */
	public String getLectureRoom() {
		return lectureRoom;
	}
	
	/**
	 * 강의실명을 받아오는 메소드이다.
	 * @param lectureRoom 강의실명
	 */
	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}
	
	/**
	 * 수료여부를 내보내는 메소드이다.
	 * @return 수료여부
	 */
	public String getFinishStatus() {
		return finishStatus;
	}
	
	/**
	 * 수료 여부를 받아오는 메소드이다.
	 * @param finishStatus 수료 날짜
	 */
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	
	/**
	 * 수료날짜를 내보내는 메소드이다.
	 * @return 수료날짜
	 */
	public String getFinishDate() {
		return finishDate;
	}
	
	/**
	 * 수료날짜를 받아오는 메소드이다.
	 * @param finishDate
	 */
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * 상태를 내보내는 메소드이다.
	 * @return 상태
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 상태를 받아오는 메소드이다.
	 * @param status 상태
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}

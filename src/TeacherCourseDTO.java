
/**
 * 
 * @author 전수희
 *
 */
public class TeacherCourseDTO {
	
	private String openProcessSeq; 			//개설 과정 번호
	private String openProcessTitle; 		//개설 과정명
	private String openProcessStartDate;	//개설 과정 시작 날짜
	private String openProcessEndDate; 		//개설 과정 종료 날짜
	
	private String openSubjectTitle;		//개설 과목명
	private String openSubjectDays;			//개설 과목 기간(일)
	private String book;					//교재명
	
	private String personnel;				//등록 인원
	private String lectureRoom;				//강의실
	private String lectureStatus;			//강의 진행 상태
	
	
	/**
	 * 개설 과정 번호를 내보내는 메소드이다.
	 * @return 개설 과정 번호
	 */
	public String getOpenProcessSeq() {
		return openProcessSeq;
	}
	
	/**
	 * 개설 과정 번호를 받아오는 메소드이다.
	 * @param openProcessSeq 개설 과정 번호
	 */
	public void setOpenProcessSeq(String openProcessSeq) {
		this.openProcessSeq = openProcessSeq;
	}
	
	/**
	 * 개설 과정명을 내보내는 메소드이다.
	 * @return 개설 과정명
	 */
	public String getOpenProcessTitle() {
		return openProcessTitle;
	}
	
	/**
	 * 개설 과정명을 받아오는 메소드이다.
	 * @param openProcessTitle 개설 과정명
	 */
	public void setOpenProcessTitle(String openProcessTitle) {
		this.openProcessTitle = openProcessTitle;
	}
	
	/**
	 * 개설 과정 시작 날짜를 내보내는 메소드이다.
	 * @return 개설 과정 시작 날짜
	 */
	public String getOpenProcessStartDate() {
		return openProcessStartDate;
	}
	
	/**
	 * 개설 과정 시작날짜를 받아오는 메소드이다.
	 * @param openProcessStartDate 개설 과정 시작날짜
	 */
	public void setOpenProcessStartDate(String openProcessStartDate) {
		this.openProcessStartDate = openProcessStartDate;
	}
	
	/**
	 * 개설 과정 종료날짜를 내보내는 메소드이다.
	 * @return 개설 과정 종료날짜
	 */
	public String getOpenProcessEndDate() {
		return openProcessEndDate;
	}
	
	/**
	 * 개설 과정 종료날짜를 받아오는 메소드이다.
	 * @param openProcessEndDate 개설 과정 종료날짜
	 */
	public void setOpenProcessEndDate(String openProcessEndDate) {
		this.openProcessEndDate = openProcessEndDate;
	}
	
	/**
	 * 개설 과목명을 내보내는 메소드이다.
	 * @return 개설 과목명
	 */
	public String getOpenSubjectTitle() {
		return openSubjectTitle;
	}
	
	/**
	 * 개설 과목명을 받아오는 메소드이다.
	 * @param openSubjectTitle 개설 과목명
	 */
	public void setOpenSubjectTitle(String openSubjectTitle) {
		this.openSubjectTitle = openSubjectTitle;
	}
	
	/**
	 * 개설 과목기간을 내보내는 메소드이다.
	 * @return 개설 과목 기간
	 */
	public String getOpenSubjectDays() {
		return openSubjectDays;
	}
	
	/**
	 * 개설 과목기간을 받아오는 메소드이다.
	 * @param openSubjectDays 개설 과목기간
	 */
	public void setOpenSubjectDays(String openSubjectDays) {
		this.openSubjectDays = openSubjectDays;
	}
	
	/**
	 * 교재명을 내보내는 메소드이다.
	 * @return 교재명
	 */
	public String getBook() {
		return book;
	}
	
	/**
	 * 교재명을 받아오는 메소드이다.
	 * @param book 교재명
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	/**
	 * 등록 인원 수를 내보내는 메소드이다.
	 * @return 등록 인원 수
	 */
	public String getPersonnel() {
		return personnel;
	}
	
	/**
	 * 등록 인원 수를 받아오는 메소드이다.
	 * @param personnel 등록 인원 수
	 */
	public void setPersonnel(String personnel) {
		this.personnel = personnel;
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
	 * 강의 진행 상태를 내보내는 메소드이다.
	 * @return 강의 진행 상태
	 */
	public String getLectureStatus() {
		return lectureStatus;
	}
	
	/**
	 * 강의 진행 상태를 받아오는 메소드이다.
	 * @param lectureStatus 강의 진행 상태
	 */
	public void setLectureStatus(String lectureStatus) {
		this.lectureStatus = lectureStatus;
	}

}

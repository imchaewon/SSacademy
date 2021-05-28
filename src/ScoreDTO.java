
/**
 * 시험관련된 배점 및 성적정보를 저장하기위한 클래스이다.
 * @author 이진수,노형준
 */
public class ScoreDTO {
	
	private String studentSeq;
	private String studentName;
	private String openSubject;	//개설 과목명
	private String regidentRegistrationNumber;	//주민번호
	private String phone;	//전화번호
	private String regdate;	//등록일
	private String title;	//과정명
	private String startdate;
	private String enddate;
	private String scoreInputStatus; 
	private String handwritingScore;	
	private String practicalScore;
	private String attendanceScore;
	private String finishDate;
	private String finishStatus; //수료여부
	private String handwritingPoint;
	private String practicalPoint;
	private String attendancePoint;
	private String openProcessName;
	private String lectureRoom;	//강의실
	private String pStartdate;		//과정 시작일
	private String pEnddate;		//과정 종료일
	private String bookName;		//과목 교재명
	
	
	/**
	 * 해당 과목의 종료 날짜를 내보내는 Getter메소드이다.
	 * @return	과목 종료 날짜
	 */
	public String getFinishDate() {
		return finishDate;
	}
	/**
	 * 해당 과목의 종료 날짜를 받아오는 Setter메소드이다.
	 * @param finishDate 과목 종료 날짜
	 */
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * 해당 과정의 이름을 내보내는 Getter메소드이다.
	 * @return	개설 과정 이름
	 */
	public String getOpenProcessName() {
		return openProcessName;
	}
	/**
	 * 해당 과정의 이름을 가져오는 Setter메소드이다.
	 * @param openProcessName	개설 과정 이름
	 */
	public void setOpenProcessName(String openProcessName) {
		this.openProcessName = openProcessName;
	}
	/**
	 * 해당 과정을 듣는 강의실을 내보내는 Getter메소드이다.
	 * @return	강의실이름
	 */
	public String getLectureRoom() {
		return lectureRoom;
	}
	/**
	 * 해당 과정을 듣는 강의실을 가져오는 Setter메소드이다.
	 * @param lectureRoom 강의실 이름
	 */
	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}
	/**
	 * 해당 과정의 시작 날짜를 내보내는 Getter메소드이다.
	 * @return 과정 시작 날짜
	 */
	public String getpStartdate() {
		return pStartdate;
	}
	/**
	 * 해당 과정의 시작 날짜를 가져오는 Setter메소드이다.
	 * @param pStartdate 과정의 시작 날짜
	 */
	public void setpStartdate(String pStartdate) {
		this.pStartdate = pStartdate;
	}
	/**
	 * 해당 과정의 종료 날짜를 내보내는 Getter메소드이다.
	 * @return	과정의 종료 날짜
	 */
	public String getpEnddate() {
		return pEnddate;
	}
	/**
	 * 해당 과정의 시작 날짜를 가져오는 Setter메소드이다.
	 * @param pEnddate	과정 시작 날짜
	 */
	public void setpEnddate(String pEnddate) {
		this.pEnddate = pEnddate;
	}
	/**
	 * 해당 과목에 사용되는 교재를 내보내는 Getter메소드이다.
	 * @return	교재명
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * 해당 과목에 사용되는 교재를 가져오는 Setter메소드이다.
	 * @param bookName 교재명
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * 해당 과정의 진행여부를 내보내는 Getter메소드이다.
	 * @return 과정진행여부
	 */
	public String getFinishStatus() {
		return finishStatus;
	}
	/**
	 * 해당 과정의 진행여부를 가져오는 Setter메소드이다.
	 * @param finishStatus	과정진행여부
	 */
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	/**
	 * 해당 과정의 필기 점수를 내보내는 Getter메소드이다.
	 * @return	필기 점수
	 */
	public String getHandwritingPoint() {
		return handwritingPoint;
	}
	/**
	 * 해당 과정의 필기 점수를 가져오는 Setter메소드이다.
	 * @param handwritingPoint 필기 점수
	 */
	public void setHandwritingPoint(String handwritingPoint) {
		this.handwritingPoint = handwritingPoint;
	}
	/**
	 * 해당 과정의 실기 점수를 내보내는 Getter메소드이다.
	 * @return	실기 점수
	 */
	public String getPracticalPoint() {
		return practicalPoint;
	}
	/**
	 * 해당 과정의 실기 점수를 가져오는 Setter메소드이다.
	 * @param practicalPoint	실기점수
	 */
	public void setPracticalPoint(String practicalPoint) {
		this.practicalPoint = practicalPoint;
	}
	/**
	 * 해당 과정의 출석 점수를 내보내는 Getter메소드이다.
	 * @return	출석 점수
	 */
	public String getAttendancePoint() {
		return attendancePoint;
	}
	/**
	 * 해당 과정의 출석 점수를 가져오는 Setter메소드이다.
	 * @param attendancePoint 출석 점수
	 */
	public void setAttendancePoint(String attendancePoint) {
		this.attendancePoint = attendancePoint;
	}
	/**
	 * 해당 과목의 시작 날짜를 내보내는 Getter메소드이다.
	 * @return	과목 시작 날짜
	 */
	public String getStartdate() {
		return startdate;
	}
	/**
	 * 해당 과목의 시작 날짜를 가져오는 Setter메소드이다.
	 * @param startdate	과목 시작 날짜
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	/**
	 * 해당 과목의 종료 날짜를 내보내는 Getter메소드이다.
	 * @return 과목 종료 날짜
	 */
	public String getEnddate() {
		return enddate;
	}
	/**
	 * 해당 과목의 종료 날짜를 가져오는 Setter메소드이다.
	 * @param enddate 과목 종료 날짜
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	/**
	 * 해당 과목의 성적등록 여부를 내보내는 Getter메소드이다.
	 * @return 과목 성적 등록 여부
	 */
	public String getScoreInputStatus() {
		return scoreInputStatus;
	}
	/**
	 * 해당 과목의 성적 등록 여부를 가져오는 Setter메소드이다.
	 * @param scoreInputStatus	과목 성적 등록 여부
	 */
	public void setScoreInputStatus(String scoreInputStatus) {
		this.scoreInputStatus = scoreInputStatus;
	}
	/**
	 * 해당 과목의 이름을 내보내는 Getter메소드이다.
	 * @return	과목 이름
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 해당 과목의 이름을 가져오는 Setter메소드이다.
	 * @param title 과목 이름
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 해당 과정을 듣는 교육생의 주민등록번호뒷자리를 내보내는 Getter메소드이다.
	 * @return 주민등록번호 뒷자리
	 */
	public String getRegidentRegistrationNumber() {
		return regidentRegistrationNumber;
	}
	/**
	 * 해당 과정을 듣는 교육생의 주민등록번호뒷자리를 가져오는 Setter메소드이다.
	 * @param regidentRegistrationNumber 주민등록번호 뒷자리
	 */
	public void setRegidentRegistrationNumber(String regidentRegistrationNumber) {
		this.regidentRegistrationNumber = regidentRegistrationNumber;
	}
	/**
	 * 해당 과정을 듣는 교육생의 전화번호를 내보내는 Getter메소드이다.
	 * @return	전화번호
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 해당 과정을 듣는 교육생의 전화번호를 가져오는 Setter메소드이다.
	 * @param phone 전화번호
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 해당 과정 등록 날짜를 내보내는 Getter메소드이다.
	 * @return 과정 등록 날짜
	 */
	public String getRegdate() {
		
		return regdate;
	}
	/**
	 * 해당 과정 등록 날짜를 가져오는 Setter메소드이다.
	 * @param regdate 과정 등록 날짜
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	/**
	 * 해당 과정을 듣는 교육생번호를 내보내는 Getter 메소드이다.
	 * @return 교육생 번호
	 */
	public String getStudentSeq() {
		return studentSeq;
	}
	/**
	 * 해당 과정을 듣는 교육생번호는 가져오는 Setter 메소드이다.
	 * @param studentSeq 교육생 번호
	 */
	public void setStudentSeq(String studentSeq) {
		this.studentSeq = studentSeq;
	}
	/**
	 * 해당 과정을 듣는 교육생 이름을 내보내는 Getter 메소드이다.
	 * @return	교육생 이름
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 해당 과정을 듣는 교육생 이름을 가져오는 Setter 메소드이다.
	 * @param studentName	교육생 이름
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * 해당 개설 과목의 이름을 내보내는 Getter 메소드이다.
	 * @return	개설 과목 이름
	 */
	public String getOpenSubject() {
		return openSubject;
	}
	/**
	 * 해당 개설 과목의 이름을 가져오는 Setter 메소드이다.
	 * @param openSubject 개설 과목 이름
	 */
	public void setOpenSubject(String openSubject) {
		this.openSubject = openSubject;
	}
	/**
	 * 해당 과정의 필기 배점을 내보내는 Getter메소드이다.
	 * @return	필기 배점
	 */
	public String getHandwritingScore() {
		return handwritingScore;
	}
	/**
	 * 해당 과정의 필기 배점을 가져오는 Setter메소드이다.
	 * @param handwritingScore 필기 배점
	 */
	public void setHandwritingScore(String handwritingScore) {
		this.handwritingScore = handwritingScore;
	}
	/**
	 * 해당 과정의 실기 배점을 내보내는 Getter메소드이다.
	 * @return	실기 배점
	 */
	public String getPracticalScore() {
		return practicalScore;
	}
	/**
	 * 해당 과정의 실기 배점을 가져오는 Setter메소드이다.
	 * @param practicalScore 실기 배점
	 */
	public void setPracticalScore(String practicalScore) {
		this.practicalScore = practicalScore;
	}
	/**
	 * 해당 과정의 출결 배점을 내보내는 Getter메소드이다.
	 * @return 출결 배점
	 */
	public String getAttendanceScore() {
		return attendanceScore;
	}
	/**
	 * 해당 과정의 출결 배점을 가져오는 Setter메소드이다.
	 * @param attendanceScore	출결 배점
	 */
	public void setAttendanceScore(String attendanceScore) {
		this.attendanceScore = attendanceScore;
	}

	
	
}

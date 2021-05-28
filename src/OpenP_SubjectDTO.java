/**
 * 
 *개설 과목의 정보를 저장하는 DTO 클래스이다.
 * seq 개설과목번호
 * subjectName 과목명
 * subStartDate 과목시작날짜
 * subEndDate 과목종료날짜
 * book 교재
 * attendancePoint 출결배점
 * handWritingTestPoint 필기배점
 * practicalTestPoint 실기배점
 * attendanceScore 출결점수
 * handWritingTestScore 필기점수
 * practicalTestScore 실기점수
 * examDate 시험날짜
 * examFileStatus 시험문제등록여부
 * @author 박민선
 */
public class OpenP_SubjectDTO {

	private String seq; //개설과목번호
	private String subjectName; //과목명
	private String subStartDate; //과목시작날짜
	private String subEndDate; //과목종료날짜
	private String book; //교재
	private String attendancePoint; //출결배점
	private String handWritingTestPoint; //필기배점
	private String practicalTestPoint; //실기배점
	private String attendanceScore; //출결점수
	private String handWritingTestScore; //필기점수
	private String practicalTestScore; //실기점수
	private String examDate; //시험날짜
	private String examFileStatus; //시험문제등록여부
	
	/**
	 * 개설과목 데이터를 내보내는 메소드이다.
	 * @return 개설과목번호
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * 개설과목 데이터를 받는 메소드이다.
	 * @param seq 개설과목번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * 과목명의 데이터를 내보내는 메소드이다.
	 * @return 과목 이름
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * 과목명의 데이터를 받는 메소드이다.
	 * @param subjectName 과목 이름
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * 과목시작날짜의 데이터를 내보내는 메소드이다.
	 * @return 과목 시작 날짜
	 */
	public String getSubStartDate() {
		return subStartDate;
	}
	
	/**
	 * 과목시작날짜의 데이터를 받는 메소드이다.
	 * @param subStartDate 과목 시작 날짜
	 */
	public void setSubStartDate(String subStartDate) {
		this.subStartDate = subStartDate;
	}
	
	/**
	 * 과목종료날짜의 데이터를 내보내는 메소드이다.
	 * @return 과목 종료 날짜
	 */
	public String getSubEndDate() {
		return subEndDate;
	}
	
	/**
	 * 과목종료날짜의 데이터를 받는 메소드이다.
	 * @param subEndDate 과목 종료 날짜
	 */
	public void setSubEndDate(String subEndDate) {
		this.subEndDate = subEndDate;
	}
	
	/**
	 * 교재 데이터를 내보내는 메소드이다.
	 * @return 교재
	 */
	public String getBook() {
		return book;
	}
	
	/**
	 * 교재 데이터를 받는 메소드이다.
	 * @param book 교재
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	/**
	 * 출결배점의 데이터를 내보내는 메소드이다.
	 * @return 출결배점
	 */
	public String getAttendancePoint() {
		return attendancePoint;
	}
	
	/**
	 * 출결배점의 데이터를 받는 메소드이다.
	 * @param attendancePoint
	 */
	public void setAttendancePoint(String attendancePoint) {
		this.attendancePoint = attendancePoint;
	}
	
	/**
	 * 필기배점의 데이터를 내보내는 메소드이다.
	 * @return 필기배점
	 */
	public String getHandWritingTestPoint() {
		return handWritingTestPoint;
	}
	
	/**
	 * 필기배점의 데이터를 받는 메소드이다.
	 * @param handWritingTestPoint 필기배점
	 */
	public void setHandWritingTestPoint(String handWritingTestPoint) {
		this.handWritingTestPoint = handWritingTestPoint;
	}
	
	/**
	 * 실기배점의 데이터를 내보내는 메소드이다.
	 * @return 실기배점
	 */
	public String getPracticalTestPoint() {
		return practicalTestPoint;
	}
	
	/**
	 * 실기배점의 데이터를 받는 메소드이다.
	 * @param practicalTestPoint 실기배점
	 */
	public void setPracticalTestPoint(String practicalTestPoint) {
		this.practicalTestPoint = practicalTestPoint;
	}
	
	/**
	 * 출결점수의 데이터를 내보내는 메소드이다.
	 * @return 출결점수
	 */
	public String getAttendanceScore() {
		return attendanceScore;
	}
	
	/**
	 * 출결점수의 데이터를 받는 메소드이다.
	 * @param attendanceScore 출결점수
	 */
	public void setAttendanceScore(String attendanceScore) {
		this.attendanceScore = attendanceScore;
	}
	
	/**
	 * 필기점수의 데이터를 내보내는 메소드이다.
	 * @return 필기점수
	 */
	public String getHandWritingTestScore() {
		return handWritingTestScore;
	}
	
	/**
	 * 필기점수의 데이터를 받는 메소드이다.
	 * @param handWritingTestScore 필기점수
	 */
	public void setHandWritingTestScore(String handWritingTestScore) {
		this.handWritingTestScore = handWritingTestScore;
	}
	
	/**
	 * 실기점수의 데이터를 내보내는 메소드이다.
	 * @return 실기점수
	 */
	public String getPracticalTestScore() {
		return practicalTestScore;
	}
	
	/**
	 * 실기점수의 데이터를 받는 메소드이다.
	 * @param practicalTestScore 실기점수
	 */
	public void setPracticalTestScore(String practicalTestScore) {
		this.practicalTestScore = practicalTestScore;
	}
	
	/**
	 * 시험날짜에 대한 데이터를 내보내는 메소드이다
	 * @return 시험날짜
	 */
	public String getExamDate() {
		return examDate;
	}
	
	/**
	 * 시험날짜에 대한 데이터를 받는 메소드이다
	 * @param examDate 시험날짜
	 */
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * 시험문제등록여부에 대한 데이터를 내보내는 메소드이다.
	 * @return 시험 문제 등록 여부
	 */
	public String getExamFileStatus() {
		return examFileStatus;
	}
	
	/**
	 * 시험문제등록여부에 대한 데이터를 받는 메소드이다.
	 * @param examFileStatus 시험 문제 등록 여부
	 */
	public void setExamFileStatus(String examFileStatus) {
		this.examFileStatus = examFileStatus;
	}

}

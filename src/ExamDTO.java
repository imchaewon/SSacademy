/**
 * 시험에 관련덴 정보를 저장하는 DTO클래스이다.
 * @author 
 * String stitle;	과목명
 * String hpoint;	필기배점
 * String ppoint;	실기배점
 * String apoint;	출결배점
 * String examDate;	시험날짜
 * String examFileStatus;	시험등록여부
 * @author 이진수
 *
 */

public class ExamDTO {
	
	private String stitle;  //과목명
	private String hpoint; //필기배점
	private String ppoint; //실기배점
	private String apoint; //출결배점
	private String examDate; //시험날짜
	private String examFileStatus; //시험등록여부
	
	/**
	 * 과목명에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목명
	 */
	public String getStitle() {
		return stitle;
	}
	
	/**
	 * 과목명에 대한 데이터를 받는 메소드이다.
	 * @param stitle 과목명
	 */
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	
	/**
	 * 필기배점에 대한 데이터를 내보내는 메소드이다.
	 * @return 필기배점
	 */
	public String getHpoint() {
		return hpoint;
	}
	
	/**
	 * 필기배점에 대한 데이터를 받는 메소드이다.
	 * @param hpoint 필기배점
	 */
	public void setHpoint(String hpoint) {
		this.hpoint = hpoint;
	}
	
	/**
	 * 실기배점에 대한 데이터를 내보내는 메소드이다.
	 * @return 필기배점
	 */
	public String getPpoint() {
		return ppoint;
	}
	
	/**
	 * 실기배점에 대한 데이터를 받는 메소드이다.
	 * @param ppoint 실기배점
	 */
	public void setPpoint(String ppoint) {
		this.ppoint = ppoint;
	}
	
	/**
	 * 출결배점에 대한 데이터를 내보내는 메소드이다.
	 * @return 출결배점
	 */
	public String getApoint() {
		return apoint;
	}
	
	/**
	 * 출결배점에 대한 데이터를 받는 메소드이다.
	 * @param apoint 출결배점
	 */
	public void setApoint(String apoint) {
		this.apoint = apoint;
	}
	
	/**
	 * 시험날짜에 대한 데이터를 내보내는 메소드이다.
	 * @return 시험날짜
	 */
	public String getExamDate() {
		return examDate;
	}
	
	/**
	 * 시험날짜에 대한 데이터를 받는 메소드이다.
	 * @param examDate 시험날짜
	 */
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * 시험등록여부에 대한 데이터를 내보내는 메소드이다.
	 * @return 시험등록여부
	 */
	public String getExamFileStatus() {
		return examFileStatus;
	}
	
	/**
	 * 시험등록여부에 대한 데이터를 받는 메소드이다.
	 * @param examFileStatus 시험등록여부
	 */
	public void setExamFileStatus(String examFileStatus) {
		this.examFileStatus = examFileStatus;
	}

}

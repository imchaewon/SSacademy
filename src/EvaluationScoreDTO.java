/**
 * 특정 개설 과정의 교육생들의 평가 목록을 저장하기 위한 클래스이다.
 * 수강번호,교육생 이름,강의계획서 점수,강의내용 전달 및 이해점수,소통 점수,
 * 유익성 점수, 전반적인 만족도 점수를 관리한다.
 * @author 전수희
 *
 */

public class EvaluationScoreDTO {
	
	private String registrationSeq; //수강 번호 
	private String studentName; //교육생 이름
	private String processScore; //평가 - 강의계획서 이행점수
	private String understandScore; //평가 - 강의내용 전달 및 이해점수
	private String communicationScore; //평가 - 소통 점수
	private String usefulScore; //평가 - 유익성 점수
	private String satisfactionScore; //평가 - 전반적인 만족도 점수
	
	/**
	 * 수강 번호를 내보내는 메소드이다.
	 * @return 수강 번호
	 */
	public String getRegistrationSeq() {
		return registrationSeq;
	}
	
	/**
	 * 수강 번호를 받는 메소드이다.
	 * @param registrationSeq 수강 번호
	 */
	public void setRegistrationSeq(String registrationSeq) {
		this.registrationSeq = registrationSeq;
	}
	
	/**
	 * 교육생 이름을 내보내는 메소드이다.
	 * @return 교육생 이름
	 */
	public String getStudentName() {
		return studentName;
	}
	
	/**
	 * 교육생 이름을 받는 메소드이다.
	 * @param studentName 교육생 이름
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 평가 - 강의계획서 이행점수를 내보내는 메소드이다.
	 * @return 평가 - 이행점수
	 */
	public String getProcessScore() {
		return processScore;
	}
	
	/**
	 * 평가 - 강의계획서 이행점수를 받는 메소드이다.
	 * @param processScore 평가 - 이행점수
	 */
	public void setProcessScore(String processScore) {
		this.processScore = processScore;
	}
	
	/**
	 * 평가 - 강의내용 전달 및 이해점수를 내보내는 메소드이다.
	 * @return 평가 - 이해점수
	 */
	public String getUnderstandScore() {
		return understandScore;
	}
	
	/**
	 * 평가 - 강의내용 전달 및 이해점수를 받는 메소드이다.
	 * @param understandScore 평가 - 이해점수
	 */
	public void setUnderstandScore(String understandScore) {
		this.understandScore = understandScore;
	}
	
	/**
	 * 평가 - 소통 점수를 내보내는 메소드이다.
	 * @return 평가 - 소통 점수
	 */
	public String getCommunicationScore() {
		return communicationScore;
	}
	
	/**
	 * 평가 - 소통 점수를 받는 메소드이다.
	 * @param communicationScore 평가 - 소통 점수
	 */
	public void setCommunicationScore(String communicationScore) {
		this.communicationScore = communicationScore;
	}
	
	/**
	 * 평가 - 유익성 점수를 내보내는 메소드이다.
	 * @return 평가 - 유익성 점수
	 */
	public String getUsefulScore() {
		return usefulScore;
	}
	
	/**
	 * 평가 - 유익성 점수를 받는 메소드이다.
	 * @param usefulScore 평가 - 유익성 점수
	 */
	public void setUsefulScore(String usefulScore) {
		this.usefulScore = usefulScore;
	}
	
	/**
	 * 평가 - 전반적인 만족도 점수에 대한 내용을 내보내는 메소드이다.
	 * @return 평가 - 만족도 점수
	 */
	public String getSatisfactionScore() {
		return satisfactionScore;
	}
	
	/**
	 * 평가 - 전반적인 만족도 점수에 대한 내용을 받는 메소드이다.
	 * @param satisfactionScore 평가 - 만족도 점수
	 */
	public void setSatisfactionScore(String satisfactionScore) {
		this.satisfactionScore = satisfactionScore;
	}
	
	
	

}

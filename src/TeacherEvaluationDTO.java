/**
 * 
 * 교사 평가 정보를 저장하기 위한 클래스이다.
 * 교사평가번호, 과정명, 과정시작날짜, 과정종료날짜, 과정수료여부, 교사명
 * 강의계획서 이행점수, 강의내용 전달 및 이해점수, 소통 점수, 유익성 점수, 전반적인 만족도 점수를 관리한다.
 * seq; //교사평가번호
 * processName; //과정명
 * startDate; //과정시작날짜
 * endDate; //과정종료날짜
 * finishStatus; //과정수료여부
 * teacherName; //교사명
 * processScore; //평가 - 강의계획서 이행점수
 * understandScore; //평가 - 강의내용 전달 및 이해점수
 * communicationScore; //평가 - 소통 점수
 * usefulScore; //평가 - 유익성 점수
 * satisfactionScore; //평가 - 전반적인 만족도 점수
 * @author 전수희 
 *
 */
public class TeacherEvaluationDTO {

	private String seq; //교사평가번호
	private String processName; //과정명
	private String startDate; //과정시작날짜
	private String endDate; //과정종료날짜
	private String finishStatus; //과정수료여부
	private String teacherName; //교사명
	private String processScore; //평가 - 강의계획서 이행점수
	private String understandScore; //평가 - 강의내용 전달 및 이해점수
	private String communicationScore; //평가 - 소통 점수
	private String usefulScore; //평가 - 유익성 점수
	private String satisfactionScore; //평가 - 전반적인 만족도 점수
	
	
	
	/**
	 * 교사평가 번호를 내보내는 Getter 메소드이다.
	 * @return	교사평가 번호
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * 
	 * 교사평가 번호를 받아오는 Setter 메소드이다.
	 * @param seq 교사평가 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 교사평가를 한 과정이름을 내보내는 Getter 메소드이다.
	 * @return	과정 이름
	 */
	public String getProcessName() {
		return processName;
	}
	/**
	 * 교사평가를 받아오는 Setter 메소드이다.
	 * @param processName 과정 이름
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	/**
	 * 교사평가대상 과정의 과정시작날짜를 내보내는 Getter 메소드이다.
	 * @return 과정시작날짜
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * 교사평가대상 과정의 과정시작날짜를 받아오는 Setter 메소드이다.
	 * @param startDate 과정시작날짜
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 *  교사평가대상 과정의 과정종료날짜를 내보내는 Getter 메소드이다.
	 * @return 과정종료날짜
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * 교사평가대상 과정의 과정종료날짜를 받아오는 Setter 메소드이다.
	 * @param endDate 과정종료날짜
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * 교사평가대상 과정의 수료여부를 내보내는 Getter 메소드이다.
	 * @return 과정 수료여부
	 */
	public String getFinishStatus() {
		return finishStatus;
	}
	/**
	 * 교사평가대상 과정의 수료여부를 받아오는 Setter 메소드이다.
	 * @param finishStatus 과정 수료여부
	 */
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	/**
	 * 교사평가대상 교사의 이름을 내보내는 Getter 메소드이다.
	 * @return 교사 이름
	 */
	public String getTeacherName() {
		return teacherName;
	}
	/**
	 * 교사평가대상 교사의 이름을 가져오는 Setter 메소드이다.
	 * @param teacherName 교사 이름
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	 * 교사평가 중 강의계획서 이행점수를 내보내는 Getter메소드이다.
	 * @return	강의계획서 이행점수
	 */
	public String getProcessScore() {
		return processScore;
	}
	/**
	 * 교사평가 중 강의계획서 이행점수를 가져오는 Setter메소드이다.
	 * @param processScore 강의계획서 이행점수
	 */
	public void setProcessScore(String processScore) {
		this.processScore = processScore;
	}
	/**
	 * 교사평가 중 강의내용 전달 및 이해점수를 내보내는 Getter 메소드이다.
	 * @return 강의내용 전달 및 이해점수
	 */
	public String getUnderstandScore() {
		return understandScore;
	}
	/**
	 * 교사평가 중 강의내용 전달 및 이해점수를 가져오는 Setter 메소드이다.
	 * @param understandScore	강의내용 전달 및 이해점수
	 */
	public void setUnderstandScore(String understandScore) {
		this.understandScore = understandScore;
	}
	/**
	 * 교사평가 중 소통 점수를 내보내는 Getter 메소드이다.
	 * @return 소통 점수
	 */
	public String getCommunicationScore() {
		return communicationScore;
	}
	/**
	 * 교사평가 중 소통 점수를 가져오는 Setter 메소드이다.
	 * @param communicationScore	소통 점수
	 */
	public void setCommunicationScore(String communicationScore) {
		this.communicationScore = communicationScore;
	}
	/**
	 * 교사평가 중 유익성 점수를 내보내는 Getter 메소드이다.
	 * @return 유익성 점수
	 */
	public String getUsefulScore() {
		return usefulScore;
	}
	/**
	 * 교사평가 중 유익성 점수를 가져오는 Setter 메소드이다.
	 * @param usefulScore 유익성 점수
	 */
	public void setUsefulScore(String usefulScore) {
		this.usefulScore = usefulScore;
	}
	/**
	 * 교사평가 중 전반적인 만족도 점수를 내보내는 Getter메소드이다.
	 * @return 전반적인 만족도 점수
	 */
	public String getSatisfactionScore() {
		return satisfactionScore;
	}
	/**
	 * 교사평가 중 전반적인 만족도 점수를 가져오는 Setter메소드이다.
	 * @param satisfactionScore 전반적인 만족도 점수
	 */
	public void setSatisfactionScore(String satisfactionScore) {
		this.satisfactionScore = satisfactionScore;
	}
	
	
}
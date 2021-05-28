/**
 * 
 * 과목의 정보를 저장하는 DTO클래스이다.
 * seq; //과목 번호
 * title; //과목명
 * days; //기간(일)
 * @author 전수희
 */
public class SubjectDTO {
	
	private String seq; //과목 번호
	private String title; //과목명
	private String days; //기간(일)
	
	/**
	 * 과목 번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 과목 번호에 대한 데이터를 받는 메소드이다.
	 * @param seq 과목번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 과목명에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목명
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 과목명에 대한 데이터를 받는 메소드이다.
	 * @param title 과목명
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 과목 기간(일)에 대한 데이터를 내보내는 메소드이다.
	 * @return 과목 기간(일)
	 */
	public String getDays() {
		return days;
	}
	
	/**
	 * 과목 기간(일)에 대한 데이터를 받는 메소드이다.
	 * @param days 과목 기간(일)
	 */
	public void setDays(String days) {
		this.days = days;
	}
	
	

}

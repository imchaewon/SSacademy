/**
 * 
 * 과정의 정보를 저장하는 DTO이다. 
 * seq; //번호 
 * title; //과정명 
 * days; //기간
 * @author 전수희
 */
public class ProcessDTO {
	
	private String seq; //번호
	private String title; //과정명
	private String days; //기간
	
	/**
	 * 과정번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 과정번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 과정번호에 대한 데이터를 받는 메소드이다.
	 * @param seq 과정번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 과정명에 대한 데이터를 내보내는 메소드이다.
	 * @return 과정명
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 과정명에 대한 데이터를 받는 메소드이다.
	 * @param title 과정명
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 기간(일)에 대한 데이터를 내보내는 메소드이다.
	 * @return 기간(일)
	 */
	public String getDays() {
		return days;
	}
	
	/**
	 * 기간(일)에 대한 데이터를 받는 메소드이다.
	 * @param days 기간(일)
	 */
	public void setDays(String days) {
		this.days = days;
	}

}

/**
 * 
 * 개설 과목의 정보를 저장하는 DTO 이다.
 * seq;			//과목번호
 * openProcess;	//개설과정
 * title;		//과목명
 * book;		//교재
 * startDate;	//시작날짜
 * endDate;		//종료날짜
 *	@author 전수희
 */
public class OpenSubjectDTO {
	
	private String seq;			//과목번호
	private String openProcess;	//개설과정
	private String title;		//과목명
	private String book;		//교재
	private String startDate;	//시작날짜
	private String endDate;		//종료날짜
	
	/**
	 * 과목번호를 내보내는 메소드이다.
	 * @return 과목번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 과목번호를 받는 메소드이다.
	 * @param seq 과목번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 개설과정을 내보내는 메소드이다.
	 * @return 개설과정
	 */
	public String getOpenProcess() {
		return openProcess;
	}
	
	/**
	 * 개설과정을 받는 메소드이다.
	 * @param openProcess 개설과정
	 */
	public void setOpenProcess(String openProcess) {
		this.openProcess = openProcess;
	}
	
	/**
	 * 과목명을 내보내는 메소드이다.
	 * @return 과목명
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 과목명을 받는 메소드이다.
	 * @param title 과목명
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 교재에 대한 데이터를 내보내는 메소드이다.
	 * @return 교재
	 */
	public String getBook() {
		return book;
	}
	
	/**
	 * 교재에 대한 데이터를 받는 메소드이다.
	 * @param book 교재
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	/**
	 * 시작날짜를 내보내는 메소드이다.
	 * @return 시작날짜
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * 시작날짜를 받는 메소드이다.
	 * @param startDate 시작날짜
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 종료날짜를 내보내는 메소드이다.
	 * @return 종료날짜
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * 종료날짜를 받는 메소드이다.
	 * @param endDate 종료날짜
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}

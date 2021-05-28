/**
 * 교재 정보를 저장하기 위한 클래스이다.
 * 교재번호, 교재명, 출판사를 관리한다.
 * private String seq;	// 교재번호
 * private String title; // 교재명
 * private String publisher; // 출판사
 * @author 전수희
 * 
 */
public class BookDTO {
	
	private String seq;	// 교재번호
	private String title; // 교재명
	private String publisher; // 출판사
	
	/**
	 * 교재번호를 내보내는 메소드이다.
	 * @return 교재번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 교재번호를 받는 메소드이다.
	 * @param seq 교재번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 교재명을 내보내는 메소드이다.
	 * @return 교재명
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 교재명을 받는 메소드이다.
	 * @param title 교재명
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 출판사를 내보내는 메소드이다.
	 * @return 출판사
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * 출판사를 받는 메소드이다.
	 * 
	 * @param publisher 출판사
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	

}

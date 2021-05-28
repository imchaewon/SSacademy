/**
 * 
 * 교육생의 정보를 저장하는 DTO클래스이다.
 *	 seq; // 교육생 번호
	 name; // 교육생 이름
	 regidentRegistrationNumber; // 주민번호뒷자리
	 phone; // 전화번호
	 regdate; // 등록일
	 finishStatus; //수료여부
 * @author 전수희
 */
public class StudentDTO {
	
	private String seq; // 교육생 번호
	private String name; // 교육생 이름
	private String regidentRegistrationNumber; // 주민번호뒷자리
	private String phone; // 전화번호
	private String regdate; // 등록일
	private String finishStatus; //수료여부
	
	/**
	 * 교육생 번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 교육생 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 교육생 번호에 대한 데이터를 받는 메소드이다.
	 * @param seq 교육생 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 교육생 이름에 대한 데이터를 내보내는 메소드이다.
	 * @return 교육생 이름
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 교육생 이름에 대한 데이터를 받는 메소드이다.
	 * @param name 교육생 이름
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 주민등록번호(뒷자리)에 대한 데이터를 내보내는 메소드이다.
	 * @return 주민등록번호(뒷자리)
	 */
	public String getRegidentRegistrationNumber() {
		return regidentRegistrationNumber;
	}
	
	/**
	 * 주민등록번호(뒷자리)에 대한 데이터를 받는 메소드이다.
	 * @param regidentRegistrationNumber 주민등록번호(뒷자리)
	 */
	public void setRegidentRegistrationNumber(String regidentRegistrationNumber) {
		this.regidentRegistrationNumber = regidentRegistrationNumber;
	}
	
	/**
	 * 교육생 전화번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 전화번호
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 교육생 전화번호에 대한 데이터를 받는 메소드이다.
	 * @param phone 전화번호
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 교육생의 등록일에 대한 데이터를 내보내는 메소드이다.
	 * @return 등록일
	 */
	public String getRegdate() {
		return regdate;
	}
	
	/**
	 * 교육생의 등록일에 대한 데이터를 받는 메소드이다.
	 * @param regdate 등록일
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	/**
	 * 교육생의 수료여부에 대한 데이터를 내보내는 메소드이다.
	 * @return 수료여부
	 */
	public String getFinishStatus() {
		return finishStatus;
	}
	
	/**
	 * 교육생의 수료여부에 대한 데이터를 받는 메소드이다.
	 * @param finishStatus 수료여부
	 */
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	
	

}

/**
 * 교사 데이터 정보를 저장하기 위한 클래스이다.
 * 교사번호,교사이름,주민번호뒷자리,전화번호를 관리한다.
 * seq;	//교사번호
 * name;	//교사이름
 * regidentRegistrationNumber;	//교사 주민등록번호 뒷자리
 * phone;	//교사 전화번호
 * @author 전수희
 */


public class TeacherDTO {
	
	private String seq;
	private String name;
	private String regidentRegistrationNumber;
	private String phone;
	
	/**
	 * 교사 번호의 데이터를 내보내는 메소드이다.
	 * @return 교사 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 교사 번호의 데이터를 받는 메소드이다.
	 * @param seq 교사 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 이름에 대한 데이터를 내보내는 메소드이다.
	 * @return 이름
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 이름에 대한 데이터를 받는 메소드이다.
	 * @param name 이름
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
	 * 전화번호에 대한 데이터를 내보내는 메소드이다.
	 * @return 전화번호
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 전화번호에 대한 데이터를 받는 메소드이다.
	 * @param phone 전화번호
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}

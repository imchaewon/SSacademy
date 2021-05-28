/**
 * 출석 관련 정보를 받아오고 관리하는 클래스이다.
 */
public class checkStatus {
	
	private String month;
	private String time;
	private String status;
	
	/**
	 * 출석한 날짜의 월을 내보내는 Getter 메소드이다.
	 * @return	출석한 날의 월
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * 출석한 날짜의 월을 받아오는 Setter 메소드이다.
	 * @param month	출석한 날의 월
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * 출석한 날짜의 시간을 내보내는 Getter 메소드이다.
	 * @return	출석한 날의 시간
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 출석한 날짜의 시간을 받아오는 Setter 메소드이다.
	 * @param time 출석한 날의 시간
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 출석한 날짜의 출석상태를 내보내는 Getter 메소드이다.
	 * @return	출석상태
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 출석한 날짜의 출석상태를 받아오는 Setter 메소드이다.
	 * @param status	출석상태
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}

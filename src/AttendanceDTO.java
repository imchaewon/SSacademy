/**
 * 특정 교육생의 수강정보를 저장하기 위한 클래스이다.
 * 출석 관련된 변수를 선언하는 부분
 * seq; //수강과정번호
 * openProcessName; //과정명
 * startDate; //과정시작날짜
 * endDate; //과정종료날짜
 * attendanceTime; //시간
 * attendanceStatus; //출결상태
 * rcseq; //수강번호
 * openProcessNum; //과정번호
 * attendanceTime2; 시간
 * @author 이진수
 */

public class AttendanceDTO {
	
	private String seq; 
	private String openProcessName; 
	private String startDate;
	private String endDate; 
	private String attendanceTime; 
	private String attendanceStatus; 
	private String rcseq;			
	private String openProcessNum;	
	private String attendanceTime2;
	
	/**
	 * 출결단위(일) 시간을 내보내는 메소드이다.
	 * @return 출결단위(일)
	 */
	public String getAttendanceTime2() {
		return attendanceTime2;
	}
	
	/**
	 * 출결단위(일) 시간을 받는 메소드이다.
	 * @param attendanceTime2 출결단위(일)
	 */
	public void setAttendanceTime2(String attendanceTime2) {
		this.attendanceTime2 = attendanceTime2;
	}
	
	/**
	 * 수강 과정번호를 내보내는 메소드이다.
	 * @return 과정번호
	 */
	public String getOpenProcessNum() {
		return openProcessNum;
	}
	
	/**
	 * 수강 과정번호를 받는 메소드이다.
	 * @param openProcessNum 과정번호
	 */
	public void setOpenProcessNum(String openProcessNum) {
		this.openProcessNum = openProcessNum;
	}
	
	/**
	 * 수강번호를 내보내는 메소드이다.
	 * @return 수강번호
	 */
	public String getRcseq() {
		return rcseq;
	}
	
	/**
	 * 수강번호를 받는 메소드이다.
	 * @param rcseq 수강번호
	 */
	public void setRcseq(String rcseq) {
		this.rcseq = rcseq;
	}
	
	/**
	 * 수강과정번호를 내보내는 메소드이다.
	 * @return 수강과정번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 수강과정번호를 받는 메소드이다.
	 * @param seq 수강과정번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 과정명을 내보내는 메소드이다.
	 * @return 과정명
	 */
	public String getOpenProcessName() {
		return openProcessName;
	}
	
	/**
	 * 과정명을 받는 메소드이다.
	 * @param openProcessName 과정명
	 */
	public void setOpenProcessName(String openProcessName) {
		this.openProcessName = openProcessName;
	}
	
	/**
	 * 과정시작날짜를 내보내는 메소드이다.
	 * @return 과정시작날짜
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * 과정종료날짜를 받는 메소드이다.
	 * @param startDate 과정시작날짜
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 과정종료날짜를 내보내는 메소드이다.
	 * @return 과정종료날짜
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * 과정종료날짜를 받는 메소드이다.
	 * @param endDate 과정종료날짜
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 출결단위(월)을 내보내는 메소드이다.
	 * @return 출결단위(월)
	 */
	public String getAttendanceTime() {
		return attendanceTime;
	}
	
	/**
	 * 출결단위(월)을 받는 메소드이다.
	 * @param attendanceTime 출결단위(월)
	 */
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	
	/**
	 * 출결상태를 내보내는 메소드이다.
	 * @return 출결상태
	 */
	public String getAttendanceStatus() {
		return attendanceStatus;	
	}
	
	/**
	 * 출결상태를 받는 메소드이다.
	 * @param attendanceStatus 출결상태
	 */
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	

}

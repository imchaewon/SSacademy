/**
 * 교사 강의 가능 과목 정보를 저장하기 위한 클래스이다.
 * 강의가능과목 번호, 교사 번호, 과목 번호를 관리한다.
 * @author 전수희
 *
 */
public class AvailableSubjectDTO {
	
	private String seq; //강의가능과목 번호
	private String teacher_seq; //교사 번호
	private String subject_seq; //과목 번호
	
	/**
	 * 강의가능과목 번호를 내보내는 메소드이다.
	 * @return 강의가능과목 번호
	 */
	public String getSeq() {
		return seq;
	}
	
	/**
	 * 강의가능과목 번호를 받는 메소드이다.
	 * @param seq 강의가능과목 번호
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	/**
	 * 교사 번호를 내보내는 메소드이다.
	 * @return 교사 번호
	 */
	public String getTeacher_seq() {
		return teacher_seq;
	}
	
	/**
	 * 교사 번호를 받는 메소드이다.
	 * @param teacher_seq 교사 번호
	 */
	public void setTeacher_seq(String teacher_seq) {
		this.teacher_seq = teacher_seq;
	}
	
	/**
	 * 과목 번호를 내보내는 메소드이다.
	 * @return 과목 번호
	 */
	public String getSubject_seq() {
		return subject_seq;
	}
	
	/**
	 * 과목 번호를 받는 메소드이다.
	 * @param subject_seq 과목 번호
	 */
	public void setSubject_seq(String subject_seq) {
		this.subject_seq = subject_seq;
	}
	
	

}

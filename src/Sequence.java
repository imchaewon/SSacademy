import java.util.ArrayList;

/**
 * 모든 클래스 파일에서 사용할 수 있도록 전역변수로 지정해주는 Class이다.
 * 교사번호와 교육생 번호를 저장하여 사용한다.
 * teacherSeq = 교사 번호
 * StudentSeq = 교육생 번호
 * @author 이진수
 *
 */
public class Sequence {
	
	public static String teacherSeq;
	public static String StudentSeq;
	public static ArrayList<AttendanceDTO> chul = new ArrayList<AttendanceDTO>();
	
}

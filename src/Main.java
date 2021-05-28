
public class Main {
	
	/**
	 * 메인 메소드이다.
	 * 로그인을 시작하는 클래스이다.
	 * @param args args
	 */
	public static void main(String[] args) {
		
		//로그인
		//컨트롤러 3개 : 관리자, 교사, 교육생
		
		Login lg = new Login();
		lg.start();
		
		
//		AdminController admin = new AdminController();
//		admin.start();
//		TeacherController teacher = new TeacherController();
//		teacher.start();
//		StudentController student = new StudentController();
//		student.start();
		
	}

}

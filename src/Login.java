
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 관리자,교사,교육생 로그인을 위한 클래스이다.
 *
 */
public class Login {

	private static Scanner scan;
	private static Connection conn;
	private static Statement stat;
	private static ResultSet rs;

	static {
		scan = new Scanner(System.in);
	}

	/**
	 * 오라클 접속을 위하여 Connection객체와 Statement객체를 생성해준다.
	 */
	public Login() {

		try {

			conn = DBUtil.open();
			stat = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 메인(관리자,교사,교육생) 로그인 화면 관리자,교사,교육생을 선택하여 로그인한다.
	 */
	public static void start() {
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃        메인화면\t           ┃");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		boolean loop = true;

		while (loop) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");

			System.out.println("┃ ┏━━━━━━━━┓     ┏━━━━━━━━┓     ┏━━━━━━━━┓ ┃");
			System.out.println("┃ ┃1.관리자┃     ┃2.교사  ┃     ┃3.교육생┃ ┃");
			System.out.println("┃ ┗━━━━━━━━┛     ┗━━━━━━━━┛     ┗━━━━━━━━┛ ┃");

			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.print("선택 : ");

			String main = scan.nextLine();

			if (main.equals("1")) {
				loop = false;
				loginAdmin();
			} else if (main.equals("2")) {
				loop = false;
				loginTeacher();
			} else if (main.equals("3")) {
				loop = false;
				loginStudent();
			} else
				System.out.println("1,2,3번중 선택하세요");

		}

	}

	/**
	 * 관리자 로그인 화면
	 */
	private static void loginAdmin() {
		String id = "";
		String password = "";
		boolean check = true;

//         System.out.println("\n\n\n\n\n[관리자]");
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃            관리자 로그인 페이지          ┃");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃┏━━━━━━━━━━━━━━━━━┓    ┏━━━━━━━━━━━━━━━━━┓┃");
		System.out.println("┃┃    1.로그인     ┃    ┃   0.메인 이동   ┃┃");
		System.out.println("┃┗━━━━━━━━━━━━━━━━━┛    ┗━━━━━━━━━━━━━━━━━┛┃");
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		System.out.print("선택 : ");
		String main = scan.nextLine();

		if (main.equals("1")) {

//            System.out.println("[관리자]");
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃           관리자 로그인 페이지           ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.print("ID   :");
			id = scan.nextLine();
			System.out.print("Password:");
			password = scan.nextLine();

//            관리자 로그인하는곳

			try {

				String sql = "select * from tbladmin";
				rs = stat.executeQuery(sql);
				while (check) {
					while (rs.next()) {
						if (rs.getString("name").equals(id)
								&& rs.getString("regidentRegistrationNumber").equals(password)) {
							check = false;
							break;
						} else {
							check = true;
						}

					}
					if (check == false) {
						System.out.printf("%s 관리자님 로그인 완료\n", rs.getString("name"));
						AdminController admin = new AdminController();
						admin.start();
					} else {
						System.out.println("아이디와 비밀번호를 확인해주세요");
						loginAdmin();
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		} else {
			System.out.println("메인으로 이동합니다.");
			start();
		}

	}

	/**
	 * 교사 로그인 화면
	 */
	private static void loginTeacher() {

		String id = "";
		String password = "";
		boolean check = true;

		System.out.println("[교사]");
		System.out.println(
				"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t\t\t\t\t\t\t\t\t\t교사 로그인 페이지");
		System.out.println(
				"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

		System.out.println("1. 교사 로그인으로 이동  ");
		System.out.println("0. 메인으로  ");

		System.out.print("선택 : ");
		String main = scan.nextLine();

		if (main.equals("1")) {

			System.out.println("[교사]");
			System.out.println(
					"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t\t\t\t\t\t\t\t\t\t교사 로그인 페이지");
			System.out.println(
					"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

			System.out.print("ID   :");
			id = scan.nextLine();
			System.out.print("Password:");
			password = scan.nextLine();

//         교사 로그인하는곳

			try {

				String sql = "select * from tblTeacher";
				rs = stat.executeQuery(sql);
				while (check) {
					while (rs.next()) {
						if (rs.getString("name").equals(id)
								&& rs.getString("regidentRegistrationNumber").equals(password)) {

							check = false;
							break;
						} else {
							check = true;
						}

					}
					if (check == false) {
						System.out.printf("%s 교사님 로그인 완료\n", rs.getString("name"));
						TeacherController teacher = new TeacherController();
						Sequence.teacherSeq = rs.getString("seq");
						teacher.start();
					} else {
						System.out.println("아이디와 비밀번호를 확인해주세요");
						loginTeacher();
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		} else {
			System.out.println("메인으로 이동합니다.");
			start();
		}

	}

	/**
	 * 교육생 로그인 화면
	 */
	private static void loginStudent() {

		String id = "";
		String password = "";
		boolean check = true;

		System.out.println("[교육생]");
		System.out.println(
				"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 로그인 페이지");
		System.out.println(
				"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

		System.out.println("1. 교육생 로그인으로 이동  ");
		System.out.println("0. 메인으로  ");

		System.out.print("선택 : ");
		String main = scan.nextLine();

		if (main.equals("1")) {

			System.out.println("[교육생]");
			System.out.println(
					"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("\t\t\t\t\t\t\t\t\t\t교육생 로그인 페이지");
			System.out.println(
					"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

			System.out.print("ID   :");
			id = scan.nextLine();
			System.out.print("Password:");
			password = scan.nextLine();

//         교육생 로그인하는곳

			try {

				String sql = "select * from tblStudent";
				rs = stat.executeQuery(sql);
				while (check) {
					while (rs.next()) {
						if (rs.getString("name").equals(id)
								&& rs.getString("regidentRegistrationNumber").equals(password)) {

							check = false;
							break;
						} else {
							check = true;
						}

					}
					if (check == false) {
						System.out.printf("%s 교육생님 로그인 완료\n", rs.getString("name"));
						StudentController student = new StudentController();
						Sequence.StudentSeq = rs.getString("seq");
						student.start();
					} else {
						System.out.println("아이디와 비밀번호를 확인해주세요");
						loginStudent();
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

			}

		} else {
			System.out.println("메인으로 이동합니다.");
			start();
		}

	}

}
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교육생의 모든 기능을 컨트롤할 수 있는 클래스이다.
 * 출결 현황 및 출결 체크, 성적 조회, 교사 평가 기능을 컨트롤할 수 있다.
 */
public class StudentController {
	
	private Scanner scan;
	private StudentView view;
	private OpenProcessDAO opdao;
	private AttendanceDAO atdao;
	private boolean loop;
	private OpenP_SubjectDAO opsdao;
	private TeacherEvaluationDAO tedao;
	
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다. 
	 */
	public StudentController() {
		scan = new Scanner(System.in);
		view = new StudentView();
		loop = true;
		tedao = new TeacherEvaluationDAO();
		opdao = new OpenProcessDAO();
		opsdao = new OpenP_SubjectDAO();
	}
	
	/**
	 * 교육생 메인 메뉴를 보여준다.
	 */
	public void start() {	
		
		//프로그램 시작
//		boolean loop = true;
		
		while(loop) {
			
			view.mainMenu();
			String sel = scan.nextLine();
			
			if(sel.equals("1")) { attendanceMenu(); } //출결 현황 및 출결 체크
			else if(sel.equals("2")) { selectScoreMenu(); } //성적 조회 
			else if(sel.equals("3")) { teacherEvaluationMenu(); } //교사 평가 
			else { loop=false; }
			
		} //프로그램 끝
		
	} //start()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//1. 출결 현황 및 출결 체크
	private void attendanceMenu() {

		// 교육생이 수강한 과정 목록 출력
//			ArrayList<OpenProcessDTO> result = opdao.registProcessList();
//			view.opProcessList(result);

		// 선택 과정 출결 조회 목록 출력
		view.checkMenu();
		String sel = scan.nextLine(); // 원하는 조회 번호 입력

		boolean loop = true;

		while (loop) {
			if (sel.equals("0")) {
				start();
			} // 뒤로가기
			else if (sel.equals("1")) {
				allAttendanceList();
				loop = false;
			} // 전체 조회
			else if (sel.equals("2")) {
				monthAttendanceList();
				loop = false;
			} // 월별 조회
			else if (sel.equals("3")) {
				attendanceCheck();
				loop = false;
			} // 출결 체크
			else {
				loop = false;
			}
		}

	}

	// 1-1. 전체 조회
	private void allAttendanceList() {

		// 교육생 모든과정 출결 목록 출력
		ArrayList<AttendanceDTO> result = atdao.attendanceList(Sequence.StudentSeq);
		view.allAttendanceList(result);
		String sel = scan.nextLine();
		boolean loop = true;
		while (loop) {
			if (sel.equals("0")) {
				attendanceMenu();
			} // 1. 출결 현황 및 출결 체크 화면으로
			loop = false;
		}

	}

	// 1-2. 월별 조회
	private void monthAttendanceList() {

		// 교육생이 원하는 입력(년, 월)
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("월별 조회(2번 선택)");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("검색 할 연도 입력 : ");
		String year = scan.nextLine();
		System.out.print("검색 할 월 입력 : ");
		String month = scan.nextLine();

		ArrayList<AttendanceDTO> list = atdao.monthAttendanceList(year, month);
		view.chooseMonthList(list);

		System.out.println("0. 뒤로가기"); // 수강과정목록으로
		System.out.println("-------------------------------------------------------------------------------------");
		String sel = scan.nextLine();

		boolean loop = true;
		while (loop) {
			if (sel.equals("0")) {
				attendanceMenu();
			} // 1. 출결 현황 및 출결 체크 화면으로
		}

	}

	// 1-3. 출결체크
	private void attendanceCheck() {
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("출결 체크(3번 선택)");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("출결 체크 하시겠습니까? (Y/N)");
		System.out.print("입력 : ");
		String sel = scan.nextLine();
		sel = sel.toUpperCase();
		// 출결체크(x)

		if (sel.equals("N")) {
			attendanceMenu();
		} else if (sel.equals("Y")) {
			AttendanceCheck2();
		} else {
			System.out.println("잘못 입력하셨습니다.");
			attendanceCheck();
		}
		attendanceMenu();
	}

	// 교육생 출석 체크
	private void AttendanceCheck2() {
		int result = atdao.AttendanceCheck2();
		view.checkResult(result);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//2. 성적 조회
	private void selectScoreMenu() {
		
		//교육생 수강한 과정 선택 후 과목 조회
		ArrayList<OpenProcessDTO> result = opdao.registProcessList();
		view.opProcessList(result);
		
		System.out.println("<<개설과정리스트에 있는 개설과정 번호를 입력해주세요>>");
		System.out.print("선택 : ");
		String sel = scan.nextLine();
		System.out.println("-------------------------------------------------------------------------------------");
		
		ArrayList<OpenP_SubjectDTO> slist = opsdao.registP_Subjects(sel);
		view.openP_Subject(slist);
		
		String nsel = scan.nextLine();
		
		while(loop) {
			if(nsel.equals("0")) { start(); } //교육생 초기화면으로
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//3. 교사 평가
	private void teacherEvaluationMenu() {
		
		//교육생이 수강한 과정별 교사평가 조회
		String studentSeq = Sequence.StudentSeq;
		ArrayList<TeacherEvaluationDTO> dto = tedao.studentEvaluationList(studentSeq);
		view.teacherEvaluationList(dto);
		
		System.out.println("<<교사 평가 조회리스트에 있는 교사평가번호를 입력해주세요>>");
		System.out.print("선택 : ");
		String pnum = scan.nextLine(); //과정번호입력받기
		
		while(loop) {
		System.out.println("1. 교사 평가 등록"); //교사 평가 점수 등록하기
		System.out.println("2. 교사 평가 수정"); //등록한 교사 평가 점수 수정하기
		System.out.println("3. 교사 평가 삭제"); //등록한 교사 평가 점수 삭제하기
		System.out.println();
		System.out.println("0. 뒤로가기"); //교육생 카테고리로
		System.out.println("---------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		String sel = scan.nextLine();
		
			if(sel.equals("0")) { start(); } //교육생 카테고리로
			else if(sel.equals("1")) { addEvaluation(); }
			else if(sel.equals("2")) { updateEvaluation(); }
			else if(sel.equals("3")) { deleteEvaluation(pnum); }
			else { loop = false; }
		}
		
	}//3. teacherEvaluation()

	//3-1. 교사 평가 등록
	private void addEvaluation() {
		
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("교사 평가 등록(1번 선택)");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("1) 강의계획서 이행 점수 입력(1~5) : ");
		String processScore = scan.nextLine();
		System.out.print("2) 강의내용 전달 및 이해 점수 입력(1~5) : ");
		String understandScore = scan.nextLine();
		System.out.print("3) 소통 점수 입력(1~5) : ");
		String communicationScore = scan.nextLine();
		System.out.print("4) 유익성 점수 입력(1~5) : ");
		String usefulScore = scan.nextLine();
		System.out.print("5) 전반적인 만족도 점수 입력(1~5) : ");
		String satisfactionScore = scan.nextLine();
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("1. 등록");
		System.out.println("0. 뒤로가기"); // 교사평가조회로
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			teacherEvaluationMenu();
		} else if(sel.equals("1")) {
			String studentSeq = Sequence.StudentSeq;
			int addList = tedao.addTeacherEvaluation(studentSeq, processScore, understandScore, communicationScore, usefulScore, satisfactionScore);
			view.addResult(addList);
		} else {
			System.out.println("평가 등록이 실패하였습니다.");
		}
		
		
	}

	//3-2. 교사 평가 수정
	private void updateEvaluation() {
		
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("교사 평가 수정(2번 선택)");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("1) 강의계획서 이행 점수 입력(1~5) : ");
		String processScore = scan.nextLine();
		System.out.print("2) 강의내용 전달 및 이해 점수 입력(1~5) : ");
		String understandScore = scan.nextLine();
		System.out.print("3) 소통 점수 입력(1~5) : ");
		String communicationScore = scan.nextLine();
		System.out.print("4) 유익성 점수 입력(1~5) : ");
		String usefulScore = scan.nextLine();
		System.out.print("5) 전반적인 만족도 점수 입력(1~5) : ");
		String satisfactionScore = scan.nextLine();
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("1. 수정");
		System.out.println("0. 뒤로가기"); // 교사평가조회로
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) {
			teacherEvaluationMenu();
		} else if(sel.equals("1")) {
			String studentSeq = Sequence.StudentSeq;
			int updateList = tedao.updateTeacherEvaluation(studentSeq, processScore, understandScore, communicationScore, usefulScore, satisfactionScore);
			view.updateResult(updateList);
		} else {
			System.out.println("평가 수정이 실패하였습니다.");
		}
		
	}

	/**
	 * 형준
	 * @param pnum
	 */
	//3-3. 교사 평가 삭제
	private void deleteEvaluation(String pnum) {
		
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("교사 평가 삭제(3번 선택)");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("1. 삭제하기");
		System.out.println("0. 뒤로가기"); // 교사평가삭제로
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		String sel = scan.nextLine();
		
		if(sel.equals("0")) { 
			teacherEvaluationMenu();
		} else if(sel.equals("1")) {
			int delList = tedao.deleteTeacherEvaluation(pnum);
			view.deleteResult(delList);
		} else {
			System.out.println("잘못 입력하셨습니다.");
			deleteEvaluation(pnum); //안내 문구 출력 후 교사평가삭제로
		}
		
	}
	
}


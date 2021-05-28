import java.util.ArrayList;
import java.util.Scanner;

/**
 * 교사의 모든 기능을 컨트롤할 수 있는 클래스이다.
 * 강의 스케줄 조회, 배점 및 시험 정보 관리, 성적 정보 관리, 출결 조회, 교사 평가 조회 기능을 컨트롤할 수있다.
 * @author 전수희
 *
 */
public class TeacherController {
	
	public static String teacherSeq;			//로그인 한 교사 Seq
	
	private Scanner scan;
	private TeacherView view;
	
	private TeacherDAO tcdao;					//교사 담당 개설 과정
	
	private LectureScheduleDAO lsdao;			//강의 스케줄
	private ExamDAO edao;						//배점 및 시험 정보
	private AttendanceDAO adao;					//출결
	private TeacherEvaluationDAO tedao;			//교사 평가
	private OpenProcessDAO opdao;
	private String openPorcess;
	
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다. 
	 */
	//생성자
	public TeacherController() {
		scan = new Scanner(System.in);
		view = new TeacherView();
		
		tcdao = new TeacherDAO();				//교사 담당 개설 과정
		opdao = new OpenProcessDAO();
		lsdao = new LectureScheduleDAO();		//강의 스케줄
		edao = new ExamDAO();					//배점 및 시험 정보
		adao = new AttendanceDAO();				//출결 
		tedao = new TeacherEvaluationDAO();		//교사 평가
	}
	
	
	/**
	 * 교사 메인 메뉴를 보여준다.
	 */
	public void start() {

		//교사 로그인
		boolean loop = true;
		
		while (loop) {
			
			view.mainMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { scheduleList(); } //강의스케줄 조회
			else if (sel.equals("2")) { examInformationManagement(); } //배점 및 시험 정보 관리
			else if (sel.equals("3")) { scoreManagement(); } //성적 정보 관리
			else if (sel.equals("4")) { selectAttendance(); } //출결 조회
			else if (sel.equals("5")) { selectTeacherEvaluation(); } //교사 평가 조회
			else { loop = false; }
		}
		
		//교사 로그아웃
		DBUtil.close();
	}
	
	
	//로그인 한 교사의 담당 개설 과목 목록 출력 > 개설과정 선택
	private String teacherCourse() {
		
		String teacherSeq = Sequence.teacherSeq;
		
		//담당 개설 과정 목록 보여주기
		ArrayList<TeacherCourseDTO> teacherCourses = tcdao.teacherCourseList(teacherSeq);
		view.teacherCourseList(teacherCourses);
		
		//개설 과정 선택
		System.out.println("<<개설 과정 리스트에 있는 개설 과정 번호를 입력해주세요.>>");
		System.out.print("번호(숫자) : ");
		String openProcessSeq = scan.nextLine(); //개설 과정 선택
		
		return openProcessSeq;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//강의스케줄 조회
	private void scheduleList() {
		
		//선택한 개설 과정
		String openProcessSeq = teacherCourse();
		
		boolean loop = true;
		while (loop) {
			
			view.choosescheduleMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { openSubjectList(openProcessSeq); } //개설 과목 조회
			else if (sel.equals("2")) { studentList(openProcessSeq); } //교육생 정보 조회
			else if (sel.equals("0")) { return; } //뒤로가기
			else { loop = false; }
		}
	}
	
	//개설 과목 조회 
	private void openSubjectList(String openProcessSeq) {
		
		//특정 개설 과정의 개설 과목 목록
		ArrayList<OpenSubjectDTO> openSubjects = lsdao.openSubjectList(openProcessSeq);
		view.openSubjectList(openSubjects);
	}
	
	
	//교육생 정보 조회 
	private void studentList(String openProcessSeq) {
		
		//특정 개설 과정의 교육생 목록
		ArrayList<StudentDTO> students = lsdao.studentList(openProcessSeq);
		view.studentList(students);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//배점 및 시험 정보 관리
	private void examInformationManagement() {
		
		//선택한 개설 과정
		String openProcessSeq = teacherCourse();
		
		//특정 개설 과정의 과목 목록 출력
		ArrayList<OpenSubjectDTO> openSubjects = edao.openSubejct(openProcessSeq);
		view.openSubjectList(openSubjects);
				
		System.out.println("<<개설 과목 리스트에 있는 개설 과목 번호를 입력해주세요.>>");
		System.out.print("선택 : ");
		String openSubjectSeq = scan.nextLine();
		
		//선택한 개설 과정의 개설 과목의 시험 정보 출력
		ArrayList<ExamDTO> examInformation = edao.examInformation(openProcessSeq, openSubjectSeq);
		view.examInformation(examInformation);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseExamInformationMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addExamInformationMenu(openProcessSeq, openSubjectSeq); } //배점 및 시험 정보 등록
			if (sel.equals("2")) { updateExamInformationMenu(openProcessSeq, openSubjectSeq); } //배점 및 시험 정보 수정
			if (sel.equals("3")) { deleteExamInformationMenu(openProcessSeq, openSubjectSeq); } //배점 및 시험 정보 삭제
			else { loop = false; }
		}
	}


	//배점 및 시험 정보 등록 메뉴
	private void addExamInformationMenu(String openProcessSeq, String openSubjectSeq) {
		
		//배점 및 시험 정보 등록 관리 헤더
		view.addExamInformationMenuHedaer();
		
		System.out.print("필기 배점 : ");
		String hpoint = scan.nextLine();
		
		System.out.print("실기 배점 : ");
		String ppoint = scan.nextLine();
		
		System.out.print("출결 배점 : ");
		String apoint = scan.nextLine();

		System.out.print("시험 날짜(년, 4자리) : ");
		String year = scan.nextLine();
		System.out.print("시험 날짜(월, 2자리) : ");
		String month= scan.nextLine();
		System.out.print("시험 날짜(일, 2자리) : ");
		String day = scan.nextLine();
		String examDate = year + "-" + month + "-" + day;
		
		System.out.print("시험 문제 등록 여부(등록/미등록) : ");
		String examFileStatus = scan.nextLine();
		
		ExamDTO edto = new ExamDTO();
		edto.setHpoint(hpoint);
		edto.setPpoint(ppoint);
		edto.setApoint(apoint);
		edto.setExamDate(examDate);
		edto.setExamFileStatus(examFileStatus);
		
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addExamInformation(openProcessSeq, openSubjectSeq , edto);
			loop = false;}
			else { loop = false; }
		}
	}

	//배점 및 시험 정보 등록
	private void addExamInformation(String openProcessSeq, String openSubjectSeq , ExamDTO edto ) {
		int result = edao.addExamInformation(openProcessSeq, openSubjectSeq ,edto);
		view.addResult(result);
	}
	
	
	//배점 및 시험 정보 수정 메뉴
	private void updateExamInformationMenu(String openProcessSeq, String openSubjectSeq) {
		
		//배점 및 시험 정보 수정 헤더
		view.updateExamInformationMenuHedaer();	
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateExamInforamtionMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateHandwritingPointMenu(openProcessSeq, openSubjectSeq); } //필기 배점 수정
			if (sel.equals("2")) { updatePracticalPointMenu(openProcessSeq, openSubjectSeq); } //실기 배점 수정
			if (sel.equals("3")) { updateAttendancePointMenu(openProcessSeq, openSubjectSeq); } //출결 배점 수정 
			if (sel.equals("4")) { updateExamDateMenu(openProcessSeq, openSubjectSeq); } //시험 날짜 수정 
			if (sel.equals("5")) { updateExamFileStatusMenu(openProcessSeq, openSubjectSeq); } //시험 문제 등록 여부 수정 
			else { loop = false; }
		}
	}

	//필기 배점 수정
	private void updateHandwritingPointMenu(String openProcessSeq, String openSubjectSeq) {
		
		//필기 배점 수정 헤더 
		view.updateHandwritingPointMenuHeader();
		
		System.out.print("필기 배점 : ");
		String hpoint = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateHandwritingPoint(openProcessSeq, openSubjectSeq, hpoint);
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//필기 배점 수정
	private void updateHandwritingPoint(String openProcessSeq, String openSubjectSeq , String hpoint) {
		int result = edao.updateHandwritingPoint(openProcessSeq, openSubjectSeq , hpoint);
		view.updateResult(result);
	}
	
	//실기 배점 수정 메뉴
	private void updatePracticalPointMenu(String openProcessSeq, String openSubjectSeq) {
		
		//실기 배점 수정 헤더 
		view.updatePracticalPointMenuHeader();
		
		System.out.print("실기 배점 : ");
		String ppoint = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updatePracticalPoint(openProcessSeq, openSubjectSeq , ppoint); 
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//실기 배점 수정
	private void updatePracticalPoint(String openProcessSeq, String openSubjectSeq , String ppoint) {
		int result = edao.updatePracticalPoint(openProcessSeq, openSubjectSeq , ppoint);
		view.updateResult(result);
	}
	
	//출결 배점 수정 메뉴
	private void updateAttendancePointMenu(String openProcessSeq, String openSubjectSeq) {
		
		//출결 배점 수정 헤더 
		view.updateAttendancePointMenuHeader();
		
		System.out.print("출결 배점 : ");
		String ppoint = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateAttendancePoint(openProcessSeq, openSubjectSeq , ppoint); 
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//출결 배점 수정
	private void updateAttendancePoint(String openProcessSeq, String openSubjectSeq , String ppoint) {
		int result = edao.updateAttendancePoint(openProcessSeq, openSubjectSeq , ppoint);
		view.updateResult(result);
	}
	
	//시험 날짜 수정 메뉴
	private void updateExamDateMenu(String openProcessSeq, String openSubjectSeq) {
		
		//시험 날짜 수정 헤더 
		view.updateExamDateMenuHeader();
		
		System.out.print("시험 날짜(년, 4자리) : ");
		String year = scan.nextLine();
		System.out.print("시험 날짜(월, 2자리) : ");
		String month= scan.nextLine();
		System.out.print("시험 날짜(일, 2자리) : ");
		String day = scan.nextLine();
		String examDate = year + "-" + month + "-" + day;
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateExamDate(openProcessSeq, openSubjectSeq,examDate); 
			loop = false;}
			else { loop = false; }
		}
	}
	
	//시험 날짜 수정
	private void updateExamDate(String openProcessSeq, String openSubjectSeq , String examDate) {
		int result = edao.updateExamDate(openProcessSeq, openSubjectSeq , examDate);
		view.updateResult(result);
	}
	
	//시험 문제 등록 여부 수정 메뉴
	private void updateExamFileStatusMenu(String openProcessSeq, String openSubjectSeq) {
		
		boolean check = true;
		String examFile ="";
		//시험 문제 등록 여부 수정 헤더 
		view.updateExamFileStatusMenuHeader();
		while(check) {
		System.out.print("시험 문제 등록 여부 : ");
		examFile = scan.nextLine();
		if(examFile.equals("등록")||examFile.equals("미등록")) {
			check=false;
		}else {
			System.out.println("등록 혹은 미등록으로 입력해주세요");
		}
		}
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateExamFileStatus(openProcessSeq, openSubjectSeq, examFile);
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//시험 문제 등록 여부 수정
	private void updateExamFileStatus(String openProcessSeq, String openSubjectSeq,String examFile) {
		int result = edao.updateExamFileStatus(openProcessSeq, openSubjectSeq,examFile);
		view.updateResult(result);
	}

	
	
	//배점 및 시험 정보 삭제 메뉴
	private void deleteExamInformationMenu(String openProcessSeq, String openSubjectSeq) {
		//배점 삭제 헤더
		view.deleteExamInformationMenuHedaer();	
		
		boolean loop = true;
		while (loop) {
			
			view.chooseDeleteExamInforamtionMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteHandwritingPointMenu(openProcessSeq, openSubjectSeq); } //필기 배점 삭제
			if (sel.equals("2")) { deletePracticalPointMenu(openProcessSeq, openSubjectSeq); } //실기 배점 삭제
			if (sel.equals("3")) { deleteAttendancePointMenu(openProcessSeq, openSubjectSeq); } //출결 배점 삭제 
			if (sel.equals("4")) { deleteExamDateMenu(openProcessSeq, openSubjectSeq); } //시험 날짜 삭제 
			if (sel.equals("5")) { deleteExamFileStatusMenu(openProcessSeq, openSubjectSeq); } //시험 문제 등록 여부 삭제 
			else { loop = false; }
		}		
	}

	//필기 배점 삭제 메뉴
	private void deleteHandwritingPointMenu(String openProcessSeq, String openSubjectSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteHandwritingPoint(openProcessSeq, openSubjectSeq);
			loop = false;}
			else { loop = false; }
		}
	}
	
	//필기 배점 삭제
	private void deleteHandwritingPoint(String openProcessSeq, String openSubjectSeq) {
		int result = edao.deleteHandwritingPoint(openProcessSeq, openSubjectSeq);
		view.deleteResult(result);
	}
	
	//실기 배점 삭제 메뉴
	private void deletePracticalPointMenu(String openProcessSeq, String openSubjectSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deletePracticalPoint(openProcessSeq, openSubjectSeq); 
			loop = false;}
			else { loop = false; }
		}
	}
	
	//실기 배점 삭제
	private void deletePracticalPoint(String openProcessSeq, String openSubjectSeq) {
		int result = edao.deletePracticalPoint(openProcessSeq, openSubjectSeq);
		view.deleteResult(result);
	}
	
	//출결 배점 삭제 메뉴
	private void deleteAttendancePointMenu(String openProcessSeq, String openSubjectSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteAttendancePoint(openProcessSeq, openSubjectSeq); 
			loop = false;}
			else { loop = false; }
		}
	}
	
	//출결 배점 삭제
	private void deleteAttendancePoint(String openProcessSeq, String openSubjectSeq) {
		int result = edao.deleteAttendancePoint(openProcessSeq, openSubjectSeq);
		view.deleteResult(result);
	}
	
	//시험 날짜 삭제 메뉴
	private void deleteExamDateMenu(String openProcessSeq, String openSubjectSeq) {
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteExamDate(openProcessSeq, openSubjectSeq);
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//시험 날짜 삭제
	private void deleteExamDate(String openProcessSeq, String openSubjectSeq) {
		int result = edao.deleteExamDate(openProcessSeq, openSubjectSeq);
		view.deleteResult(result);
	}
	
	//시험 문제 등록 여부 삭제 메뉴
	private void deleteExamFileStatusMenu(String openProcessSeq, String openSubjectSeq) {
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteExamFileStatus(openProcessSeq, openSubjectSeq);
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//시험 문제 등록 여부 삭제
	private void deleteExamFileStatus(String openProcessSeq, String openSubjectSeq) {
		int result = edao.deleteExamFileStatus(openProcessSeq, openSubjectSeq);
		view.deleteResult(result);
	}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 형준 성적 관리
	 */
	
	//성적 관리
	private void scoreManagement() {
		
		
		
		//선택한 개설 과정
		String openProcessSeq = teacherCourse();
		
		//담당 개설 과정의 과목 목록 출력
		ArrayList<ScoreDTO> openSubjects = edao.allScoreBySubject();
		view.listAllScoreBySubject(openSubjects);
				
		System.out.println("<<개설 과목 리스트에 있는 개설 과목 번호를 입력해주세요.>>");
		System.out.print("선택 : ");
		String openSubjectSeq = scan.nextLine();
		
		
		//선택한 개설 과정의 개설 과목의 교육생 성적 목록 출력
		//이름, 필기 성적, 실기 성적, 출결 성적, 성적 등록 여부 
		ArrayList<ScoreDTO> studentScores = edao.listAllScoreByOneSubject(openSubjectSeq);
		view.studentScoreList(studentScores);
		
		System.out.println("<<교육생 리스트에 있는 교육생 번호를 입력해주세요.>>");
		System.out.print("선택 : ");
		String studentSeq = scan.nextLine();
		
		//선택한 교육생의 점수 출력
		ArrayList<ScoreDTO> score = edao.score(openProcessSeq, openSubjectSeq, studentSeq);
		view.score(score);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseScoreMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { handwritingScoreManagement(openProcessSeq, openSubjectSeq, studentSeq); } //필기 점수 관리
			if (sel.equals("2")) { practicalScoreManagement(openProcessSeq, openSubjectSeq, studentSeq); } //실기 점수 관리
			if (sel.equals("3")) { attendanceScoreManagement(openProcessSeq, openSubjectSeq, studentSeq); } //출결 점수 관리
			else { loop = false; }
		}
		
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openProcessSeq
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//필기 점수 관리
	private void handwritingScoreManagement(String openProcessSeq, String openSubjectSeq, String studentSeq) {
		
		//필기 점수 관리 헤더
		view.handwritingScoreManagementHeader();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseHandwritingScoreMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addHandwritingScoreMenu(openSubjectSeq, studentSeq); } //필기 점수 등록
			if (sel.equals("2")) { updateHandwritingScoreMenu(openSubjectSeq, studentSeq); } //필기 점수 수정
			if (sel.equals("3")) { deleteHandwritingScoreMenu(openSubjectSeq, studentSeq); } //필기 점수 삭제
			else { loop = false; }
		}
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//필기 점수 등록 메뉴
	private void addHandwritingScoreMenu(String openSubjectSeq, String studentSeq) {
		
		//필기 점수 등록 헤더
		view.addHandwritingScoreMenuHeader();
		
		System.out.print("점수 : ");
		String score = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addHandwritingScore(openSubjectSeq, studentSeq, score); }
			else { loop = false; }
		}
		
		
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 * @param score
	 */
	//필기 점수 등록
	private void addHandwritingScore(String openSubjectSeq, String studentSeq, String score) {
		int result = edao.addHandwritingScore(openSubjectSeq, studentSeq, score);
		
		view.addResult(result);
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//필기 점수 수정 메뉴
	private void updateHandwritingScoreMenu(String openSubjectSeq, String studentSeq) {
		//필기 점수 수정 헤더
		view.updateHandwritingScoreMenuHeader();
		
		System.out.print("점수 : ");
		String score = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateHandwritingScore(openSubjectSeq, studentSeq, score); }
			else { loop = false; }
		}
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 * @param score
	 */
	//필기 점수 수정
	private void updateHandwritingScore(String openSubjectSeq, String studentSeq, String score) {
		int result = edao.updateHandwritingScore(openSubjectSeq, studentSeq, score);
		
		view.updateResult(result);
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//필기 점수 삭제 메뉴
	private void deleteHandwritingScoreMenu(String openSubjectSeq, String studentSeq) {
		//필기 점수 삭제 헤더
		view.deleteHandwritingScoreMenuHeader();
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteHandwritingScore(openSubjectSeq, studentSeq); }
			else { loop = false; }
		}
	}

	//필기 점수 삭제
	private void deleteHandwritingScore(String openSubjectSeq, String studentSeq) {
		int result = edao.deleteHandwritingScore(openSubjectSeq, studentSeq);
		
		view.deleteResult(result);
		
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openProcessSeq
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//실기 점수 관리
	private void practicalScoreManagement(String openProcessSeq, String openSubjectSeq, String studentSeq) {
		
		//실기 점수 관리 헤더
		view.practicalScoreManagementHeader();
		
		boolean loop = true;
		while (loop) {
			
			view.choosePracticalScoreMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addPracticalScoreMenu(openSubjectSeq, studentSeq); } //실기 점수 등록
			if (sel.equals("2")) { updatePracticalScoreMenu(openSubjectSeq, studentSeq); } //실기 점수 수정
			if (sel.equals("3")) { deletePracticalScoreMenu(openSubjectSeq, studentSeq); } //실기 점수 삭제
			else { loop = false; }
		}
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//실기 점수 등록 메뉴
	private void addPracticalScoreMenu(String openSubjectSeq, String studentSeq) {
		
		//실기 점수 등록 헤더
		view.addPracticalScoreMenuHeader();
		
		System.out.print("점수 : ");
		String score = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addPracticalScore(openSubjectSeq, studentSeq, score); }
			else { loop = false; }
		}
		
		
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 * @param score
	 */
	//실기 점수 등록
	private void addPracticalScore(String openSubjectSeq, String studentSeq, String score) {
		int result = edao.addPracticalScore(openSubjectSeq, studentSeq, score);
		
		view.addResult(result);
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//실기 점수 수정 메뉴
	private void updatePracticalScoreMenu(String openSubjectSeq, String studentSeq) {
		//실기 점수 수정 헤더
		view.updatePracticalScoreMenuHeader();
		
		System.out.print("점수 : ");
		String score = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updatePracticalScore(openSubjectSeq, studentSeq, score); }
			else { loop = false; }
		}
	}

	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 * @param score
	 */
	//실기 점수 수정
	private void updatePracticalScore(String openSubjectSeq, String studentSeq, String score) {
		int result = edao.updatePracticalScore(openSubjectSeq, studentSeq, score);
		
		view.updateResult(result);
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//실기 점수 삭제 메뉴
	private void deletePracticalScoreMenu(String openSubjectSeq, String studentSeq) {
		//실기 점수 삭제 헤더
		view.deletePracticalScoreMenuHeader();
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deletePracticalScore(openSubjectSeq, studentSeq); }
			else { loop = false; }
		}
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//실기 점수 삭제
	private void deletePracticalScore(String openSubjectSeq, String studentSeq) {
		int result = edao.deletePracticalScore(openSubjectSeq, studentSeq);
		
		view.deleteResult(result);
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openProcessSeq
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 관리
	private void attendanceScoreManagement(String openProcessSeq, String openSubjectSeq, String studentSeq) {
		
		//출결 점수 관리 헤더
		view.attendanceScoreManagementHeader();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseAttendanceScoreMenu(); 
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addAttendanceScoreMenu(openSubjectSeq, studentSeq); } //출결 점수 등록
			if (sel.equals("2")) { updateAttendanceScoreMenu(openSubjectSeq, studentSeq); } //출결 점수 수정
			if (sel.equals("3")) { deleteAttendanceScoreMenu(openSubjectSeq, studentSeq); } //출결 점수 삭제
			else { loop = false; }
		}
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 등록 메뉴
	private void addAttendanceScoreMenu(String openSubjectSeq, String studentSeq) {
		
		//출결 점수 등록 헤더
		view.addAttendanceScoreMenuHeader();
		
		System.out.print("점수 : ");
		String score = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addAttendanceScore(openSubjectSeq, studentSeq, score); }
			else { loop = false; }
		}
		
		
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 * @param score
	 */
	//출결 점수 등록
	private void addAttendanceScore(String openSubjectSeq, String studentSeq, String score) {
		int result = edao.addAttendanceScore(openSubjectSeq, studentSeq, score);
		
		view.addResult(result);
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 수정 메뉴
	private void updateAttendanceScoreMenu(String openSubjectSeq, String studentSeq) {
		//출결 점수 수정 헤더
		view.updateAttendanceScoreMenuHeader();
		
		System.out.print("점수 : ");
		String score = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateAttendanceScore(openSubjectSeq, studentSeq, score); }
			else { loop = false; }
		}
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 * @param score
	 */
	//출결 점수 수정
	private void updateAttendanceScore(String openSubjectSeq, String studentSeq, String score) {
		int result = edao.updateAttendanceScore(openSubjectSeq, studentSeq, score);
		
		view.updateResult(result);
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 삭제 메뉴
	private void deleteAttendanceScoreMenu(String openSubjectSeq, String studentSeq) {
		//출결 점수 삭제 헤더
		view.deleteAttendanceScoreMenuHeader();
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteAttendanceScore(openSubjectSeq, studentSeq); }
			else { loop = false; }
		}
	}
	
	/**
	 * 형준 교사 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 삭제
	private void deleteAttendanceScore(String openSubjectSeq, String studentSeq) {
		int result = edao.deleteAttendanceScore(openSubjectSeq, studentSeq);
		
		view.deleteResult(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//출결 조회
	private void selectAttendance() {

		ArrayList<OpenProcessDTO> process = opdao.openProcessList();
		// 출결 조회 헤더
		view.selectAttendanceHeader(process);

		// 선택한 개설 과정
		String openProcessSeq = teacherCourse();
		openPorcess = openProcessSeq; // openPorcess >> 맨위에 전역으로 하나 만들어놈
		// 특정 개설 과정의 교육생 목록
		ArrayList<StudentDTO> students = adao.student(openProcessSeq);
		view.studentList(students);

		System.out.println("<<해당 개설과정을 듣고 있는 교육생의 수강 번호를 입력해주세요.>>");
		System.out.print("번호 : ");
		String registrationSeq = scan.nextLine();
		Sequence.StudentSeq = registrationSeq;

		// 특정 개설과정의 특정 교육생의 전체 출결
		ArrayList<AttendanceDTO> attendances = adao.attendanceList(registrationSeq);
		view.attendanceList(attendances);

		boolean loop = true;
		while (loop) {

			view.chooseAttendanceMenu();

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				searchPeriod(registrationSeq);
			} // 기간 검색
			else {
				loop = false;
			}
		}
	}

	// 기간 검색
	private void searchPeriod(String registrationSeq) {

		// 기간 검색 헤더
//			view.searchPeriodHeader();

		System.out.print("시작 날짜(년, 4자리) : ");
		String syear = scan.nextLine();
		System.out.print("시작 날짜(월, 2자리) : ");
		String smonth = scan.nextLine();
		System.out.print("시작 날짜(일, 2자리) : ");
		String sday = scan.nextLine();
		String startDate = syear + "-" + smonth + "-" + sday;
		System.out.println();

		System.out.print("종료 날짜(년, 4자리) : ");
		String eyear = scan.nextLine();
		System.out.print("종료 날짜(월, 2자리) : ");
		String emonth = scan.nextLine();
		System.out.print("종료 날짜(일, 2자리) : ");
		String eday = scan.nextLine();
		String endDate = eyear + "-" + emonth + "-" + eday;

		ArrayList<AttendanceDTO> attendances = adao.searchPeriod(registrationSeq, startDate, endDate, openPorcess);
		view.attendanceList(attendances);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 형준
	 */
	//교사 평가 조회
	private void selectTeacherEvaluation() {
		
		//로그인 한 교사의 담당 개설 과정들의 평가
		String teacherSeq = Sequence.teacherSeq; 
		ArrayList<OpenProcessEvaluationDTO> openProcesses = tedao.teacherCourse(teacherSeq);
		view.evaluatedOpenProcessList(openProcesses);
		
		System.out.println("<<개설 과정 리스트에 있는 개설 과정 번호를 입력해주세요.>>");
		System.out.print("선택 : ");
		String openProcessSeq = scan.nextLine();
		
		//선택한 개설 과정의 모든 교육생의 평가 점수 출력
		ArrayList<EvaluationScoreDTO> evaluationScores = tedao.scoreList(openProcessSeq);
		view.evaluatedOpenProcess(evaluationScores);
		
	}


}

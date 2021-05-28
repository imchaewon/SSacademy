import java.util.ArrayList;
import java.util.Scanner;

/**
 * 관리자의 모든 기능을 컨트롤 할수 있는 클래스이다.
 * 기초정보관리,교사계정관리,개설과정관리,개설과목관리,교육생관리
 * 시험관리 및 성적 조회,출결 관리 및 출결 조회를 컨트롤할 수있다.
 *
 */

public class AdminController {
	
	private Scanner scan;
	private AdminView view;
	
	private ProcessDAO pdao;
	private SubjectDAO sdao;
	private BookDAO bdao;
	private LectureRoomDAO lrdao;
	private TeacherDAO tcdao;
	private StudentDAO stdao;
	private OpenProcessDAO opdao;
	private OpenSubjectDAO osdao;
	private ExamDAO edao;
	private AttendanceDAO adao;
	private TeacherEvaluationDAO tedao;
	private static String opnum;
	
	static {
		opnum = "";
	}
	
	/**
	 * 기본 생성자에서 컨트롤에 이용될 DAO들을 생성해준다. 
	 */
	public AdminController() {
		
		scan = new Scanner(System.in);
		view = new AdminView();
		
		pdao = new ProcessDAO();
		sdao = new SubjectDAO();
		bdao = new BookDAO();
		lrdao = new LectureRoomDAO();
		tcdao = new TeacherDAO();
		stdao = new StudentDAO();
		opdao = new OpenProcessDAO();
		osdao = new OpenSubjectDAO();
		edao = new ExamDAO();
		adao = new AttendanceDAO();
		tedao = new TeacherEvaluationDAO();
		
	}
	
	/**
	 * 관리자 메인 메뉴를 보여준다.
	 */
	public void start() {
		//관리자 로그인
		boolean loop = true;
		while (loop) {
			
			view.mainMenu();
			
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { basicDataManagement(); } //기초 정보 관리 - 수희
			else if (sel.equals("2")) { teacherAccountManagement(); } //교사 계정 관리 - 수희
			else if (sel.equals("3")) { studentManagement(); } //교육생 계정 관리 - 수희
			else if (sel.equals("4")) { openProcessManagement(); } //개설 과정 관리 - 수희
			else if (sel.equals("5")) { openSubjectManagement(); } //개설 과목 관리 - 민선
			else if (sel.equals("6")) { examAndScoreManagement(); } //시험 및 성적 관리
			else if (sel.equals("7")) { attendanceManagement(); } //출결 조회 및 근태 관리
			else if (sel.equals("8")) { teacherEvaluationManagement(); } //교사 평가 관리
			else { loop = false; }
		}
		
		//관리자 로그아웃
	}
	

	
	
	
	
	
	
	







	//기초 정보 관리
	private void basicDataManagement() {
		
		boolean loop = true;
		
		while (loop) {
			
			view.chooseBasicDataManagement();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { processManagement(); } //과정 관리
			else if (sel.equals("2")) { subjectManagement(); } //과목 관리
			else if (sel.equals("3")) { bookManagement(); } // 교재 관리
			else if (sel.equals("4")) { lectureRoomManagement(); } //강의실 관리
			else { loop = false; }
		}
	}

	
	
	
	
	
	
	
	
	
	//과정 관리
	private void processManagement() {
		
		//전체 과정
		ArrayList<ProcessDTO> result = pdao.processList();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseProcessMenu(result);
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addProcessMenu(); } //과정 등록
			else if (sel.equals("2")) { updateProcessMenu(); } //과정 수정
			else if (sel.equals("3")) { deleteProcessMenu(); } //과정 삭제
			else { loop = false; }
		}
	}

	//과정 등록 메뉴
	private void addProcessMenu() {
		
		//과정 등록 메뉴 헤더
		view.addProcessMenuHeader();
		
		System.out.print("과정명 : ");
		String title = scan.nextLine();
		
		System.out.print("기간 : ");
		String days = scan.nextLine();
		
		ProcessDTO pdto = new ProcessDTO(); //상자
		pdto.setTitle(title);
		pdto.setDays(days);
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addProcess(pdto); return; } //과정 등록
			else { loop = false; }
		}
		
	}

	//과정 등록
	private void addProcess(ProcessDTO pdto) {
		
		//쿼리
		int result = pdao.addProcess(pdto);
		view.addResult(result);
		
	}

	//과정 수정
	private void updateProcessMenu() {
		
		//과정 수정 헤더
		view.updateProcessMenuHeader();
		
		//전체 과정
		ArrayList<ProcessDTO> result = pdao.processList();
		view.processList(result);
		
		System.out.print("과정 번호 : ");
		String processSeq = scan.nextLine(); //과정 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateProcess();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateProcessTitle(processSeq); return; } //과정명 수정
			if (sel.equals("2")) { updateProcessDays(processSeq); return; } //기간 수정
			else { loop = false; }
		}
		
	}

	//과정명 수정
	private void updateProcessTitle(String processSeq) {
		
		//과정명 수정 헤더
		view.updateProcessTitleHeader();
		
		System.out.print("과정명 : ");
		String processTitle = scan.nextLine(); //과정명 입력
		
		//쿼리
		int result = pdao.updateProcess(processSeq, processTitle, "");
		view.updateResult(result);

	}

	//기간 수정
	private void updateProcessDays(String processSeq) {
		
		//기간 수정 헤더
		view.updateProcessDaysHeader();
		
		System.out.print("기간(일) : ");
		String processDays = scan.nextLine(); //기간(일) 입력
		
		//쿼리
		int result = pdao.updateProcess(processSeq, "", processDays);
		view.updateResult(result);

		
	}

	//과정 삭제 메뉴
	private void deleteProcessMenu() {
		
		//과정 삭제 헤더
		view.deleteProcessMenuHeader();
		
		//전체 과정
		ArrayList<ProcessDTO> result = pdao.processList();
		view.processList(result);
		
		System.out.print("과정 번호 : ");
		String processSeq = scan.nextLine(); //과정 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteProcess(processSeq); return; } //과정 삭제
			else { loop = false; }
		}
		
	}
	
	//과정 삭제
	private void deleteProcess(String processSeq) {
		
		//쿼리
		int result = pdao.deleteProcess(processSeq);
		view.deleteResult(result);
		
	}
	
	
	
	
	
	

	
	
	//과목 관리
	private void subjectManagement() {
		
		//전체 과목
		ArrayList<SubjectDTO> result = sdao.subjectList();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseSubjectMenu(result);
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addSubjectMenu(); } //과목 등록
			else if (sel.equals("2")) { updateSubjectMenu(); } //과목 수정
			else if (sel.equals("3")) { deleteSubjectMenu(); } //과목 삭제
			else { loop = false; }
		}
	}

	//과목 등록 메뉴
	private void addSubjectMenu() {
		
		view.addSubjectMenuHeader();
		
		System.out.print("과목명 : ");
		String title = scan.nextLine();
		
		System.out.print("기간 : ");
		String days = scan.nextLine();
		
		SubjectDTO sdto = new SubjectDTO(); //상자
		sdto.setTitle(title);
		sdto.setDays(days);
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addSubject(sdto); return; } //과목 등록
			else { loop = false; }
		}
		
	}

	//과목 등록
	private void addSubject(SubjectDTO sdto) {
		
		//쿼리
		int result = sdao.addSubject(sdto);
		view.addResult(result);
		
	}
	
	//과목 수정 메뉴
	private void updateSubjectMenu() {
		
		//과목 수정 메뉴 헤더
		view.updateSubjectMenuHeader();
		
		//전체 과목 출력
		ArrayList<SubjectDTO> result = sdao.subjectList();
		view.subjectList(result);
		
		System.out.print("과목 번호 : ");
		String subjectSeq = scan.nextLine(); //과목 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateSubject();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateSubjectTitle(subjectSeq); return; } //과목명 수정
			if (sel.equals("2")) { updateSubjectDays(subjectSeq); return; } //기간 수정
			else { loop = false; }
		}
		
	}

	//과목명 수정
	private void updateSubjectTitle(String subjectSeq) {
		
		//과목명 수정 헤더
		view.updateSubjectTitleHeader();
		
		System.out.print("과목명 : ");
		String subjectTitle = scan.nextLine(); //과목명 입력
		
		//쿼리
		int result = sdao.updateSubject(subjectSeq, subjectTitle, "");
		view.updateResult(result);
		
	}

	//기간 수정
	private void updateSubjectDays(String subjectSeq) {
		
		//기간 수정 헤더
		view.updateSubjectDaysHeader();
		
		System.out.print("기간(일) : ");
		String subjectDays = scan.nextLine(); //기간(일) 입력
		
		//쿼리
		int result = sdao.updateSubject(subjectSeq, "", subjectDays);
		view.updateResult(result);
		
	}

	
	//과목 삭제 메뉴
	private void deleteSubjectMenu() {
		
		//과목 삭제 헤더
		view.deleteSubjectMenuHeader();
		
		//전체 과목
		ArrayList<SubjectDTO> result = sdao.subjectList();
		view.subjectList(result);
		
		System.out.print("과목 번호 : ");
		String subjectSeq = scan.nextLine(); //과목 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteSubject(subjectSeq); return; } //과목 삭제
			else { loop = false; }
		}
		
	}
	
	//과목 삭제
	private void deleteSubject(String subjectSeq) {
		
		//쿼리
		int result = sdao.deleteSubject(subjectSeq);
		view.deleteResult(result);
			
	}
	
	
	
	
	
	
	
	
	
	
	


	//교재 관리
	private void bookManagement() {
		
		//전체 교재
		ArrayList<BookDTO> result =  bdao.bookList();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseBookMenu(result);
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addBookMenu(); } //교재 등록
			else if (sel.equals("2")) { updateBookMenu(); } //교재 수정
			else if (sel.equals("3")) { deleteBookMenu(); } //교재 삭제
			else { loop = false; }
		}
	}

	//교재 등록 메뉴
	private void addBookMenu() {
		
		view.addBookMenuHeader();
		
		System.out.print("교재명 : ");
		String title = scan.nextLine();
		
		System.out.print("출판사 : ");
		String publisher = scan.nextLine();
		
		BookDTO bdto = new BookDTO(); //상자
		bdto.setTitle(title);
		bdto.setPublisher(publisher);
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addBook(bdto); return; } //교재 등록
			else { loop = false; }
		}
		
	}

	//교재 등록
	private void addBook(BookDTO bdto) {
		
		//쿼리
		int result = bdao.addBook(bdto);
		view.addResult(result);
		
	}
	
	//교재 수정 메뉴
	private void updateBookMenu() {
		
		//교재 수정 헤더
		view.updateBookMenuHeader();
		
		//전체 교재 출력
		ArrayList<BookDTO> result = bdao.bookList();
		view.allBookList(result);
		
		System.out.print("교재 번호 : ");
		String bookSeq = scan.nextLine(); //교재 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateBook();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateBookTitle(bookSeq); return;} //교재명 수정
			if (sel.equals("2")) { updateBookPublisher(bookSeq); return;} //출판사 수정
			else { loop = false; }
		}
		
	}

	//교재명 수정
	private void updateBookTitle(String bookSeq) {
		
		//교재명 수정 헤더
		view.updateBookTitleHeader();
		
		System.out.print("교재명 : ");
		String bookTitle = scan.nextLine(); //교재명 입력
		
		//쿼리
		int result = bdao.updateBook(bookSeq, bookTitle, "");
		view.updateResult(result);

	}

	//출판사 수정
	private void updateBookPublisher(String bookSeq) {
		
		//출판사 수정 헤더
		view.updateBookPublisherHeader();
		
		System.out.print("기간(일) : ");
		String bookPublisher = scan.nextLine(); //기간(일) 입력
		
		//쿼리
		int result = bdao.updateBook(bookSeq, "", bookPublisher);
		view.updateResult(result);

	}

	
	//교재 삭제 메뉴
	private void deleteBookMenu() {
		
		//교재 삭제 헤더
		view.deleteBookMenuHeader();
		
		//전체 교재
		ArrayList<BookDTO> result = bdao.bookList();
		view.allBookList(result);
		
		System.out.print("교재 번호 : ");
		String bookSeq = scan.nextLine(); //교재 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteBook(bookSeq); return; } //교재 삭제
			else { loop = false; }
		}
		
	}
	
	//교재 삭제
	private void deleteBook(String bookSeq) {
		
		//쿼리
		
		int result = bdao.deleteBook(bookSeq);
		view.deleteResult(result);
			
	}
	
	
	
	
	
	
	
	
	
	
	
	

	//강의실 관리
	private void lectureRoomManagement() {
		
		//전체 강의실
		ArrayList<LectureRoomDTO> result =  lrdao.allLectureRoomList();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseLectureRoomMenu(result);
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addLectureRoomMenu(); } //강의실 등록
			else if (sel.equals("2")) { updateLectureRoomMenu(); } //강의실 수정
			else if (sel.equals("3")) { deleteLectureRoomMenu(); } //강의실 삭제
			else { loop = false; }
		}
	}

	//강의실 등록 메뉴
	private void addLectureRoomMenu() {
		
		//강의실 등록 메뉴 헤더
		view.addLectureRoomMenuHeader();
		
		System.out.print("강의실명 : ");
		String name = scan.nextLine();
		
		System.out.print("수용인원 : ");
		String acceptablePersonnel = scan.nextLine();
		
		LectureRoomDTO lrdto = new LectureRoomDTO(); //상자
		lrdto.setName(name);
		lrdto.setAcceptablePersonnel(acceptablePersonnel);
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addLectureRoom(lrdto); return; } //강의실 등록
			else { loop = false; }
		}
		
	}

	//강의실 등록
	private void addLectureRoom(LectureRoomDTO lrdto) {
		
		//쿼리
		int result = lrdao.addLecture(lrdto);
		view.addResult(result);
		
	}
	
	//강의실 수정 메뉴
	private void updateLectureRoomMenu() {
		
		//강의실 수정 헤더
		view.updateLectureRoomMenuHeader();
		
		//전체 강의실 출력
		ArrayList<LectureRoomDTO> result = lrdao.allLectureRoomList();
		view.lectureRoomList(result);
		
		System.out.print("강의실 번호 : ");
		String lectureRoomSeq = scan.nextLine(); //강의실 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateLectureRoom();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateLectureRoomTitle(lectureRoomSeq); return; } //강의실명 수정
			if (sel.equals("2")) { updateLectureRoomAcceptablePersonnel(lectureRoomSeq); return; } //수용인원수 수정
			else { loop = false; }
		}
		
	}

	//강의실명 수정
	private void updateLectureRoomTitle(String lectureRoomSeq) {		
		
		//강의실명 수정 헤더
		view.updateLectureRoomNameHeader();
		
		System.out.print("강의실명 : ");
		String lectureRoomName = scan.nextLine(); //강의실명 입력
		
		//쿼리
		int result = lrdao.updateLectureRoom(lectureRoomSeq, lectureRoomName, "");
		view.updateResult(result);

	}

	//수용인원수 수정
	private void updateLectureRoomAcceptablePersonnel(String lectureRoomSeq) {
		
		//수용인원수 수정 헤더
		view.updateLectureRoomAcceptablePersonnelHeader();
		
		System.out.print("수용인원 : ");
		String lectureRoomAcceptablePersonnel = scan.nextLine(); //수용인원수 입력
		
		//쿼리
		int result = lrdao.updateLectureRoom(lectureRoomSeq, "", lectureRoomAcceptablePersonnel);
		view.updateResult(result);

	}

	
	//강의실 삭제 메뉴
	private void deleteLectureRoomMenu() {
		
		//강의실 삭제 헤더
		view.deleteLectureRoomMenuHeader();
		
		//전체 교재
		ArrayList<LectureRoomDTO> result = lrdao.allLectureRoomList();
		view.lectureRoomList(result);
		
		System.out.print("강의실 번호 : ");
		String lectureRoomSeq = scan.nextLine(); //강의실 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteLectureRoom(lectureRoomSeq); return; } //강의실 삭제
			else { loop = false; }
		}
		
	}
	
	//강의실 삭제
	private void deleteLectureRoom(String lectureRoomSeq) {
		
		//쿼리
		int result = lrdao.deleteLectureRoom(lectureRoomSeq);
		view.deleteResult(result);
			
	}

	
	
	
	
	
	


	//교사 계정 관리
	private void teacherAccountManagement() {
		
		//전체 교사
		ArrayList<TeacherDTO> result =  tcdao.allTeacherList();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseTeacherMenu(result);
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addTeacherMenu(); } //교사 등록
			else if (sel.equals("2")) { updateTeacherMenu(); } //교사 수정
			else if (sel.equals("3")) { deleteTeacherMenu(); } //교사 삭제
			else if (sel.equals("4")) { selectSpecificTeacherMenu(); } //특정 교사 조회
			else { loop = false; }
		}
	}

	//교사 등록 메뉴
	private void addTeacherMenu() {
		
		view.addTeacherMenuHeader();
		
		System.out.print("교사명 : ");
		String name = scan.nextLine();
		
		System.out.print("주민번호 뒷자리 : ");
		String regidentRegistrationNumber = scan.nextLine();

		System.out.print("전화번호 : ");
		String phone = scan.nextLine();
		
		TeacherDTO tdto = new TeacherDTO(); //상자
		tdto.setName(name);
		tdto.setRegidentRegistrationNumber(regidentRegistrationNumber);
		tdto.setPhone(phone);
		

		//전체 과목 출력
		ArrayList<SubjectDTO> subjects = sdao.subjectList();
		view.subjectList(subjects);
		
		System.out.println("<<과목리스트 중 해당 교사가 강의 가능한 과목을 입력하세요.");
		System.out.print("강의 가능 과목(,로 다중 선택) : ");
		String[] teacherAvailableSubjects = scan.nextLine().split(",");
		
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addTeacher(tdto, teacherAvailableSubjects); return; } //교사 등록
			else { loop = false; }
		}
		
	}

	//교사 등록
	private void addTeacher(TeacherDTO tadto, String[] teacherAvailableSubjects) {
		
		//쿼리
		int result = tcdao.addTeacher(tadto, teacherAvailableSubjects);
		view.addResult(result);
	}
	
	//교사 정보 수정 메뉴
	private void updateTeacherMenu() {
		
		//교사 수정 헤더
		view.updateTeacherMenuHeader();
		
		//전체 교사 출력
		ArrayList<TeacherDTO> result = tcdao.allTeacherList();
		view.teacherList(result);
		
		System.out.print("교사 번호 : ");
		String teacherSeq = scan.nextLine(); //교사 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateTeacher();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateTeacherName(teacherSeq); return; } //교사명 수정
			if (sel.equals("2")) { updateTeacherRegidentRegistrationNumber(teacherSeq); return; } //주민번호 뒷자리 수정
			if (sel.equals("3")) { updateTeacherPhone(teacherSeq); return; } //전화번호 수정
			if (sel.equals("4")) { updateTeacherAvailable(teacherSeq); return; } //강의가능과목 수정
			else { loop = false; }
		}
		
	}

	//교사명 수정
	private void updateTeacherName(String teacherSeq) {
		
		//교사명 수정 헤더
		view.updateTeacherNameHeader();
		
		System.out.print("교사명 : ");
		String teacherName = scan.nextLine(); //교사명 입력
		
		//쿼리	
		int result = tcdao.updateTeacher(teacherSeq, teacherName, "", "");
		view.updateResult(result);

	}

	//교사 주민번호 뒷자리 수정
	private void updateTeacherRegidentRegistrationNumber(String teacherSeq) {
		
		//교사 주민번호 뒷자리 수정 헤더
		view.updateTeacherRegidentRegistrationNumberHeader();
		
		System.out.print("주민번호 뒷자리 : ");
		String teacherRegidentRegistrationNumber = scan.nextLine(); //주민번호 뒷자리 입력
		
		//쿼리
		int result = tcdao.updateTeacher(teacherSeq, "", teacherRegidentRegistrationNumber, "");
		view.updateResult(result);
	}

	//교사 - 전화번호 수정
	private void updateTeacherPhone(String teacherSeq) {
		
		//전화번호 수정 헤더
		view.updateTeacherPhoneHeader();
		
		System.out.print("전화번호 : ");
		String teacherPhone = scan.nextLine(); //전화번호 입력
		
		//쿼리		
		int result = tcdao.updateTeacher(teacherSeq, "", "", teacherPhone);
		view.updateResult(result);
	}

	//교사 - 강의 가능 과목 수정
	//1. 기존 강의 가능 과목 수정
	//2. 강의 가능 과목 추가 *** 기능 넣기
	private void updateTeacherAvailable(String teacherSeq) {
		
		//강의 가능 과목 수정 헤더
		view.updateTeacherAvailableSubjectHeader();
		
		//현재 교사가 담당하고 있는 담당 과목 목록 띄우기
		ArrayList<TeacherAvailableSubjectDTO> availableSubjects = tcdao.availableSubjectList(teacherSeq);
		view.teacherAvailableSubjectList(availableSubjects);
		
		//이전 과목번호를 선택하게 하고 
		System.out.print("바꿀 과목 번호 : ");
		String subject1 = scan.nextLine();	//이전 강의 가능 과목 입력
		
		//과목 목록 띄우기
		ArrayList<SubjectDTO> subjects = sdao.subjectList();
		view.subjectList(subjects);
		
		//새 과목 번호도 입력받음
		System.out.print("새 과목 번호 : ");
		String subject2 = scan.nextLine(); //새 강의 가능 과목 입력
		
		//쿼리
		int result = tcdao.updateAvailableSubject(teacherSeq, subject1, subject2);
		view.updateResult(result);
	}
	

	
	//교사 삭제 메뉴
	private void deleteTeacherMenu() {
		
		//교재 삭제 헤더
		view.deleteTeacherMenuHeader();
		
		//전체 교사
		ArrayList<TeacherDTO> result = tcdao.allTeacherList();
		view.teacherList(result);
		
		System.out.print("교사 번호 : ");
		String teacherSeq = scan.nextLine(); //교사 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteTeacher(teacherSeq); return; } //교사 삭제
			else { loop = false; }
		}
		
	}
	
	//교사 삭제
	private void deleteTeacher(String teacherSeq) {
		
		//쿼리
		int result = tcdao.deleteTeacher(teacherSeq);
		view.deleteResult(result);
			
	}

	//특정 교사 조회 메뉴
	private void selectSpecificTeacherMenu() {
		
		//특정 교사 조회 메뉴 헤더
		view.selectSpecificTeacherMenuHeader();
		
		System.out.print("교사번호(숫자) : ");
		String teacherSeq = scan.nextLine();
		
		ArrayList<TeacherCourseDTO> teacherCourses = tcdao.teacherCourseList(teacherSeq);
		view.teacherCourseList(teacherCourses);
		
	}
	
	
	
	
	
	
	
	

	
	
	
	

	//교육생 계정 관리
	private void studentManagement() {

		//전체 교육생
		ArrayList<StudentDTO> result = stdao.studentList();

		boolean loop = true;
		while (loop) {

			view.chooseStudentMenu(result);

			String sel = scan.nextLine();
			if (sel.equals("1")) { addStudentMenu(); } // 교육생 등록
			else if (sel.equals("2")) { updateStudentMenu(); } // 교육생 정보 수정
			else if (sel.equals("3")) {	deleteStudentMenu(); } // 교육생 삭제
			else if (sel.equals("4")) {	selectSpecificStudentMenu(); } // 특정 교육생 조회
			else { loop = false; }
		}
	}

	//교육생 등록 메뉴
	private void addStudentMenu() {

		view.addStudentMenuHeader();

		System.out.print("이름 : ");
		String name = scan.nextLine();

		System.out.print("주민번호 뒷자리 : ");
		String regidentRegistrationNumber = scan.nextLine();

		System.out.print("전화번호 : ");
		String phone = scan.nextLine();

		StudentDTO stdto = new StudentDTO(); // 상자
		stdto.setName(name);
		stdto.setRegidentRegistrationNumber(regidentRegistrationNumber);
		stdto.setPhone(phone);

		boolean loop = true;
		while (loop) {

			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addStudent(stdto); return; } // 교육생 등록
			else {
				loop = false;
			}
		}

	}

	//교육생 등록
	private void addStudent(StudentDTO stdto) {

		// 쿼리
		int result = stdao.addStudent(stdto);
		view.addResult(result);
	}

	//교육생 정보 수정 메뉴
	private void updateStudentMenu() {

		//교육생 수정 헤더
		view.updateStudentMenuHeader();

		//전체 교육생 출력
		ArrayList<StudentDTO> result = stdao.studentList();
		view.allStudentList(result);

		System.out.print("교육생 번호 : ");
		String studentSeq = scan.nextLine(); //교육생 번호 선택

		boolean loop = true;
		while (loop) {

			view.chooseUpdateStudent();

			String sel = scan.nextLine();
			if (sel.equals("1")) { updateStudentName(studentSeq); return; } // 이름 수정
			if (sel.equals("2")) { updateStudentRegidentRegistrationNumber(studentSeq); return; } // 주민번호 뒷자리 수정
			if (sel.equals("3")) { updateStudentPhone(studentSeq); return; } // 전화번호 수정
			if (sel.equals("4")) { updateStudentRegdate(studentSeq); return; } // 등록일 수정
			else { loop = false; }
		}

	}

	//교육생명 수정
	private void updateStudentName(String studentSeq) {

		//교육생명 수정 헤더
		view.updateStudentNameHeader();

		System.out.print("이름 : ");
		String studentName = scan.nextLine(); //교육생명 입력

		// 쿼리
		int result = stdao.updateStudent(studentSeq, studentName, "", "");
		view.updateResult(result);
	}

	//교육생 주민번호 뒷자리 수정
	private void updateStudentRegidentRegistrationNumber(String studentSeq) {
		// 교재명 수정 헤더
		view.updateStudentRegidentRegistrationNumberHeader();

		System.out.print("주민번호 뒷자리 : ");
		String studentRegidentRegistrationNumber = scan.nextLine(); // 주민번호 뒷자리 입력

		// 쿼리
		int result = stdao.updateStudent(studentSeq, "", studentRegidentRegistrationNumber, "");
		view.updateResult(result);

	}

	//교육생 전화번호 수정
	private void updateStudentPhone(String studentSeq) {
		// 전화번호 수정 헤더
		view.updateStudentPhoneHeader();

		System.out.print("전화번호 : ");
		String studentPhone = scan.nextLine(); // 전화번호 입력

		// 쿼리
		int result = stdao.updateStudent(studentSeq, "", "", studentPhone);
		view.updateResult(result);

	}

	//교육생 등록일 수정
	private void updateStudentRegdate(String studentSeq) {
		// 강의 가능 과목 수정 헤더
		view.updateStudentRegdateHeader();

		System.out.print("등록날짜(년, 4자리) : ");
		String year = scan.nextLine(); //년

		System.out.print("등록날짜(월, 2자리) : ");
		String month = scan.nextLine(); //월
		
		System.out.print("등록날짜(일, 2자리) : ");
		String day = scan.nextLine(); //일
		
		String studentRegdate = year + "-"  + month + "-" + day;

		// 쿼리
		int result = stdao.updateStudentRegdate(studentSeq, studentRegdate);
		view.updateResult(result);
	}

	//교육생 삭제 메뉴
	private void deleteStudentMenu() {

		//교육생 삭제 헤더
		view.deleteStudentMenuHeader();

		// 전체 교육생
		ArrayList<StudentDTO> result = stdao.studentList();
		view.allStudentList(result);

		System.out.print("교육생 번호 : ");
		String studentSeq = scan.nextLine(); //교육생 번호 선택

		boolean loop = true;
		while (loop) {

			view.deleteCheckMenu();

			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteStudent(studentSeq); return; } //교육생 삭제
			else {
				loop = false;
			}
		}

	}

	//교육생 삭제
	private void deleteStudent(String studentSeq) {

		// 쿼리
		int result = stdao.deleteStudent(studentSeq);
		view.deleteResult(result);

	}

	//특정 교육생 조회
	private void selectSpecificStudentMenu() {

		//특정 교육생 조회 헤더
		view.selectSpecificStudentMenuHeader();

		System.out.print("교육생번호(숫자) : ");
		String studentSeq = scan.nextLine();
				
		ArrayList<StudentCourseDTO> list = stdao.studentCourseList(studentSeq);
		view.studentCourseList(list);
		
		System.out.print("수강 번호 : ");
		String registrationSeq = scan.nextLine(); //수강 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateFinishStatusOrDate();

			String sel = scan.nextLine();
			if (sel.equals("1")) { updateFinishStatus(registrationSeq); return; } //수료여부 수정
			if (sel.equals("2")) { updateFinishDate(registrationSeq); return; } //수료날짜 수정
			else {
				loop = false;
			}
		}
	}
	
	//교육생의 개설과정 수료 여부 수정
	private void updateFinishStatus(String registrationSeq) {
		
		// 수료 및 중도탈락 여부 수정 헤더
		view.updateStudentFinishStatusHeader();

		System.out.print("수료 및 중도탈락 여부 : ");
		String finishStatus = scan.nextLine(); //수료 및 중도탈락 여부

		// 쿼리
		int result = stdao.updateStudentFinishStatus(registrationSeq, finishStatus);
		view.updateResult(result);
	}

	//교육생의 개설과정 수료 날짜 수정
	private void updateFinishDate(String registrationSeq) {
		// 수료 및 중도탈락 날짜 수정 헤더
		view.updateStudentFinishDateHeader();

		System.out.print("년(4자리) : ");
		String year = scan.nextLine(); 

		System.out.print("월(2자리) : ");
		String month = scan.nextLine(); 
		
		System.out.print("일(2자리) : ");
		String day = scan.nextLine(); 
		String finishDate = year + "-" + month + "-" + day;

		// 쿼리
		int result = stdao.updateStudentFinishDate(registrationSeq, finishDate);
		view.updateResult(result);
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	//개설 과정 관리
	private void openProcessManagement() {
		
		//전체 개설 과정
		ArrayList<OpenProcessDTO> result =  opdao.openProcessList();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseOpenProcessMenu(result);
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addOpenProcessMenu(); } //개설 과정 등록
			else if (sel.equals("2")) { updateOpenProcessMenu(); } //개설 과정 정보 수정
			else if (sel.equals("3")) { deleteOpenProcessMenu(); } //개설 과정 삭제
			else if (sel.equals("4")) { selectSpecificOpenProcessMenu(); } //특정 개설 과정 조회
			else { loop = false; }
		}
	}

	//개설 과정 등록 메뉴
	private void addOpenProcessMenu() {
		
		String title;						//과정명
		String startDate;					//과정시작날짜
		String endDate;						//과정종료날짜
		String lectureRoom; 				//강의실
		String personnel = "0";				//등록인원수
		String status = "개설예정"; 		//상태
		String existStatus = "미등록";		//개설과목등록여부
		
		
		view.addOpenProcessMenuHeader();
		
		
		//과정명
		//전체 과정 출력
		ArrayList<ProcessDTO> processes = pdao.processList();
		view.processList(processes);
		
		System.out.print("과정(번호) : ");
		title = scan.nextLine();
		
		//과정 시작 날짜
		System.out.print("과정 시작 날짜(년,4자리) : ");
		String syear = scan.nextLine();
		System.out.print("과정 시작 날짜(월,2자리) : ");
		String smonth = scan.nextLine();
		System.out.print("과정 시작 날짜(일,2자리) : ");
		String sday = scan.nextLine();
		startDate = syear + "-" + smonth + "-" + sday;
		
		System.out.println();
		
		//과정 종료 날짜
		System.out.print("과정 종료 날짜(년,4자리) : ");
		String eyear = scan.nextLine();
		System.out.print("과정 종료 날짜(월,2자리) : ");
		String emonth = scan.nextLine();
		System.out.print("과정 종료 날짜(일,2자리) : ");
		String eday = scan.nextLine();
		endDate = eyear + emonth + eday;
		
		//강의실
		//전체 과정 출력
		ArrayList<LectureRoomDTO> lectureRooms = lrdao.allLectureRoomList();
		view.lectureRoomList(lectureRooms);
		
		System.out.print("강의실(번호) : ");
		lectureRoom = scan.nextLine();
		
		

		//교사
		//전체 교사 출력
		//개설과정에 담당 교사 등록 : 쿼리 따로
		ArrayList<TeacherDTO> teachers = tcdao.allTeacherList();
		view.teacherList(teachers);
		
		System.out.print("교사(번호) : ");
		String teacherSeq = scan.nextLine();
		
		OpenProcessDTO opdto = new OpenProcessDTO(); //상자
		opdto.setTitle(title);
		opdto.setLectureRoom(lectureRoom);
		opdto.setPersonnel(personnel);
		opdto.setStartDate(startDate);
		opdto.setEndDate(endDate);
		opdto.setStatus(status);
		opdto.setExistStatus(existStatus);
		
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addOpenProcess(opdto, teacherSeq); } //개설 과정 등록
			else { loop = false; }
		}
		
	}

	//개설 과정 등록
	private void addOpenProcess(OpenProcessDTO opdto, String teacherSeq) {
		
		//쿼리
		//만든 개설 과정의 seq 가져와서 교사가 담당하게 만들기
		int result = opdao.addOpenProcess(opdto, teacherSeq);
		view.addResult(result);
		
	}
	

	//개설 과정 정보 수정 메뉴
	private void updateOpenProcessMenu() {
		
		//개설 과정 정보 수정 헤더
		view.updateOpenProcessMenuHeader();
		
		System.out.print("개설 과정 번호 : ");
		String openProcessSeq = scan.nextLine(); //개설 과정 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.chooseUpdateOpenProcess();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateOpenProcessStartDate(openProcessSeq); return; } //과정 시작 날짜 수정
			if (sel.equals("2")) { updateOpenProcessEndDate(openProcessSeq); return; } //과정 종료 날짜 수정
			if (sel.equals("3")) { updateOpenProcessLectureRoom(openProcessSeq); return; } //강의실 수정
			if (sel.equals("4")) { updateOpenProcessTeacher(openProcessSeq); return; } //담당 교사 수정
			if (sel.equals("5")) { updateOpenProcessFinishDate(openProcessSeq); return; } //수료 날짜 수정
			else { loop = false; }
		}
		
	}




	//과정 시작 날짜 수정
	private void updateOpenProcessStartDate(String openProcessSeq) {
		
		//과정 시작 날짜 수정 헤더
		view.updateOpenProcessStartDateHeader();
		
		System.out.print("과정 시작 날짜 (년, 4자리) : ");
		String year = scan.nextLine(); 
		System.out.print("과정 시작 날짜 (월, 2자리) : ");
		String month = scan.nextLine();
		System.out.print("과정 시작 날짜 (일, 2자리) : ");
		String day = scan.nextLine();
		String startDate = year + "-" + month + "-" + day;
		
		//쿼리		
		int result = opdao.updateOpenProcess(openProcessSeq, startDate, "", "");
		view.updateResult(result);
	}
	
	//과정 종료 날짜 수정
	private void updateOpenProcessEndDate(String openProcessSeq) {
		
		//과정 종료 날짜 수정 헤더
		view.updateOpenProcessEndDateHeader();
		
		System.out.print("과정 종료 날짜 (년, 4자리) : ");
		String year = scan.nextLine(); 
		System.out.print("과정 종료 날짜 (월, 2자리) : ");
		String month = scan.nextLine();
		System.out.print("과정 종료 날짜 (일, 2자리) : ");
		String day = scan.nextLine();
		String endDate = year + "-" + month + "-" + day;
		
		//쿼리
		int result = opdao.updateOpenProcess(openProcessSeq, "", endDate, "");
		view.updateResult(result);
	}


	//강의실 수정
	private void updateOpenProcessLectureRoom(String openProcessSeq) {
		
		//강의실 수정 헤더
		view.updateOpenProcessLectureRoomHeader();
		
		//전체 강의실 출력
		ArrayList<LectureRoomDTO> lectureRooms = lrdao.allLectureRoomList();
		view.lectureRoomList(lectureRooms);
		
		
		System.out.println("<<강의실 리스트에 있는 강의실 번호를 입력해주세요.>>");
		System.out.print("강의실 : ");
		String lectureRoom = scan.nextLine(); //강의실 번호 입력
		
		//쿼리
		int result = opdao.updateOpenProcess(openProcessSeq, "", "", lectureRoom);
		view.updateResult(result);
		
	}
	
	//과정 수료 상태 수정
	private void updateOpenProcessFinishDate(String openProcessSeq) {
		
		//수료 상태 수정 헤더
		view.updateOpenProcessFinishDateHeader();
		
		System.out.print("수료 날짜 (년, 4자리) : ");
		String year = scan.nextLine(); 
		System.out.print("수료 날짜 (월, 2자리) : ");
		String month = scan.nextLine(); 
		System.out.print("수료 날짜 (월, 2자리) : ");
		String day = scan.nextLine(); 
		String finishDate = year + "-" + month + "-" + day;
		
		//쿼리
		int result = opdao.updateFinishDate(openProcessSeq, finishDate);
		view.updateResult(result);
						
	}
	
	//담당 교사 수정
	private void updateOpenProcessTeacher(String openProcessSeq) {
		
		//담당 교사 수정 헤더
		view.updateOpenProcessTeacherHeader();
		
		//전체 교사 출력
		ArrayList<TeacherDTO> teachers = tcdao.allTeacherList();
		view.teacherList(teachers);
		
		System.out.println("<<교사 리스트에 있는 교사 번호를 입력해주세요.>>");
		System.out.print("교사 : ");
		String teacherSeq = scan.nextLine();
		
		//쿼리
		int result = opdao.updateTeacherCourse(openProcessSeq, teacherSeq);
		view.updateResult(result);
		
	}

	
	//개설 과정 삭제 메뉴
	private void deleteOpenProcessMenu() {
		
		//개설 과정 삭제 헤더
		view.deleteOpenProcessMenuHeader();
		
		System.out.print("개설 과정 번호 : ");
		String openProcessSeq = scan.nextLine(); //개설 과정 번호 선택
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteOpenProcess(openProcessSeq); return; } //개설 과정 번호
			else { loop = false; }
		}
		
	}
	
	//개설 과정 삭제
	private void deleteOpenProcess(String openProcessSeq) {
		
		//쿼리
		int result = opdao.deleteOpenProcess(openProcessSeq);
		view.deleteResult(result);
			
	}

	//특정 개설과정 조회
	private void selectSpecificOpenProcessMenu() {
		
		//특정 개설 과정 조회 헤더
		view.selectSpecificOpenProcessMenuHeader();
		
		System.out.print("개설 과정 번호(숫자) : ");
		String openProcessSeq = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseSelectMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { selectOpenSubject(openProcessSeq); } //개설 과목 조회
			if (sel.equals("2")) { selectStudent(openProcessSeq); } //교육생 조회
			else { loop = false; }
		}
	}

	//특정 개설 과정의 과목 조회
	private void selectOpenSubject(String openProcessSeq) {
		
		ArrayList<OpenSubjectDTO> openSubjects = opdao.openSubjectList(openProcessSeq);
		view.openSubjectList(openSubjects);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseOpenSubjectMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addOpenSubjectMenu(openProcessSeq); } //개설 과목 추가
			if (sel.equals("2")) { deleteOpenSubjectMenu(openProcessSeq); } //개설 과목 삭제
			else { loop = false; }
		}
	}

	//특정 개설 과정에 개설 과목 추가 메뉴
	private void addOpenSubjectMenu(String openProcessSeq) {
		
		//개설 과정이 지정되지 않은 개설 과목 목록 출력
		ArrayList<OpenSubjectDTO> openSubjects = opdao.openSubjectListOfNull();
		view.openSubjectList(openSubjects);
		
		//개설 과목 번호 입력
		System.out.print("개설 과목 번호(다중 선택 가능 ex]1,2,3) : ");
		String[] openSubjectSeqs = scan.nextLine().split(",");
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addOpenSubject(openProcessSeq, openSubjectSeqs); return;} 
			else { loop = false; }
		}
		
	}
	
	//특정 개설 과정에 개설 과목 추가
	private void addOpenSubject(String openProcessSeq, String[] openSubjectSeqs) {
		
		//쿼리
		int result = opdao.addOpenSubjectToOP(openProcessSeq, openSubjectSeqs);
		view.addResult(result);
		
	}

	//특정 개설 과정에서 개설 과목 삭제 메뉴
	private void deleteOpenSubjectMenu(String openProcessSeq) {
		
		//개설 과목 번호 입력
		System.out.print("개설 과목 번호 : ");
		String openSubjectSeq = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteOpenSubject(openProcessSeq, openSubjectSeq); return;} //개설 과정 번호
			else { loop = false; }
		}
	}

	//특정 개설 과정에서 개설 과목 삭제
	private void deleteOpenSubject(String openProcessSeq, String openSubjectSeq) {
		
		//쿼리
		int result = opdao.deleteOpenSubject(openProcessSeq, openSubjectSeq);
		view.deleteResult(result);
	}

	//특정 개설 과정의 교육생 조회
	private void selectStudent(String openProcessSeq) {
		
		//특정 개설 과정의 교육생 출력
		ArrayList<StudentDTO> students = opdao.studentList(openProcessSeq);
		view.studentList(students);
		
		boolean loop = true;
		while (loop) {
			
			view.chooseStudentMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addStudentMenu(openProcessSeq); } //교육생 추가
			if (sel.equals("2")) { deleteStudentMenu(openProcessSeq); } //교육생 삭제
			else { loop = false; }
		}
	}
	
	//특정 개설 과정에 교육생 추가 메뉴
	private void addStudentMenu(String openProcessSeq) {
		
		//교육생 목록 출력
		ArrayList<StudentDTO> students = opdao.studentListNotRC();
		view.studentListNotRC(students);
		
		//교육생 번호 입력
		System.out.print("교육생 번호(다중 선택 가능 ex]1,2,3) : ");
		String[] studentSeqs = scan.nextLine().split(",");
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addStudent(openProcessSeq, studentSeqs); return; }
			else { loop = false; }
		}		
	}
	
	//특정 개설 과정에 교육생 추가
	private void addStudent(String openProcessSeq, String[] studentSeqs) {
		
		//쿼리
		int result = opdao.addStudent(openProcessSeq, studentSeqs);
		view.addResult(result);

	}

	//특정 개설 과정에서 교육생 삭제 메뉴
	private void deleteStudentMenu(String openProcessSeq) {
		
		//교육생 번호 입력
		System.out.print("교육생 번호 : ");
		String studentSeq = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteStudent(openProcessSeq, studentSeq); return; } 
			else { loop = false; }
		}
		
	}

	//특정 개설 과정에서 교육생 삭제
	private void deleteStudent(String openProcessSeq, String studentSeq) {
		
		//쿼리
		int result = opdao.deleteStudent(openProcessSeq, studentSeq);
		view.deleteResult(result);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	// 개설 과목 관리
	private void openSubjectManagement() {

		// 전체 개설 과목
		ArrayList<OpenSubjectDTO> result = osdao.openSubjectList();

		
		boolean loop = true;
		while (loop) {

			view.chooseOpenSubjectMenu(result);

			String sel = scan.nextLine();
			if (sel.equals("1")) { addOpenSubjectMenu(); } //개설 과목 등록
			else if (sel.equals("2")) { updateOpenSubjectMenu(); } //개설 과목 수정
			else if (sel.equals("3")) { deleteOpenSubjectMenu(); } //개설 과목 삭제
			else { loop = false; }
		}
	}
   
	// 개설 과목 등록 메뉴
	private void addOpenSubjectMenu() {

		// 개설 과목 등록 헤더
		view.addOpenSubjectMenuHeader();

		// 과목
		// 전체 과목 목록 출력
		ArrayList<SubjectDTO> subjects = sdao.subjectList();
		view.subjectList(subjects);

		System.out.println("<<과목 리스트에 있는 과목 번호를 입력해주세요.>>");
		System.out.print("과목 : ");
		String subjectSeq = scan.nextLine(); // 과목선택

		// 과목 시작 날짜
		System.out.print("과목 시작 날짜(년, 4자리) : ");
		String syear = scan.nextLine();
		System.out.print("과목 시작 날짜(월, 2자리) : ");
		String smonth = scan.nextLine();
		System.out.print("과목 시작 날짜(일, 2자리) : ");
		String sday = scan.nextLine();
		String startDate = syear + "-" + smonth + "-" + sday;

		// 과목 종료 날짜
		System.out.print("과목 종료 날짜(년, 4자리) : ");
		String eyear = scan.nextLine();
		System.out.print("과목 종료 날짜(월, 2자리) : ");
		String emonth = scan.nextLine();
		System.out.print("과목 종료 날짜(일, 2자리) : ");
		String eday = scan.nextLine();
		String endDate = eyear + "-" + emonth + "-" + eday;

		// 교재
		// 교재 목록 출력
		ArrayList<BookDTO> books = bdao.bookList();
		view.allBookList(books);

		System.out.println("<<교재 리스트에 있는 교재 번호를 입력해주세요.>>");
		System.out.print("교재 : ");
		String bookSeq = scan.nextLine(); // 교재선택

		OpenSubjectDTO osdto = new OpenSubjectDTO();
		osdto.setTitle(subjectSeq);
		osdto.setBook(bookSeq);
		osdto.setStartDate(startDate);
		osdto.setEndDate(endDate);

		boolean loop = true;
		while (loop) {

			view.addCheckMenu();

			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addOpenSubject(osdto);
				return;
			} // 개설 과목 등록
			else {
				loop = false;
			}
		}
	}

	// 개설 과목 등록
	private void addOpenSubject(OpenSubjectDTO osdto) {

		// 쿼리
		int result = osdao.addOpenSubject(osdto);
		view.addResult(result);

	}

	// 개설 과목 정보 수정 메뉴
	private void updateOpenSubjectMenu() {

		// 개설 과목 수정 메뉴 헤더
		view.updateOpenSubjectMenuHeader();

		System.out.print("개설 과목 번호 : ");
		String openSubjectSeq = scan.nextLine(); // 개설 과목 번호 선택

		boolean loop = true;
		while (loop) {

			view.chooseUpdateOpenSubject();
			String sel = scan.nextLine();
         
         if (sel.equals("1")) { updateOpenSubjectStartDateMenu(openSubjectSeq); } //과목 시작 날짜 수정
         if (sel.equals("2")) { updateOpenSubjectEndDateMenu(openSubjectSeq); } //과목 종료 날짜 수정
         if (sel.equals("3")) { updateOpenSubjectBookMenu(openSubjectSeq); } //교재 수정
         else { loop = false; }
		}
	}

	// 개설 과목 - 시작 날짜 수정 메뉴
	private void updateOpenSubjectStartDateMenu(String openSubjectSeq) {

		// 개설 과목 시작 날짜 수정 헤더
		view.updateOpenSubjectStartDateHeader();

		System.out.print("개설 과목 시작 날짜(년, 4자리) : ");
		String syear = scan.nextLine();

		System.out.print("개설 과목 시작 날짜(월, 2자리) : ");
		String smonth = scan.nextLine();

		System.out.print("개설 과목 시작 날짜(일, 2자리) : ");
		String sday = scan.nextLine();

		String startDate = syear + "-" + smonth + "-" + sday;

		// 쿼리
		boolean loop = true;
		while (loop) {

			view.updateCheckMenu();
			String sel = scan.nextLine();

			if (sel.equals("1")) { updateOpenSubjectStartDate(openSubjectSeq, startDate); return; } 
			else { loop = false; }
		}
	}
	
	// 개설 과목 - 시작 날짜 수정
	private void updateOpenSubjectStartDate(String openSubjectSeq, String startDate) {
		int updateList = osdao.updateOpenSubject(openSubjectSeq, startDate, "", "");
		view.updateResult(updateList);
	}

	// 개설 과목 - 종료 날짜 수정 메뉴
	private void updateOpenSubjectEndDateMenu(String openSubjectSeq) {

		// 개설 과목 종료 날짜 수정 헤더
		view.updateOpenSubjectEndDateHeader();

		System.out.print("개설 과목 종료 날짜(년, 4자리) : ");
		String eyear = scan.nextLine();

		System.out.print("개설 과목 종료 날짜(월, 2자리) : ");
		String emonth = scan.nextLine();

		System.out.print("개설 과목 종료 날짜(일, 2자리) : ");
		String eday = scan.nextLine();

		String endDate = eyear + "-" + emonth + "-" + eday;

		// 쿼리
		boolean loop = true;
		while (loop) {

			view.updateCheckMenu();
			String sel = scan.nextLine();

			if (sel.equals("1")) { updateOpenSubjectEndDate(openSubjectSeq, endDate); return; }
			else { loop = false; }
		}
	}
	
	// 개설 과목 - 종료 날짜 수정
	private void updateOpenSubjectEndDate(String openSubjectSeq, String endDate) {
		int updateList = osdao.updateOpenSubject(openSubjectSeq, "", endDate, "");
		view.updateResult(updateList);
	}

	// 개설 과목 - 교재 수정 메뉴
	private void updateOpenSubjectBookMenu(String openSubjectSeq) {

		// 개설 과목 교재 수정 헤더
		view.updateOpenSubjectBookHeader();

		ArrayList<BookDTO> books = bdao.bookList();
		view.allBookList(books);

		System.out.println("<<교재 리스트에 있는 교재 번호를 입력해주세요.>>");
		System.out.print("교재 : ");
		String book = scan.nextLine();

		boolean loop = true;
		while (loop) {

			view.updateCheckMenu();
			String sel = scan.nextLine();

			if (sel.equals("1")) {updateOpenSubjectBook(openSubjectSeq, book); return; } 
			else { loop = false; }
		}
	}
	
	// 개설 과목 - 교재 수정
	private void updateOpenSubjectBook(String openSubjectSeq, String book) {
		int updateBookList = osdao.updateOpenSubject(openSubjectSeq, "", "", book);
		view.updateResult(updateBookList);
	}

	// 개설 과목 삭제 메뉴
	private void deleteOpenSubjectMenu() {

		// 개설 과목 삭제 메뉴 헤더
		view.deleteOpenProcessMenuHeader();

		System.out.print("개설 과목 번호 : ");
		String openSubjectSeq = scan.nextLine(); // 개설 과목 번호 선택

		boolean loop = true;
		while (loop) {

			view.deleteCheckMenu();
			String sel = scan.nextLine();

			if (sel.equals("1")) { deleteOpenSubject(openSubjectSeq); return; }
			else { loop = false; }
		}
	}

	//개설 과목 삭제
	private void deleteOpenSubject(String openSubjectSeq) {
		int result = osdao.deleteOpenSubject(openSubjectSeq);
		view.deleteResult(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//시험 및 성적 관리
   private void examAndScoreManagement() {
      
      //전체 개설 과정
      ArrayList<OpenProcessDTO> openProcesses = edao.listAllOpenProcess();
      view.examAndScoreMenu(openProcesses);
      
      String openProcessSeq = scan.nextLine(); //개설 과정 번호 선택
      
      //특정 개설 과정의 과목 목록 출력
      ArrayList<OpenSubjectDTO> openSubjects = edao.openSubejct(openProcessSeq);
      view.openSubjectList(openSubjects);
      
      System.out.println("<<개설 과목 리스트에 있는 개설 과목 번호를 입력해주세요.>>");
      System.out.print("선택 : ");
      String openSubjectSeq = scan.nextLine();
      
      boolean loop = true;
      while (loop) {
         
         view.chooseManagementExam(); 
         
         String sel = scan.nextLine();
         if (sel.equals("1")) { examInformationManagement(openProcessSeq, openSubjectSeq); } //시험 관리
         if (sel.equals("2")) { scoreManagement(openProcessSeq, openSubjectSeq); } //성적 관리
         else { loop = false; }
      }
   
   }

	//배점 및 시험 정보 관리
	private void examInformationManagement(String openProcessSeq, String openSubjectSeq) {
		
		//선택한 개설 과정의 선택한 개설 과목의 시험 정보 출력
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
		boolean loop = true;
		while (loop) {
		
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
		
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) {
				addExamInformation(openProcessSeq, openSubjectSeq, edto); 
				return;
			
			}
			else { continue; }
		}
	}

	//배점 및 시험 정보 등록
	private void addExamInformation(String openProcessSeq, String openSubjectSeq ,ExamDTO edto) {
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
	private void updateHandwritingPoint(String openProcessSeq, String openSubjectSeq ,String hpoint) {
		int result = edao.updateHandwritingPoint(openProcessSeq, openSubjectSeq ,hpoint);
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
			if (sel.equals("1")) { updatePracticalPoint(openProcessSeq, openSubjectSeq, ppoint);
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//실기 배점 수정
	private void updatePracticalPoint(String openProcessSeq, String openSubjectSeq, String ppoint) {
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
			if (sel.equals("1")) { updateAttendancePoint(openProcessSeq, openSubjectSeq,ppoint); 
			loop = false;}
			else { loop = false; }
		}
		
	}
	
	//출결 배점 수정
	private void updateAttendancePoint(String openProcessSeq, String openSubjectSeq , String ppoint) {
		int result = edao.updateAttendancePoint(openProcessSeq, openSubjectSeq ,ppoint);
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
			if (sel.equals("1")) { updateExamDate(openProcessSeq, openSubjectSeq , examDate);
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
	private void updateExamFileStatus(String openProcessSeq, String openSubjectSeq ,String examFile) {
		int result = edao.updateExamFileStatus(openProcessSeq, openSubjectSeq , examFile);
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
	 * 형준 관리자 성적 관리
	 * @param openProcessSeq
	 * @param openSubjectSeq
	 */
	//성적 관리
	private void scoreManagement(String openProcessSeq, String openSubjectSeq) {
		
		
		//선택한 개설 과정의 개설 과목의 교육생 성적 목록 출력
		//이름, 필기 성적, 실기 성적, 출결 성적, 성적 등록 여부 
		ArrayList<ScoreDTO> studentScores = edao.studentScore(openProcessSeq, openSubjectSeq);
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
	 * 형준 관리자 성적 관리
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
			if (sel.equals("3")) { deleteHandwritingScoreMenu(openProcessSeq, openSubjectSeq, studentSeq); } //필기 점수 삭제
			else { loop = false; }
		}
	}

	
	/**
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
	 * @param openProcessSeq
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//필기 점수 삭제 메뉴
	private void deleteHandwritingScoreMenu(String openProcessSeq, String openSubjectSeq, String studentSeq) {
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

	/**
	 * 형준 관리자 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//필기 점수 삭제
	private void deleteHandwritingScore(String openSubjectSeq, String studentSeq) {
		int result = edao.deleteHandwritingScore(openSubjectSeq, studentSeq);
		view.deleteResult(result);
	}
	
	
	/**
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//실기 점수 삭제
	private void deletePracticalScore(String openSubjectSeq, String studentSeq) {
		int result = edao.deletePracticalScore(openSubjectSeq, studentSeq);
		view.deleteResult(result);
	}
	
	/**
	 * 형준 관리자 성적 관리
	 * @param openProcess
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 관리
	private void attendanceScoreManagement(String openProcess, String openSubjectSeq, String studentSeq) {
		
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
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
	 * 형준 관리자 성적 관리
	 * @param openSubjectSeq
	 * @param studentSeq
	 */
	//출결 점수 삭제
	private void deleteAttendanceScore(String openSubjectSeq, String studentSeq) {
		int result = edao.deleteAttendanceScore(openSubjectSeq, studentSeq);
		view.deleteResult(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//출결 조회 및 근태 관리 
	public void attendanceManagement() {
		
		
		//출결 조회 및 근태 관리 헤더
		view.attendanceManagementHeader();
		
		//전체 개설 과정 목록
		ArrayList<OpenProcessDTO> openProcesses =  adao.openProcessList();
		view.allOpenProcessList(openProcesses);
		
		System.out.println("<<개설 과정 리스트에 있는 개설 과정 번호를 입력해주세요.>>");
		System.out.print("선택 : ");
		String openProcessSeq = scan.nextLine();
		opnum = openProcessSeq;
		ArrayList<StudentDTO> students = adao.student(openProcessSeq);
		view.allStudentList(students);
		
		System.out.println("<<해당 개설과정을 듣고 있는 교육생의 수강 번호를 입력해주세요.>>");
		System.out.print("번호 : ");
		String registrationSeq = scan.nextLine();
		Sequence.StudentSeq = registrationSeq;
		
		boolean loop = true;
		while (loop) {
			
			view.chooseAttendanceMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { searchPeriod(openProcessSeq, registrationSeq); } //기간 검색
			else if (sel.equals("2")) { updateAttendanceMenu(); } //출결 관리
			else { loop = false; }
		}
		
		
	}
	
	
	//기간 검색
	private void searchPeriod(String openProcessSeq, String registrationSeq) {
		//기간 검색 헤더
		view.searchPeriodHeader();
		
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
		
		ArrayList<AttendanceDTO> attendances = adao.searchPeriod(registrationSeq, startDate, endDate, openProcessSeq);
		view.attendanceList(attendances);
		
		boolean loop = true;
		while (loop) {
			
//			view.chooseAttendance();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateAttendanceMenu(); } //출결 관리
			else { loop = false; }
		}
		
	}
	
	//출결 관리 메뉴
	private void updateAttendanceMenu() {
		
		System.out.println("<<출결을 관리하고 싶은 날짜의 번호를 입력하세요.>>");
		System.out.print("날짜 번호 : ");
		String attendanceSeq = scan.nextLine();
		
//			System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
//			System.out.println("<<변경할 시간을 입력해주세요.>>");
//			System.out.print("시(2자리, 24시간체계) : ");
//			String hour = scan.nextLine();
//			System.out.print("분(2자리) : ");
//			String minute = scan.nextLine();
//			System.out.print("초(2자리) : ");
//			String second = scan.nextLine();
//			String time = hour + ":" + minute + ":" + second;
		
		
		boolean loop = true;
		while (loop) {
			
//				view.updateCheckMenu();
			
//				String sel = scan.nextLine();
//				if (sel.equals("1")) { 
				updateAttendance(attendanceSeq); 
				loop = false;
//					} //출결 관리
//				else { loop = false; }
		}
		
	}
	
	//출결 관리
	private void updateAttendance(String attendanceSeq) {
			ArrayList<AttendanceDTO> result = adao.updateAttendance(attendanceSeq);
		view.updateAttendancer(result);
//			view.updateCheckMenu();
		System.out.println("출결 번호를 선택해 주세요.");
		System.out.print("번호 입력: ");
		String seqNum = scan.nextLine();
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("<<변경할 시간을 입력해주세요.>>");
		System.out.print("시(2자리, 24시간체계) : ");
		String hour = scan.nextLine();
		System.out.print("분(2자리) : ");
		String minute = scan.nextLine();
		System.out.print("초(2자리) : ");
		String second = scan.nextLine();
		String time = hour + ":" + minute + ":" + second;
		
		view.chooseAttendance();
		String a = scan.nextLine();
		if(a.equals("1")) {
		int result1 = adao.updateAttendance2(seqNum , time);
		view.updateResult(result1);
		}else {
			attendanceManagement();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//교사 평가 관리
	private void teacherEvaluationManagement() {
		
		//교사 평가 관리 헤더
		view.teacherEvaluationManagementHeader();
		
		//전체 교사 목록 
		ArrayList<TeacherDTO> teachers = tcdao.allTeacherList();
		view.teacherList(teachers);
		
		System.out.println("<<교사 리스트에 있는 교사 번호를 입력해주세요.>>");
		System.out.print("교사 : ");
		String teacherSeq = scan.nextLine();
		
		//선택한 교사의 담당 개설 과정들의 평가
		ArrayList<OpenProcessEvaluationDTO> openProcesses = tedao.teacherCourse(teacherSeq);
		view.evaluatedOpenProcessList(openProcesses);
		
		System.out.println("<<개설 과정 리스트에 있는 개설 과정 번호를 입력해주세요.>>");
		System.out.print("선택 : ");
		String openProcessSeq = scan.nextLine();
		
		//선택한 개설 과정의 모든 교육생의 평가 점수 출력
		ArrayList<EvaluationScoreDTO> scores = tedao.scoreList(openProcessSeq);
		view.evaluatedOpenProcess(scores);
		
		
		System.out.print("수강 번호 선택 : ");
		String registrationSeq = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.chooseEvaluationMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addEvaluationMenu(registrationSeq); } //교사 평가 등록
			if (sel.equals("2")) { updateEvaluationMenu(registrationSeq); } //교사 평가 수정
			if (sel.equals("3")) { deleteEvaluationMenu(registrationSeq); } //교사 평가 삭제
			else { loop = false; }
		}
		
	}

	

	//교사 평가 등록 메뉴
	private void addEvaluationMenu(String registrationSeq) {
		
		//교사 평가 등록 헤더
		view.addEvaluationHeader();
		
		System.out.print("강의계획서 이행 점수(1-5) : ");
		String processScore = scan.nextLine();
		
		System.out.print("강의내용 전달 및 이해점수(1-5) : ");
		String understandScore = scan.nextLine();
		
		System.out.print("소통점수(1-5) : ");
		String communicationScore = scan.nextLine();
		
		System.out.print("유익성 점수(1-5) : ");
		String usefulScore = scan.nextLine();
		
		System.out.print("전반적인 만족도 점수(1-5) : ");
		String satisfactionScore = scan.nextLine();
		
		
		boolean loop = true;
		while (loop) {
			
			view.addCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { addEvaluation(registrationSeq, processScore, understandScore, communicationScore, usefulScore, satisfactionScore); return; }
			else { loop = false; }
		}
		
	}

	//교사 평가 등록
	private void addEvaluation(String registrationSeq, String processScore, String understandScore,
			String communicationScore, String usefulScore, String satisfactionScore) {

		int result = tedao.addTeacherEvaluation(registrationSeq, processScore, understandScore, communicationScore, usefulScore, satisfactionScore);
		
		view.addResult(result);
		
	}
	
	//교사 평가 수정 메뉴
	private void updateEvaluationMenu(String registrationSeq) {
		
		boolean loop = true;
		while (loop) {
			
			view.chooseEvaluationUpdate();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateProcessScoreMenu(registrationSeq);  } //강의계획서 이행 점수 수정
			if (sel.equals("2")) { updateUnderstandScoreMenu(registrationSeq); } //이해 점수 수정
			if (sel.equals("3")) { updateCommunicationScoreMenu(registrationSeq); } //소통 점수 수정
			if (sel.equals("4")) { updateUsefulScoreMenu(registrationSeq); } //유용성 점수 수정
			if (sel.equals("5")) { updateSatisfactionScoreMenu(registrationSeq); } //전반적 만족도 점수 수정
			else { loop = false; }
		}
		
	}

	//강의계획서 이행 점수 수정 메뉴
	private void updateProcessScoreMenu(String registrationSeq) {
		System.out.print("강의계획서 이행 점수(1-5) : ");
		String processScore = scan.nextLine();	
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateProcessScore(registrationSeq, processScore); updateEvaluationMenu(registrationSeq); } //강의계획서 이행 점수 수정
			else { loop = false; }
		}
	}

	//강의계획서 이행 점수 수정
	private void updateProcessScore(String registrationSeq, String processScore) {

		int result = tedao.updateProcessScore(registrationSeq, processScore);
		
		view.updateResult(result);
		
	}

	//강의내용 전달 및 이해 점수 수정 메뉴
	private void updateUnderstandScoreMenu(String registrationSeq) {
		System.out.print("강의내용 전달 및 이해점수(1-5) : ");
		String understandScore = scan.nextLine();	
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateUnderStandScore(registrationSeq, understandScore); updateEvaluationMenu(registrationSeq); } //이해 점수 수정
			else { loop = false; }
		}
	}

	//강의내용 전달 및 이해 점수 수정
	private void updateUnderStandScore(String registrationSeq, String understandScore) {

		int result = tedao.updateUnderstandScore(registrationSeq, understandScore);
		
		view.updateResult(result);
		
	}

	//소통 점수 수정 메뉴
	private void updateCommunicationScoreMenu(String registrationSeq) {
		System.out.print("소통점수(1-5) : ");
		String communicationScore = scan.nextLine();
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateCommunicationScore(registrationSeq, communicationScore); updateEvaluationMenu(registrationSeq); } //소통 점수 수정
			else { loop = false; }
		}
	}

	//소통 점수 수정
	private void updateCommunicationScore(String registrationSeq, String communicationScore) {

		int result = tedao.updateCommunicationScore(registrationSeq, communicationScore);
		
		view.updateResult(result);
		
	}

	//강의의 유익성 점수 수정
	private void updateUsefulScoreMenu(String registrationSeq) {
		System.out.print("유익성 점수(1-5) : ");
		
		String usefulScore = scan.nextLine();
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateUsefulScore(registrationSeq, usefulScore); updateEvaluationMenu(registrationSeq); } //유익성 점수 수정
			else { loop = false; }
		}
	
	}

	//유익성 점수 수정
	private void updateUsefulScore(String registrationSeq, String usefulScore) {

		int result = tedao.updateUsefulScore(registrationSeq, usefulScore);
		view.updateResult(result);
		
	}
	
	//전반적인 만족도 점수 수정
	private void updateSatisfactionScoreMenu(String registrationSeq) {
		System.out.print("전반적인 만족도 점수(1-5) : ");
		String satisfactionScore = scan.nextLine();	
		
		boolean loop = true;
		while (loop) {
			
			view.updateCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { updateSatisfactionScore(registrationSeq, satisfactionScore); updateEvaluationMenu(registrationSeq); } //전반적인 만족도 수정
			else { loop = false; }
		}
	}
		
	//전반적인 만족도 점수 수정
	private void updateSatisfactionScore(String registrationSeq, String satisfactionScore) {

		int result = tedao.updateSatisfactionScore(registrationSeq, satisfactionScore);
		
		view.updateResult(result);
		
	}

	//교사 평가 삭제
	private void deleteEvaluationMenu(String registrationSeq) {
		
		boolean loop = true;
		while (loop) {
			
			view.chooseEvaluationDelete();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteProcessScoreMenu(registrationSeq); } //강의계획서 이행 점수 삭제
			if (sel.equals("2")) { deleteUnderstandScoreMenu(registrationSeq); } //이해 점수 삭제
			if (sel.equals("3")) { deleteCommunicationScoreMenu(registrationSeq); } //소통 점수 삭제
			if (sel.equals("4")) { deleteUsefulScoreMenu(registrationSeq); } //유용성 점수 삭제
			if (sel.equals("5")) { deleteSatisfactionScoreMenu(registrationSeq); } //전반적 만족도 점수 삭제
			else { loop = false; }
		}		
	}
	
	//강의계획서 이행 점수 삭제 메뉴
	private void deleteProcessScoreMenu(String registrationSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteProcessScore(registrationSeq); }
			else { loop = false; }
		}		
	}

	//강의계획서 이행 점수 삭제
	private void deleteProcessScore(String registrationSeq) {

		String str = "proc_deleteProcessscore";
		int result = tedao.deleteEvaluations(registrationSeq, str);
		
		view.deleteResult(result);
		
	}

	//이해 점수 삭제 메뉴
	private void deleteUnderstandScoreMenu(String registrationSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteUnderStandScore(registrationSeq); }
			else { loop = false; }
		}				
	}

	//이해 점수 삭제 
	private void deleteUnderStandScore(String registrationSeq) {

		String str = "proc_deleteUnderstandscore";
		int result = tedao.deleteEvaluations(registrationSeq, str);
		
		view.deleteResult(result);
		
	}

	//소통 점수 삭제 메뉴
	private void deleteCommunicationScoreMenu(String registrationSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteCommunicationScore(registrationSeq); }
			else { loop = false; }
		}				
	}

	//소통 점수 삭제
	private void deleteCommunicationScore(String registrationSeq) {

		String str = "proc_deleteCommunicationScore";
		int result = tedao.deleteEvaluations(registrationSeq, str);
		
		view.deleteResult(result);
		
	}

	//유익성 점수 삭제 메뉴
	private void deleteUsefulScoreMenu(String registrationSeq) {
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteUsefulScore(registrationSeq); }
			else { loop = false; }
		}	
	}

	//유익성 점수 삭제 
	private void deleteUsefulScore(String registrationSeq) {

		String str = "proc_deleteUsefulscore";
		int result = tedao.deleteEvaluations(registrationSeq, str);
		
		view.deleteResult(result);
		
	}

	//전반적인 만족도 점수 삭제 메뉴
	private void deleteSatisfactionScoreMenu(String registrationSeq) {
		
		boolean loop = true;
		while (loop) {
			
			view.deleteCheckMenu();
			
			String sel = scan.nextLine();
			if (sel.equals("1")) { deleteSatisfactionScore(registrationSeq); }
			else { loop = false; }
		}				
	}

	//전반적인 만족도 점수 삭제
	private void deleteSatisfactionScore(String registrationSeq) {

		String str = "proc_deleteSatisfactionscore";
		int result = tedao.deleteEvaluations(registrationSeq, str);
		
		view.deleteResult(result);
		
	}

}

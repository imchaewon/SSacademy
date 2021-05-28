import java.util.ArrayList;



/**
 * 교육생 뷰
 * @author 유건우, 전수희
 * 교육생 계정이 이용 가능한 모든 메뉴를 출력해주는 메소드이다.
 */
public class StudentView {
	
	/**
	 * 교육생 계정의 메인 화면을 출력해주는 메소드이다.
	 */
	public void mainMenu() {

		//학생메인
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("학생 메인 화면");
		System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("1. 출결 현황 및 출결체크");
		System.out.println("2. 성적 조회");
		System.out.println("3. 교사 평가");
		System.out.println();
		System.out.println("0. 로그아웃");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택(번호) : ");
		
	}

	/**
	 * 교육생이 수강한 모든 과정의 목록을 출력하는 메소드이다.
	 * 개설 과정번호, 과정명, 과정시작날짜, 과정종료날짜, 강의실, 등록인원, 개설과목등록여부 등을 출력한다.
	 * @param result 모든 개설과정의 정보가 저장되어 있는 배열이다.
	 * 
	 */
	public void opProcessList(ArrayList<OpenProcessDTO> result) {

		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("수강 과정 목록");
		System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
		System.out.println("번호\t과정명\t과정시작날짜\t과정종료날짜\t강의실\t등록인원\t개설과목등록여부");
		System.out.println("-------------------------------------------------------------------------------------");
		
		for(OpenProcessDTO opdto : result) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
									, opdto.getSeq()
									, opdto.getTitle()
									, opdto.getStartDate()
									, opdto.getEndDate()
									, opdto.getLectureRoom()
									, opdto.getPersonnel()
									, opdto.getExistStatus());
		}
		System.out.println("-------------------------------------------------------------------------------------");
		
	}
	
	/**
	 * 교육생의 출결과 관련된 메뉴를 출력해주는 메소드이다.
	 */
	public void checkMenu() {

		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("1. 전체 조회");
		System.out.println("2. 월별 조회");
		System.out.println("3. 출결 체크");
		System.out.println();
		System.out.println("0. 뒤로가기");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		
	}

	/**
	 * 교육생이 수강한 과정의 전체 출결을 출력해주는 메소드이다.
	 * 수강과정번호, 과정명, 과정시작날짜, 과정종료날짜, 시간, 출결상태를 출력한다.
	 * @param result 출결과 관련된 정보가 저장되어 있는 배열이다.
	 */
	public void allAttendanceList(ArrayList<AttendanceDTO> result) {

//		System.out.println("-------------------------------------------------------------------------------------");
//		System.out.println("전체 조회(1번 선택)");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("수강과정번호\t과정명\t과정시작날짜\t과정종료날짜\t시간\t출결상태");
		System.out.println("-------------------------------------------------------------------------------------");
		
		for(AttendanceDTO atdto : result) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n"
									, atdto.getSeq()
									, atdto.getOpenProcessName()
									, atdto.getStartDate()
									, atdto.getEndDate()
									, atdto.getAttendanceTime()
									, atdto.getAttendanceStatus());
		}
		
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("0. 뒤로가기"); //수강과정목록으로
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		
	}

	/**
	 * 교육생이 수강한 과정의 출결을 원하는 년도 와 월을 입력받아서 월별로 출력해주는 메소드이다.
	 * 수강과정번호, 과정명, 과정시작날짜, 과정종료날짜, 시간, 출결상태 등을 출력한다.
	 * @param result 출결과 관련된 정보가 저장되어 있는 배열이다.
	 */
	public void chooseMonthList(ArrayList<AttendanceDTO> result) {

		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("수강과정번호\t과정명\t과정시작날짜\t과정종료날짜\t시간\t출결상태");
		System.out.println("-------------------------------------------------------------------------------------");
		
		for(AttendanceDTO atdto : result) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n"
									, atdto.getSeq()
									, atdto.getOpenProcessName()
									, atdto.getStartDate()
									, atdto.getEndDate()
									, atdto.getAttendanceTime()
									, atdto.getAttendanceStatus());
		}
		System.out.println("-------------------------------------------------------------------------------------");
		
	}

	
	/**
	 * 교육생 뷰의 출석체크의 결과를 출력하는 메소드이다.
	 * 출석체크를 성공시 "출석체크 완료"메세지를 , 실패시 "출석체크 실패" 메세지를 출력한다.
	 * @param addList 0 또는 1이 저장되어 있는 변수이다.
	 */
	public void checkResult(int addList) {

		if(addList==0) {
			System.out.println("출석체크 실패.");
		} else if(addList==1) {
			System.out.println("출석체크 완료.");
		}
	}
	
	
	
	/**
	 * 교육생이 수강한 과목의 정보 및 시험 배점을 출려하는 메소드이다.
	 * 개설과목번호, 과목명, 과목시작날짜, 과목종료날짜, 교재명, 출결배점, 필기배점, 실기배점, 출결점수, 필기점수, 실기점수, 시험날짜, 시험문제등록여부 등을 출력한다.
	 * @param slist 모든 개설과목에 대한 정보가 저장되어 있는 배열이다.
	 */
	public void openP_Subject(ArrayList<OpenP_SubjectDTO> slist) {

		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("개설과목번호\t과목명\t과목시작날짜\t과목종료날짜\t교재\t출결배점\t필기배점\t실기배점\t출결점수\t필기점수\t실기점수\t시험날짜\t시험문제등록여부");
		System.out.println("-------------------------------------------------------------------------------------");
		
		for(OpenP_SubjectDTO opsdto : slist) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
									, opsdto.getSeq()
									, opsdto.getSubjectName()
									, opsdto.getSubStartDate()
									, opsdto.getSubEndDate()
									, opsdto.getBook()
									, opsdto.getAttendancePoint()
									, opsdto.getHandWritingTestPoint()
									, opsdto.getPracticalTestPoint()
									, opsdto.getAttendanceScore()
									, opsdto.getHandWritingTestScore()
									, opsdto.getPracticalTestScore()
									, opsdto.getExamDate()
									, opsdto.getExamFileStatus());
		}
		
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("0. 뒤로가기");
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.print("선택 : ");
		
	}
	


	/**
	 * 교육생이 수강한 과정의 교사에 대한 교사평가 정보를 출력하는 메소드이다.
	 * 교사평가번호, 과정명, 과정시작날짜, 과정수료날짜, 과정수료여부, 교사명, 강의계획서 이행점수, 강의내용 전달 및 이해 점수, 소통점수, 유익성점수, 전반적인 만족도 점수를 출력한다.
	 * @param list 교사평가에 대한 모든 정보가 저장되어 있는 배열이다.
	 */
	//학생전용 교사평가 조회
	public void teacherEvaluationList(ArrayList<TeacherEvaluationDTO> list) {

		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("교사평가번호\t과정명\t과정시작날짜\t과정수료날짜\t과정수료여부\t교사명\t강의계획서 이행점수\t강의내용 전달 및 이해 점수\t소통점수\t유익성점수\t전반적인 만족도 점수");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		for(TeacherEvaluationDTO tedto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n"
									, tedto.getSeq() //교사평가번호
									, tedto.getProcessName() //과정명
									, tedto.getStartDate() //과정시작날짜
									, tedto.getEndDate() //과정종료날짜
									, tedto.getFinishStatus() //과정수료여부
									, tedto.getTeacherName() //교사명
									, tedto.getProcessScore() == null ? "-" : tedto.getProcessScore() //평가 - 강의계획서 이행점수
									, tedto.getUnderstandScore() == null ? "-" : tedto.getUnderstandScore() //평가 - 강의내용 전달 및 이해점수
									, tedto.getCommunicationScore() == null ? "-" : tedto.getCommunicationScore() //평가 - 소통 점수
									, tedto.getUsefulScore() == null ? "-" : tedto.getUsefulScore() //평가 - 유익성 점수
									, tedto.getSatisfactionScore() == null ? "-" : tedto.getSatisfactionScore()); //평가 - 전반적인 만족도 점수
			
		}
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		
	}
	
	/**
	 * 교육생 뷰의 등록 결과를 출력하는 메소드이다.
	 * 0일 시 삭제 실패, 1일 시 등록 성공 문구를 출력한다.
	 * @param addList 0 또는 1이 저장되어 있는 변수이다.
	 */
	public void addResult(int addList) {
		
		if(addList==0) {
			System.out.println("평가 등록이 실패하였습니다.");
		} else if(addList==1) {
			System.out.println("평가 등록이 성공하였습니다.");
		}
		
	}
	

	/**
	 * 교육생 뷰의 수정 결과를 출력하는 메소드이다.
	 * 0일 시 삭제 실패, 1일 시 삭제 성공 문구를 출력한다.
	 * @param updateList 0 또는 1이 저장되어 있는 변수이다.
	 */
	public void updateResult(int updateList) {
		
		if(updateList==0) {
			System.out.println("평가 수정이 실패하였습니다.");
		} else if(updateList==1) {
			System.out.println("평가 수정이 성공하였습니다.");
		}
		
	}
	
	
	/**
	 * 교육생 뷰의 삭제 결과를 출력하는 메소드이다.
	 * 0일 시 삭제 실패, 1일 시 삭제 성공 문구를 출력한다.
	 * @param delList 0 또는 1이 저장되어 있는 변수이다.
	 */
	public void deleteResult(int delList) {

		if(delList==0) {
			System.out.println("평가 삭제를 실패하였습니다.");
		} else if(delList==1) {
			System.out.println("평가 삭제를 성공하였습니다.");
		}
		
	}

}


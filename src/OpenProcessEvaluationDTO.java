/**
 * 
 * 개설 과정 평가 정보를 저장하는 DTO 이다. 
 * seq; //개설과정번호 
 * title; //과정명 startDate; //시작 날짜
 * endDate; //종료 날짜 
 * personnel; //수강 인원 
 * evaluationPersonnel; //평가 인원
 * finishStatus; //수료 여부 
 * processScore; //평가 - 강의계획서 이행점수 평균 
 * understandScore; //평가 - 강의내용 전달 및 이해점수 평균 
 * communicationScore; //평가 - 소통 점수 평균 
 * usefulScore; //평가 - 유익성 점수 평균 
 * satisfactionScore; //평가 - 전반적인 만족도 점수 평균
 * @author 전수희
 */
public class OpenProcessEvaluationDTO {
   
   private String seq; //개설과정번호
   private String title; //과정명
   private String startDate; //시작 날짜
   private String endDate; //종료 날짜
   private String personnel; //수강 인원
   private String evaluationPersonnel; //평가 인원
   private String finishStatus; //수료 여부
   private String processScore; //평가 - 강의계획서 이행점수 평균
   private String understandScore; //평가 - 강의내용 전달 및 이해점수 평균
   private String communicationScore; //평가 - 소통 점수 평균
   private String usefulScore; //평가 - 유익성 점수 평균
   private String satisfactionScore; //평가 - 전반적인 만족도 점수 평균
   
   /**
    * 개설과정번호에 대한 데이터를 내보내는 메소드이다.
    * @return 개설과정번호
    */
   public String getSeq() {
      return seq;
   }
   
   /**
    * 개설과정번호에 대한 데이터를 받는 메소드이다.
    * @param seq 개설과정번호
    */
   public void setSeq(String seq) {
      this.seq = seq;
   }
   
   /**
    * 과정명에 대한 데이터를 내보내는 메소드이다.
    * @return 과정명
    */
   public String getTitle() {
      return title;
   }
   
   /**
    * 과정명에 대한 데이터를 받는 메소드이다.
    * @param title 과정명
    */
   public void setTitle(String title) {
      this.title = title;
   }
   
   /**
    * 시작날짜에 대한 데이터를 내보내는 메소드이다.
    * @return 시작날짜
    */
   public String getStartDate() {
      return startDate;
   }
   
   /**
    * 수강시작날짜에 대한 데이터를 받는 메소드이다.
    * @param startDate 수강시작날짜
    */
   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }
   
   /**
    * 수강종료날짜에 대한 데이터를 내보내는 메소드이다.
    * @return 수강종료날짜
    */
   public String getEndDate() {
      return endDate;
   }
   
   /**
    * 수강종료날짜에 대한 데이터를 받는 메소드이다.
    * @param endDate 수강종료날짜
    */
   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }
   
   /**
    * 수강 인원에 대한 데이터를 내보내는 메소드이다.
    * @return 수강 인원
    */
   public String getPersonnel() {
      return personnel;
   }
   
   /**
    * 수강 인원에 대한 데이터를 받는 메소드이다.
    * @param personnel 수강 인원
    */
   public void setPersonnel(String personnel) {
      this.personnel = personnel;
   }
   
   /**
    * 평가 인원에 대한 데이터를 내보내는 메소드이다.
    * @return 평가 인원
    */
   public String getEvaluationPersonnel() {
      return evaluationPersonnel;
   }
   
   /**
    * 평가 인원에 대한 데이터를 받는 메소드이다.
    * @param evaluationPersonnel 평가 인원
    */
   public void setEvaluationPersonnel(String evaluationPersonnel) {
      this.evaluationPersonnel = evaluationPersonnel;
   }
   
   /**
    * 수료 여부에 대한 데이터를 내보내는 메소드이다.
    * @return 수료 여부
    */
   public String getFinishStatus() {
      return finishStatus;
   }
   
   /**
    * 수료 여부에 대한 데이터를 받는 메소드이다.
    * @param finishStatus 수료 여부
    */
   public void setFinishStatus(String finishStatus) {
      this.finishStatus = finishStatus;
   }
   
   /**
    * 평가 - 이행점수(평균)에 대한 데이터를 내보내는 메소드이다.
    * @return 평가 - 이행점수(평균)
    */
   public String getProcessScore() {
      return processScore;
   }
   
   /**
    * 평가 - 이행점수(평균)에 대한 데이터를 받는 메소드이다.
    * @param processScore 평가 - 이행점수(평균)
    */
   public void setProcessScore(String processScore) {
      this.processScore = processScore;
   }
   
   /**
    * 평가 - 강의내용 전달 및 이해점수 (평균)에 대한 데이터를 내보내는 메소드이다.
    * @return 평가 - 강의내용 전달 및 이해점수 (평균)
    */
   public String getUnderstandScore() {
      return understandScore;
   }
   
   /**
    * 평가 - 강의내용 전달 및 이해점수 (평균)에 대한 데이터를 받는 메소드이다.
    * @param understandScore 평가 - 강의내용 전달 및 이해점수 (평균)
    */
   public void setUnderstandScore(String understandScore) {
      this.understandScore = understandScore;
   }
   
   /**
    * 평가 - 소통점수(평균)에 대한 데이터를 내보내는 메소드이다.
    * @return 평가 - 소통점수(평균)
    */
   public String getCommunicationScore() {
      return communicationScore;
   }
   
   /**
    * 평가 - 소통점수(평균)에 대한 데이터를 받는 메소드이다.
    * @param communicationScore 평가 - 소통점수(평균)
    */
   public void setCommunicationScore(String communicationScore) {
      this.communicationScore = communicationScore;
   }
   
   /**
    * 평가 - 유익성 점수(평균)에 대한 데이터를 내보내는 메소드이다.
    * @return 평가 - 유익성 점수(평균)
    */
   public String getUsefulScore() {
      return usefulScore;
   }
   
   /**
    * 평가 - 유익성 점수(평균)에 대한 데이터를 받는 메소드이다.
    * @param usefulScore 평가 - 유익성 점수 (평균)
    */
   public void setUsefulScore(String usefulScore) {
      this.usefulScore = usefulScore;
   }
   
   /**
    * 평가 - 전반적인 만족도 점수(평균)에 대한 데이터를 내보내는 메소드이다.
    * @return 평가 - 전반적인 만족도 점수(평균)
    */
   public String getSatisfactionScore() {
      return satisfactionScore;
   }
   
   /**
    * 평가 - 전반적인 만족도 점수(평균)에 대한 데이터를 받는 메소드이다.
    * @param satisfactionScore 평가 - 전반적인 만족도 점수(평균)
    */
   public void setSatisfactionScore(String satisfactionScore) {
      this.satisfactionScore = satisfactionScore;
   }
   

}
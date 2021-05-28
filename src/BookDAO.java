import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 교재와 관련한 기능을 담당하는 클래스이다.
 * 전체 교재 조회, 새 교재 등록, 교재 정보 수정, 교재 삭제 기능을 포함한다.
 * @author 전수희
 */
public class BookDAO {
	
	Connection conn;
	Statement stat;
	PreparedStatement pstat;
	CallableStatement cstat;
	ResultSet rs;
	
	
	/**
	 * 교재 DAO의 기본 생성자이다.
	 */
	public BookDAO() {
		
		try {

			conn = DBUtil.open();
			stat = conn.createStatement();

		} catch (Exception e) {
			System.out.println("BookDAO.BookDAO() : " + e.toString());
		}
	}
	
	/**
	 * 전체 교재 정보를 배열로 반환하는 메소드이다.
	 * 교재의 정보에는 교재 번호, 책 제목, 출판사가 포함된다.
	 * @return 교재 정보 배열
	 */
	//전체 교재 목록 조회
	public ArrayList<BookDTO> bookList() {
		
		ArrayList<BookDTO> result = new ArrayList<BookDTO>();
		
		try {

			String sql = "SELECT seq, title, publisher FROM tblBook order by seq asc";
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				BookDTO bdto = new BookDTO();
				
				bdto.setSeq(rs.getString("seq"));
				bdto.setTitle(rs.getString("title"));
				bdto.setPublisher(rs.getString("publisher"));
				
				result.add(bdto);
			}
			rs.close();
			
			return result;

		} catch (Exception e) {
			System.out.println("BookDAO.bookList() : " + e.toString());
		}
		return null;
	}
	
	/**
	 * 새 교재를 등록하는 메소드이다.
	 * 교재 제목과 출판사가 담긴 교재 데이터 정보를 받아 교재를 등록하고 등록 성공 여부를 반환한다.
	 * 성공 여부는 등록 성공 시 1, 실패 시 0이다. 
	 * @param bdto 교재 데이터 정보
	 * @return 성공 여부
	 */
	//교재 등록
	public int addBook(BookDTO bdto) {
		
		String sql = "{ call proc_addBook(?, ?) }";
		
		try {

			cstat = conn.prepareCall(sql);
			cstat.setString(1, bdto.getTitle());	//제목
			cstat.setString(2, bdto.getPublisher()); //출판사
			return cstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BookDAO.addBook() : " + e.toString());
		}
		return 0;
	}

	/**
	 * 수정할 교재 번호와 수정할 제목 또는 출판사를 받아와 기존 교재의 정보를 수정하는 메소드이다.
	 * 성공 여부는 수정 성공 시 1, 실패 시 0이다. 
	 * @param bookSeq 교재 번호
	 * @param bookTitle 교재 제목
	 * @param bookPublisher 교재 출판사
	 * @return 수정된 교재의 수
	 */
	//교재 수정
	public int updateBook(String bookSeq, String bookTitle, String bookPublisher) {
		
		//원래 값
		String title = "";
		String publisher = "";
		
		try {
			
			String sql = "SELECT seq, title, publisher FROM tblBook WHERE seq = " + bookSeq;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				title = rs.getString("title");
				publisher = rs.getString("publisher");
			}
			
			//교재명 수정
			if(!bookTitle.equals("")) {
				title = bookTitle;
			}
			
			//출판사 수정
			if (!bookPublisher.equals("")) {
				publisher = bookPublisher;
			}
			
			sql = "{ call proc_updateBook(?, ?, ?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, bookSeq);
			cstat.setString(2, title);
			cstat.setString(3, publisher);
			
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("BookDAO.updateBook() : " + e.toString());
		}
		return 0;
	}
	
	/**
	 * 교재를 삭제하는 메소드이다.
	 * 삭제할 교재 번호를 받아 교재를 삭제하한다.
	 * 성공 여부는 삭제 성공 시 1, 실패 시 0이다. 
	 * @param bookSeq 교재 번호
	 * @return 성공 여부
	 */
	//교재 삭제
	public int deleteBook(String bookSeq) {
		
		try {
			
			String sql = "{ call proc_deleteBook(?) }";
			cstat = conn.prepareCall(sql);
			cstat.setString(1, bookSeq);
			return cstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("BookDAO.deleteBook() : " + e.toString());
		}
		
		return 0;
	}
	

}

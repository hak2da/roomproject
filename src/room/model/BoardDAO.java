package room.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbclose.util.CloseUtil;
import room.model.BoardVO;

public class BoardDAO { // Controller

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	public BoardDAO() {
	} // useBean 태그로 객체 생성하면~

	public Connection getConnection() throws Exception {
		// 연결은 JNDI * Pool 형태로 연결 객체 생성해서 리턴할것
		Context initCtx = new InitialContext();

		DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc:Board2DB");

		return ds.getConnection();
	}// getConnection() end

	// insert(vo) method - 새로운 글을 게시판에 추가, 글 입력처리에 사용
	public boolean boardInsert(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;


		try {
			conn = getConnection();
			// 현재 board 테이블에 레코드 유무 판단과 글 번호 지정

			// insert 처리 명령

			// System.out.println(sb.toString());

			pstmt = conn.prepareStatement("insert into board2(id, pwd, average,title, content, bdate) values(?,?,?,?,?,?)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setFloat(3, vo.getAverage());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getContent());
			pstmt.setTimestamp(6, vo.getBdate());

			result = pstmt.executeUpdate();
			if(result==0) return false;
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(rs);
			CloseUtil.close(pstmt);
			CloseUtil.close(conn);
		} // try end

		return false;
	}// insert() end

	/*
	 * //getListAllCount() : list.jsp 페이지에서 사용할 레코드 갯수 얻어오는 함수 public int
	 * getListAllCount() { Connection conn = null; PreparedStatement pstmt = null;
	 * ResultSet rs = null; int count = 0;
	 * 
	 * try { conn = getConnection();
	 * 
	 * //현재 board 테이블의 레코드 수 구하기 pstmt =
	 * conn.prepareStatement("SELECT COUNT(*) FROM BOARD"); rs =
	 * pstmt.executeQuery();
	 * 
	 * if(rs.next()) count = rs.getInt(1);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { CloseUtil.close(rs);
	 * CloseUtil.close(pstmt); CloseUtil.close(conn); } //end try
	 * 
	 * return count; } //getListAllCount() end
	 * 
	 * //getSelectAll(start, end) : list.jsp에서 사용 public List<BoardVO>
	 * getSelectAll(int start, int end) { Connection conn = null; PreparedStatement
	 * pstmt = null; ResultSet rs = null; List list = null;
	 * 
	 * try { conn = getConnection(); StringBuffer sb = new StringBuffer();
	 * 
	 * // 방법 3> //sb.
	 * append("SELECT j.* FROM (	SELECT k.*, rownum r FROM( SELECT * FROM board ORDER BY ref desc, re_step asc	) k ) j WHERE j.r BETWEEN ? AND ?"
	 * );
	 * 
	 * // 방법 2> //sb.
	 * append("select * from (select rownum as r,  x.*   from (select *  from board  order by ref desc, re_step asc)  x ) where r BETWEEN ? and ?"
	 * );
	 * 
	 * //방법 1> sb.
	 * append("SELECT NUM, WRITER, EMAIL, SUBJECT, PASSWD, REG_DATE, REF, RE_STEP, RE_LEVEL, CONTENT, IP, READCOUNT, R "
	 * ); sb.
	 * append("FROM(SELECT NUM, WRITER, EMAIL, SUBJECT, PASSWD, REG_DATE, REF, RE_STEP, RE_LEVEL, CONTENT, IP, READCOUNT, ROWNUM R "
	 * ); sb.
	 * append("FROM(SELECT NUM, WRITER, EMAIL, SUBJECT, PASSWD, REG_DATE, REF, RE_STEP, RE_LEVEL, CONTENT, IP, READCOUNT "
	 * ); sb.
	 * append("FROM BOARD ORDER BY REF DESC, RE_STEP ASC, re_level ) ORDER BY REF DESC,  re_step asc, re_level asc, reg_date asc) WHERE R>=? AND R<=?"
	 * );
	 * 
	 * 
	 * 
	 * select num, writer, email, subject, passwd, reg_date, ref, re_step, re_level,
	 * content, ip, readcount, r from(select num, writer, email, subject, passwd,
	 * reg_date, ref, re_step, re_level, content, ip, readcount, rownum r
	 * from(select num, writer, email, subject, passwd, reg_date, ref, re_step,
	 * re_level, content, ip, readcount from board order by ref desc, re_step asc)
	 * order by ref desc, re_step asc, re_level asc, reg_date asc) where r>=1 and
	 * r<=50;
	 * 
	 * //방법 4> sb.
	 * append("select num, writer, email, subject, passwd, reg_date, ref, re_step, re_level,"
	 * ); sb.
	 * append("content, ip, readcount, r from(select num, writer, email, subject, passwd,"
	 * ); sb.
	 * append("reg_date, ref, re_step, re_level, content, ip, readcount, rownum r  "
	 * ); sb.
	 * append("from(select num, writer, email, subject, passwd, reg_date, ref, re_step,"
	 * ); sb.
	 * append("re_level, content, ip, readcount from board order by ref desc, re_level asc)"
	 * ); sb.
	 * append("order by ref desc, re_step asc, re_level asc, reg_date asc) where r>=? and r <=?"
	 * );
	 * 
	 * //System.out.println(sb.toString());
	 * 
	 * pstmt = conn.prepareStatement(sb.toString()); pstmt.setInt(1, start);
	 * pstmt.setInt(2, end); rs = pstmt.executeQuery();
	 * 
	 * if(rs.next()) { list = new ArrayList(end);
	 * 
	 * do { BoardVO vo = new BoardVO(); vo.setNum(rs.getInt("num"));
	 * vo.setWriter(rs.getString("writer")); vo.setEmail(rs.getString("email"));
	 * vo.setSubject(rs.getString("subject")); vo.setPasswd(rs.getString("passwd"));
	 * vo.setReg_date(rs.getTimestamp("reg_date"));
	 * vo.setReadcount(rs.getInt("readcount")); vo.setRef(rs.getInt("ref"));
	 * vo.setRe_level(rs.getInt("re_level")); vo.setRe_step(rs.getInt("re_step"));
	 * vo.setIp(rs.getString("ip")); vo.setContent(rs.getString("content"));
	 * 
	 * //list 객체에 데이터 저장빈인 BoardVO 객체에 저장 list.add(vo);
	 * 
	 * }while(rs.next()); } //end if
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { CloseUtil.close(rs);
	 * CloseUtil.close(pstmt); CloseUtil.close(conn); } return list; }
	 * //getSelectAll() end
	 * 
	 * //상세보기 : content.jsp - getDataDetail(num) public BoardVO getDataDetail(int
	 * num) { Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null;
	 * 
	 * BoardVO vo = null; String sql = "";
	 * 
	 * try { conn = getConnection(); //조회수 증가 sql =
	 * "update board set readcount = readcount + 1 where num = ?"; pstmt =
	 * conn.prepareStatement(sql); pstmt.setInt(1, num); pstmt.executeUpdate();
	 * 
	 * pstmt = conn.prepareStatement("select * from Board where num = ?");
	 * pstmt.setInt(1, num); rs = pstmt.executeQuery();
	 * 
	 * if( rs.next() ) { vo = new BoardVO(); vo.setNum(rs.getInt("num"));
	 * vo.setWriter(rs.getString("writer")); vo.setEmail(rs.getString("email"));
	 * vo.setSubject(rs.getString("subject")); vo.setPasswd(rs.getString("passwd"));
	 * vo.setReg_date(rs.getTimestamp("reg_date"));
	 * vo.setReadcount(rs.getInt("readcount")); vo.setRef(rs.getInt("ref"));
	 * vo.setRe_level(rs.getInt("re_level")); vo.setRe_step(rs.getInt("re_step"));
	 * vo.setIp(rs.getString("ip")); vo.setContent(rs.getString("content")); }//if
	 * end
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }finally { CloseUtil.close(rs);
	 * CloseUtil.close(pstmt); CloseUtil.close(conn); }//try end
	 * 
	 * return vo; }//getDataDetail(num) end
	 * 
	 * //delete( num, passwd ) - deletePro.jsp public int delete(int num, String
	 * passwd) { Connection conn = null; PreparedStatement pstmt = null; ResultSet
	 * rs = null; String dbpasswd = ""; int result = 0;
	 * 
	 * try { conn = getConnection(); pstmt =
	 * conn.prepareStatement("SELECT PASSWD FROM BOARD WHERE NUM = ?");
	 * pstmt.setInt(1, num); rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { dbpasswd = rs.getString("passwd");
	 * 
	 * if (dbpasswd.equals(passwd)) { pstmt =
	 * conn.prepareStatement("DELETE FROM BOARD WHERE NUM=?"); pstmt.setInt(1, num);
	 * result = pstmt.executeUpdate(); result = 1; // 글삭제 성공 } else result = 0; //
	 * 비밀번호 틀림
	 * 
	 * } // out if end
	 * 
	 * } catch (Exception ex) { ex.printStackTrace(); } finally {
	 * CloseUtil.close(rs); CloseUtil.close(pstmt); CloseUtil.close(conn); } // end
	 * try
	 * 
	 * return result; } // delete( num, passwd ) end
	 * 
	 * //update(num) - 업데이트시 사용하는 함수 (디비에서 다 가져다가 화면 출력) public BoardVO update(int
	 * num) { Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null; BoardVO vo = null;
	 * 
	 * try { conn = getConnection(); pstmt =
	 * conn.prepareStatement("select * from board where num=?"); pstmt.setInt(1,
	 * num); rs = pstmt.executeQuery();
	 * 
	 * if( rs.next() ) { vo = new BoardVO(); vo.setNum(rs.getInt("num"));
	 * vo.setWriter(rs.getString("writer")); vo.setEmail(rs.getString("email"));
	 * vo.setSubject(rs.getString("subject")); vo.setPasswd(rs.getString("passwd"));
	 * vo.setReg_date(rs.getTimestamp("reg_date"));
	 * vo.setReadcount(rs.getInt("readcount")); vo.setRef(rs.getInt("ref"));
	 * vo.setRe_level(rs.getInt("re_level")); vo.setRe_step(rs.getInt("re_step"));
	 * vo.setIp(rs.getString("ip")); vo.setContent(rs.getString("content")); }//if
	 * end
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }finally { CloseUtil.close(rs);
	 * CloseUtil.close(pstmt); CloseUtil.close(conn); } // end try
	 * 
	 * return vo; }//update(num) end
	 * 
	 * //update(vo) - 글수정시 처리 함수 <== updatePro.jsp에서 사용 public int update(BoardVO
	 * vo) { Connection conn = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null;
	 * 
	 * String dbpasswd = ""; String sql = ""; int result = -1;
	 * 
	 * try { conn = getConnection();
	 * 
	 * pstmt = conn.prepareStatement("select passwd from board where num = ?");
	 * pstmt.setInt(1, vo.getNum()); rs = pstmt.executeQuery();
	 * 
	 * if( rs.next() ) { dbpasswd = rs.getString("passwd");
	 * 
	 * if( dbpasswd.equals(vo.getPasswd())) { sql =
	 * "update board set writer=?, email=?, subject=?,"; sql +=
	 * " passwd=?, content=? where num = ?";
	 * 
	 * pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, vo.getWriter()); pstmt.setString(2, vo.getEmail());
	 * pstmt.setString(3, vo.getSubject()); pstmt.setString(4, vo.getPasswd());
	 * pstmt.setString(5, vo.getContent()); pstmt.setInt(6, vo.getNum());
	 * 
	 * pstmt.executeUpdate(); result = 1;
	 * 
	 * } else { result = 0; }// in if end } // out if end
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { CloseUtil.close(rs);
	 * CloseUtil.close(pstmt); CloseUtil.close(conn); } // end try
	 * 
	 * return result; }// update(BoardVO vo) end
	 */}
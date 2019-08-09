package room.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc:OracleDB");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Boolean insert(MemberVO vo) throws SQLException {

		String sql = "";

		int result = 0;

		try {

			con = ds.getConnection();

			sql = "insert into member(name,id,pwd,email,phone,usernum) values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setInt(6, vo.getUsernum());
			result = pstmt.executeUpdate();

			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("회원등록 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return false;

	}

	public Boolean cinsert(MemberVO vo) throws SQLException {

		String sql = "";

		int result = 0;

		try {

			con = ds.getConnection();

			sql = "insert into member(name,id,pwd,email,phone, cname, usernum) values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getCname());
			pstmt.setInt(7, vo.getUsernum());
			result = pstmt.executeUpdate();

			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("회원등록 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return false;

	}

	public int loginCheck(String id, String pwd) {
		System.out.println("loginCheck");
		String dbPW = ""; // db에서 꺼낸 비밀번호를 담을 변수
		int x = 0;

		try {
			// 쿼리 - 먼저 입력된 아이디로 DB에서 비밀번호를 조회한다.
			String sql = "SELECT PWD FROM MEMBER WHERE ID=?";

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("id = " + id);

			if (rs.next()) // 입려된 아이디에 해당하는 비번 있을경우
			{
				dbPW = rs.getString("pwd"); // 비번을 변수에 넣는다.
				if (dbPW.equals(pwd)) {
					x = 1; // 넘겨받은 비번과 꺼내온 비번 비교. 같으면 인증성공
					System.out.println("아이디O 비번O dbpw = " + dbPW);
				} else {
					x = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패
					System.out.println("아이디O 비번X dbpw = " + dbPW);
				}
			} else {
				System.out.println("아이디X 비번X dbpw = " + dbPW);
				x = 0; // 해당 아이디가 없을 경우
			}

			return x;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return x;

	}

}

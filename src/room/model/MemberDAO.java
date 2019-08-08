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
		System.out.println("MemberDAO");
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc:OracleDB");
			System.out.println("연결");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Boolean insert(MemberVO vo) throws SQLException {
		System.out.println(vo.getName());
		System.out.println(vo.getId());
		System.out.println(vo.getPwd());
		System.out.println(vo.getEmail());
		System.out.println(vo.getPhone());
		System.out.println(vo.getUsernum());
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
		}

		return false;

	}

}

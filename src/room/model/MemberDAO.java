package room.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDAO() {
		try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void insert(MemberVO vo) {
		
		try {
			
			String sql = "insert into member(name, id, pwd, email, phone, usernum) values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setInt(6, vo.getUsernum());
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

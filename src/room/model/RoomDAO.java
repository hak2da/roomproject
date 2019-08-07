package room.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RoomDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public RoomDAO() {
		try{
			Context init = new InitialContext();
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	  		System.out.println("연결성공");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	private static RoomDAO instance = new RoomDAO();
	
	public static RoomDAO getInstance() {
		return instance;
	}
	
	//방정보 추가
	public boolean roomadd(RoomVO room) {
		
		String sql="";
		int result=0;
		
		try {
		sql = "insert into roominfo (address,image1,image2,image3,image4,image5,deposit,rent,roomtype,mpay,mpay2,rsize,parking,elve,floor,rdate,title,content) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		pstmt.setString(1, room.getAddress());
		pstmt.setString(2, room.getImage1());
		pstmt.setString(3, room.getImage2());
		pstmt.setString(4, room.getImage3());
		pstmt.setString(5, room.getImage4());
		pstmt.setString(6, room.getImage5());
		pstmt.setInt(7, room.getDeposit());
		pstmt.setInt(8, room.getRent());
		pstmt.setString(9, room.getRoomtype());
		pstmt.setInt(10, room.getMpay());
		pstmt.setString(11, room.getMpay2());
		pstmt.setInt(12, room.getRsize());
		pstmt.setInt(13, room.getParking());
		pstmt.setInt(14, room.getElve());
		pstmt.setString(15, room.getFloor());
		pstmt.setDate(16, (Date) room.getRdate());
		pstmt.setString(17, room.getTitle());
		pstmt.setString(18, room.getContent());
		
		result=pstmt.executeUpdate();
		if(result==0)return false;
		
		return true;
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	
}
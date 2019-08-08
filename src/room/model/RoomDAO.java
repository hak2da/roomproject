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
	  	    ds = (DataSource) init.lookup("java:comp/env/jdbc:OracleDB");
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
		sql = "INSERT INTO ROOMINFO (ADDRESS,IMAGE1,IMAGE2,IMAGE3,IMAGE4,IMAGE5,DEPOSIT,RENT,ROOMTYPE,MPAY,MPAY2,RSIZE,PARKING,ELVE,FLOOR,RDATE,TITLE,CONTENT) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		pstmt.setString(1, room.getADDRESS());
		pstmt.setString(2, room.getIMAGE1());
		pstmt.setString(3, room.getIMAGE2());
		pstmt.setString(4, room.getIMAGE3());
		pstmt.setString(5, room.getIMAGE4());
		pstmt.setString(6, room.getIMAGE5());
		pstmt.setInt(7, room.getDEPOSIT());
		pstmt.setInt(8, room.getRENT());
		pstmt.setString(9, room.getROOMTYPE());
		pstmt.setInt(10, room.getMPAY());
		pstmt.setString(11, room.getMPAY2());
		pstmt.setInt(12, room.getRSIZE());
		pstmt.setInt(13, room.getPARKING());
		pstmt.setInt(14, room.getELVE());
		pstmt.setString(15, room.getFLOOR());
		pstmt.setDate(16, (Date) room.getRDATE());
		pstmt.setString(17, room.getTITLE());
		pstmt.setString(18, room.getCONTENT());
		
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
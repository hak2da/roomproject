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
			return;
		}
	}
	
	//방정보 추가
	public boolean roomadd(RoomVO room) {
		
		String sql="";
		int result=0;
		
		try {
		sql = "INSERT INTO ROOMINFO (NADDRESS,RADDRESS,IMAGE1,IMAGE2,IMAGE3,IMAGE4,IMAGE5,DEPOSIT,RENT,ROOMTYPE,MPAY,MPAY2,RSIZE,PARKING,ELVE,FLOOR,RDATE,TITLE,CONTENT) VALUES "
				+ "					(       ?,       ?,     ?,     ?,     ?,     ?,     ?,      ?,   ?,       ?,   ?,    ?,    ?,      ?,   ?,    ?,    ?,    ?,      ?)";
			
		con = ds.getConnection();
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, room.getNADDRESS());
		pstmt.setString(2, room.getRADDRESS());
		pstmt.setString(3, room.getIMAGE1());
		pstmt.setString(4, room.getIMAGE2());
		pstmt.setString(5, room.getIMAGE3());
		pstmt.setString(6, room.getIMAGE4());
		pstmt.setString(7, room.getIMAGE5());
		pstmt.setInt(8, room.getDEPOSIT());
		pstmt.setInt(9, room.getRENT());
		pstmt.setString(10, room.getROOMTYPE());
		pstmt.setString(11, room.getMPAY());
		pstmt.setString(12, room.getMPAY2());
		pstmt.setInt(13, room.getRSIZE());
		pstmt.setString(14, room.getPARKING());
		pstmt.setString(15, room.getELVE());
		pstmt.setString(16, room.getFLOOR());
		pstmt.setString(17, room.getRDATE());
		pstmt.setString(18, room.getTITLE());
		pstmt.setString(19, room.getCONTENT());
		
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
package room.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.action.ActionForward;
import room.model.RoomDAO;
import room.model.RoomVO;


public class RoomOutAction implements Action{

	public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) throws Exception{
		
		RoomDAO dao = new RoomDAO();
		RoomVO vo = new RoomVO();
		ActionForward forward=new ActionForward();
		
		boolean result=false;
		
		try{
			System.out.println("RoomOutAction");
			vo.setADDRESS(request.getParameter("ADDRESS"));
			vo.setIMAGE1(request.getParameter("IMAGE1"));
			vo.setIMAGE2(request.getParameter("IMAGE1"));
			vo.setIMAGE3(request.getParameter("IMAGE1"));
			vo.setIMAGE4(request.getParameter("IMAGE1"));
			vo.setIMAGE5(request.getParameter("IMAGE1"));
			vo.setDEPOSIT(Integer.parseInt(request.getParameter("DEPOSIT")));
			vo.setRENT(Integer.parseInt(request.getParameter("RENT")));
			vo.setROOMTYPE(request.getParameter("ROOMTYPE"));
			vo.setMPAY(Integer.parseInt(request.getParameter("MPAY")));
			vo.setMPAY2(request.getParameter("MPAY2"));
			vo.setRSIZE(Integer.parseInt(request.getParameter("RSIZE")));
			vo.setPARKING(Integer.parseInt(request.getParameter("PARKING")));
			vo.setELVE(Integer.parseInt(request.getParameter("ELVE")));
			vo.setRDATE(new Date("RDATE"));
			vo.setTITLE(request.getParameter("TITLE"));
			vo.setCONTENT(request.getParameter("CONTENT"));
		
			result = dao.roomadd(vo);
			
			if(result==false){
	   			System.out.println("등록 실패");
	   			return null;
	   		}
	   		System.out.println("등록 완료");
	   		
		forward.setRedirect(true);
   		forward.setPath("./RoomOut.do");
   		return forward;
   		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

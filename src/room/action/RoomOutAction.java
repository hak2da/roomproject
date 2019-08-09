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
			request.setCharacterEncoding("UTF-8");
			String MPAY3 ="";
			int count = 0;
			String[] value = request.getParameterValues("MPAY2");
			for (String val : value) {
				count ++;
				if(value.length == 1) {
					MPAY3 = val;
					break;
				}
				if(count == value.length) {
					MPAY3 += val;
					break;
				}
					MPAY3 += val+",";
			}
			
			String MPAY4 = request.getParameter("MPAY");
			if(MPAY4 == null) {
				MPAY4 = "없음";
			}
			String NADDRESS = request.getParameter("NADDRESS") + " " + request.getParameter("ADDRESS2") + request.getParameter("ADDRESS3");
			String RADDRESS = request.getParameter("RADDRESS") + " " + request.getParameter("ADDRESS2") + request.getParameter("ADDRESS3");
			System.out.println(NADDRESS);
			System.out.println(RADDRESS);
			System.out.println("RoomOutAction");
			vo.setNADDRESS(NADDRESS);
			vo.setRADDRESS(RADDRESS);
			vo.setIMAGE1(request.getParameter("IMAGE1"));
			vo.setIMAGE2(request.getParameter("IMAGE1"));
			vo.setIMAGE3(request.getParameter("IMAGE1"));
			vo.setIMAGE4(request.getParameter("IMAGE1"));
			vo.setIMAGE5(request.getParameter("IMAGE1"));
			vo.setDEPOSIT(Integer.parseInt(request.getParameter("DEPOSIT")));
			vo.setRENT(Integer.parseInt(request.getParameter("RENT")));
			vo.setROOMTYPE(request.getParameter("ROOMTYPE"));
			vo.setMPAY(MPAY4);
			vo.setMPAY2(MPAY3);
			vo.setRSIZE(Integer.parseInt(request.getParameter("RSIZE")));
			vo.setPARKING(request.getParameter("PARKING"));
			vo.setELVE(request.getParameter("ELVE"));
			vo.setFLOOR(request.getParameter("FLOOR"));
			vo.setRDATE(request.getParameter("RDATE"));
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

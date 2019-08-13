package room.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import room.action.ActionForward;
import room.model.RoomDAO;
import room.model.RoomVO;


public class RoomOutAction implements Action{

	public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) throws Exception{
		
		RoomDAO dao = new RoomDAO();
		RoomVO vo = new RoomVO();
		ActionForward forward=new ActionForward();
		String realFolder="";
   		String saveFolder="/image";
		
		boolean result=false;
		
		int fileSize=5*1024*1024;
   		
   		realFolder=request.getRealPath(saveFolder);
		
		try{
			
			MultipartRequest multi=null;
   			
   			multi=new MultipartRequest(request,
   					realFolder,
   					fileSize,
   					"UTF-8",
   					new DefaultFileRenamePolicy());
			
			String MPAY3 ="";
			int count = 0;
			String[] value = multi.getParameterValues("MPAY2");
			System.out.println(value);
			System.out.println(multi.getParameterValues("MPAY2"));
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
			
			String MPAY4 = multi.getParameter("MPAY");
			if(MPAY4 == null) {
				MPAY4 = "없음";
			}
			String NADDRESS = multi.getParameter("NADDRESS") + " " + multi.getParameter("ADDRESS2") + multi.getParameter("ADDRESS3");
			String RADDRESS = multi.getParameter("RADDRESS") + " " + multi.getParameter("ADDRESS2") + multi.getParameter("ADDRESS3");
			System.out.println("RoomOutAction");
			vo.setNADDRESS(NADDRESS);
			vo.setRADDRESS(RADDRESS);
			vo.setIMAGE1(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			vo.setIMAGE2(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			vo.setIMAGE3(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			vo.setIMAGE4(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			vo.setIMAGE5(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
			vo.setDEPOSIT(Integer.parseInt(multi.getParameter("DEPOSIT")));
			vo.setRENT(Integer.parseInt(multi.getParameter("RENT")));
			vo.setROOMTYPE(multi.getParameter("ROOMTYPE"));
			vo.setMPAY(MPAY4);
			vo.setMPAY2(MPAY3);
			vo.setRSIZE(Integer.parseInt(multi.getParameter("RSIZE")));
			vo.setPARKING(multi.getParameter("PARKING"));
			vo.setELVE(multi.getParameter("ELVE"));
			vo.setFLOOR(multi.getParameter("FLOOR"));
			vo.setRDATE(multi.getParameter("RDATE"));
			vo.setTITLE(multi.getParameter("TITLE"));
			vo.setCONTENT(multi.getParameter("CONTENT"));
		
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

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

		String realFolder="D:\\jee-2019-03\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\roomProject\\image";

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
			
			String NADDRESS;
			String RADDRESS;
			if (multi.getParameter("NADDRESS").isEmpty()) {
				NADDRESS = "없음";
			} else {
				NADDRESS = multi.getParameter("NADDRESS") + " " + multi.getParameter("ADDRESS2") + multi.getParameter("ADDRESS3");
			}
			if (multi.getParameter("RADDRESS").isEmpty()) {
				RADDRESS = "없음";
			} else {
				RADDRESS = multi.getParameter("RADDRESS") + " " + multi.getParameter("ADDRESS2") + multi.getParameter("ADDRESS3");
			}
			vo.setNADDRESS(NADDRESS);
			vo.setRADDRESS(RADDRESS);
			vo.setIMAGE1(multi.getFilesystemName("FILE1"));
			vo.setIMAGE2(multi.getFilesystemName("FILE2"));
			vo.setIMAGE3(multi.getFilesystemName("FILE3"));
			vo.setIMAGE4(multi.getFilesystemName("FILE4"));
			vo.setIMAGE5(multi.getFilesystemName("FILE5"));
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
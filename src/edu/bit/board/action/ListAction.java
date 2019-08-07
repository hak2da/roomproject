package edu.bit.board.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bit.board.model.BoardDAO;

public class ListAction implements CommandAction {

	//jsp 파일에 있던 java코드들을 싹 걷어내 이쪽으로 몰아둠.
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {


		int pageSize = 7; //화면에 출력 레코드 수 
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageNum = request.getParameter("pageNum");
		
		if( pageNum == null ) pageNum = "1";
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage * pageSize) - 6 ;  // 1
		int endRow = currentPage * pageSize ;  // 7
		int count = 0, number = 0;	//count 전체 페이지수로쓰임
		
		List list = null;
		BoardDAO  dao = BoardDAO.getInstance();
		count = dao.getListAllCount(); //전체 페이지 리턴...
		
		if( count > 0 )  list = dao.getSelectAll(startRow, endRow);  //레코드 목록 보기
		
		number = count - (currentPage - 1) * pageSize ;	
		// ex) 15 - (2-1)*7 = 8
		
		//해당 뷰에서 사용할 속성(저장)  위쪽은 손대은거 없고 request 부분만 새로작성했어
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("list", list);	//그냥 list로 썻을뿐 bo 등으로 바꿔도되 보안위해 "" 부분

		return "/board/list.jsp";	//request.jsp로 가세요래 이거 의미가
	}
}

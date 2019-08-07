package room.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import room.board.model.BoardDAO;

public class WriteFormBoardCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insert(null);
	}

}

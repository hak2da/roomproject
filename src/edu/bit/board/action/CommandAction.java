package edu.bit.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//요청 파라미터로 명령어로 전달하는 수퍼인터페이스
public interface CommandAction {
	
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}

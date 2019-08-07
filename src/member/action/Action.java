package member.action;

import javax.servlet.http.*;

public interface Action {
	public String process(HttpServletRequest request, HttpServletResponse response) 
			throws Exception;
}


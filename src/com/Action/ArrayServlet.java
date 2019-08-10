package com.Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import map.bit.kakaomaptest.kakaoDAO;
import map.bit.kakaomaptest.kakaoVO;

@WebServlet("/ArrayServlet.do11")
public class ArrayServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ArrayServlet() {
		super();
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		if (request.getParameterValues("string[]") != null) {
			String[] arrayStr = request.getParameterValues("string[]");
			System.out.println(Arrays.toString(arrayStr) + "here!");

			kakaoVO vo = new kakaoVO(arrayStr[1], arrayStr[0], arrayStr[2], "123");
			int result = kakaoDAO.getInstance().insert(vo);

			System.out.println("data save success!" + result);

			PrintWriter out = response.getWriter();
			out.write("[\"" + arrayStr[2] + "\",\"" + result + "\"]");
			out.close();
		}		

		if (request.getParameter("latlng") != null) {
			String latlng = request.getParameter("latlng");
			//System.out.println(latlng);
			ArrayList<kakaoVO> clusterarr =new ArrayList<kakaoVO>();
			clusterarr = kakaoDAO.getInstance().getdb_to_markercluster(latlng);
			
			System.out.println("map에서 가져온 클러스터 데이터 ="+clusterarr.size());
			
			
	        //person의 JSON정보를 담을 Array 선언
	        JSONArray personArray = new JSONArray();
	        //person의 한명 정보가 들어갈 JSONObject 선언
	        
	      //  JSONObject personInfo = new JSONObject();
	        //정보 입력
	        //JSONA
	        
	        JSONObject personInfoinner= null;
			for (int i = 0; i < clusterarr.size(); i++) {
	        	JSONObject personInfo= new JSONObject();
	        	personInfo.put("lat", clusterarr.get(i).getLat());
		        personInfo.put("lng", clusterarr.get(i).getLng());
		        personInfoinner = new JSONObject();
		        personInfoinner.put("position", personInfo);
		        personInfoinner.put("text", clusterarr.get(i).getAddr());
		        personArray.add(personInfoinner);
		        	
			}
			System.out.println();
			System.out.println(personArray.toJSONString());
			System.out.println();
			
			/*
			 * if(personArray != null){ PrintWriter out = response.getWriter(); //Iterable
			 * it = (Iterable)personInfoinner.keySet().iterator();
			 * out.write("[\""+personInfoinner+"\"]"); out.close(); }
			 */      
			
			PrintWriter out = response.getWriter();
			out.println(personArray);
			out.flush();
			out.close();
			/*
			 * out.write("[\"" + "123" + "\",\"" + "234" + "\"]"); out.close();
			 */
			
			
			 
			
			
			
			//맵에보이는 최대  좌측상단 좌측하단 우측상단 우측하단에 좌표값을 보내고 해당좌표값안에있는 마커클러스터데이터
			//를  kakaoVO의 ArrayList로 보낸다.			
			
			
			//다음해야할거는 ArrayList를 jsp로 보내서 마커클러스터 찍는것
			
			
			
			
			
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}

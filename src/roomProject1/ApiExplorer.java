package roomProject1;




	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.net.URLEncoder;
	import java.io.BufferedReader;
	import java.io.IOException;

	public class ApiExplorer {
	    public static void main(String[] args) throws IOException {
	        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1611000/AptBasisInfoService/getAphusBassInfo"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=pxt8AzcUdE2DwkqgLScF6ZPnD81YPirv%2Fy27kelZrdY%2BeVMbKnOR5yUHoQ2JQHVlhlzaijJliFD2SiGIirqLtQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("kaptCode","UTF-8") + "=" + URLEncoder.encode("A10027875", "UTF-8")); /**/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	    }
	}

	
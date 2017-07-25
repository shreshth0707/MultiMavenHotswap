package org.sonatype.mavenbook.weather;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class YahooRetriever {

	private static Logger log = Logger.getLogger(YahooRetriever.class);

	public String retrieve(String zipcode) throws Exception {
		log.info( "Retrieving Weather Data" );
	//	String url = "http://weather.yahooapis.com/forecastrss?p=" + zipcode;
		String query = "select * from weather.forecast where woeid in (select woeid from geo.places where placetype='Zip' AND text='" + zipcode + "' )";
		String url = "https://query.yahooapis.com/v1/public/yql?q=" + URLEncoder.encode(query, "UTF-8");
		//String url = "http://weather.yahooapis.com/forecastrss?w=" + zipcode;
		System.out.println("URL Called: "+url);
        // Add this if you need to connect via a corporate proxy
  //    String proxyHost = "10.0.50.3";
  //    int proxyPort = 8080;
  //    SocketAddress addr = new InetSocketAddress(proxyHost, proxyPort);
 //     Proxy httpProxy = new Proxy(Proxy.Type.HTTP, addr);
  //    URLConnection conn = new URL(url).openConnection(httpProxy);
		
		URL urls=new URL(url);
//		URLConnection conn = new URL(url).openConnection();
//		return conn.getInputStream();
		
		Scanner s=new Scanner(urls.openStream());
		String str=new String();
		while(s.hasNext())
			str+=s.nextLine();
		s.close();	
		
		return str;
		
	}

}

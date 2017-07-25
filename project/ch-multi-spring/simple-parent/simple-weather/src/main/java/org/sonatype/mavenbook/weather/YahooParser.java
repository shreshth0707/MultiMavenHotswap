package org.sonatype.mavenbook.weather;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;
import org.jaxen.function.ConcatFunction;
import org.json.JSONObject;
import org.sonatype.mavenbook.weather.model.Atmosphere;
import org.sonatype.mavenbook.weather.model.Condition;
import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Weather;
import org.sonatype.mavenbook.weather.model.Wind;

public class YahooParser {

    private static Logger log = Logger.getLogger(YahooParser.class);
    
    public Weather parse(String zip, String str) throws Exception {
	Weather weather = new Weather();
	
	log.info( "Creating XML Reader" );
//	log.info(dummy+" is printed");
/*
	SAXReader xmlReader = createXmlReader();
	Document doc = xmlReader.read( inputStream );
	
	log.info( "Parsing XML Response" +doc.getText());
	Location location = new Location();
	String lc=(String)doc.valueOf("/rss/channel/y:location/@city");
	location.setCity( lc );
	location.setRegion( doc.valueOf("/rss/channel/y:location/@region") );
	location.setCountry( doc.valueOf("/rss/channel/y:location/@country") );
	location.setZip( zip );
	weather.setLocation( location );

	Condition condition = new Condition();
	condition.setText( doc.valueOf("/rss/channel/item/y:condition/@text") );
	condition.setTemp( doc.valueOf("/rss/channel/item/y:condition/@temp") );
	condition.setCode( doc.valueOf("/rss/channel/item/y:condition/@code") );
	condition.setDate( doc.valueOf("/rss/channel/item/y:condition/@date") );
	condition.setWeather( weather );
	weather.setCondition( condition );

	Atmosphere atmosphere = new Atmosphere();
	atmosphere.setHumidity( doc.valueOf("/rss/channel/y:atmosphere/@humidity") );
	atmosphere.setVisibility( doc.valueOf("/rss/channel/y:atmosphere/@visibility") );
	atmosphere.setPressure( doc.valueOf("/rss/channel/y:atmosphere/@pressure") );
	atmosphere.setRising( doc.valueOf("/rss/channel/y:atmosphere/@rising") );
	atmosphere.setWeather( weather );
	weather.setAtmosphere( atmosphere );

	Wind wind = new Wind();
	wind.setChill( doc.valueOf("/rss/channel/y:wind/@chill") );
	wind.setDirection( doc.valueOf("/rss/channel/y:wind/@direction") );
	wind.setSpeed( doc.valueOf("/rss/channel/y:wind/@speed") );
	wind.setWeather( weather );
	weather.setWind( wind );

	weather.setDate( new Date() );
	*/
	JSONObject res=new JSONObject(str);
	
	JSONObject gen=(JSONObject)res.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
	JSONObject jloc=(JSONObject)gen.getJSONObject("location");
	Location location=new Location();
	location.setCity((String)jloc.get("city"));
	location.setCountry((String)jloc.optString("country"));
	location.setRegion((String)jloc.optString("region"));
	location.setZip(zip);
	System.out.println("city"+location.getCity() ); 
	System.out.println("Country" +location.getCountry()); 
	System.out.println("Region" + location.getRegion());
	weather.setLocation(location);
	
	
	JSONObject jwind=gen.getJSONObject("wind");
	Wind w=new Wind();
	w.setChill((String)jwind.get("chill"));
	w.setDirection((String)jwind.getString("direction"));
	w.setSpeed((String)jwind.getString("speed"));
	w.setWeather(weather);
	w.setId(7);
	weather.setWind(w);
	System.out.println(w.getId() + " " +w.getChill() + " " + w.getDirection() );
	
	
	
	JSONObject jatm=(JSONObject) gen.get("atmosphere");
	Atmosphere atmos=new Atmosphere();
	atmos.setId(3);
	atmos.setHumidity((String)jatm.getString("humidity"));
	atmos.setPressure((String)jatm.get("pressure"));
	atmos.setRising((String)jatm.getString("rising"));
	atmos.setVisibility((String)jatm.getString("visibility"));
	atmos.setWeather(weather);
	weather.setAtmosphere(atmos);
	
	JSONObject jcon=(JSONObject) gen.getJSONObject("item").getJSONObject("condition");
	Condition condition=new Condition();
	condition.setCode((String)jcon.get("code"));
	condition.setDate((String)jcon.get("date"));
	condition.setTemp((String)jcon.getString("temp"));
	condition.setText((String)jcon.get("text"));
	condition.setWeather(weather);
	condition.setId(77);
	weather.setCondition(condition);
	
	return weather;
    }
    
    private SAXReader createXmlReader() {
	Map<String,String> uris = new HashMap<String,String>();
        uris.put( "y", "http://xml.weather.yahoo.com/ns/rss/1.0" );
        
        DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs( uris );
        
	SAXReader xmlReader = new SAXReader();
	xmlReader.setDocumentFactory( factory );
	return xmlReader;
    }

	
}

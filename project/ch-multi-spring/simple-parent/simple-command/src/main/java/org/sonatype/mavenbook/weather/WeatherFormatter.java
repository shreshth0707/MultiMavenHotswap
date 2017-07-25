package org.sonatype.mavenbook.weather;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Weather;

public class WeatherFormatter {

	private static Logger log = Logger.getLogger(WeatherFormatter.class);



	public String formatHistory( Location location, List<Weather> weathers ) throws Exception {
		log.info( "Formatting History Data" );
		Reader reader = new InputStreamReader( getClass().getClassLoader().getResourceAsStream("history.vm"));
		VelocityContext context = new VelocityContext();
		context.put("location", location );
		context.put("weathers", weathers );
		StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "", reader);
		return writer.toString();		
	}

	public String formatWeather(Weather weather) {
		// TODO Auto-generated method stub
		log.info("formatting data");
		Reader reader=new InputStreamReader(getClass().getClassLoader().getResourceAsStream("weather.vm"));
		System.out.println(weather.getAtmosphere().getPressure());
		VelocityContext context=new VelocityContext();
		context.put("weather",weather);
		StringWriter writer=new StringWriter();
		try {
			Velocity.evaluate(context, writer, "", reader);
		} catch (ParseErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}
}

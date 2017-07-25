package org.sonatype.mavenbook.weather;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.hibernate.cfg.Environment;

import org.sonatype.mavenbook.weather.model.Weather;
//import org.springframework.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.support.ConfigurableConversionService;




public class WeatherService {

	static int a=10;
	
	private YahooRetriever yahooRetriever;
	private YahooParser yahooParser;
	//private democlass dc;
	
	
	
	

	public WeatherService() {
	}

	public Weather retrieveForecast(String zip) throws Exception {
		// Retrieve Data
		//String dataIn = yahooRetriever.retrieve(zip);

		// Parse DataS
	
		
		
		String url="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22noida%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		
		URL urls=new URL(url);
		Scanner s=new Scanner(urls.openStream());
		String str=new String();
		while(s.hasNext())
			str+=s.nextLine();
		s.close();	
		WeatherService ws=new WeatherService();
		a=90;
		
		int b=ws.no1(20,10);
		System.out.println("value returned from  no1 is " + b);
		
//		int c=no(10);
//		System.out.println("Value returned from no is "+ c);
		
		System.out.println("value of static varible a=" + a);
 		Weather weather = yahooParser.parse(zip, str);
 		
 		
 	
 		
 		/*
 		Parameters params = new Parameters();
 	// Read data from this file
 	File propertiesFile = new File("config.properties");

 	FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
 	    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
 	    .configure(params.fileBased()
 	        .setFile(propertiesFile));
 	try
 	{
 	    Configuration config = builder.getConfiguration();
 	    // config contains all properties read from the file
 	}
 	catch(ConfigurationException cex)
 	{
 	    // loading of the configuration file failed
 	}
 		
 		*/
 		
 		
 		
 		
 		
 
 		
 		// working correctly
 		PropertiesConfiguration configuration=null;
 		       try {
 		            configuration = new PropertiesConfiguration("demo.properties");
 		            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
 		        } catch (ConfigurationException e) {
 		            e.printStackTrace();
 	        } 			
 		System.out.println(configuration.getProperty("buddy"));
 		
 	
 		
 		
 		//	System.out.println(ap.getProperty("buddy"));
		
//	
//		Properties prop=new Properties();
//		InputStream input=;
		
//		String propname="demo.properties";
//		input=getClass().getClassLoader().getResourceAsStream(propname);
//		
//		input=getClass().getResourceAsStream("classpath*:demo.properties");
//		prop.load(input);	
//		
//		
//		String mess=(String)prop.getProperty("buddy");
	// System.out.println(buddy);
	
//		dc.printmessage("hello from democlass");
	
 		
 	
 		
 		
 		
 		
 	    
//        ConfigurableApplicationContext context
//                                = new ClassPathXmlApplicationContext(confFile);
//        beanclass dbConfig = (beanclass) context.getBean("beanclass");
//        System.out.println(dbConfig.toString());
//        context.close();

// 		PropertiesConfiguration xmlconfig=new PropertiesConfiguration("appconfig.xml");
// 		xmlconfig.setAutoSave(true);
// 		xmlconfig.setReloadingStrategy(new FileChangedReloadingStrategy());
// 		
 		
 		XMLConfiguration xmlconfig=null;
 		xmlconfig=new XMLConfiguration("appconfig.xml");
 		FileChangedReloadingStrategy strategy=new FileChangedReloadingStrategy();
 		strategy.setRefreshDelay(5000);
 		xmlconfig.setReloadingStrategy(strategy);
 		
 		
 		/*
 		String confFile = "appconfig.xml";
 		ConfigurableApplicationContext ac=new ClassPathXmlApplicationContext(confFile);
 	
 		HelloWorld helloworld=(HelloWorld) ac.getBean("helloworld");
 		helloworld.SetMessage("hello world");
 		System.out.println("11111111111111111111:::::output from helloworld class is " + helloworld.getMessage());
        
 		
 		HelloWorld1 helloworld1=(HelloWorld1)ac.getBean("helloworld1");
 		helloworld1.SetMessage("welcome to helloworld1");
 		System.out.println("222222222222222222:::::::" + helloworld1.getMessage());
 		*/
 		
		return weather;
	}

//	public int no(int x)
//	{
//		System.out.println("value of x in function no :" + x  );
//		return 3;
//	}
	
	public int no1(int x,int y)
	{
		System.out.println("value of x:" + x + " and y:" + y );
		return 4;
	}
	
	public YahooRetriever getYahooRetriever() {
		return yahooRetriever;
	}

	public void setYahooRetriever(YahooRetriever yahooRetriever) {
		this.yahooRetriever = yahooRetriever;
	}

	public YahooParser getYahooParser() {
		return yahooParser;
	}

	public void setYahooParser(YahooParser yahooParser) {
		this.yahooParser = yahooParser;
	}

}

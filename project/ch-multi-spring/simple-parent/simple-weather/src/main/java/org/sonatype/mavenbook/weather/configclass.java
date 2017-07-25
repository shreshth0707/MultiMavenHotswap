package org.sonatype.mavenbook.weather;

import org.springframework.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configclass {

	@Bean
	public HelloWorld helloworld()
	{
		return new HelloWorld();
	}
	
	@Bean
	public HelloWorld1 helloworld1()
	{
		return new HelloWorld1();
	}
}

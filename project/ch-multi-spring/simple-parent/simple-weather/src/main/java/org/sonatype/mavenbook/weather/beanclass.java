package org.sonatype.mavenbook.weather;


// useless class no work to do.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;


public class beanclass {
	
	private String content;
	private String buddy;
	
	public String getContent()
	{
		return content;
	}
	
	public String getbuddy()
	{
		return buddy;
	}
	
	public void setContent(String str)
	{
		this.content=str;
	}
	
	public void setBuddy(String str)
	{
		this.buddy=str;
	}
	
	@Override
	public String toString()
	{
		String a="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+ content + " " + buddy + " ";
		return a;
	}
}

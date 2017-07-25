# MultiMavenHotswap
Hotswap fuctionality implemented for a multiModule maven project


Steps to setup DCEVM + Hotswap.jar

Prerequisites:
1.	DCEVM jar
2.	Hotswap -Agent jar
3.	In order to install the above you need to have admin access for the installation purpose.
Steps to enable hotswap :
•	Run DCEVM.jar and install option in your JDK folder as "INSTALL DCEVM as altjvm".
			Screen will look like as below

•	Choose the jdk folder you are using(in our case jdk 1.8) and select Install DCEVM as altjvm.
•	Now take the hotswap-agent.jar  and paste it in your D drive (or any place you wish).
•	Change your project run command & add following command in it  :
◦	-XXaltjvm=dcevm -javaagent:C:\Users\shreshth\Downloads\hotswap-agent-1.1.0-SNAPSHOT.jar  -XX:+UnlockDiagnosticVMOptions -XX:+EnableInvokeDynamic

where ,
	highlighted part is the path for hotswap.jar. In case it is not in D: drive, change this part with the respective address.

•	Add a properties file with name hotswap-agent.properties file & add it in src/main/resources .
•	Add autoHotswap=true line in that file
•	start the application in debug mode, check that the agent and plugins are initialized correctly:


For change in resource files:
1.	Use apache.common.configuration. Include desired dependencies in the project.
2.	 PropertiesConfiguration configuration=null;
 		       try {
 		            configuration = new PropertiesConfiguration("demo.properties");
 		            configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
 		        } catch (ConfigurationException e) {
 		            e.printStackTrace();
 	        } 			

3.	Use configuration.getProperty(“field name”) to get the desired value corresponding to the key.
4.	Under watchresources,add   /simple-weather/src/main/resources/demo.properties 
ie: the path of the resource file.




Hotswap-Agent-Properties:

1.	Extra Classpath : This may be useful for example in multi module maven project to load class changes from upstream project classes. Set extraClasspath to upstream project compiler output and .class file will have precedence to classes from built JAR file.
Ex: extraClasspath=Module1/target/classes, Module2/target/classes.

2.	watchResources= Load static web resources from different directory.
Mention the full path of  the resource/properties file.
		       Ex:watchResources=/simple-weather/src/main/resources/demo.properties
3.    webappDir= Use this setting to set to serve resources from source directory directly (e.g.   
		src/main/webapp).
                             4.   disabledPlugins= Comma separated list of disabled plugins.
5.    autoHotswap=false  	
       This property is useful if you do not want to use debugging session for some reason or
        if you want to enable hotswap at runtime environment.




How to use Hotswap in IntelliJ :

	Run your code in debug mode. Now start the debugger & make needed change in java file .
Compile(Ctrl+Shift+F9) while in debug-state & you will see the class reloaded message on successful swapping.


How to use Hotswap in Eclipse :

	It is same as that of IntelliJ. But in this case you have to save(Ctrl+S) the code rather than Recompile.

Note
Make sure you have eclipse Luna or above. It won’t work for eclipse juno.


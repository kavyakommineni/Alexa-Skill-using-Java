This is the Alexa skill developed using Java. In Alexa developer console, I've created required intents and the corresponding intent slots to get the required data from the user.

Then I've created a Maven project with all the intent handlers which interacts with the RESTful api using HttpURLConnection to fetch the required data that the user has requested.

You can find the RESTful web serivces in this link
https://github.com/kavyakommineni/Rest-ws-using-Spring-boot-JPA/tree/master/RestWSusingSpringBootJPA

This is the jar file that is available here. To deploy onto Tomcat server, I've converted the Spring boot jar into war as follows:

In pom.xml,
change the packaging from jar to war

< packaging > war < / packaging >

and the scope of the tomcat should be provided

< artifactId > spring-boot-starter-tomcat </ artifactId 

< scope > provided </ scope >
			
The class with main method which has @SpringBootApplication should extend SpringBootServletIniializer and implement the unimplemented method as follows:

@Override

protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)

	{

	return builder.sources(currentclassName.class);

	}

Give the maven package goal and get the war file

I've used AWS EC2 to create a linux instance where I've installed Tomcat to deploy my war file.

Here is the sample conversation with Alexa that I've developed.

User  : hello

Alexa : may i know your name?

User  : my name is kavya

Alexa : Hi Kavya, how may i help you

User  : can you read out my orders

Alexa : To know your orders, can you please confirm your customer id?

User  : customer id is customerid

Alexa : Here are your orders for the customerid: ....orders details... What other details can I provide you?

User  : May I know the products of the order

Alexa : To know your products, can you please confirm your order id?

User  : my order id is orderid

Alexa : Displays the requested products for provided orderid. What other details can I provide you?

User  : May I know the seller of the product

Alexa : To know the seller details, can you please confirm your product id?

User  : my product id is productid

Alexa : Displays the sellers for provided productid. What other details can I provide you?

User  : Can you provide the date of the shipment of my order

Alexa : Displays the shipment details with the date for provided orderid. What other details can I provide you?

User  : Bye

Alexa : Goodbye.

package com.alexa.ask.helloworld.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alexa.ask.helloworld.model.Product;
import com.alexa.ask.helloworld.rest.UserRestServices;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

/**
 * @author Kavya Kommineni
 *
 */
public class ProductResultIntentHandler implements RequestHandler {
	static UserRestServices rest = new UserRestServices();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ProductResultIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
    	Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    	String orderId = null;
    	Slot ordId = slots.get("orderId");
    	orderId = ordId.getValue().toString();
    	String speechText = null;
    	
    	List<Product> list = new ArrayList<Product>();
    	
    	if(orderId!=null)
    	{
    		list = rest.getProducts(orderId);
    		if(list.size()>0)
    		{
    			StringBuilder sb = new StringBuilder();
    			sb.append("Your product details for the id ");
    			sb.append(orderId);
    			sb.append(" are ");
    			sb.append("\n");
    			for(Product product : list)
    			{
    				sb.append(product.getName());
    				sb.append(" which costs ");
    				sb.append(product.getPrice());
    			}
    			sb.append(" What other details can I provide you? ");
    			speechText = sb.toString();
    		}
    		else
    		{
    			speechText = "It seems to have no products associated with the order you requested. Please enter valid order id";
    		}
    	}
    	else
    	{
    		speechText = "To know your products, can you please confirm your customer id/ order id..";
    	}
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}
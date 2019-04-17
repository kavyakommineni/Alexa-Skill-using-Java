package com.alexa.ask.helloworld.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alexa.ask.helloworld.model.Orders;
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
public class OrderResultIntentHandler implements RequestHandler {
	static UserRestServices rest = new UserRestServices();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("OrderResultIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
    	Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    	String customerId=null;
    	Slot custId = slots.get("customerId");
    	customerId = custId.getValue().toString();
    	String speechText;
    	
    	if(customerId!=null)
    	{
    		List<Orders> list = rest.getOrders(customerId);
    		if(list.size()>0)
    		{
    			StringBuilder sb = new StringBuilder();
    			sb.append("Your orders for the customer id ");
    			sb.append(customerId);
    			sb.append(" are ");
    			sb.append("\n");
    			for(Orders order : list)
    			{
    				sb.append(order.getId());
    				sb.append(" which costs ");
    				sb.append(order.getCost());
    				sb.append(" has the status ");
    				sb.append(order.getStatus());
    			}
    			sb.append(" What other details can I provide you? ");
    			speechText = sb.toString();
    		}
    		else
    		{
    			speechText = "It seems to have no orders for the customer id you requested. Please enter valid customer id";
    		}
    	}
    	else
    	{
    		speechText = "To know your orders, can you please confirm your customer id..";
    	}
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}

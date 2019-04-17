package com.alexa.ask.helloworld.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alexa.ask.helloworld.model.Seller;
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
public class SellerResultIntentHandler implements RequestHandler {
	static UserRestServices rest = new UserRestServices();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SellerResultIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
    	Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    	String productId = null;
    	String speechText = null;
    	Slot prId = slots.get("productId");
    	productId = prId.getValue().toString();
    	
    	List<Seller> list = new ArrayList<Seller>();
    	
    	if(productId!=null)
    	{
    		list = rest.getSellers(productId);
    		if(list.size()>0)
    		{
    			StringBuilder sb = new StringBuilder();
    			sb.append("Your seller details for the product id ");
    			sb.append(productId);
    			sb.append(" are ");
    			sb.append("\n");
    			for(Seller seller : list)
    			{
    				sb.append(seller.getName());
    				sb.append(" whom you can reach at ");
    				sb.append(seller.getNumber());
    			}
    			sb.append(" What other details can I provide you? ");
    			speechText = sb.toString();
    		}
    		else
    		{
    			speechText = "It seems to have no sellers for the product you requested. Please enter valid product id";
    		}
    	}
    	else
    	{
    		speechText = "To know your sellers, can you please confirm your product id..";
    	}
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}

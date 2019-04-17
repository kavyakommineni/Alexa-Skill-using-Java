package com.alexa.ask.helloworld.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alexa.ask.helloworld.model.Shipment;
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
public class ShipmentResultIntentHandler implements RequestHandler {
	static UserRestServices rest = new UserRestServices();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ShipmentResultIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
    	Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    	String orderId=null;
    	Slot ordId = slots.get("orderId");
    	orderId = ordId.getValue().toString();
    	String speechText;
    	
    	if(orderId!=null)
    	{
    		List<Shipment> list = rest.getShipments(orderId);
    		if(list.size()>0)
    		{
    			StringBuilder sb = new StringBuilder();
    			sb.append("Your Shipment details for the order id ");
    			sb.append(orderId);
    			sb.append(" are ");
    			sb.append("\n");
    			for(Shipment shipment : list)
    			{
    				sb.append(shipment.getId());
    				sb.append(" which is having the shipment date as ");
    				sb.append(shipment.getDate());
    			}
    			sb.append(" What other details can I provide you? ");
    			speechText = sb.toString();
    		}
    		else
    		{
    			speechText = "It seems to have no shipments details for the order id you requested. Please enter valid order id";
    		}
    	}
    	else
    	{
    		speechText = "To know your shipment details, can you please confirm your order id..";
    	}
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}

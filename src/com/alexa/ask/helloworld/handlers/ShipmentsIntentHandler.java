package com.alexa.ask.helloworld.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.alexa.ask.helloworld.rest.UserRestServices;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * @author Kavya Kommineni
 *
 */
public class ShipmentsIntentHandler implements RequestHandler {
	static UserRestServices rest = new UserRestServices();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ShipmentsIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText;
    	
    	speechText = "To know your date of shipment, can you please confirm your shipment id or order id?";
    	
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}

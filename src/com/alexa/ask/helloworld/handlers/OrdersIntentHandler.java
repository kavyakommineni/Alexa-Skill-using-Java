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
public class OrdersIntentHandler implements RequestHandler {
	static UserRestServices rest = new UserRestServices();
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("OrdersIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText;
    	
    	speechText = "To know your orders, can you please confirm your customer id?";
    	
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}

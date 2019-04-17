package com.alexa.ask.helloworld.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

/**
 * @author Kavya Kommineni
 *
 */
public class NameIntentHandler implements RequestHandler {
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("NameIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();        
    	Map<String, Slot> slots = intentRequest.getIntent().getSlots();
    	Slot name = slots.get("name");
    	String speechText;
    	
    	if(name.getValue().toString()!=null)
    	{
    		speechText = "Hi " + name.getValue().toString() + " How may I help you";
    	}
    	else
    	{
    		speechText = "Can you please tell your name once again";
    	}
       return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Name", speechText)
                .withShouldEndSession(false)
                .build();
    }
}

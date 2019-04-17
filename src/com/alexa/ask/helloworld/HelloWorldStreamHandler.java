package com.alexa.ask.helloworld;

import com.alexa.ask.helloworld.handlers.CancelandStopIntentHandler;
import com.alexa.ask.helloworld.handlers.FallbackIntentHandler;
import com.alexa.ask.helloworld.handlers.HelloWorldIntentHandler;
import com.alexa.ask.helloworld.handlers.HelpIntentHandler;
import com.alexa.ask.helloworld.handlers.LaunchRequestHandler;
import com.alexa.ask.helloworld.handlers.NameIntentHandler;
import com.alexa.ask.helloworld.handlers.OrderResultIntentHandler;
import com.alexa.ask.helloworld.handlers.OrdersIntentHandler;
import com.alexa.ask.helloworld.handlers.ProductResultIntentHandler;
import com.alexa.ask.helloworld.handlers.ProductsIntentHandler;
import com.alexa.ask.helloworld.handlers.SellerResultIntentHandler;
import com.alexa.ask.helloworld.handlers.SellersIntentHandler;
import com.alexa.ask.helloworld.handlers.SessionEndedRequestHandler;
import com.alexa.ask.helloworld.handlers.ShipmentResultIntentHandler;
import com.alexa.ask.helloworld.handlers.ShipmentsIntentHandler;
import com.alexa.ask.helloworld.util.Utility;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

/**
 * @author Kavya Kommineni
 *
 */
public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new NameIntentHandler(),
                        new OrdersIntentHandler(),
                        new OrderResultIntentHandler(),
                        new ProductsIntentHandler(),
                        new ProductResultIntentHandler(),
                        new SellersIntentHandler(),
                        new SellerResultIntentHandler(),
                        new ShipmentsIntentHandler(),
                        new ShipmentResultIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler())
                // Add skill id of the our skill below
                .withSkillId(Utility.ALEXA_SKILL_ID)
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}

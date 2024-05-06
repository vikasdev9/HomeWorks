package com.example.demo.Controller.PaymentController;

import com.example.demo.Model.PaymentModel.Request;
import com.example.demo.Model.PaymentModel.Response;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentIntentController {

  @PostMapping("/create-payment-intent")
  public Response createPaymentIntent(@RequestBody Request request) throws StripeException {
    // Set your Stripe API key
    Stripe.apiKey = "sk_test_51OxummSB9CtopJnulJRXOoJy6Rkcj9Yj5UrYjBFWmEWNBGhp6ejKMK9hfAItJtNsmiqofS5U4bWzxhbxuA6UdOyo00BJcDKlvY";

    // Convert the amount to paisa (INR currency's smallest unit)
    long amountInPaisa = request.getAmount() * 100;

    // Create PaymentIntent params
    PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
        .setAmount(amountInPaisa)
        .setCurrency("INR")
        .setAutomaticPaymentMethods(
            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                .setEnabled(true)
                .build())
        .build();

    // Create PaymentIntent
    PaymentIntent intent = PaymentIntent.create(params);
    // Return PaymentIntent details
    return new Response(intent.getId(), intent.getClientSecret());
  }
}

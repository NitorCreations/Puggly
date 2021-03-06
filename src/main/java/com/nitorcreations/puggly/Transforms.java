package com.nitorcreations.puggly;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.nitorcreations.puggly.domain.tranforms.ExchangeTransform;

public class Transforms {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static ExchangeTransform prettyPrintJsonRequestBody() {
        return exchange -> {
            exchange.request.body = gson.toJson(new JsonParser().parse(exchange.request.body));
            return exchange;
        };
    }

    public static ExchangeTransform prettyPrintJsonResponseBody() {
        return exchange -> {
            exchange.response.body = gson.toJson(new JsonParser().parse(exchange.response.body));
            return exchange;
        };
    }

    public static ExchangeTransform replaceResponseBody(String replacement) {
        return exchange -> {
            exchange.response.body = replacement;
            return exchange;
        };
    }

    public static ExchangeTransform replaceRequestBody(String replacement) {
        return exchange -> {
            exchange.request.body = replacement;
            return exchange;
        };
    }

    protected Transforms() {
        // empty private constructor for util class
    }
}

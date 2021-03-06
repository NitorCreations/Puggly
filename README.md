# Puggly - Java-configured Logging ServletFilter
[![Build Status](https://travis-ci.org/NitorCreations/Puggly.svg?branch=master)](https://travis-ci.org/NitorCreations/Puggly)
[![Coverage Status](https://coveralls.io/repos/NitorCreations/Puggly/badge.svg?branch=master)](https://coveralls.io/r/NitorCreations/Puggly?branch=master)

Easy to configure logging filter for servlet containers. Just create an instance of `LoggingFilter`, configure which messages to log and how to conditionally modify the logged content and you are done. All done with few lines of pure Java.

## Usage

    LoggingFilter filter = new LoggingFilter();
    
    // do not log requests to health probe
    filter.registerSkipCondition(requestUriContains("/health");
    
    // if the response is json, then pretty print it
    filter.registerTransform(hasResponseContentType("application/json"), prettyPrintJsonResponseBody());

Besides the helpers like `requestUriContains` that you can find from `Conditions` and `Transforms` classes, you can easily write your own transformations and conditions with Java 8 lambda expressions:

    // let's only log error messages
    filter.registerSkipCondition(exchange -> exchange.response.status == 200);

When you have your `LoggingFilter` configured the way you want it, just introduce it to your servlet container.

### With Spring

    @Bean
    Filter myLoggingFilter() {
        LoggingFilter filter = new LoggingFilter();
        // .. configure here like you want it
        return filter;
    }

### Example of logged message

```
Request (3): [GET /secure/user/account?sourceRequestId=1336408566f711
> host: localhost:8443
> authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1Ni..
> accept: application/json
> connection: close
> body=],
Response (3): [status=200
> Access-Control-Allow-Origin: *
> Access-Control-Allow-Methods: POST, GET, OPTIONS, DELETE
> Access-Control-Max-Age: 3600
> Access-Control-Allow-Headers: x-requested-with, Authorization
> Cache-Control: no-store, no-cache, must-revalidate, max-age=0
> X-Application-Context: application:local:8443
> contentType=application/json;charset=UTF-8
> body={
  "firstName": "John",
  "lastName": "Doe",
  "accountId": "ce36247641704aac94.....",
  "username": "john.doe@foobar.com",
  "serviceProfile": {
    "accountId": "2545345345ce36247641",
    "thirdPartyId": "52345343663"
  }
}]
```

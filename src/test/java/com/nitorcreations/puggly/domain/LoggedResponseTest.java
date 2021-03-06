package com.nitorcreations.puggly.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoggedResponseTest {

    @Test
    public void to_string() {
        LoggedResponse loggedResponse = new LoggedResponse();
        loggedResponse.body = "body";
        loggedResponse.contentType = "foo/bar";
        loggedResponse.status = 200;

        assertThat(loggedResponse.toString(), is("[status=200\n> contentType=foo/bar\n> body=body]"));
    }

    @Test
    public void to_string_with_headers() {
        LoggedResponse loggedResponse = new LoggedResponse();
        loggedResponse.body = "body";
        loggedResponse.contentType = "foo/bar";
        loggedResponse.status = 200;
        loggedResponse.headers.add("header1", "headerValue1");

        assertThat(loggedResponse.toString(), is("[status=200\n> header1: headerValue1\n> contentType=foo/bar\n> body=body]"));
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(LoggedExchange.class).withRedefinedSuperclass()
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS).verify();
    }


}
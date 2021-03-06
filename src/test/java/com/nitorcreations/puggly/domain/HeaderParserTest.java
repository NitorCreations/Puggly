package com.nitorcreations.puggly.domain;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class HeaderParserTest {

    @Test
    public void request_simple_header() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader("foo", "bar");

        assertThat(HeaderParser.getHeaders(servletRequest).get("foo"), hasItem("bar"));
    }

    @Test
    public void request_multi_value_header() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader("foo", "bar");
        servletRequest.addHeader("foo", "baz");

        assertThat(HeaderParser.getHeaders(servletRequest).get("foo"), hasItems("bar", "baz"));
    }

    @Test
    public void response_simple_header() {
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletResponse.addHeader("foo", "bar");

        assertThat(HeaderParser.getHeaders(servletResponse).get("foo"), hasItem("bar"));
    }

    @Test
    public void response_multi_value_header() {
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletResponse.addHeader("foo", "bar");
        servletResponse.addHeader("foo", "baz");

        assertThat(HeaderParser.getHeaders(servletResponse).get("foo"), hasItems("bar", "baz"));
    }

    @Test
    public void toString_multi_value_header() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader("foo", "bar");
        servletRequest.addHeader("foo", "baz");
        HttpHeaders headers = HeaderParser.getHeaders(servletRequest);
        assertThat(HeaderParser.headerString(headers), is("> foo: bar, baz"));
    }

}
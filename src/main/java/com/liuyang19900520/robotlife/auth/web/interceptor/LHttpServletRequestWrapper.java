package com.liuyang19900520.robotlife.auth.web.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.google.common.io.ByteStreams.toByteArray;

@Slf4j
public class LHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] requestBody = null;

    public LHttpServletRequestWrapper(HttpServletRequest request) {

        super(request);

        // catch the request body
        try {
            ServletInputStream inputStream = request.getInputStream();
            requestBody = toByteArray(inputStream);

            log.debug("============="+new String(requestBody));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * override getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    /**
     * override getReader()
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}

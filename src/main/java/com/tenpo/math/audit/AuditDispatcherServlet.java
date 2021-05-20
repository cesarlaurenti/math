package com.tenpo.math.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class AuditDispatcherServlet extends DispatcherServlet {

    @Autowired
    private AuditRepository auditRepository;

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request = new ContentCachingRequestWrapper(request);

        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        try {
            super.doDispatch(request, response);
        } finally {
            if ("POST".equalsIgnoreCase(request.getMethod()))
            {
                request.getReader().lines().collect(Collectors.joining(", "));
            }
            audit(request);
            updateResponse(response);
        }

    }

    private void audit(HttpServletRequest request) {
        auditRepository.save(new Audit(LocalDateTime.now(), request.getRemoteUser(), request.getMethod(),
                request.getRequestURI(), getRequest(request)));
    }

    private String getRequest(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = Math.min(buf.length, 5120);
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                }
                catch (UnsupportedEncodingException ex) {
                    // NOOP
                }
            }
        }
        return "[unknown]";

    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }

}

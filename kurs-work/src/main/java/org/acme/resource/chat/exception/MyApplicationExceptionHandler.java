package org.acme.resource.chat.exception;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyApplicationExceptionHandler implements ExceptionMapper<MyBusinessException> {

    @Inject
    protected Logger log;

    @Override
    public Response toResponse(MyBusinessException exception) {
        log.warnv("a business exception was hanled {0}", exception.getMessage());

        return Response.status(Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();

    }
}

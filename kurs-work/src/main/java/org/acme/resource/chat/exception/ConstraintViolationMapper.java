package org.acme.resource.chat.exception;


import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    protected Logger log;

    @Override
    public Response toResponse(final ConstraintViolationException exception) {

        log.errorv("constraint violation {0}", exception.getMessage() );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(prepareMessage(exception))
                .type("text/plain")
                .build();
    }

    private String prepareMessage(ConstraintViolationException exception) {
        String msg = "";
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            msg+=cv.getPropertyPath()+" "+cv.getMessage()+"\n";
        }
        return msg;
    }
}


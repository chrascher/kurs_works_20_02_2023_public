package org.acme.resource.chat.exception;


import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Inject
    protected Logger log;

    @Override
    public Response toResponse(final RuntimeException exception) {

        log.errorv("constraint violation {0}", exception.getMessage() );

        if( exception.getCause().getCause() instanceof ConstraintViolationException ) {

            ConstraintViolationException ce = (ConstraintViolationException)exception.getCause().getCause();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity( prepareMessage(ce))
                .type("text/plain")
                .build();
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Error occured" + exception.getMessage())
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


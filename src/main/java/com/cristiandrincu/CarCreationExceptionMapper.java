package com.cristiandrincu;

import com.cristiandrincu.control.CarCreationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider //scanned by JaxRs runtime and added as an extension to it
public class CarCreationExceptionMapper implements ExceptionMapper<CarCreationException> {

    @Override
    public Response toResponse(CarCreationException exception) {
        return Response.serverError()
                .header("X-Car-Error", exception.getMessage())
                .entity(exception.getMessage())
                .build();
    }
}

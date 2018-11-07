package com.cristiandrincu.boundary;

import com.cristiandrincu.entity.Car;
import com.cristiandrincu.entity.Color;
import com.cristiandrincu.entity.EngineType;
import com.cristiandrincu.entity.Specification;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("cars")
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Car> retrieveCars() {
        return carManufacturer.retrieveCars();
    }

    @GET
    @Path("/cars-json-array")
    //we do not need to mention @Produces(APPLICATION_JSON) if we are using JsonArray
    public JsonArray retrieveCarsJsonArray(@NotNull @QueryParam("filter") EngineType engineType) {
        return carManufacturer.retrieveCars().stream()
                .map(car -> Json.createObjectBuilder()
                        .add("color", car.getColor().name())
                        .add("engine", car.getEngineType().name())
                        .add("id", car.getIdentifier())
                        .build())
                .collect(JsonCollectors.toJsonArray());
    }

//    @GET
//    @Path("{id}")
//    public Car retrieveCar(@PathParam("id") String identifier) {
//        return carManufacturer.retrieveCar(identifier);
//    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createCar(@Valid @NotNull Specification specification) {
        Car car = carManufacturer.manufactureCar(specification);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(CarsResource.class)
                .path(CarsResource.class, "retrieveCar")
                .build(car.getIdentifier()); // the final path will be http://localhost:8080/learning-java-ee/resources/cars/a6d32830-ebf1-4d93-9055-7da70fbaee09 for example

        return Response.created(uri).build();
    }

    @POST
    @Path("/cars-json-array")
    public void createCarJsonObject(JsonObject jsonObject) {
        //the json object is the payload found on the request body
        Color color = Color.valueOf(jsonObject.getString("color"));
        EngineType engine = EngineType.valueOf(jsonObject.getString("engine"));
        carManufacturer.manufactureCar(new Specification(color, engine));
    }
}

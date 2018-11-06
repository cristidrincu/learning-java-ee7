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
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("cars")
public class CarsResource {

    @Inject
    CarManufacturer carManufacturer;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Car> retrieveCars() {
        return carManufacturer.retrieveCars();
    }

    @GET
    @Path("/cars-json-array")
    public JsonArray retrieveCarsJsonArray() { //we do not need to mention @Produces(APPLICATION_JSON) if we are using JsonArray, but since we have another classic, List<Car> retrieveCars method, we'll leave the annotation
        return carManufacturer.retrieveCars().stream()
                .map(car -> Json.createObjectBuilder()
                        .add("color", car.getColor().name())
                        .add("engine", car.getEngineType().name())
                        .add("id", car.getIdentifier())
                        .build())
                .collect(JsonCollectors.toJsonArray());
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public void createCar(Specification specification) {
        carManufacturer.manufactureCar(specification);
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

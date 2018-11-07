package com.cristiandrincu.boundary;

import com.cristiandrincu.control.CarFactory;
import com.cristiandrincu.entity.Car;
import com.cristiandrincu.entity.CarCreated;
import com.cristiandrincu.entity.Specification;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @PersistenceContext
    EntityManager entityManager; //used to persist cars to db

    @Inject
    Event<CarCreated> carCreatedEvent;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        entityManager.persist(car);
        carCreatedEvent.fire(new CarCreated(car.getIdentifier())); //blocks
        return car;
    }

    public List<Car> retrieveCars() {
        return entityManager.createNamedQuery(Car.FIND_ALL, Car.class).getResultList();
    }

//    public Car retrieveCar(String identifier) {
//        return entityManager.createNamedQuery(Car.FIND_CAR, Car.class).getResultList();
//    }
}

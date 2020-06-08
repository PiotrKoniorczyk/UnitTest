package JUnitExercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;
    private Coordinates coordinates;

    @BeforeEach
    void setup(){
        coordinates = new Coordinates(5,5);
        unit = new Unit(coordinates,50,35);
    }

    @Test
    void unitShouldntRideWithoutFuel(){
        //give @BeforEach

        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(26,26));
    }

    @Test
    void getFuelTest(){
        //give @BeforeEach
        //when
        //then
        assertThat(unit.getFuel(),equalTo(50));

    }

    @Test
    void getLoadTest(){
        //give @BeforeEach
        Cargo cargo1 = new Cargo("cargo1", 10);
        Cargo cargo2 = new Cargo("cargo2", 25);
        //when
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        //then
        assertThat(unit.getLoad(), equalTo(35));
    }

    @Test
    void unitShouldLoseFuelWhenMoving(){
        //give @BeforEach

        //when
        unit.move(10,10);
        //then
        assertThat(unit.getFuel(),lessThan(50));
    }

    @Test
    void afterFuelingFuelLevelShouldIncrease(){
        //give @BeforEach

        //when
        unit.move(10,10);
        int curentFuelLevel = unit.getFuel();
        unit.tankUp();
        //then
        assertThat(unit.getFuel(), greaterThan(curentFuelLevel));
    }
    @Test
    void fuelingShouldNotExceedMaxFuelLimit(){
        //give @BeforEach

        //when
        unit.tankUp();
        //them
        assertThat(unit.getFuel(),equalTo(50));
    }

    @Test
    void coordinatesAfterMoveShouldBeSameThanCoordinatesCopy(){
        //give @BeforEach


        //when
        Coordinates copy = Coordinates.copy(coordinates,coordinates.getX(),coordinates.getX());
        Coordinates move =  unit.move(coordinates.getX(),coordinates.getX());

        //then
        assertThat(move, equalTo(copy));
        assertThat(move, not(sameInstance(copy)));
    }

    @Test
    void cargoCanNotBeLoadeMoreThanMaxCargoWeight(){
        //give @BeforEach
        Cargo cargo = new Cargo("cargo", 50);
        //when
        //then
        assertThrows(IllegalStateException.class,()->unit.loadCargo(cargo));
    }

    @Test
    void unloadThingFromCargo(){
        //give @BeforEach
        Cargo cargo1 = new Cargo("cargo1", 10);
        Cargo cargo2 = new Cargo("cargo2", 25);
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        //when
        unit.unloadCargo(cargo1);
        //then
        assertThat(unit.getLoad(),equalTo(25) );
    }

    @Test
    void unloadAllCargoShouldReduceUnitLoadToZero(){
        //give @BeforEach
        Cargo cargo1 = new Cargo("cargo1", 10);
        Cargo cargo2 = new Cargo("cargo2", 25);
        unit.loadCargo(cargo1);
        unit.loadCargo(cargo2);
        //when
        unit.unloadAllCargo();
        //then
        assertThat(unit.getLoad(),equalTo(0));
    }




}
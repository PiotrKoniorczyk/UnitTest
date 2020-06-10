package JUnitExercise;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void coordinatesCanNotBeLessThan0(){
        //give
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1,0) );
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0,-1) );
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1,-1) );
    }

    @Test
    void coordinatesCanNotBeMoreThan100(){
        //give
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101,0) );
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0,101) );
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101,101) );
    }

    @Test
    void copyShouldBeEqualToCoordinates(){
        //give
        Coordinates coordinates = new Coordinates(5,5);
        //when
        Coordinates copy = Coordinates.copy(coordinates,0,0);
        //then
        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));


    }






}
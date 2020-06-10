package MockitoExercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UnitServiceTest {


    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private UnitService unitService;



    @Test
    void addedCargoShouldBeLoadedOnUnit(){
    //give
        Coordinates coordinates = new Coordinates(0,0);
        Cargo cargo = new Cargo("Box", 20);
        Unit unit = new Unit(coordinates,50,50);

        given(cargoRepository.findCargoByName("Box")).willReturn(Optional.of(cargo));
    //when
        unitService.addCargoByName(unit,"Box");
    //then
        verify(cargoRepository).findCargoByName("Box");
        assertThat(unit.getLoad(), is(20));
        assertThat(unit.getCargo().get(0), equalTo(cargo));
    }

    @Test
    void shouldThrowExceptionIfNoFoundCargo(){
        //give
        Coordinates coordinates = new Coordinates(0,0);
        Unit unit = new Unit(coordinates,50,50);

        given(cargoRepository.findCargoByName("Box")).willReturn(Optional.empty());
        //when
        //then
        assertThrows(NoSuchElementException.class, ()-> unitService.addCargoByName(unit,"Box"));
    }

    @Test
    void shouldReturnUnitByCoordinates(){
        //given
        Coordinates coordinates = new Coordinates(0,0);
        Unit unit = new Unit(coordinates,50,50);

        given(unitRepository.getUnitByCoordinates(coordinates)).willReturn(unit);
        //when
        Unit unitCoordinates = unitService.getUnitOn(coordinates);

        //then
        assertThat(unitCoordinates, sameInstance(unit));
    }

    @Test
    void shouldReturnExceptionWhenNotFoundUnitOnGivenCoordinates(){
        //given
        Coordinates coordinates = new Coordinates(0,0);
        given(unitRepository.getUnitByCoordinates(coordinates)).willReturn(null);
        //when
        //then
        assertThrows(NoSuchElementException.class, ()-> unitService.getUnitOn(coordinates));
    }
}
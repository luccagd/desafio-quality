package com.example.desafioquality.unit;

import com.example.desafioquality.unit.mocks.FakeRoomObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class PropertyUnitTests {



    @Test
    public void shouldReturnExceptionIfIdIsLessThanOne() {
        Long invalidId = 0L;

        FakePropertyService fakePropertyService = new FakePropertyService(new FakePropertyRepository());

        ArrayIndexOutOfBoundsException exception = Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> fakePropertyService.findById(invalidId));
        Assertions.assertEquals(new ArrayIndexOutOfBoundsException().getClass(), exception.getClass());
    }

    @Test
    public void shouldReturnExceptionIfPropertyNotFound() {
        Long validId = 1L;

        FakePropertyRepository mockRepository = Mockito.mock(FakePropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(null);
        FakePropertyService fakePropertyService = new FakePropertyService(mockRepository);

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> fakePropertyService.findById(validId));
        Assertions.assertEquals(new ArithmeticException().getClass(), exception.getClass());
    }

    @Test
    public void shouldCallFindByIdFromRepositoryWithCorrectValues() {
        Long validId = 1L;

        FakePropertyRepository mockRepository = Mockito.mock(FakePropertyRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenCallRealMethod();
        FakePropertyService fakePropertyService = new FakePropertyService(mockRepository);

        fakePropertyService.findById(validId);
        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.eq(validId));
    }

    @Test
    public void shouldReturnCorrectAreaOfTheProperty() {
        Long validId = 1L;

        FakePropertyService fakePropertyService = new FakePropertyService(new FakePropertyRepository());

        Double propertyArea = fakePropertyService.calculateArea(validId);
        Assertions.assertEquals(15.0, propertyArea);
    }

    @Test
    public void shouldReturnTheBiggestRoom() {
        Long validId = 1L;
        FakeRoom fakeRoom = FakeRoomObject.makeFakeRoom("C", 3.0, 3.0);

        FakePropertyService fakePropertyService = new FakePropertyService(new FakePropertyRepository());
        FakeRoom roomReturned = fakePropertyService.getBiggestRoom(validId);

        Assertions.assertEquals(fakeRoom, roomReturned);
    }

    @Test
    public void shouldReturnTheCorrectAreaOfEachRoom() {
        Long validId = 1L;

        FakeRoom fakeRoom = Mockito.mock(FakeRoom.class);
        FakePropertyService fakePropertyService = new FakePropertyService(new FakePropertyRepository());
        List<FakeRoom> roomsReturned = fakePropertyService.getRoomsAreas(validId);

        Mockito.when(fakeRoom.getArea()).thenReturn(1.0);
        Assertions.assertEquals(fakeRoom.getArea(), roomsReturned.get(0).getArea());

        Mockito.when(fakeRoom.getArea()).thenReturn(4.0);
        Assertions.assertEquals(fakeRoom.getArea(), roomsReturned.get(1).getArea());

        Mockito.when(fakeRoom.getArea()).thenReturn(9.0);
        Assertions.assertEquals(fakeRoom.getArea(), roomsReturned.get(2).getArea());
    }

}

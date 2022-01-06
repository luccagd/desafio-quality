package com.example.desafioquality.unit;

import com.example.desafioquality.dto.DistrictDTO;
import com.example.desafioquality.entity.District;
import com.example.desafioquality.exception.BusinessException;
import com.example.desafioquality.repository.DistrictRepository;
import com.example.desafioquality.service.DistrictService;
import com.example.desafioquality.unit.mocks.FakeDistrictObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class DistrictUnitTests {

    @Test
    public void shouldReturnExceptionIfIdIsLessThanOne() {
        Long invalidId = 0L;

        DistrictService districtService = new DistrictService(new DistrictRepository());

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> districtService.findById(invalidId));
        Assertions.assertEquals(new BusinessException().getClass(), exception.getClass());
        Assertions.assertEquals("Id should be greater than zero", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionIfPropertyNotFound() {
        Long validId = 1L;

        DistrictRepository mockRepository = Mockito.mock(DistrictRepository.class);
        Mockito.when(mockRepository.findById(validId)).thenReturn(null);
        DistrictService districtService = new DistrictService(mockRepository);

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class,
                () -> districtService.findById(validId));
        Assertions.assertEquals(new NoSuchElementException().getClass(), exception.getClass());
        Assertions.assertEquals("District not found for the id passed as parameter", exception.getMessage());
    }

    @Test
    public void shouldCallFindByIdFromRepositoryWithCorrectValues() {
        Long validId = 1L;

        DistrictRepository mockRepository = Mockito.mock(DistrictRepository.class);
        Mockito.when(mockRepository.findById(Mockito.anyLong())).thenReturn(FakeDistrictObject.makeFakeDistrict());
        DistrictService districtService = new DistrictService(mockRepository);

        districtService.findById(validId);
        Mockito.verify(mockRepository, Mockito.times(1)).findById(Mockito.eq(validId));
    }

    @Test
    public void shouldReturnTheExistentDistrict() throws IOException {
        District fakeDistrict = FakeDistrictObject.makeFakeDistrict();

        DistrictDTO fakeDistrictDTO = DistrictDTO.builder().name("Valid Name").squareMeterPrice(new BigDecimal(0.0))
                .build();

        DistrictRepository mockRepository = Mockito.mock(DistrictRepository.class);
        Mockito.when(mockRepository.save(Mockito.any())).thenReturn(fakeDistrict);
        DistrictService districtService = new DistrictService(mockRepository);

        District savedDistrict = districtService.save(fakeDistrictDTO);
        Assertions.assertEquals(savedDistrict.getId(), fakeDistrict.getId());
    }
}

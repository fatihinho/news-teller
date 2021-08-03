package com.fcinar.newsteller.service;

import com.fcinar.newsteller.dto.CreateNewRequest;
import com.fcinar.newsteller.dto.NewDto;
import com.fcinar.newsteller.dto.converter.NewDtoConverter;
import com.fcinar.newsteller.model.New;
import com.fcinar.newsteller.repository.INewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NewServiceTest {
    private NewService newService;
    private INewRepository newRepository;
    private NewDtoConverter newDtoConverter;

    @BeforeEach
    public void setup() {
        newRepository = mock(INewRepository.class);
        newDtoConverter = mock(NewDtoConverter.class);
        newService = new NewService(newRepository, newDtoConverter);
    }

    @Test
    public void testFindNewById_whenNewIdExists_shouldReturnNew() {
        New expected = new New("id", "title", "description", false);
        when(newRepository.findById("id")).thenReturn(Optional.of(expected));
        New actual = newService.findNewById("id");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNewById_whenNewIdExists_shouldReturnNewDto() {
        NewDto excepted = new NewDto("id", "title", "description", false);
        New newTest = new New("id", "title", "description", false);
        when(newRepository.findById("id")).thenReturn(Optional.of(newTest));
        when(newDtoConverter.convert(newTest)).thenReturn(excepted);
        NewDto actual = newService.getNewById("id");
        assertEquals(excepted, actual);
    }
}

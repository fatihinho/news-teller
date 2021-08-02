package com.fcinar.newsteller.service;

import com.fcinar.newsteller.dto.CreateNewRequest;
import com.fcinar.newsteller.dto.NewDto;
import com.fcinar.newsteller.dto.UpdateNewRequest;
import com.fcinar.newsteller.dto.converter.NewDtoConverter;
import com.fcinar.newsteller.model.New;
import com.fcinar.newsteller.repository.INewRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewService {
    private final INewRepository newRepository;
    private final NewDtoConverter newDtoConverter;

    public NewService(INewRepository newRepository, NewDtoConverter newDtoConverter) {
        this.newRepository = newRepository;
        this.newDtoConverter = newDtoConverter;
    }

    public List<NewDto> getAllNews() {
        List<New> news = newRepository.findAll();
        return news.stream().map(newDtoConverter::convert).collect(Collectors.toList());
    }

    public List<NewDto> getNewsByTitleContaining(String title) {
        List<New> news = newRepository.findByTitleContaining(title);
        return news.stream().map(newDtoConverter::convert).collect(Collectors.toList());
    }

    public List<NewDto> getNewsByPublished(Boolean published) {
        List<New> news = newRepository.findByPublished(published);
        return news.stream().map(newDtoConverter::convert).collect(Collectors.toList());
    }

    public NewDto getNewById(String id) {
        New newObj = newRepository.findById(id).orElseThrow();
        return newDtoConverter.convert(newObj);
    }

    public NewDto createNew(@NotNull CreateNewRequest createNewRequest) {
        New newObj = new New(createNewRequest.getTitle(),
                createNewRequest.getDescription(), false);

        return newDtoConverter.convert(newRepository.save(newObj));
    }

    public NewDto updateNewById(String id, @NotNull UpdateNewRequest updateNewRequest) {
        New newObj = newRepository.findById(id).orElseThrow();
        newObj.setTitle(updateNewRequest.getTitle());
        newObj.setDescription(updateNewRequest.getDescription());
        newObj.setPublished(updateNewRequest.getPublished());
        return newDtoConverter.convert(newRepository.save(newObj));
    }

    public void deleteNewById(String id) {
        newRepository.deleteById(id);
    }

    public void deleteAllNews() {
        newRepository.deleteAll();
    }
}

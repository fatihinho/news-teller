package com.fcinar.newsteller.controller;

import com.fcinar.newsteller.dto.CreateNewRequest;
import com.fcinar.newsteller.dto.NewDto;
import com.fcinar.newsteller.dto.UpdateNewRequest;
import com.fcinar.newsteller.service.NewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NewController {
    private final NewService newService;

    public NewController(NewService newService) {
        this.newService = newService;
    }

    @GetMapping("/news")
    public ResponseEntity<List<NewDto>> getAllNews(@RequestParam(required = false) String title) {
        try {
            List<NewDto> news = new ArrayList<>();
            if (title == null) {
                newService.getAllNews().forEach(news::add);
            } else {
                newService.getNewsByTitleContaining(title).forEach(news::add);
            }
            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(newService.getAllNews(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/news/published")
    public ResponseEntity<List<NewDto>> getNewsByPublished() {
        try {
            List<NewDto> news = newService.getNewsByPublished(true);
            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<NewDto> getNewById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(newService.getNewById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/news")
    public ResponseEntity<NewDto> createNew(@RequestBody CreateNewRequest createNewRequest) {
        try {
            return new ResponseEntity<>(newService.createNew(createNewRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/news/{id}")
    public ResponseEntity<NewDto> updateNewById(@PathVariable String id,
                                                @RequestBody UpdateNewRequest updateNewRequest) {
        try {
            return new ResponseEntity<>(newService.updateNewById(id, updateNewRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity<HttpStatus> deleteNew(@PathVariable String id) {
        try {
            newService.deleteNewById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/news")
    public ResponseEntity<HttpStatus> deleteAllNews() {
        try {
            newService.deleteAllNews();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

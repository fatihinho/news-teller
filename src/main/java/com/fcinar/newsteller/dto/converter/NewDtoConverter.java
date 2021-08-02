package com.fcinar.newsteller.dto.converter;

import com.fcinar.newsteller.dto.NewDto;
import com.fcinar.newsteller.model.New;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NewDtoConverter {
    public NewDto convert(@NotNull New from) {
        return new NewDto(Objects.requireNonNull(from.getId()), from.getTitle(),
                from.getDescription(), from.getPublished());
    }
}

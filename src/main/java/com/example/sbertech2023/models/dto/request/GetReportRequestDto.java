package com.example.sbertech2023.models.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetReportRequestDto {
    @Min(value = 1, message = "Id can not be less than 1")
    private Long userId;
    @Min(value = 1, message = "Id can not be less than 1")
    private Long appealId;
}

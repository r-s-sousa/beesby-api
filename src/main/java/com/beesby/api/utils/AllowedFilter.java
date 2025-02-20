package com.beesby.api.utils;

import com.beesby.api.enums.FilterOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllowedFilter {

    private String key;
    private FilterOperation operation;
}

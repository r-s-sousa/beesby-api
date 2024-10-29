package com.beesby.api.utils;

import com.beesby.api.enums.FilterOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterItem {

    private String key;
    private FilterOperation operation;
    private Object value;
}

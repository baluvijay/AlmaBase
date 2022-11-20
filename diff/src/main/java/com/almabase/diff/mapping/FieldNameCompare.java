package com.almabase.diff.mapping;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class FieldNameCompare {
    private  String sourceFieldName;
    private  String destinationFieldName;
}

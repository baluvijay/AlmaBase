package com.almabase.diff.mapping;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Mapper {

    private  String sourceId;
    private  String destinationId;
    List<FieldNameCompare> fieldNameCompareList;


}

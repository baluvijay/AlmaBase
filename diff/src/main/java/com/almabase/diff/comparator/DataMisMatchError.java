package com.almabase.diff.comparator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class DataMisMatchError extends RuntimeException{
}
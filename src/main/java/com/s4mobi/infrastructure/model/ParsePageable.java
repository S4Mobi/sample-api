package com.s4mobi.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.logging.log4j.util.Strings;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ParsePageable {

    public int limit;
    public int skip;
    public String orders = Strings.EMPTY;
}

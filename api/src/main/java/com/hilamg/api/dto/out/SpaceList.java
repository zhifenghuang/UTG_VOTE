package com.hilamg.api.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpaceList {
    private Integer id;

    private String logo;

    private String name;

    private Integer number;

    // 1:未加入，2:已加入
    private String isJson;
}

package com.ehi.domain;

import lombok.Data;

@Data
public class Plan {
    private Integer id;
    private String name;
    private Integer carrierId;
    private Integer type;
    private String status;
}

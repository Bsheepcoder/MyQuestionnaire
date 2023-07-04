package com.computer.network.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionStatistic {
    private Integer questionId;
    private Integer sequence;
    private String content;
    private Integer selectedNum;
}
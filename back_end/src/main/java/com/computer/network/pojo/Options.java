package com.computer.network.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Options {
    private Integer questionId;

    //在这个问题中是第几个选项
    private Integer sequence;

    private String content;
}
package com.computer.network.pojo;

import com.computer.network.pojo.Options;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private Integer paperId;
    private Integer type;
    private String title;
    private List<Options> options;
}

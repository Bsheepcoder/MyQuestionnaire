package com.computer.network.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionStatistic {
    private Integer id;
    private Integer paperId;
    private Integer type;
    private String title;
    //此题填写人数
    private Integer filledInNum;
    //选择题所有属性 简答题此属性为null
    private List<OptionStatistic> optionStatistics;
    //简单题的所有answer 选择题此属性为null
    private List<Answer> answerVOList;
}

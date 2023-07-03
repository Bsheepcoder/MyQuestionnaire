package com.computer.network.service;

import com.computer.network.pojo.Answer;
import com.computer.network.pojo.BaseResponse;

import java.util.List;

public interface AnswerService {
    BaseResponse addAnswers(List<Answer> answerVOList);
}

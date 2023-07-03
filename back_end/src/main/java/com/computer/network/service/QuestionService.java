package com.computer.network.service;

import com.computer.network.pojo.Question;
import com.computer.network.pojo.BaseResponse;

public interface QuestionService {

    BaseResponse addQuestion(Integer paperId);

    BaseResponse updateQuestion(Question question);

    BaseResponse deleteQuestion(Integer questionId);
}

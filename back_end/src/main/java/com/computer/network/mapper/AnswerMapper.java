package com.computer.network.mapper;

import com.computer.network.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {
    int addAnswer(Answer Answer);

    List<Answer> selectByQuestionId(int questionId);
}

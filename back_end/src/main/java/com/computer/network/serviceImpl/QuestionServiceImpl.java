package com.computer.network.serviceImpl;

import com.computer.network.mapper.OptionsMapper;
import com.computer.network.mapper.QuestionMapper;
import com.computer.network.service.QuestionService;
import com.computer.network.pojo.Options;
import com.computer.network.pojo.Question;
import com.computer.network.pojo.BaseResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements QuestionService {
    private final static String INVALIDATION = "无效ID";
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    OptionsMapper optionsMapper;

    @Override
    public BaseResponse addQuestion(Integer paperId) {
        try {
            Question question = new Question();
            question.setPaperId(paperId);
            questionMapper.addQuestion(question);
            return BaseResponse.buildSuccess(question.getId());
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Override
    public BaseResponse updateQuestion(Question Question) {
        try {
            if (questionMapper.selectByQuestionId(Question.getId()) == null)
                return BaseResponse.buildFailure(INVALIDATION);
            Question question = new Question();
            BeanUtils.copyProperties(Question, question);
            questionMapper.updateQuestion(question);
            for (Options options : Question.getOptions()) {
                Options Options = new Options();
                BeanUtils.copyProperties(options, Options);
                if (optionsMapper.findOption(Options) == null)
                    optionsMapper.addOption(Options);
                else
                    optionsMapper.updateOption(Options);
            }
            return BaseResponse.buildSuccess();
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Override
    public BaseResponse deleteQuestion(Integer questionId) {
        try {
            if (questionMapper.selectByQuestionId(questionId) == null)
                return BaseResponse.buildFailure(INVALIDATION);
            questionMapper.deleteQuestion(questionId);
            optionsMapper.deleteByQuestionId(questionId);
            return BaseResponse.buildSuccess();
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }
}

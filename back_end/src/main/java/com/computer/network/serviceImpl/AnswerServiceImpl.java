package com.computer.network.serviceImpl;

import com.computer.network.enums.PaperStatus;
import com.computer.network.mapper.AnswerMapper;
import com.computer.network.mapper.PaperMapper;
import com.computer.network.service.AnswerService;
import com.computer.network.pojo.Answer;
import com.computer.network.pojo.Paper;
import com.computer.network.pojo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final static String EARLY = "问卷未开始发放";
    private final static String INVALIDATION = "问卷已失效";
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    PaperMapper paperMapper;

    @Override
    public BaseResponse addAnswers(List<Answer> answerVOList) {
        try {
            int paperId = answerVOList.get(0).getPaperId();
            Paper Paper = paperMapper.selectByPaperId(paperId);
            if (Paper.getStartTime() != null && Paper.getEndTime() != null) {
                if (Paper.getStatus() == PaperStatus.INIT)
                    return BaseResponse.buildFailure(EARLY);
                if (Paper.getStatus() == PaperStatus.STOP)
                    return BaseResponse.buildFailure(INVALIDATION);
            }
            for (Answer Answer : answerVOList)
                answerMapper.addAnswer(Answer);
            return BaseResponse.buildSuccess();
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }
}

package com.computer.network.serviceImpl;

import com.computer.network.enums.PaperStatus;
import com.computer.network.mapper.AnswerMapper;
import com.computer.network.mapper.OptionsMapper;
import com.computer.network.mapper.PaperMapper;
import com.computer.network.mapper.QuestionMapper;
import com.computer.network.pojo.Options;
import com.computer.network.service.PaperService;
import com.computer.network.pojo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    private final static String EMPTY = "无效ID";
    private final static String USER_EMPTY = "用户未创建任何问卷";

    @Autowired
    PaperMapper paperMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    OptionsMapper optionsMapper;
    @Autowired
    AnswerMapper answerMapper;

    @Override
    public BaseResponse addPaper(Paper Paper) {
        try {
            paperMapper.addPaper(Paper);
            return BaseResponse.buildSuccess(Paper);
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Override
    public BaseResponse updatePaper(Paper Paper) {
        try {
            Paper paper = paperMapper.selectByPaperId(Paper.getId());
            if (paper == null)
                return BaseResponse.buildFailure(EMPTY);
            else {
                paperMapper.updatePaper(Paper);
                return BaseResponse.buildSuccess();
            }
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Scheduled(cron = "0 0/1 * * * ?")
        //每分钟检查一次
    void checkPaperStatus() {
        List<Paper> paperVOList = paperMapper.getTimePapers();
        for (Paper Paper : paperVOList) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = sdf.format(date);
            if (Paper.getStatus() == PaperStatus.INIT) {    //没开放的开放它
                if (currentDate.compareTo(Paper.getStartTime()) >= 0)
                    paperMapper.changeStatus(PaperStatus.START, Paper.getId());
            } else if (Paper.getStatus() == PaperStatus.START) {   //没结束的结束它
                if (currentDate.compareTo(Paper.getEndTime()) >= 0)
                    paperMapper.changeStatus(PaperStatus.STOP, Paper.getId());
            }
        }
    }

    @Override
    public BaseResponse deletePaper(int paperId) {
        try {
            Paper paper = paperMapper.selectByPaperId(paperId);
            if (paper == null)
                return BaseResponse.buildFailure(EMPTY);
            else {
                paperMapper.deletePaper(paperId);
                List<Question> questionList = questionMapper.selectByPaperId(paperId);
                for (Question question : questionList)
                    optionsMapper.deleteByQuestionId(question.getId());
                questionMapper.deleteByPaperId(paperId);
                return BaseResponse.buildSuccess();
            }
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Override
    public BaseResponse getUserPapers(int userId) {
        try {
            List<Paper> paperVOList = paperMapper.getUserPapers(userId);
            if (paperVOList == null)
                return BaseResponse.buildFailure(USER_EMPTY);
            else
                return BaseResponse.buildSuccess(paperVOList);
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Override
    public BaseResponse checkPaper(int paperId) {
        try {
            Paper Paper = paperMapper.selectByPaperId(paperId);
            if (Paper == null)
                return BaseResponse.buildFailure(EMPTY);
            PaperDetail paperDetail = new PaperDetail();
            BeanUtils.copyProperties(Paper, paperDetail);

            List<Question> questionVOList = new ArrayList<>();
            List<Question> questionList = questionMapper.selectByPaperId(paperId);
            for (Question question : questionList) {
                int questionId = question.getId();
                List<Options> optionsList = optionsMapper.selectByQuestionId(questionId);

                Question Question = new Question();
                BeanUtils.copyProperties(question, Question);
                Question.setOptions(optionsList);
                questionVOList.add(Question);
            }
            paperDetail.setQuestionList(questionVOList);
            return BaseResponse.buildSuccess(paperDetail);

        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

    @Override
    public BaseResponse reviewPaper(int paperId) {
        try {
            Paper Paper = paperMapper.selectByPaperId(paperId);
            if (Paper == null)
                return BaseResponse.buildFailure(EMPTY);
            else {
                PaperStatistic paperStatistic = new PaperStatistic();
                BeanUtils.copyProperties(Paper, paperStatistic);

                List<QuestionStatistic> questionStatisticList = new ArrayList<>();
                List<Question> questionList = questionMapper.selectByPaperId(paperId);

                for (Question question : questionList) {

                    QuestionStatistic questionStatistic = new QuestionStatistic();
                    BeanUtils.copyProperties(question, questionStatistic);

                    int questionId = question.getId();

                    if (question.getType() != 3) {    //单选题和多选题
                        List<Options> optionsList = optionsMapper.selectByQuestionId(questionId);
                        List<OptionStatistic> optionStatisticList = new ArrayList<>();
                        for (Options options : optionsList) {   //先都转成另一个VO
                            OptionStatistic optionStatistic = new OptionStatistic();
                            BeanUtils.copyProperties(options, optionStatistic);
                            optionStatistic.setSelectedNum(0);   //后面用于+1
                            optionStatisticList.add(optionStatistic);
                        }

                        List<Answer> answerVOList = answerMapper.selectByQuestionId(questionId);

                        for (Answer Answer : answerVOList) {
                            String answerContent = Answer.getAnswerContent();
                            String[] optionSequenceList = answerContent.split(",");
                            for (String sequenceStr : optionSequenceList) {
                                int sequence = Integer.valueOf(sequenceStr);
                                for (OptionStatistic optionStatistic : optionStatisticList) {
                                    if (optionStatistic.getSequence() == sequence) {
                                        optionStatistic.setSelectedNum(optionStatistic.getSelectedNum() + 1);
                                        break;
                                    }
                                }
                            }
                        }
                        questionStatistic.setOptionStatistics(optionStatisticList);
                    } else {    //简答题
                        questionStatistic.setAnswerVOList(answerMapper.selectByQuestionId(questionId));
                    }
                    questionStatistic.setFilledInNum(answerMapper.selectByQuestionId(questionId).size());   //此题填写人数
                    questionStatisticList.add(questionStatistic);
                }
                paperStatistic.setQuestionStatistics(questionStatisticList);
                return BaseResponse.buildSuccess(paperStatistic);
            }
        } catch (Exception e) {
            System.out.println(e);
            return BaseResponse.buildFailure(e.getMessage());
        }
    }

}

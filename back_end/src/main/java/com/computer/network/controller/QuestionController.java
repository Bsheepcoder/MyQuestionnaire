package com.computer.network.controller;

import com.computer.network.service.QuestionService;
import com.computer.network.pojo.Question;
import com.computer.network.pojo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/{paperId}/addQuestion")
    public BaseResponse addQuestion(@PathVariable Integer paperId) {
        return questionService.addQuestion(paperId);
    }

    @PostMapping("/updateQuestion")
    public BaseResponse updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @GetMapping("/{questionId}/deleteQuestion")
    public BaseResponse deleteQuestion(@PathVariable Integer questionId) {
        return questionService.deleteQuestion(questionId);
    }
}

package com.computer.network.controller;

import com.computer.network.service.PaperService;
import com.computer.network.pojo.Paper;
import com.computer.network.pojo.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Autowired
    PaperService paperService;

    @PostMapping("/addPaper")
    public BaseResponse addPaper(@RequestBody Paper Paper) {
        return paperService.addPaper(Paper);
    }

    @PostMapping("/updatePaper")
    public BaseResponse updatePaper(@RequestBody Paper Paper) {
        return paperService.updatePaper(Paper);
    }

    @GetMapping("/{paperId}/deletePaper")
    public BaseResponse invalidatePaper(@PathVariable Integer paperId) {
        return paperService.deletePaper(paperId);
    }

    @GetMapping("/{userId}/getUserPapers")
    public BaseResponse getUserPapers(@PathVariable Integer userId) {
        return paperService.getUserPapers(userId);
    }

    @GetMapping("/{paperId}/checkPaper")
    public BaseResponse checkPaper(@PathVariable Integer paperId) {
        return paperService.checkPaper(paperId);
    }

    @GetMapping("/{paperId}/reviewPaper")
    public BaseResponse reviewPaper(@PathVariable Integer paperId) {
        return paperService.reviewPaper(paperId);
    }
}

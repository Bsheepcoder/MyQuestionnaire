package com.computer.network.service;

import com.computer.network.pojo.Paper;
import com.computer.network.pojo.BaseResponse;

public interface PaperService {

    BaseResponse addPaper(Paper Paper);

    BaseResponse updatePaper(Paper Paper);

    BaseResponse deletePaper(int paperId);

    BaseResponse getUserPapers(int userId);

    BaseResponse getUserPapersCount(int userId);

    BaseResponse checkPaper(int paperId);

    BaseResponse reviewPaper(int paperId);
}

package com.computer.network.mapper;

import com.computer.network.enums.PaperStatus;
import com.computer.network.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperMapper {

    int addPaper(Paper Paper);

    void updatePaper(Paper Paper);

    Paper selectByPaperId(int paperId);

    void deletePaper(int paperId);

    List<Paper> getUserPapers(int userId);

    List<Paper> getTimePapers();

    void changeStatus(@Param("paperStatus") PaperStatus paperStatus, @Param("id") int id);
}

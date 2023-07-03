package com.computer.network.mapper;

import com.computer.network.pojo.Options;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OptionsMapper {

    int addOption(Options options);

    Options findOption(Options options);

    void updateOption(Options options);

    void deleteByQuestionId(Integer questionId);

    List<Options> selectByQuestionId(int questionId);
}

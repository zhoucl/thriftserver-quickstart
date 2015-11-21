package com.eboji.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eboji.pojo.Home;
import com.eboji.pojo.HomeExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface HomeMapper {
    int countByExample(HomeExample example);

    int deleteByExample(HomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Home record);

    int insertSelective(Home record);

    List<Home> selectByExample(HomeExample example, PageBounds pageBounds);

    Home selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Home record, @Param("example") HomeExample example);

    int updateByExample(@Param("record") Home record, @Param("example") HomeExample example);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
}
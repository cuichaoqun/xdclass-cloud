package net.xdclass.dao;

import net.xdclass.Video;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper {

    @Select("select * from video where id = #{videoId}")
    Video findById(int videoId);
}

package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.Interact;
import com.example.entity.dto.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {
    @Insert("""
            <script>
                insert ignore into db_topic_interact_${type} values
                <foreach collection="interacts" item="item" separator = ",">
                    (#{item.tid}, #{item.uid}, #{item.time})
                </foreach>
            </script>
            """)
    void addInteract(List<Interact> interacts, String type);

    @Delete("""
            <script>
                delete from db_topic_interact_${type} where
                <foreach collection="interacts" item="item" separator = " or ">
                    (tid = #{item.tid} and uid = #{item.uid})
                </foreach>
            </script>
            """)
    void deleteInteract(List<Interact> interacts, String type);

    @Select("""
            select count(*) from db_topic_interact_${type} where tid = #{tid}
            """)
    int interactCount(int tid, String type);

    @Select("""
            select count(*) from db_topic_interact_${type} where tid = #{tid} and uid = #{uid}
            """)
    int userInteractCount(int tid, int uid, String type);

    @Select("""
            select * from db_topic_interact_collect left join db_topic on tid = db_topic.id
            where db_topic_interact_collect.uid = #{uid}
            """)
    List<Topic> collectTopics(int uid);


    @Select("SELECT t.type, COUNT(*) as count " +
            "FROM db_topic t " +
            "JOIN ( SELECT tid FROM db_topic_interact_like WHERE uid = #{uid}" +
            "       UNION ALL " +
            "       SELECT tid FROM db_topic_interact_collect WHERE uid = #{uid} ) as interactions " +
            "ON t.id = interactions.tid " +
            "GROUP BY t.type " +
            "ORDER BY count DESC LIMIT 1")
    List<Topic> selectMostInteractedTopicType(int uid);


}

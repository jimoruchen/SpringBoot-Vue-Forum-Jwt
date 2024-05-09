package com.example.entity.vo.request;

import com.alibaba.fastjson2.JSONObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TopicUpdateVO {
    @Min(0)
    int id;
    @Min(1)
    @Max(20)
    int type;
    @Length(min = 1, max = 100)
    String title;
    JSONObject content;
}

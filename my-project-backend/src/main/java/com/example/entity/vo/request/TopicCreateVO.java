package com.example.entity.vo.request;

import com.alibaba.fastjson2.JSONObject;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class TopicCreateVO {
    @Min(1)
    int type;
    @Length(min = 0, max = 100)
    String title;
    JSONObject content;
}

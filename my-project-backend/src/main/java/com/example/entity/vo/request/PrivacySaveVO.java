package com.example.entity.vo.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PrivacySaveVO {
    @Pattern(regexp = ("gender|phone|qq|wx|email"))
    String type;
    boolean status;
}

package com.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String number;
    private String name;
    private Integer age;
    private Integer chinese;
    private Integer math;
    private Integer english;
}

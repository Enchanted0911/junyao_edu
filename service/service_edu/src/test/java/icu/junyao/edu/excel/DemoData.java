package icu.junyao.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //设置excel表头名称 属性名要遵循驼峰命名 不然写入为空
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer stuNo;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String stuName;
}

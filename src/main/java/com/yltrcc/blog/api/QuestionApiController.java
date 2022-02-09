package com.yltrcc.blog.api;

import com.yltrcc.blog.api.entity.ApiResponse;
import com.yltrcc.blog.model.domain.Question;
import com.yltrcc.blog.model.domain.QuestionCategory;
import com.yltrcc.blog.model.domain.QuestionCustom;
import com.yltrcc.blog.service.QuestionCategoryService;
import com.yltrcc.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.yltrcc.blog.api
 * Date：2022-02-03
 * Time：13:21
 * Description：用户获取面试题相关API
 *
 * @author yltrcc
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/question")
public class QuestionApiController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionCategoryService questionCategoryService;


    @GetMapping("/getpage")
    public String getpage(@RequestParam("id") int id) {

        int count = questionService.getCounts();
        if (count == 0) {
            return "Sorry, 没有数据，请添加数据";
        }
        if (id > count) {
            return "Sorry, 当前数据库中没有此数据";
        }
        StringBuilder sb = new StringBuilder();
        Question question = questionService.findByQuestionId(id);
        sb.append(question.getArticleContent());
        sb.append("\n\n");

        return sb.toString();
    }

    @GetMapping("/getcount")
    public int getcount() {

        return questionService.getCounts();
    }

    @GetMapping("/getcategory")
    public ApiResponse<QuestionCategory> getcategory() {

        List<QuestionCategory> data =  questionCategoryService.findCategory();
        ApiResponse<QuestionCategory> response = new ApiResponse<>(data);
        response.setStatus(200);
        response.setCode(200);
        response.setMessage("获取成功！！！");

        return response;
    }

    @GetMapping("/queryByCategory")
    public ApiResponse<Question> queryByCategory(@RequestParam("categoryId") Long categoryId) {

        List<Question> data = questionService.findByCategory(categoryId);
        ApiResponse<Question> response = new ApiResponse<>(data);
        response.setStatus(200);
        response.setCode(200);
        response.setMessage("获取成功！！！");

        return response;
    }
}

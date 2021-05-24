package com.springboot.book.web;

import com.springboot.book.config.auth.LoginUser;
import com.springboot.book.config.auth.dto.SessionUser;
import com.springboot.book.service.posts.PostsService;
import com.springboot.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }
    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id,Model model){

        PostsResponseDto responseDto=postsService.findById(id);
        model.addAttribute("post",responseDto);

        return "posts-update";
    }
}

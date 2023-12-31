package com.github.commerce03.web.controller;

import com.github.commerce03.service.PostService;
import com.github.commerce03.web.dto.post.PostRequestDto;
import com.github.commerce03.web.dto.post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

//    @ApiOperation("모든 게시글을 검색")
    @GetMapping("/posts")
    public List<PostResponseDto> findAllPost(){
        log.info("GET /posts 요청이 들어왔습니다.");
        List<PostResponseDto> posts = postService.findAllPost();
        log.info("GET /posts 응답: " + posts);
        return posts;
    }

    @PostMapping("/posts")
    public String createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        log.info("POST /posts 요청이 들어왔습니다.");
        return postService.createPost(postRequestDto,request);
    }

    @PutMapping("/posts/{post_id}")
    public String updatePost(@PathVariable Integer post_id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatePost(post_id, postRequestDto);
    }

    @DeleteMapping("/posts/{post_id}")
    public String deleteItemByPathId(@PathVariable Integer post_id){
        return postService.deletePost(post_id);
    }

    @GetMapping("/posts/search")
    public List<PostResponseDto> findByEmail(@RequestParam("author_email")String usrEmail){
        log.info("GET /posts/search 요청이 들어왔습니다.");
        List<PostResponseDto> posts = postService.findByEmail(usrEmail);
        log.info("GET /posts/search 응답: " + posts);
        return posts;
    }
}

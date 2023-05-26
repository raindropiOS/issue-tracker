package com.example.be.comment;

import com.example.be.comment.dto.CommentInputFormDTO;
import com.example.be.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/issues/{issueNumber}/comments")
    public Map<String, String> addComment(@PathVariable int issueNumber, @Validated @RequestBody CommentInputFormDTO commentInputFormDTO) {
        System.out.println("hi");
        User testUser = new User("1234", "codesquad", "BE", "mock.img");
        commentService.save(issueNumber, testUser, commentInputFormDTO);
        return Map.of("commentSave", "OK");
    }
}

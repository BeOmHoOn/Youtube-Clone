package com.clone.metube.video.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    @PostMapping
    public ResponseEntity<?> saveComment() {

        return null;
    }

    @GetMapping()
    public ResponseEntity<?> getComments() {

        return null;
    }

    @PatchMapping
    public ResponseEntity<?> updateComment() {

        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment() {

        return null;
    }
}

package org.example.mariajeu.controller.homepageController;

import org.example.mariajeu.dto.homepageDto.ReplyDTO;
import org.example.mariajeu.service.homepageService.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews/{reviewId}/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @GetMapping
    public ResponseEntity<List<ReplyDTO>> getRepliesByReviewId(@PathVariable Long reviewId) {
        List<ReplyDTO> replies = replyService.getRepliesByReviewId(reviewId);
        return ResponseEntity.ok(replies);
    }

    @PostMapping
    public ResponseEntity<ReplyDTO> createReply(@PathVariable Long reviewId, @RequestBody ReplyDTO replyDTO) {
        ReplyDTO createdReply = replyService.createReply(reviewId, replyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReply);
    }

    @DeleteMapping("/{replyId}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long replyId) {
        replyService.deleteReply(replyId);
        return ResponseEntity.noContent().build();
    }
}


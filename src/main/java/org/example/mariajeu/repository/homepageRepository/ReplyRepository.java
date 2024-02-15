package org.example.mariajeu.repository.homepageRepository;

import org.example.mariajeu.domain.homepageDomain.Review.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByReviewId(Long reviewId);

}

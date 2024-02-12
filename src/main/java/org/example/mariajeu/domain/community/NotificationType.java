package org.example.mariajeu.domain.community;

public enum NotificationType {
    // 내 게시글에 좋아요 알림
    POST_LIKES_NOTICE,

    // 내 게시글에 댓글 알림
    POST_COMMENT_NOTICE,

    // 내 댓글에 대댓글 알림
    COMMNENT_RECOMMENT_NOTICE,


    // 나한테 팔로우요청 알림
    FOLLOW_REQUEST_NOTICE,

    // 상대가 팔로우 수락 알림
    FOLLOW_ALLOWED_NOTICE

}

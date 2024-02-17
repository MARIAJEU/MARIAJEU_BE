package org.example.mariajeu.controller.homepageController;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.mariajeu.domain.homepageDomain.WineType;
import org.example.mariajeu.domain.userDomain.User;
import org.example.mariajeu.dto.homepageDto.FoodArticleDTO;
import org.example.mariajeu.repository.userRepository.UserRepository;
import org.example.mariajeu.service.homepageService.FoodArticleLikesService;
import org.example.mariajeu.service.homepageService.FoodArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/FoodArticle")
public class FoodArticleController {

    private final UserRepository userRepository;
    private final FoodArticleService foodArticleService;
    private final FoodArticleLikesService foodArticleLikesService;

    // 모든 FoodArticle 데이터를 DTO 형태로 조회
    @GetMapping
    public List<FoodArticleDTO> getAllFoodArticles() {
        return foodArticleService.getAllFoodArticles();
    }


    @GetMapping("/{id}")
    public ResponseEntity<FoodArticleDTO> getFoodArticleById(@PathVariable Long id) {
        FoodArticleDTO foodArticleDTO = foodArticleService.getFoodArticleById(id);
        return ResponseEntity.ok(foodArticleDTO);
    }
    // FoodArticle 추가
    @PostMapping
    public ResponseEntity<?> addArticle(@RequestBody FoodArticleDTO foodArticleDTO) {
        FoodArticleDTO savedArticleDTO = foodArticleService.addArticle(foodArticleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticleDTO);
    }

    // FoodArticle 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody FoodArticleDTO foodArticleDTO) {
        FoodArticleDTO updatedArticleDTO = foodArticleService.updateArticle(id, foodArticleDTO);
        return ResponseEntity.ok(updatedArticleDTO);
    }

    // FoodArticle 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        foodArticleService.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<FoodArticleDTO>> getFoodArticlesByWineCharacteristics(
            @RequestParam WineType wineType,
            @RequestParam int boldness,
            @RequestParam int acidity,
            @RequestParam int fizziness,
            @RequestParam int tannic) {

        List<FoodArticleDTO> articles = foodArticleService.getFoodArticlesByWineCharacteristics(wineType, boldness, acidity, fizziness, tannic);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodArticleDTO>> searchFoodArticles(
            @RequestParam(required = false) String foodName,
            @RequestParam(required = false) WineType wineType) {
        List<FoodArticleDTO> articles;
        if (foodName != null) {
            articles = foodArticleService.searchByFoodName(foodName);
        } else if (wineType != null) {
            articles = foodArticleService.searchByWineType(wineType);
        } else {
            articles = foodArticleService.getAllFoodArticles();
        }
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/likes/{foodArticleId}")
    public ResponseEntity<?> addLike(@PathVariable Long foodArticleId) {
        // SecurityContext에서 Authentication 객체를 얻습니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication 객체에서 Principal을 얻습니다.
        Object principal = authentication.getPrincipal();

        String username;

        // Principal이 UserDetails 인스턴스인지 확인합니다.
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            // String 타입으로 반환된 경우 (일반적으로 이 경우는 사용자 이름)
            username = (String) principal;
        } else {
            // 예외 처리
            throw new IllegalStateException("Principal type is not supported");
        }

        // 사용자 저장소에서 사용자 정보를 조회합니다.
        User user = userRepository.findByUsername(username);

        // 좋아요 추가 서비스 호출
        foodArticleLikesService.addLike(user.getId(), foodArticleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/likes/{foodArticleId}")
    public ResponseEntity<?> removeLike(@PathVariable Long foodArticleId) {
        // SecurityContext에서 Authentication 객체를 얻습니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Authentication 객체에서 UserDetails를 얻습니다.
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // UserDetails에서 사용자 이름(Username)을 얻습니다.
        String username = userDetails.getUsername();

        // 사용자 저장소에서 사용자 정보를 조회합니다.
        User user = userRepository.findByUsername(username);

        // 좋아요 삭제 서비스 호출
        foodArticleLikesService.removeLike(user.getId(), foodArticleId);
        return ResponseEntity.ok().build();
    }
    // 특정 FoodArticle의 좋아요 수를 반환하는 API
    @GetMapping("/likes/count/{id}")
    public ResponseEntity<?> getLikesCount(@PathVariable Long id) {
        FoodArticleDTO foodArticleDTO = foodArticleService.findById(id);
        if (foodArticleDTO == null) {
            throw new EntityNotFoundException("FoodArticle not found with id: " + id);
        }
        int likesCount = foodArticleDTO.getLikesCount();
        return ResponseEntity.ok(Collections.singletonMap("likesCount", likesCount));
    }
}



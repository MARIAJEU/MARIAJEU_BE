package org.example.mariajeu.repository;

import org.example.mariajeu.domain.Menu;
import org.example.mariajeu.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 레스토랑 이름으로 검색

    // 날짜, 시간, 인원 수로 검색


}

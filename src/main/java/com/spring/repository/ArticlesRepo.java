package com.spring.repository;

import com.spring.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepo extends JpaRepository<Articles,Integer> {
}

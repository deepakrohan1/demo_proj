package com.example.demo.deepakrohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.deepakrohan.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	

}

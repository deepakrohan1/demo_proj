package com.example.demo.deepakrohan.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.deepakrohan.beans.Post;
import com.example.demo.deepakrohan.repository.PostRepository;

@RestController
public class PostController {
	
	@Autowired
	private PostRepository postRepository;

	@GetMapping("/posts")
	public List<Post> getAll(){
		List<Post> sample = postRepository.findAll();
		return sample;
		
	}
	
	@GetMapping("/posts/{id}")
	public Post retrievePost(@PathVariable long id) {
		System.out.println(id);
		Optional<Post> post = postRepository.findById(id);

		if (!post.isPresent())
			System.out.println("No element present");
			//throw new StudentNotFoundException("id-" + id);

		return post.get();
	}
	
	@PostMapping("/posts")
	public ResponseEntity<Object> createPost(@RequestBody Post post) {
		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable long id) {
		postRepository.deleteById(id);
	}
}

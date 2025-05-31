package com.Omnify.Assignment.Controller;

import com.Omnify.Assignment.Entity.User;
import com.Omnify.Assignment.Model.Blog;
import com.Omnify.Assignment.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private Userservice userservice;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userservice.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody User request) {
        return ResponseEntity.ok(userservice.login(request.getEmail(), request.getPassword()));
    }
    @GetMapping("/Home")
    public ResponseEntity<List<Blog>> getallBlogs() {
        return ResponseEntity.ok(userservice.getallblogs());
    }

    @GetMapping("/My_Blogs")
    public ResponseEntity<List<Blog>> getById(@RequestParam int i) {
        return ResponseEntity.ok(userservice.getById(i));
    }

    @PutMapping("/My_Blogs/Edit")
    public ResponseEntity<Integer> updateblog(@RequestBody Blog blog){
        return ResponseEntity.ok(userservice.Updateblog(blog));
    }

    @DeleteMapping("/My_Blogs/Delete")
    public ResponseEntity<Integer> DeleteBlog(@RequestBody Blog blog){
        return ResponseEntity.ok(userservice.DeleteBlog(blog));
    }

    @PostMapping("/My_Blogs/Add")
        public ResponseEntity<Integer> AddBlog(@RequestBody Blog blog){
            return ResponseEntity.ok(userservice.AddBlog(blog));
        }
}


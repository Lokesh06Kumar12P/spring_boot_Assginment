package com.Omnify.Assignment.Service;

import com.Omnify.Assignment.Entity.User;
import com.Omnify.Assignment.Model.Blog;
import com.Omnify.Assignment.Reposistory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Userservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Map<String,Object> login(String username, String password) {
        User user = userRepository.findByEmail(username);
        Map<String,Object> returnm = new HashMap<>();
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            returnm.put("Id",-1);
            returnm.put("Status",false);
            return returnm;
        }
        returnm.put("Id",user.getId());
        returnm.put("Status",true);
        return returnm;
    }


    public List<Blog> getallblogs(){
        List<Blog> Blog = userRepository.getAllBlogs();
        return Blog;
    }

    public List<Blog> getById(int i){
        List<Blog> Blog = userRepository.getById(i);
        return Blog;
    }

    public int Updateblog(Blog blog){
        int Blog = userRepository.updateBlogByIdAndTitle(blog.getId(),blog.getTitle(),blog.getSubtitle(), blog.getContent());
        return Blog;
    }

    public int DeleteBlog(Blog blog){
        int Blog = userRepository.deleteByIdAndTitle(blog.getId(), blog.getTitle());
        return Blog;
    }

    public int AddBlog(Blog blog){
        int Blog = userRepository.insertBlog(blog.getId(), blog.getTitle(), blog.getSubtitle(), blog.getContent());
        return  Blog;
    }
}

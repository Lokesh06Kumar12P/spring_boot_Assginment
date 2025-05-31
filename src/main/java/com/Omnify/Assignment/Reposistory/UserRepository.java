package com.Omnify.Assignment.Reposistory;

import com.Omnify.Assignment.Entity.User;
import com.Omnify.Assignment.Model.Blog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Query(value = "SELECT * FROM blog", nativeQuery = true)
    List<Blog> getAllBlogs();

    @Query(value = "SELECT * FROM blog WHERE id = :id", nativeQuery = true)
    List<Blog> getById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE blog SET subtitle = :subtitle, content = :content WHERE id = :id AND title = :title", nativeQuery = true)
    int updateBlogByIdAndTitle(@Param("id") int id,
                               @Param("title") String title,
                               @Param("subtitle") String subtitle,
                               @Param("content") String content);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM blog WHERE id = :id AND title = :title", nativeQuery = true)
    int deleteByIdAndTitle(@Param("id") int id,@Param("title") String title);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO blog (id,title, subtitle, content) VALUES (:id,:title,:subtitle,:content)", nativeQuery = true)
    int insertBlog(@Param("id") int id, @Param("title") String title, @Param("subtitle") String subtitle, @Param("content") String content);
}

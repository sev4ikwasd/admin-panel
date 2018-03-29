package com.sev4ikwasd.dao;

import com.sev4ikwasd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    @Autowired
    private JdbcTemplate template;

    public List<User> getAll() {
        try {
            return template.query("SELECT * FROM user;", new BeanPropertyRowMapper<>(User.class));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User get(Long id) {
        try {
            return template.queryForObject("SELECT * FROM user WHERE id = " + id.toString() + ";", new BeanPropertyRowMapper<>(User.class));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Void add(User user) {
        template.update("INSERT INTO user(username, password, email) VALUES(?, ?, ?)", user.getUsername(), user.getPassword(), user.getEmail());
        return null;
    }

    public User edit(User user) {
        template.update("UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?;", user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
        try {
            return template.queryForObject("SELECT * FROM user WHERE id = " + user.getId().toString() + ";", new BeanPropertyRowMapper<>(User.class));
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Void delete(Long id) {
        template.update("DELETE FROM user WHERE id = " + id.toString() + ";");
        return null;
    }
}

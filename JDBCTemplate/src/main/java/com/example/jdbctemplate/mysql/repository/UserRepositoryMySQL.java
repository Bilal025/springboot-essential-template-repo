package com.example.jdbctemplate.mysql.repository;

import com.example.jdbctemplate.mysql.model.UserMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryMySQL {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserMySQL> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    public UserMySQL findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
    }

    public int save(UserMySQL user) {
        return jdbcTemplate.update("INSERT INTO users (id, name, email) VALUES (?, ?, ?)", user.getId(), user.getName(), user.getEmail());
    }

    public int updateEmail(int id, String email) {
        return jdbcTemplate.update("UPDATE users SET email = ? WHERE id = ?", email, id);
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    private static class UserRowMapper implements RowMapper<UserMySQL> {
        @Override
        public UserMySQL mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserMySQL(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
        }
    }
}

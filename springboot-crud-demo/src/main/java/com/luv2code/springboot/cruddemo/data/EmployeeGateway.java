package com.luv2code.springboot.cruddemo.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeGateway {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JdbcTemplate jdbcTemplate;

    public EmployeeGateway(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, first_name, last_name, email from employee", rowMapper);
    }

    public List<Employee> findAllOrderByLastName() {
        return jdbcTemplate.query("select id, first_name, last_name, email from employee" +
                " order by last_name", rowMapper);
    }


    public Employee findById(int id) {
        return jdbcTemplate.query(
                "select id, first_name, last_name, email from employee where id = ?",
                singleRowMapper, id);
    }

    public Employee save(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "insert into employee (first_name, last_name, email) values (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            return ps;
        }, keyHolder);
        return findById(keyHolder.getKey().intValue());
    }

    public Employee update(Employee employee) {
        if (employee.getId() == 0) {
            return null;
        }
        int rows = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "update employee set first_name = ?, last_name = ?, email = ?  where id = ?"
            );
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getId());
            return ps;
        });
        if (rows == 0) {
            return null;
        } else if (rows > 1) {
            logger.warn("update rows: {}, employee.id: {}", rows, employee.getId());
        }
        return findById(employee.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("delete from employee where id = ?", id);
    }

    private RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        return new Employee(rs.getInt("id"), rs.getString("first_name"),
                rs.getString("last_name"), rs.getString("email"));
    };

    private ResultSetExtractor<Employee> singleRowMapper = (rs) -> {
        return rs.next() ? rowMapper.mapRow(rs, 1) : null;
    };

}

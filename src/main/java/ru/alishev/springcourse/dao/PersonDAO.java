package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("Select * From Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("Select * From Person Where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT Into Person VALUES (1, ?, ?, ?)", person.getName(), person.getEmail(), person.getAge());
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

    }

    public void update(int id, Person personUpdated) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", personUpdated.getName(), personUpdated.getAge(),
                personUpdated.getEmail(), id);
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(personUpdated.getName());
//        personToBeUpdated.setAge(personUpdated.getAge());
//        personToBeUpdated.setEmail(personUpdated.getEmail());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from Person where id=? ", id);
//        people.removeIf(p->p.getId()==id);

    }
}

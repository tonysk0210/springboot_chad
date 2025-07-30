package com.example.demo.dao;

import com.example.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create jpql
        String jpql = "SELECT s FROM Student s ORDER BY s.lastName";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);

        //return query results
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        //create jpql
        String jpql = "SELECT s FROM Student s WHERE s.lastName = :lastName";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);

        //set named parameter
        query.setParameter("lastName", lastName);

        //return query results
        return query.getResultList();
    }

    @Transactional
    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Transactional
    @Override
    public void delete(int id) {
        //retrieve student
        Student student = entityManager.find(Student.class, id);
        //delete student
        entityManager.remove(student);
    }

    @Transactional
    @Override
    public int deleteAll() {
        String jpql = "Delete from Student s";
        int numRowsDeleted = entityManager.createQuery(jpql).executeUpdate();
        return numRowsDeleted;
    }
}

package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}

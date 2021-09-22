package com.example.demo.repositories;

import java.util.List;
import com.example.demo.repositories.BalancesRepository;
import com.example.demo.Service.BalanceService;
import com.example.demo.Entity.Balances;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface BalancesRepository extends JpaRepository <Balances, Long>
{
List<Balances>findByAccountnr(String accountnr);
    List<Balances> getBalancesForUpdate(String accountnr);
}
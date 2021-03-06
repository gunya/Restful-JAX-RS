package com.hepexta.jaxrs.bank.repository.cache;

import com.hepexta.jaxrs.bank.model.Account;
import com.hepexta.jaxrs.bank.repository.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryCache implements Repository<Account> {

    private AtomicInteger accountCounter = new AtomicInteger();

    private Map<String, Account> accounts = new HashMap<>();

    private AccountRepositoryCache() {
    }

    private static AccountRepositoryCache INSTANCE;

    public static AccountRepositoryCache getINSTANCE(){
        if (INSTANCE==null){
            INSTANCE = new AccountRepositoryCache();
        }
        return INSTANCE;
    }
    @Override
    public Collection<Account> getList() {
        return accounts.values();
    }

    @Override
    public Account findById(String id) {
        return accounts.get(id);
    }

    @Override
    public String insert(Account model) {
        String id = String.valueOf(accountCounter.incrementAndGet());
        model.setNumber(id);
        accounts.put(id, model);
        return id;
    }

    @Override
    public boolean modify(Account model) {
        return accounts.put(model.getNumber(), model) != null;
    }

    @Override
    public boolean delete(String id) {
        return accounts.remove(id) != null;
    }

    public void clear(){
        accounts = new HashMap<>();
        accountCounter = new AtomicInteger();
    }
}

package com.restaurant_backend.restaurant_backend.service.impl;

import com.restaurant_backend.restaurant_backend.entity.UserMaster;
import com.restaurant_backend.restaurant_backend.repository.UserMasterRepository;
import com.restaurant_backend.restaurant_backend.service.UserMasterService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserMasterService {
    @Autowired
    UserMasterRepository userMasterRepository;
   @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<UserMaster> findAll() {
        return userMasterRepository.findAll();
    }

    @Override
    public UserMaster findByUserName (String userName) {
        Optional<UserMaster> result =userMasterRepository.findByUserName(userName);

        UserMaster user =null;

        if(result.isPresent()){

            user =result.get();
        }else{

            throw  new RuntimeException("Did not find User name - "+userName);
        }
        return user;
    }

    @Override
    public UserMaster save(UserMaster user) {
        return userMasterRepository.save(user);
    }

    @Override
    public UserMaster update(UserMaster user) {
        Transaction transaction =null;
        try{

            Session session = sessionFactory.openSession();

            transaction = session.beginTransaction();

            session.update(user);
            transaction.commit();

        }catch (Exception e){

        }

        return user;
    }

    @Override
    public void deleteById(Long id) {
        userMasterRepository.deleteById(id);
    }
}

//package com.solo.test.service;
//
//
//import com.solo.test.domain.Account;
//import com.solo.test.domain.AccountAdapter;
//import com.solo.test.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//public class AccountService implements UserDetailsService {
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public Account saveAccount(Account account) {
//        account.setPassword(passwordEncoder.encode(account.getPassword()));
//        return accountRepository.save(account);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Account account = accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
//        return new AccountAdapter(account);
//    }
//}

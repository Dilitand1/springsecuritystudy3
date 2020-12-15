package ru.litvinov.springsecuritystudy2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.litvinov.springsecuritystudy2.model.User;
import ru.litvinov.springsecuritystudy2.repo.UserRepository;

@Service("userDetailsServiceImpl")
public class UserDetailsSeviceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsSeviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found"));
        return null;
    }
}

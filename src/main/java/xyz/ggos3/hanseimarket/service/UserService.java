package xyz.ggos3.hanseimarket.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.ggos3.hanseimarket.domain.user.account.Account;
import xyz.ggos3.hanseimarket.domain.user.account.AccountRepository;
import xyz.ggos3.hanseimarket.domain.user.User;
import xyz.ggos3.hanseimarket.domain.user.UserRepository;
import xyz.ggos3.hanseimarket.dto.user.account.request.AccountCreateRequest;
import xyz.ggos3.hanseimarket.vo.user.UserCreateVO;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void saveUser(UserCreateVO request) {
        User newUser = new User(
                request.getName(),
                request.getStudentCode(),
                request.getPhoneNumber()
        );
        userRepository.save(newUser);
        log.info("User Class = {}", newUser.getStudentClass());
    }

    @Transactional
    public void createAccount(AccountCreateRequest request) {
        UserCreateVO userCreateRequest = request.getUserCreateRequest();
        Account newAccount = new Account(request.getAccountId(), request.getAccountPassword());

        saveUser(userCreateRequest);
        accountRepository.save(newAccount);
        log.info("newAccount = {}", newAccount.getId());
    }
}

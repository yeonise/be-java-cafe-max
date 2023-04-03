package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<String, User> STORE = new ConcurrentHashMap<>(); // 동시성 문제

    @Override
    public User save(User user) {
        STORE.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User findByUserId(String userId) {
        return STORE.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(STORE.values());
    }

    @Override
    public boolean isExists(String userId) {
        return STORE.containsKey(userId);
    }
}

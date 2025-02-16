package org.lib.library.repository;

import org.lib.library.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo extends JpaRepository<Guest, Long> {
    boolean existsByPhone(String phone);
}

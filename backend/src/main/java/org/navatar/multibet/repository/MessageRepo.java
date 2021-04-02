package org.navatar.multibet.repository;

import org.navatar.multibet.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}

package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chats")
@NamedEntityGraph(name = "chat-entity-graph",
        attributeNodes = {@NamedAttributeNode("sender"), @NamedAttributeNode("recipient")})
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "chat_id", nullable = false, unique = true)
    private String chatId;

    //@BatchSize(size = 5)
    @OneToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

   // @BatchSize(size = 5)
    @OneToOne(targetEntity = User.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    public Chat(String chatId, User sender, User recipient) {
        this.chatId = chatId;
        this.sender = sender;
        this.recipient = recipient;
    }

}

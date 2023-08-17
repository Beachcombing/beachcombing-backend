package beachcombing.backend.domain.giftcard.domain;

import beachcombing.backend.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Giftcard {

    @Id
    @Column(name = "giftcard_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cost;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}

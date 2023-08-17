package beachcombing.backend.domain.giftcard.domain;

import beachcombing.backend.domain.store.domain.Store;
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

    @Builder
    public Giftcard(Long id, Integer cost, Store store) {
        this.cost = cost;
        this.store = store;
    }

    public Giftcard createGiftcard(Long id, Integer cost, Store store) {
        return Giftcard.builder()
                .cost(cost)
                .store(store)
                .build();
    }


}

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
    private Long id; // 상품권 id

    private Integer cost; // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store; // 사용 가능한 상점

    @Builder
    public Giftcard(Integer cost, Store store) {
        this.cost = cost;
        this.store = store;
    }

    public Giftcard createGiftcard(Integer cost, Store store) {
        return Giftcard.builder()
                .cost(cost)
                .store(store)
                .build();
    }


}

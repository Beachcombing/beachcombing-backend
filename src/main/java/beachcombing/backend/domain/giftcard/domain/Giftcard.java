package beachcombing.backend.domain.giftcard.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Giftcard {

    @Id
    @Column(name = "giftcard_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cost;

    private Long store_id;

}

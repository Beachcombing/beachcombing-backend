package beachcombing.backend.domain.store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상점 id

    @Column(nullable = false)
    private String name; // 이름
    private String location; // 위치
    private String image; // 상점 이미지

    @Builder
    public Store(String name, String location, String image) {
        this.name = name;
        this.location = location;
        this.image = image;
    }

    public static Store createStore(String name, String location, String image) {
        return Store.builder()
                .name(name)
                .location(location)
                .image(image)
                .build();
    }
}

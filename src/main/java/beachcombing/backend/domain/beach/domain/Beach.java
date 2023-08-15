package beachcombing.backend.domain.beach.domain;

import beachcombing.backend.domain.common.domain.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Beach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beach_id")
    private Long id;

    private String name;
    private String image;
    private String range;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lng", column = @Column(nullable = false, precision = 10, scale = 8)),
            @AttributeOverride(name = "lng", column = @Column(nullable = false, precision = 11, scale = 8))
    })
    private Location location; // TODO: column embeddable 타입 내에서 적용해도 바깥까지 적용되는지 확인해보기

    @Builder
    public Beach(String name, String image, String range, Location location) {

        this.name = name;
        this.image = image;
        this.range = range;
        this.location = location;
    }

    public static Beach createBeach(String name, String image, String range, Location location) {

        return Beach.builder()
                .name(name)
                .image(image)
                .range(range)
                .location(location)
                .build();
    }
}

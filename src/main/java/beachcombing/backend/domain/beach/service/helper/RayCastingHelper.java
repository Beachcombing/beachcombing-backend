package beachcombing.backend.domain.beach.service.helper;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class RayCastingHelper {
    // 점 (x, y)가 다각형 내부에 있는지 판별하는 메서드
    public boolean isInsidePolygon(List<BigDecimal> xCoords, List<BigDecimal> yCoords, BigDecimal x, BigDecimal y) {
        int numVertices = xCoords.size(); // 다각형의 꼭짓점 수
        boolean inside = false;

        for (int i = 0, j = numVertices - 1; i < numVertices; j = i++) { // 다각형의 모든 변을 순회
            BigDecimal xi = xCoords.get(i);
            BigDecimal yi = yCoords.get(i);
            BigDecimal xj = xCoords.get(j);
            BigDecimal yj = yCoords.get(j);

            // 현재 점과 현재 선분이 교차하는지를 판별하는 조건
            boolean intersectCondition = ((yi.compareTo(y) > 0) != (yj.compareTo(y) > 0))
                    && (x.compareTo(xi.add(xj.subtract(xi).multiply(y.subtract(yi).divide(yj.subtract(yi),RoundingMode.HALF_DOWN)))) < 0);

            if (intersectCondition) {
                inside = !inside; // 교차할 때마다 내부/외부 상태를 변경
            }
        }
        return inside;
    }
}

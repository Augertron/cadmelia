/*
 * Copyright 2017 David Naramski.
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *      http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.nowina.cadmelia.shape;

import net.nowina.cadmelia.construction.Polygon;
import net.nowina.cadmelia.construction.Vector;

import java.util.List;

public abstract class ShapeUtil {

    public static boolean isCW(Polygon polygon) {

        List<Vector> points = polygon.getPoints();
        return isCW(points);
    }

    public static boolean isCW(List<Vector> points) {
        double total = 0;
        for (int i = 0; i < points.size(); i++) {

            Vector p1 = points.get(i);

            int nexti = (i + 1) % points.size();
            Vector p2 = points.get(nexti);

            double current = (p2.x() - p1.x()) * (p2.y() + p1.y());
            total += current;
        }

        return total > 0;
    }

    public static boolean isCCW(Polygon polygon) {

        return !isCW(polygon);
    }

}

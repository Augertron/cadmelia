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
package net.nowina.cadmelia;

import java.util.ArrayList;
import java.util.List;

public class TriangleMesh {

    List<Triangle> polygons;

    public TriangleMesh(List<Triangle> polygons) {
        this.polygons = polygons;
    }

    public TriangleMesh(TriangleMesh... polyhedron) {
        polygons = new ArrayList<>();
        for (TriangleMesh p : polyhedron) {
            polygons.addAll(p.polygons);
        }
    }

    int getPolygonCount() {
        return polygons.size();
    }

    public List<Triangle> getPolygons() {
        return polygons;
    }

}

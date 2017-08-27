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
package net.nowina.cadmelia.solid.bspcsg;

import net.nowina.cadmelia.construction.Vector;
import net.nowina.cadmelia.solid.CubeTesselation;

public class Cube extends CubeTesselation<CSGSolid> {

    public Cube(CSGSolidBuilder builder, double sizeX, double sizeY, double sizeZ, boolean centered) {
        this(builder, new Vector(centered ? -sizeX / 2 : 0, centered ? -sizeY / 2 : 0, centered ? -sizeZ / 2 : 0),
                new Vector(centered ? sizeX / 2 : sizeX, centered ? sizeY / 2 : sizeY, centered ? sizeZ / 2 : sizeZ));
    }

    public Cube(CSGSolidBuilder builder, Vector min, Vector max) {
        super(min, max, new MeshToCSGSolid(builder));
    }

}
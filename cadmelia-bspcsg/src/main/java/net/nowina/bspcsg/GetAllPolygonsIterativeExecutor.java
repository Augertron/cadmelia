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
package net.nowina.bspcsg;

import net.nowina.bspcsg.collection.PolygonList;
import net.nowina.bspcsg.collection.PolygonListBrowser;

public class GetAllPolygonsIterativeExecutor extends GetAllPolygonsExecutor {

    public GetAllPolygonsIterativeExecutor(Factory factory) {
        super(factory);
    }

    @Override
    public PolygonList execute(Node root) {

        PolygonList collection = getFactory().newPolygonList();

        preOrderTraversal(root, node -> {
            for (PolygonListBrowser browser = node.polygons.browse(); browser.hasNext(); browser.next()) {
                collection.addPolygon(browser.get());
            }
        });

        if (getFactory().isUsingDecomposablePolygon()) {
            PolygonList filtered = recomposePolygon(collection);
            return filtered;
        }

        return collection;

    }

}

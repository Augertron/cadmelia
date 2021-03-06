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
package net.nowina.cadmelia.script.expression;

import net.nowina.cadmelia.construction.Vector;
import net.nowina.cadmelia.script.Expression;
import net.nowina.cadmelia.script.Literal;
import net.nowina.cadmelia.script.ScriptContext;

import java.util.List;

public class VectorElementExpression extends Expression {

    private Expression list;

    private Expression indexExpr;

    public VectorElementExpression(Expression list, Expression index) {
        this.list = list;
        this.indexExpr = index;
    }

    @Override
    protected Object doEvaluation(ScriptContext scriptContext) {

        List value = list.evaluate(scriptContext).asList();
        int index = indexExpr.evaluateAsInteger(scriptContext);
        return value.get(index);
    }

    @Override
    public String toString() {
        return list + "[" + indexExpr + "]";
    }
}

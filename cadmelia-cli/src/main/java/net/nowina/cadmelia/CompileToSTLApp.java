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

import net.nowina.cadmelia.construction.BuilderFactory;
import net.nowina.cadmelia.script.Script;
import net.nowina.cadmelia.script.ScriptScene;
import net.nowina.cadmelia.script.parser.ParseException;
import net.nowina.cadmelia.script.parser.ScriptParser;
import net.nowina.cadmelia.shape.impl.ShapeImplBuilder;
import net.nowina.cadmelia.solid.bspcsg.FactoryBuilder;
import net.nowina.cadmelia.stl.STLWriter;

import java.io.*;

public class CompileToSTLApp {

    private BuilderFactory builderFactory;
    private File input;
    private File output;

    public static void main(String[] argv) throws Exception {

        CompileToSTLApp app = new CompileToSTLApp();

        File input = new File(argv[1]);
        app.setInput(input);
        File output = new File(argv[2]);
        app.setOutput(output);

        app.render();

    }

    public void render() throws IOException, ParseException {

        try (InputStreamReader in = new InputStreamReader(new FileInputStream(input))) {
            render(in);
        }

    }

    public void render(Reader in) throws ParseException, IOException {
        BuilderFactory factory = getBuilderFactory();
        ScriptScene scene = new ScriptScene(factory);

        ScriptParser parser = new ScriptParser(in);
        Script script = parser.Script();
        scene.executeScript(script);

        try(PrintWriter out = new PrintWriter(new FileOutputStream(output))) {
            STLWriter writer = new STLWriter();
            writer.write(scene.getRoot(), out);
        }
    }

    private BuilderFactory getBuilderFactory() {
        if(builderFactory == null) {
            BuilderFactory.registerShapeBuilder(new ShapeImplBuilder());
            BuilderFactory.registerSolidBuilder(new FactoryBuilder().build());
            builderFactory = BuilderFactory.getInstance();
        }
        return builderFactory;
    }

    public void setInput(File input) {
        this.input = input;
    }

    public void setOutput(File output) {
        this.output = output;
    }

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jasper.compiler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import io.undertow.test.TomcatBaseTest;
import org.junit.Assert;
import org.junit.Test;


import org.apache.tomcat.util.buf.ByteChunk;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TestJspDocumentParser extends TomcatBaseTest {

    @Test
    public void testBug47977() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug47977.jspx", new ByteChunk(), null);

        Assert.assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, rc);
    }

    @Test
    public void testBug48827() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        Exception e = null;
        try {
            getUrl("http://localhost:" + getPort() + "/test/bug48nnn/bug48827.jspx");
        } catch (IOException ioe) {
            e = ioe;
        }

        // Should not fail
        Assert.assertNull(e);
    }

    @Test
    public void testBug54801() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk bc = new ByteChunk();
        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54801a.jspx", bc, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        bc.recycle();
        rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54801b.jspx", bc, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
    }

    @Test
    public void testBug54821() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk bc = new ByteChunk();
        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54821a.jspx", bc, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        bc.recycle();
        rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54821b.jspx", bc, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
   }

 
}
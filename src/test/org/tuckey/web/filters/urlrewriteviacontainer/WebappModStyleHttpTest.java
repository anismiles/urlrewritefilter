/**
 * Copyright (c) 2005-2007, Paul Tuckey
 * All rights reserved.
 * ====================================================================
 * Licensed under the BSD License. Text as follows.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   - Neither the name tuckey.org nor the names of its contributors
 *     may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 */
package org.tuckey.web.filters.urlrewriteviacontainer;

import org.apache.commons.httpclient.methods.GetMethod;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * @author Paul Tuckey
 * @version $Revision: 33 $ $Date: 2006-09-12 16:41:56 +1200 (Tue, 12 Sep 2006) $
 */
public class WebappModStyleHttpTest extends ContainerTestBase {

    protected String getApp() {
        return "webapp-mod-style";
    }

    public void testStatusRecord() throws IOException {
        super.recordRewriteStatus();
    }

    public void testSimpleTest() throws ServletException, IOException, SAXException {
        GetMethod method = new GetMethod(getBaseUrl() + "/index.jsp");
        method.setFollowRedirects(false);
        client.executeMethod(method);
        assertEquals("this is index.jsp", method.getResponseBodyAsString());
    }

    public void testSimpleTestRewrite() throws ServletException, IOException, SAXException {
        GetMethod method = new GetMethod(getBaseUrl() + "/simple/test");
        method.setFollowRedirects(false);
        client.executeMethod(method);
        assertEquals("this is index.jsp", method.getResponseBodyAsString());
    }

    public void testStatus1() throws ServletException, IOException, SAXException {
        GetMethod method = new GetMethod(getBaseUrl() + "/rewrite-status");
        method.setFollowRedirects(false);
        client.executeMethod(method);
        assertTrue(method.getResponseBodyAsString().contains("Running Status"));
        assertFalse(method.getResponseBodyAsString().contains("Error"));
    }

}

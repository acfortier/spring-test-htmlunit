/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.test.web.servlet.htmlunit;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * @author Rob Winch
 */
public class MockMvcWebConnectionTests {
	MockMvc mockMvc;

	WebClient webClient;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
							.standaloneSetup(new HelloController())
							.build();

		webClient = new WebClient();
	}

	@Test
	public void contextPathNull() throws IOException {
		webClient.setWebConnection(new MockMvcWebConnection(mockMvc, null));

		Page page = webClient.getPage("http://localhost/context/a");

		assertThat(page.getWebResponse().getStatusCode()).isEqualTo(200);
	}

	@Test
	public void contextPathExplicit() throws IOException {
		webClient.setWebConnection(new MockMvcWebConnection(mockMvc, "/context"));

		Page page = webClient.getPage("http://localhost/context/a");

		assertThat(page.getWebResponse().getStatusCode()).isEqualTo(200);
	}

	@Test
	public void contextPathEmpty() throws IOException {
		webClient.setWebConnection(new MockMvcWebConnection(mockMvc, ""));

		Page page = webClient.getPage("http://localhost/context/a");

		assertThat(page.getWebResponse().getStatusCode()).isEqualTo(200);
	}

	@Test(expected = IllegalArgumentException.class)
	public void contextPathDoesNotStartWithSlash() throws IOException {
		new MockMvcWebConnection(mockMvc, "context");
	}

	@Test(expected = IllegalArgumentException.class)
	public void contextPathEndsWithSlash() throws IOException {
		new MockMvcWebConnection(mockMvc, "/context/");
	}
}
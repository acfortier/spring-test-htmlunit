/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.springframework.test.web.server.htmlunit.pages

import geb.Module


/**
 * Represents a Message when viewed in a list view
 * @author Rob Winch
 *
 */
class MessageRow extends Module {
	static content = {
		cell { $("td", it) }
		user { cell(0).text() }
		subject { cell(1).text() }
		link { cell(1).$('a') }
	}
}
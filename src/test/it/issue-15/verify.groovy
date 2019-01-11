/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012-2014 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle licenses this file to You under the Apache License, Version 2.0
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
 *
 *
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright 2006 Codehaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Verify that correct set of files have been generated
 
// execution id1
// Hello.wsdl should have been used
def source = new File( basedir, 'target/generated-sources/wsimport/ws1/HelloWs.java' )
assert source.exists()

// execution id2
// po2.wsdl should have been used, not echo.wsdl
source = new File( basedir, 'target/generated-sources/wsimport/ws2/ProcessOrder.java' )
assert source.exists()
source = new File( basedir, 'target/generated-sources/wsimport/ws2/EchoService.java' )
assert !source.exists()

// execution id3
// ProcessOrder and echo.wsdl should have been used
source = new File( basedir, 'target/generated-sources/test-wsimport/ws3/ProcessOrder.java' )
assert source.exists()
source = new File( basedir, 'target/generated-sources/test-wsimport/ws3/EchoService.java' )
assert source.exists()

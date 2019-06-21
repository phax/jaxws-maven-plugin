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
package org.codehaus.mojo.jaxws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.testng.Assert;

/**
 * @author lukas
 */
public class Assertions
{
  public static void assertFilePresent (final File project, final String path)
  {
    final File f = new File (project, path);
    Assert.assertTrue (f.exists (), "Not found " + f.getAbsolutePath ());
  }

  public static void assertFileNotPresent (final File project, final String path)
  {
    final File f = new File (project, path);
    Assert.assertFalse (f.exists (), "Found " + f.getAbsolutePath ());
  }

  public static void assertFileContains (final File project, final String path, final String s) throws IOException
  {
    final File f = new File (project, path);
    Assert.assertTrue (f.exists (), f.getAbsolutePath () + " does not exist");
    Assert.assertTrue (f.isFile (), f.getAbsolutePath () + " is not a file");
    try (BufferedReader r = new BufferedReader (new FileReader (f)))
    {
      if (r.lines ().filter (l -> l.contains (s)).findFirst ().isPresent ())
      {
        return;
      }
    }
    Assert.fail ("'" + s + "' is missing in:" + f.getAbsolutePath ());
  }

  public static void assertJarContains (final File project,
                                        final String jarName,
                                        final String path) throws ZipException, IOException
  {
    final File f = new File (project, "target/" + jarName);
    Assert.assertTrue (f.exists (), f.getAbsolutePath () + " does not exist");
    Assert.assertTrue (f.isFile (), f.getAbsolutePath () + " is not a file");
    try (ZipFile zf = new ZipFile (f))
    {
      Assert.assertNotNull (zf.getEntry (path), "'" + path + "' is missing in: " + jarName);
    }
  }

  public static void assertJarNotContains (final File project,
                                           final String jarName,
                                           final String path) throws ZipException, IOException
  {
    final File f = new File (project, "target/" + jarName);
    Assert.assertTrue (f.exists (), f.getAbsolutePath () + " does not exist");
    Assert.assertTrue (f.isFile (), f.getAbsolutePath () + " is not a file");
    try (ZipFile zf = new ZipFile (f))
    {
      Assert.assertNull (zf.getEntry (path), "'" + path + "' is in: " + jarName);
    }
  }
}

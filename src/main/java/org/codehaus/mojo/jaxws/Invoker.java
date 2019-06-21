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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lukas
 */
public final class Invoker
{
  private static final Logger LOGGER = Logger.getLogger (Invoker.class.getName ());

  public static void main (final String... args) throws Exception
  {
    final String toolClassname = args[0];

    int idx = 1;
    String cp = args[2];
    if ("-pathfile".equals (args[1]))
    {
      // load cp from properties file
      final File pathFile = new File (args[2]);
      pathFile.deleteOnExit ();
      final Properties p = new Properties ();
      try (InputStream is = new FileInputStream (pathFile))
      {
        p.load (is);
      }
      cp = p.getProperty ("cp");
      idx = 3;
    }

    final URLClassLoader cl = new URLClassLoader (_toUrls (cp));

    // save original classloader and java.class.path property
    final ClassLoader orig = Thread.currentThread ().getContextClassLoader ();
    final String origJcp = System.getProperty ("java.class.path");

    // set to values for tool invocation
    Thread.currentThread ().setContextClassLoader (cl);
    System.setProperty ("java.class.path", cp);
    try
    {
      final Class <?> toolClass = cl.loadClass (toolClassname);

      final Object tool = toolClass.getConstructor (OutputStream.class).newInstance (System.out);
      final Method runMethod = toolClass.getMethod ("run", String [].class);

      final String [] wsargs = new String [args.length - idx];
      System.arraycopy (args, idx, wsargs, 0, args.length - idx);

      final Boolean result = (Boolean) runMethod.invoke (tool, new Object [] { wsargs });
      System.exit (result.booleanValue () ? 0 : 1);
    }
    catch (final NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException
        | IllegalAccessException | IllegalArgumentException ex)
    {
      LOGGER.log (Level.SEVERE, null, ex);
    }
    catch (final InvocationTargetException ex)
    {
      throw new RuntimeException (ex);
    }
    finally
    {
      Thread.currentThread ().setContextClassLoader (orig);
      System.setProperty ("java.class.path", origJcp);
    }
  }

  private static URL [] _toUrls (final String c)
  {
    final List <URL> urls = new ArrayList <> ();
    for (final String s : c.split (File.pathSeparator))
    {
      try
      {
        urls.add (new File (s).toURI ().toURL ());
      }
      catch (final MalformedURLException ex)
      {
        LOGGER.log (Level.SEVERE, null, ex);
      }
    }
    return urls.toArray (new URL [0]);
  }
}

/*
 * Copyright 2013 Brian Matthews
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btmatthews.maven.plugins.grinder;

import com.btmatthews.utils.monitor.AbstractServer;
import com.btmatthews.utils.monitor.Logger;
import net.grinder.common.GrinderException;
import net.grinder.engine.agent.Agent;
import net.grinder.engine.agent.AgentDaemon;
import net.grinder.engine.agent.AgentImplementation;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Used to launch, configure and shutdown the local Grinder agent.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class GrinderServer extends AbstractServer {
    /**
     * The name of the property that contains a reference to the <a href="http://grinder.sourceforge.net/g3/properties.html">grinder.properties</a>
     * file.
     */
    public final static String PROPERTIES_FILE_PROPERTY = "propertiesFile";
    /**
     * Used by Grinder to output log messages.
     */
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GrinderServer.class);
    /**
     * The property that contains a reference to the <a href="http://grinder.sourceforge.net/g3/properties.html">grinder.properties</a>
     * file.
     */
    private File propertiesFile;
    /**
     * The local Grinder agent.
     */
    private Agent agent;

    /**
     * Configure a server property. The only property supported is {@link #PROPERTIES_FILE_PROPERTY} which contains a
     * reference to the <a href="http://grinder.sourceforge.net/g3/properties.html">grinder.properties</a> file.
     *
     * @param name   The property name.
     * @param value  The property value.
     * @param logger Used to log information and error messages.
     */
    @Override
    public void configure(final String name,
                          final Object value,
                          final Logger logger) {
        if (PROPERTIES_FILE_PROPERTY.equals(name)) {
            if (value instanceof File) {
                propertiesFile = (File) value;
            } else {
                logger.logError("Property \"" + PROPERTIES_FILE_PROPERTY + "\" must be of type java.io.File");
            }
        } else {
            logger.logError("Property \"" + name + "\" is not supported");
        }
    }

    /**
     * Start the local Grinder agent. The local Grinder agent is run as a daemon process.
     *
     * @param logger Used to log information and error messages.
     */
    @Override
    public void start(final Logger logger) {
        try {
            agent = new AgentDaemon(LOGGER, 60000, new AgentImplementation(LOGGER, propertiesFile, true));
        } catch (final GrinderException e) {
            logger.logError("Failed to start agent daemon", e);
        }
    }

    /**
     * Stop the local Grinder agent.
     *
     * @param logger Used to log information and error messages.
     */
    @Override
    public void stop(final Logger logger) {
        agent.shutdown();
    }
}

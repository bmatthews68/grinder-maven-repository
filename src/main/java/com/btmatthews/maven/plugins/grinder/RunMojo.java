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

import com.btmatthews.utils.monitor.mojo.AbstractRunMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This mojo launches a local Grinder agent.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.INTEGRATION_TEST)
public class RunMojo extends AbstractRunMojo {

    /**
     * The location of the <code>grinder.properties</code> file that configures the local Grinder agent.
     */
    @Parameter(property = "grinder.properties", defaultValue = "grinder/grinder.properties")
    private File propertiesFile;

    /**
     * Get the name that is used to locate the server factory.
     *
     * @return Always returns {@link GrinderServerFactory#SERVER_NAME}.
     */
    @Override
    public String getServerType() {
        return GrinderServerFactory.SERVER_NAME;
    }

    /**
     * Get the properties that will be used to configure the server.
     *
     * @return A map containing the reference to the properties file.
     */
    @Override
    public Map<String, Object> getServerConfig() {
        final Map<String, Object> config = new HashMap<String, Object>();
        config.put(GrinderServer.PROPERTIES_FILE_PROPERTY, propertiesFile);
        return config;
    }
}

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

import com.btmatthews.utils.monitor.Server;
import com.btmatthews.utils.monitor.ServerFactory;

/**
 * Used to create the object that launches, configures and shuts down the local Grinder agent.
 *
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class GrinderServerFactory implements ServerFactory {
    /**
     * The name used to identify the server that controls the local Grinder agent.
     */
    public static final String SERVER_NAME = "grinder";

    /**
     * Get the name of the server that controls the local Grinder agent.
     *
     * @return Always returns {@link #SERVER_NAME}.
     */
    @Override
    public String getServerName() {
        return SERVER_NAME;
    }

    /**
     * Create a server that controls a local Grinder agent.
     *
     * @return An instance of {@link GrinderServer}.
     */
    @Override
    public Server createServer() {
        return new GrinderServer();
    }
}

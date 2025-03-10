/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.eventmesh.connector.rabbitmq;

import org.apache.eventmesh.connector.rabbitmq.consumer.RabbitmqConsumer;
import org.apache.eventmesh.connector.rabbitmq.producer.RabbitmqProducer;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;

public class RabbitmqServer {
    protected RabbitmqConsumer rabbitmqConsumer;
    protected RabbitmqProducer rabbitmqProducer;

    @Before
    public void setup() throws Exception {
        RabbitmqMockConnectionFactory rabbitmqMockConnectionFactory = new RabbitmqMockConnectionFactory();

        rabbitmqConsumer = new RabbitmqConsumer();
        rabbitmqConsumer.setRabbitmqConnectionFactory(rabbitmqMockConnectionFactory);
        rabbitmqConsumer.init(new Properties());
        rabbitmqConsumer.start();

        rabbitmqProducer = new RabbitmqProducer();
        rabbitmqProducer.setRabbitmqConnectionFactory(rabbitmqMockConnectionFactory);
        rabbitmqProducer.init(new Properties());
        rabbitmqProducer.start();
    }

    @After
    public void shutdown() {
        rabbitmqConsumer.shutdown();
        rabbitmqProducer.shutdown();
    }
}

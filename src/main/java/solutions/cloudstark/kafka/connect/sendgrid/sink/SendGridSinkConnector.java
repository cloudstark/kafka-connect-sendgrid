/*
 * Copyright 2019 SMB GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package solutions.cloudstark.kafka.connect.sendgrid.sink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
import solutions.cloudstark.kafka.connect.sendgrid.Version;
import solutions.cloudstark.kafka.connect.sendgrid.config.SendGridSinkConnectorConfig;

/**
 * Entry point for Kafka Connect SendGrid Sink.
 */
public class SendGridSinkConnector extends SinkConnector {

    private Map<String, String> config;

    @Override
    public void start(Map<String, String> properties) {
        this.config = properties;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return SendGridSinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> taskConfigs = new ArrayList<>(maxTasks);
        Map<String, String> taskProps = new HashMap<>();
        taskProps.putAll(config);
        for (int i = 0; i < maxTasks; i++) {
            taskConfigs.add(taskProps);
        }
        return taskConfigs;
    }

    @Override
    public void stop() {
        // nothing to do
    }

    @Override
    public ConfigDef config() {
        return SendGridSinkConnectorConfig.conf();
    }

    @Override
    public String version() {
        return Version.getVersion();
    }

}

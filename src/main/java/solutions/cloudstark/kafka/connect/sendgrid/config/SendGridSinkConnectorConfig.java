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

package solutions.cloudstark.kafka.connect.sendgrid.config;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

/**
 *
 */
@Slf4j
public class SendGridSinkConnectorConfig extends AbstractConfig {

    public static final String SENDGRID_API_KEY = "sendgrid.api.key";
    public static final String SENDGRID_FROM_NAME = "sendgrid.from.name";
    public static final String SENDGRID_FROM_NAME_DEFAULT = "";
    public static final String SENDGRID_FROM_EMAIL = "sendgrid.from.email";
    private static final String SENDGRID_API_KEY_DOC = "SendGrid API Key.";
    private static final String SENDGRID_FROM_NAME_DOC = "The name of the person or company that is sending the email.";
    private static final String SENDGRID_FROM_EMAIL_DOC = "The email address of the person or company that is sending the email.";

    public SendGridSinkConnectorConfig(ConfigDef config, Map<String, String> parsedConfig) {
        super(config, parsedConfig);
    }

    public SendGridSinkConnectorConfig(final Map<String, String> parsedConfig) {
        super(conf(), parsedConfig);
    }

    public static ConfigDef conf() {
        return new ConfigDef()
                .define(SENDGRID_API_KEY, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, SENDGRID_API_KEY_DOC)
                .define(SENDGRID_FROM_EMAIL, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH, SENDGRID_FROM_EMAIL_DOC)
                .define(SENDGRID_FROM_NAME, ConfigDef.Type.STRING, SENDGRID_FROM_NAME_DEFAULT, ConfigDef.Importance.LOW, SENDGRID_FROM_NAME_DOC);
    }

}

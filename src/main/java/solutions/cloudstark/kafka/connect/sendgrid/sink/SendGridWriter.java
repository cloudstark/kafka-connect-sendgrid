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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.sink.SinkRecord;
import solutions.cloudstark.kafka.connect.sendgrid.config.SendGridSinkConnectorConfig;

/**
 *
 */
@Slf4j
public class SendGridWriter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private SendGrid client;

    private Email from;

    public SendGridWriter(Map<String, String> properties) {
        final SendGridSinkConnectorConfig config = new SendGridSinkConnectorConfig(properties);

        from = new Email(
                config.getString(SendGridSinkConnectorConfig.SENDGRID_FROM_EMAIL),
                config.getString(SendGridSinkConnectorConfig.SENDGRID_FROM_NAME));

        client = new SendGrid(config.getString(SendGridSinkConnectorConfig.SENDGRID_API_KEY));
    }

    public void write(Collection<SinkRecord> records) {
        for (SinkRecord sinkRecord : records) {
            try {
                JsonNode jsonNode = MAPPER.valueToTree(sinkRecord.value());

                log.info(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));

                Mail mail = new Mail();
                Personalization personalization = new Personalization();

                mail.setFrom(from);

                String title = jsonNode.get("title").asText();
                personalization.setSubject(title);

                String body = jsonNode.get("body").asText();
                Content content = new Content("text/plain", body);
                mail.addContent(content);

                JsonNode recipients = jsonNode.get("recipients");
                if (recipients.isArray()) {
                    for (final JsonNode recipient : recipients) {
                        String name = recipient.get("name").asText();
                        String emailAddress = recipient.get("emailAddress").asText();
                        Email to = new Email(emailAddress, name);
                        personalization.addTo(to);
                    }
                }
                mail.addPersonalization(personalization);

                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    request.setBody(mail.build());
                    Response response = client.api(request);
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
            } catch (Exception e) {
                log.error("Error while sending", e);
            }
        }

    }
}
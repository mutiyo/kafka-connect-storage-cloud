/*
 * Copyright 2016 Confluent Inc.
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

package io.confluent.connect.s3;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Width;

import java.util.Map;

import io.confluent.connect.storage.StorageSinkConnectorConfig;

public class S3SinkConnectorConfig extends StorageSinkConnectorConfig {

  // S3 Group
  public static final String S3_BUCKET_CONFIG = "s3.bucket.name";
  private static final String S3_BUCKET_DOC = "The S3 Bucket.";
  private static final String S3_BUCKET_DISPLAY = "S3 Bucket";

  private final String name;

  static {
    {
      final String group = "S3";
      int orderInGroup = 0;
      CONFIG_DEF.define(S3_BUCKET_CONFIG,
                    Type.STRING,
                    Importance.HIGH,
                    S3_BUCKET_DOC,
                    group,
                    ++orderInGroup,
                    Width.MEDIUM,
                    S3_BUCKET_DISPLAY);
    }
  }

  public S3SinkConnectorConfig(Map<String, String> props) {
    this(CONFIG_DEF, props);
  }

  protected S3SinkConnectorConfig(ConfigDef configDef, Map<String, String> props) {
    super(configDef, props);
    this.name = parseName(props);
  }
  public String getBucketName() {
    return getString(S3_BUCKET_CONFIG);
  }

  protected static String parseName(Map<String, String> props) {
    String nameProp = props.get("name");
    return nameProp != null ? nameProp : "S3-sink";
  }

  public String getName() {
    return name;
  }

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.streams.kstream.internals;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.ProcessorSupplier;
import org.apache.kafka.streams.processor.api.Record;

public class KStreamPassThrough<KIn, VIn> implements ProcessorSupplier<KIn, VIn, KIn, VIn> {

    @Override
    public Processor<KIn, VIn, KIn, VIn> get() {
        return new KStreamPassThroughProcessor();
    }

    private class KStreamPassThroughProcessor implements Processor<KIn, VIn, KIn, VIn> {

        private ProcessorContext<KIn, VIn> context;

        @Override
        public void init(final ProcessorContext<KIn, VIn> context) {
            this.context = context;
        }

        @Override
        public void process(final Record<KIn, VIn> record) {
            context.forward(record);
        }
    }
}

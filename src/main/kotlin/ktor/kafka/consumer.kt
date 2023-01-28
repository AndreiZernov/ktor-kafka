package ktor.kafka

import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy
import mu.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.errors.WakeupException
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import org.apache.kafka.common.serialization.StringDeserializer

private val logger = KotlinLogging.logger {}

class Consumer<K, V>(private val consumer: KafkaConsumer<K, V>, topic: String) : ClosableJob {
    private val closed: AtomicBoolean = AtomicBoolean(false)
    private var finished = CountDownLatch(1)

    init {
        consumer.subscribe(listOf(topic))
    }

    override fun run() {
        try {
            while (!closed.get()) {
                val records = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS))
                for (record in records) {
                    logger.info { "topic = ${record.topic()}, partition = ${record.partition()}, offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}" }
                }
                if (!records.isEmpty) {
                    consumer.commitAsync { offsets, exception ->
                        if (exception != null) {
                            logger.error(exception) { "Commit failed for offsets $offsets" }
                        } else {
                            logger.info { "Offset committed  $offsets" }
                        }
                    }
                }
            }
            logger.info { "Finish consuming" }
        } catch (e: Throwable) {
            when (e) {
                is WakeupException -> logger.info { "Consumer waked up" }
                else -> logger.error(e) { "Polling failed" }
            }
        } finally {
            logger.info { "Commit offset synchronously" }
            consumer.commitSync()
            consumer.close()
            finished.countDown()
            logger.info { "Consumer successfully closed" }
        }
    }

    override fun close() {
        logger.info { "Close job..." }
        closed.set(true)
        consumer.wakeup()
        finished.await(3000, TimeUnit.MILLISECONDS)
        logger.info { "Job is successfully closed" }
    }
}

fun <K, V> buildConsumer(): Consumer<K, V> {
    val consumerProps = Properties().apply {
        this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = listOf("localhost:29092")
        this[ConsumerConfig.CLIENT_ID_CONFIG] = "kafka-producer"
        this[ConsumerConfig.GROUP_ID_CONFIG] = "group"
        this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaAvroDeserializer::class.java

        this[ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG] = 1000
        this[ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG] = 1000
        this[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = "false"
        this[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        this[KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG] = "http://localhost:8085"
        this[KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG] = true
        this[KafkaAvroDeserializerConfig.VALUE_SUBJECT_NAME_STRATEGY] = TopicRecordNameStrategy::class.java.name

    }
    return Consumer(KafkaConsumer(consumerProps), "users")
}
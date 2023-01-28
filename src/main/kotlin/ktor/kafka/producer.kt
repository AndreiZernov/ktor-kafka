package ktor.kafka

import io.confluent.kafka.serializers.KafkaAvroSerializer
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy
import kotlinx.coroutines.suspendCancellableCoroutine
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import org.apache.kafka.common.serialization.StringSerializer

fun <K, V> buildProducer(): KafkaProducer<K, V> {
    val producerProps = Properties().apply {
        this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = listOf("localhost:29092")
        this[ProducerConfig.CLIENT_ID_CONFIG] = "kafka-consumer"
        this[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        this[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaAvroSerializer::class.java
        this[ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG] = 1000
        this[ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG] = 1000
        this[KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG] = "http://localhost:8085"
        this[KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY] = TopicRecordNameStrategy::class.java.name
    }
    return KafkaProducer(producerProps)
}

suspend inline fun <reified K : Any, reified V : Any> KafkaProducer<K, V>.dispatch(record: ProducerRecord<K, V>) =
    suspendCancellableCoroutine<RecordMetadata> { continuation ->
        this.send(record) { metadata, exception ->
            if (metadata == null) continuation.resumeWithException(exception!!) else continuation.resume(metadata)
        }
    }
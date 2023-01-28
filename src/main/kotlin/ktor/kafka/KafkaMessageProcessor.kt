package ktor.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.errors.SerializationException
import java.util.UUID
import ktor.kafka.AvroUser

class KafkaMessageProcessor(
    private val producer: KafkaProducer<String, AvroUser>,
    private val topic: String,
) {
    fun publishUser(firstname: String, age: Int) {
        try {
            val user = AvroUser()
            user.firstName = firstname
            user.lastName = "Test"
            user.setAge(age)

            val key = UUID.randomUUID().toString()

            this.producer.send(
                ProducerRecord(this.topic, key, user)
            )
        } catch (ex: SerializationException) {
            println("################# SerializationException")
            println(ex)
        } finally {
            producer.flush()
            producer.close()
        }
    }
}

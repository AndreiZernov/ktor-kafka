/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package ktor.kafka

import java.io.IOException
import java.io.ObjectInput
import java.io.ObjectOutput
import java.nio.ByteBuffer
import org.apache.avro.AvroMissingFieldException
import org.apache.avro.AvroRuntimeException
import org.apache.avro.Schema
import org.apache.avro.data.RecordBuilder
import org.apache.avro.io.Encoder
import org.apache.avro.io.ResolvingDecoder
import org.apache.avro.message.BinaryMessageDecoder
import org.apache.avro.message.BinaryMessageEncoder
import org.apache.avro.message.SchemaStore
import org.apache.avro.specific.AvroGenerated
import org.apache.avro.specific.SpecificData
import org.apache.avro.specific.SpecificRecord
import org.apache.avro.specific.SpecificRecordBase
import org.apache.avro.specific.SpecificRecordBuilderBase
import org.apache.avro.util.Utf8

/** Definition for an user.  */
@AvroGenerated
class AvroUser : SpecificRecordBase, SpecificRecord {
    /**
     * Serializes this AvroUser to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    @Throws(IOException::class)
    fun toByteBuffer(): ByteBuffer {
        return encoder.encode(this)
    }
    /**
     * Gets the value of the 'first_name' field.
     * @return First name of the user
     */
    /**
     * Sets the value of the 'first_name' field.
     * First name of the user
     * @param value the value to set.
     */
    /** First name of the user  */
    var firstName: CharSequence? = null
    /**
     * Gets the value of the 'last_name' field.
     * @return First name of the user
     */
    /**
     * Sets the value of the 'last_name' field.
     * First name of the user
     * @param value the value to set.
     */
    /** First name of the user  */
    var lastName: CharSequence? = null

    /** Age of the user  */
    private var age = 0

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use `newBuilder()`.
     */
    constructor() {}

    /**
     * All-args constructor.
     * @param first_name First name of the user
     * @param last_name First name of the user
     * @param age Age of the user
     */
    constructor(first_name: CharSequence?, last_name: CharSequence?, age: Int) {
        firstName = first_name
        lastName = last_name
        this.age = age
    }

    override fun getSpecificData(): SpecificData {
        return `MODEL$`
    }

    override fun getSchema(): Schema {
        return classSchema
    }

    // Used by DatumWriter.  Applications should not call.
    override fun get(`field$`: Int): Any {
        return when (`field$`) {
            0 -> firstName!!
            1 -> lastName!!
            2 -> age
            else -> throw IndexOutOfBoundsException("Invalid index: $`field$`")
        }
    }

    // Used by DatumReader.  Applications should not call.
    override fun put(`field$`: Int, `value$`: Any) {
        when (`field$`) {
            0 -> firstName = `value$` as CharSequence
            1 -> lastName = `value$` as CharSequence
            2 -> age = `value$` as Int
            else -> throw IndexOutOfBoundsException("Invalid index: $`field$`")
        }
    }

    /**
     * Gets the value of the 'age' field.
     * @return Age of the user
     */
    fun getAge(): Int {
        return age
    }

    /**
     * Sets the value of the 'age' field.
     * Age of the user
     * @param value the value to set.
     */
    fun setAge(value: Int) {
        age = value
    }

    /**
     * RecordBuilder for AvroUser instances.
     */
    @AvroGenerated
    class Builder : SpecificRecordBuilderBase<AvroUser>, RecordBuilder<AvroUser> {
        /**
         * Gets the value of the 'first_name' field.
         * First name of the user
         * @return The value.
         */
        /** First name of the user  */
        var firstName: CharSequence? = null
            private set
        /**
         * Gets the value of the 'last_name' field.
         * First name of the user
         * @return The value.
         */
        /** First name of the user  */
        var lastName: CharSequence? = null
            private set
        /**
         * Gets the value of the 'age' field.
         * Age of the user
         * @return The value.
         */
        /** Age of the user  */
        var age = 0
            private set

        /** Creates a new Builder  */
        private constructor() : super(classSchema, `MODEL$`) {}

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private constructor(other: Builder) : super(other) {
            if (isValidValue(fields()[0], other.firstName)) {
                firstName = data().deepCopy(fields()[0].schema(), other.firstName)
                fieldSetFlags()[0] = other.fieldSetFlags()[0]
            }
            if (isValidValue(fields()[1], other.lastName)) {
                lastName = data().deepCopy(fields()[1].schema(), other.lastName)
                fieldSetFlags()[1] = other.fieldSetFlags()[1]
            }
            if (isValidValue(fields()[2], other.age)) {
                age = data().deepCopy(fields()[2].schema(), other.age)
                fieldSetFlags()[2] = other.fieldSetFlags()[2]
            }
        }

        /**
         * Creates a Builder by copying an existing AvroUser instance
         * @param other The existing instance to copy.
         */
        private constructor(other: AvroUser) : super(classSchema, `MODEL$`) {
            if (isValidValue(fields()[0], other.firstName)) {
                firstName = data().deepCopy(fields()[0].schema(), other.firstName)
                fieldSetFlags()[0] = true
            }
            if (isValidValue(fields()[1], other.lastName)) {
                lastName = data().deepCopy(fields()[1].schema(), other.lastName)
                fieldSetFlags()[1] = true
            }
            if (isValidValue(fields()[2], other.age)) {
                age = data().deepCopy(fields()[2].schema(), other.age)
                fieldSetFlags()[2] = true
            }
        }

        /**
         * Sets the value of the 'first_name' field.
         * First name of the user
         * @param value The value of 'first_name'.
         * @return This builder.
         */
        fun setFirstName(value: CharSequence?): Builder {
            validate(fields()[0], value)
            firstName = value
            fieldSetFlags()[0] = true
            return this
        }

        /**
         * Checks whether the 'first_name' field has been set.
         * First name of the user
         * @return True if the 'first_name' field has been set, false otherwise.
         */
        fun hasFirstName(): Boolean {
            return fieldSetFlags()[0]
        }

        /**
         * Clears the value of the 'first_name' field.
         * First name of the user
         * @return This builder.
         */
        fun clearFirstName(): Builder {
            firstName = null
            fieldSetFlags()[0] = false
            return this
        }

        /**
         * Sets the value of the 'last_name' field.
         * First name of the user
         * @param value The value of 'last_name'.
         * @return This builder.
         */
        fun setLastName(value: CharSequence?): Builder {
            validate(fields()[1], value)
            lastName = value
            fieldSetFlags()[1] = true
            return this
        }

        /**
         * Checks whether the 'last_name' field has been set.
         * First name of the user
         * @return True if the 'last_name' field has been set, false otherwise.
         */
        fun hasLastName(): Boolean {
            return fieldSetFlags()[1]
        }

        /**
         * Clears the value of the 'last_name' field.
         * First name of the user
         * @return This builder.
         */
        fun clearLastName(): Builder {
            lastName = null
            fieldSetFlags()[1] = false
            return this
        }

        /**
         * Sets the value of the 'age' field.
         * Age of the user
         * @param value The value of 'age'.
         * @return This builder.
         */
        fun setAge(value: Int): Builder {
            validate(fields()[2], value)
            age = value
            fieldSetFlags()[2] = true
            return this
        }

        /**
         * Checks whether the 'age' field has been set.
         * Age of the user
         * @return True if the 'age' field has been set, false otherwise.
         */
        fun hasAge(): Boolean {
            return fieldSetFlags()[2]
        }

        /**
         * Clears the value of the 'age' field.
         * Age of the user
         * @return This builder.
         */
        fun clearAge(): Builder {
            fieldSetFlags()[2] = false
            return this
        }

        override fun build(): AvroUser {
            return try {
                val record = AvroUser()
                record.firstName = if (fieldSetFlags()[0]) firstName else defaultValue(fields()[0]) as CharSequence
                record.lastName = if (fieldSetFlags()[1]) lastName else defaultValue(fields()[1]) as CharSequence
                record.age = if (fieldSetFlags()[2]) age else (defaultValue(fields()[2]) as Int)
                record
            } catch (e: AvroMissingFieldException) {
                throw e
            } catch (e: Exception) {
                throw AvroRuntimeException(e)
            }
        }
    }

    @Throws(IOException::class)
    override fun writeExternal(out: ObjectOutput) {
        `WRITER$`.write(this, SpecificData.getEncoder(out))
    }

    @Throws(IOException::class)
    override fun readExternal(`in`: ObjectInput) {
        `READER$`.read(this, SpecificData.getDecoder(`in`))
    }

    override fun hasCustomCoders(): Boolean {
        return true
    }

    @Throws(IOException::class)
    override fun customEncode(out: Encoder) {
        out.writeString(firstName)
        out.writeString(lastName)
        out.writeInt(age)
    }

    @Throws(IOException::class)
    override fun customDecode(`in`: ResolvingDecoder) {
        val fieldOrder = `in`.readFieldOrderIfDiff()
        if (fieldOrder == null) {
            firstName = `in`.readString(if (firstName is Utf8) firstName as Utf8? else null)
            lastName = `in`.readString(if (lastName is Utf8) lastName as Utf8? else null)
            age = `in`.readInt()
        } else {
            for (i in 0..2) {
                when (fieldOrder[i].pos()) {
                    0 -> firstName = `in`.readString(if (firstName is Utf8) firstName as Utf8? else null)
                    1 -> lastName = `in`.readString(if (lastName is Utf8) lastName as Utf8? else null)
                    2 -> age = `in`.readInt()
                    else -> throw IOException("Corrupt ResolvingDecoder.")
                }
            }
        }
    }

    companion object {
        private const val serialVersionUID = 3051761084506169178L
        val classSchema = Schema.Parser()
            .parse("{\"type\":\"record\",\"name\":\"AvroUser\",\"namespace\":\"ktor.kafka\",\"doc\":\"Definition for an user.\",\"fields\":[{\"name\":\"first_name\",\"type\":\"string\",\"doc\":\"First name of the user\"},{\"name\":\"last_name\",\"type\":\"string\",\"doc\":\"First name of the user\"},{\"name\":\"age\",\"type\":\"int\",\"doc\":\"Age of the user\"}]}")
        private val `MODEL$` = SpecificData()

        /**
         * Return the BinaryMessageEncoder instance used by this class.
         * @return the message encoder used by this class
         */
        val encoder = BinaryMessageEncoder<AvroUser>(`MODEL$`, classSchema)

        /**
         * Return the BinaryMessageDecoder instance used by this class.
         * @return the message decoder used by this class
         */
        val decoder = BinaryMessageDecoder<AvroUser>(`MODEL$`, classSchema)

        /**
         * Create a new BinaryMessageDecoder instance for this class that uses the specified [SchemaStore].
         * @param resolver a [SchemaStore] used to find schemas by fingerprint
         * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
         */
        fun createDecoder(resolver: SchemaStore?): BinaryMessageDecoder<AvroUser> {
            return BinaryMessageDecoder(`MODEL$`, classSchema, resolver)
        }

        /**
         * Deserializes a AvroUser from a ByteBuffer.
         * @param b a byte buffer holding serialized data for an instance of this class
         * @return a AvroUser instance decoded from the given buffer
         * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
         */
        @Throws(IOException::class)
        fun fromByteBuffer(
            b: ByteBuffer?
        ): AvroUser {
            return decoder.decode(b)
        }

        private val `WRITER$` = `MODEL$`.createDatumWriter(classSchema)
        private val `READER$` = `MODEL$`.createDatumReader(classSchema)
    }
}
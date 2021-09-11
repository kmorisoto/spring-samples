package com.example.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.util.StdConverter
import org.hashids.Hashids
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.beans.PropertyEditorSupport
import java.io.IOException


@RestController
@RequestMapping(value = ["/"])
class FooController {
    @GetMapping
    fun aa(): Foo = Foo(1)

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE, "application/x-www-form-urlencoded;charset=UTF-8"])
    fun bb(bar: Bar): Foo {
        println(bar)
        return Foo(bar.id)
    }

    @GetMapping
    @RequestMapping("baz")
    fun baz(): Baz = Baz(1)

    @InitBinder("bar")
    fun aaa(binder: WebDataBinder) {
        println(binder)
        binder.registerCustomEditor(Bar::class.java, APropertyEditorSupport())
    }
}

class APropertyEditorSupport : PropertyEditorSupport() {
    override fun setValue(value: Any?) {

        super.setValue(value)
    }

}

data class Foo(
//    @JsonSerialize(using = IDSerializer::class)
    @JsonSerialize(converter = IDStdConverter::class)
    val id: Int
)

data class Bar(
//    @JsonDeserialize(using = IDDeserializer::class)
    @JsonDeserialize(converter = IDDeStdConverter::class)
    var id: Int
)

data class Baz(
//    @JsonDeserialize(using = IDDeserializer::class)
    var id: Int
)

class IDJsonDeserializer : JsonDeserializer<String>() {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): String {
        return "hoge"
    }
}

class IDJsonSerializer : JsonSerializer<Int>() {
    override fun serialize(value: Int, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeNumber(value + 1)
    }
}

@Component
class IDStdConverter : StdConverter<Int, String>() {
    override fun convert(value: Int): String {
        return "$value foo"
    }
}

@Component
class IDDeStdConverter : StdConverter<String, Int>() {
    override fun convert(value: String): Int {
        return value.toInt()
    }
}

@Component
class IDSerializer(
) : JsonSerializer<Int>() {
    private val hashids: Hashids = Hashids("123", 10)
    override fun serialize(value: Int, gen: JsonGenerator, serializers: SerializerProvider) {
        hashids.encode(value.toLong()).also { gen.writeString(it) }
    }
}

@Component
class IDDeserializer : JsonDeserializer<Int>() {
    private val hashids: Hashids = Hashids("123", 10)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Int {
        println(p.valueAsString)
        println(hashids.decode(p.valueAsString))
        println(hashids.decode(p.valueAsString).size)
        println(hashids.decode(p.valueAsString).get(0))
        return hashids.decode(p.valueAsString)[0].toInt()
    }
}

class IDStdDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<String>(vc) {
    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): String {
        return "hoge"
    }
}

class StringToIntegerDeserializer : JsonDeserializer<Int>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Int {
        val value = jp.codec.readValue(jp, String::class.java)
        try {
            return Integer.valueOf(value)
        } catch (e: NumberFormatException) {
        }
        return 0
    }
}


@Configuration(proxyBeanMethods = false)
class JacksonConfig {

    private companion object {
        const val HASH_SALT = "lDWi23DMkyf9ooU2nMQk"
        const val MIN_HASH_LENGTH = 10
    }

    // MappingJackson2XmlHttpMessageConverterConfiguration を参考に生成
    @Bean
    fun mappingJackson2HttpMessageConverter(
        objectMapper: ObjectMapper
    ): MappingJackson2HttpMessageConverter = objectMapper
        .apply {
            enable(MapperFeature.USE_STD_BEAN_NAMING)
            // 未知のプロパティがあってもエラーにしない
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
        .let {
            MappingJackson2HttpMessageConverter(objectMapper)
        }

    @Bean
    fun hashids(): Hashids = Hashids(HASH_SALT, MIN_HASH_LENGTH)
}


fun main() {
    val HASH_SALT = "lDWi23DMkyf9ooU2nMQk"
    val MIN_HASH_LENGTH = 10
    val hashids: Hashids = Hashids(HASH_SALT, MIN_HASH_LENGTH)

    println(hashids.encode(1))
    println(hashids.encode(10))
    println(hashids.encode(100))
    println(hashids.encode(Int.MAX_VALUE.toLong()))
}

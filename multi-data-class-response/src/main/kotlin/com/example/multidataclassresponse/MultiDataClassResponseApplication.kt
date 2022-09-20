package com.example.multidataclassresponse

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MultiDataClassResponseApplication

fun main(args: Array<String>) {
    runApplication<MultiDataClassResponseApplication>(*args)
}

interface Foo {
    val name: String
}

data class AFoo(
    override val name: String,
    val a: Int
):Foo

data class BFoo(
    override val name: String,
    val b: String
):Foo


data class Bar(
    val foos: List<Foo>
)

@RestController
class FooController {
    @GetMapping(value = ["/bar"], produces = [MediaType.APPLICATION_XML_VALUE])
    fun foo(): ResponseEntity<Bar> = ResponseEntity.ok(Bar(listOf(AFoo("a", 1), BFoo("b", "bb"))))
}

@Configuration(proxyBeanMethods = false)
class JacksonConfig {

    // MappingJackson2XmlHttpMessageConverterConfiguration を参考に生成
    @Bean
    fun mappingJackson2XmlHttpMessageConverter(
        builder: Jackson2ObjectMapperBuilder
    ): MappingJackson2XmlHttpMessageConverter = builder.createXmlMapper(true).build<XmlMapper>()
        .apply {
            // <?xml version='1.0' encoding='UTF-8'?> を付与
            enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION)
            // 未知のプロパティがあってもエラーにしない
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
        .let {
            MappingJackson2XmlHttpMessageConverter(it)
        }
}

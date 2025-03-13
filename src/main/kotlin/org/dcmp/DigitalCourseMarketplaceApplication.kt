package org.dcmp

import org.dcmp.infrastructure.security.RsaKeyProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@EnableConfigurationProperties(RsaKeyProperties::class)
@SpringBootApplication
class DigitalCourseMarketplaceApplication

fun main(args: Array<String>) {
    runApplication<DigitalCourseMarketplaceApplication>(*args)
}

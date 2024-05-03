package tech.derrickmwendwa.utils

/**
 * Marks a class, function, or property as part of the public API
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY
)
annotation class PublicApi
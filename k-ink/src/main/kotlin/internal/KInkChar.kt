package tech.derrickmwendwa.internal

/**
 * Represents a character in the KInk language
 * @property content The content of the character
 */
internal data class KInkChar(val content: List<String>) {
    init {
        // Each character must have a content
        require(content.isNotEmpty()) {
            "Content cannot be empty"
        }

        // Each character must have a content with a length of [CHARACTER_HEIGHT]
        require(content.size == CHARACTER_HEIGHT) {
            "Content must have a length of $CHARACTER_HEIGHT"
        }
    }
}
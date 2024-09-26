package today.dozzari.dozzari.global.constant

object SecurityConstant {
    val SWAGGER_URIS = listOf(
        "/api-docs.html",
        "/api-docs/**",
        "/swagger-ui/**",
        "/v3/**",
    )

    val H2_URIS = listOf(
        "/h2-console/**",
    )

    val NO_AUTHENTICATION_API = listOf(
        "/auth/**",
        "/health",
    )

    val ALLOWED_CORS_URLS = listOf(
        "http://localhost:3000",
        "https://api.dozzari.today",
    )

    val ALLOWED_METHODS = listOf(
        "GET",
        "POST",
        "PUT",
        "DELETE",
    )

    val ALLOWED_HEADERS = listOf(
        "*",
    )
}
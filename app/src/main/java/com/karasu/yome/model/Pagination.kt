package com.karasu.yome.model

data class Pagination (
    var currentPage: Int,
    var itemsPerPage: Int,
    var totalItems: Int,
    var totalPages: Int,
)

data class PaginatedResult<T> (
    var result : T,
    var pagination : Pagination
)
package org.dcmp.domain.contracts

data class PagedResult<T>(
    val items: List<T> = emptyList(), // Data list
    val totalRecords: Long = 0, // Total records matching the query
    val totalPages: Int = 0, // Total pages based on page size
    val currentPage: Int = 0, // Current page number
    val limit: Long = 0 // Records per page
) {
    constructor(items: List<T>, totalRecords: Long, limit: Long, offset: Int) : this(
        items = items,
        totalRecords = totalRecords,
        limit = limit,
        totalPages = if (limit > 0) ((totalRecords + limit - 1) / limit).toInt() else 0,
        currentPage = if (limit > 0) (((offset + 1) / limit).toInt()) else 0 // Offset + 1 to match 1-based index
    )
}


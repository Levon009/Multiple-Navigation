package com.example.jetpack_multiplenavigation.products.presentation.pagination

class BasePaginationImpl<Page, Item>(
    private val initialPage: Page,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextPage: Page) -> Result<List<Item>>,
    private inline val getNextPage: suspend (List<Item>) -> Page,
    private inline val onSuccess: suspend (items: List<Item>, newPage: Page) -> Unit,
    private inline val onError: suspend (Throwable?) -> Unit
) : BasePagination<Page, Item> {
    private var currentPage = initialPage
    private var isMakingRequest = false

    override suspend fun loadNextItem() {
        if (isMakingRequest)
            return
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentPage)
        isMakingRequest = false
        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentPage = getNextPage(items)
        onSuccess(items, currentPage)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentPage = initialPage
    }
}
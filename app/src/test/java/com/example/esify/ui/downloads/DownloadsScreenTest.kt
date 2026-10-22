package com.example.esify.ui.downloads

import com.example.esify.ui.downloads.components.DefaultAvailableItems
import com.example.esify.ui.downloads.components.DefaultDownloadedItems
import com.example.esify.ui.downloads.components.DefaultDownloadingItem
import com.example.esify.ui.downloads.components.DefaultFilterOptions
import com.example.esify.ui.downloads.model.StorageUsage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DownloadsScreenTest {

    @Test
    fun storageUsage_defaultValuesAreValid() {
        val usage = StorageUsage()
        assertTrue("Storage progress must be between 0 and 1", usage.progress in 0f..1f)
        assertEquals("62%", usage.percentageText)
        assertEquals("3.1 GB", usage.usedStorage)
        assertEquals("5.0 GB", usage.totalStorage)
        assertEquals("48", usage.chaptersCount)
        assertEquals("12", usage.mcqBanksCount)
        assertEquals("8", usage.keyBooksCount)
    }

    @Test
    fun filterOptions_containExpectedFiveFilters() {
        assertEquals(5, DefaultFilterOptions.size)
        assertEquals("All (25)", DefaultFilterOptions[0])
        assertTrue(DefaultFilterOptions.contains("Key Books (4)"))
        assertTrue(DefaultFilterOptions.contains("MCQs (12)"))
        assertTrue(DefaultFilterOptions.contains("Notes (6)"))
        assertTrue(DefaultFilterOptions.contains("Others (3)"))
    }

    @Test
    fun downloadedItems_matchReferenceData() {
        assertEquals(4, DefaultDownloadedItems.size)

        val titles = DefaultDownloadedItems.map { it.title }
        assertTrue(titles.any { it.contains("Computer Science - Chapter 1") })
        assertTrue(titles.any { it.contains("Chemistry - Chapter 2") })
        assertTrue(titles.any { it.contains("Physics - Chapter 1") })
        assertTrue(titles.any { it.contains("Biology - Chapter 1") })

        DefaultDownloadedItems.forEach { item ->
            assertTrue(item.filesCount > 0)
            assertTrue(item.sizeText.isNotEmpty())
            assertTrue(item.dateText.isNotEmpty())
        }
    }

    @Test
    fun downloadingItem_progressIsValid() {
        assertTrue(DefaultDownloadingItem.progress in 0f..1f)
        assertEquals("70%", DefaultDownloadingItem.percentageText)
        assertEquals("Physics - Chapter 3", DefaultDownloadingItem.title)
    }

    @Test
    fun availableItems_matchReferenceData() {
        assertEquals(2, DefaultAvailableItems.size)
        assertTrue(DefaultAvailableItems.any { it.title.contains("Computer Science - Chapter 4") })
        assertTrue(DefaultAvailableItems.any { it.title.contains("Chemistry - Chapter 3") })
    }
}

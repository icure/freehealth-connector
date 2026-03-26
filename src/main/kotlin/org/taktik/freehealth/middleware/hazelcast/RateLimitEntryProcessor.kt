/*
 *
 * Copyright (C) 2018 iCure SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.hazelcast

import com.hazelcast.map.EntryProcessor
import com.hazelcast.nio.ObjectDataInput
import com.hazelcast.nio.ObjectDataOutput
import com.hazelcast.nio.serialization.IdentifiedDataSerializable
import org.taktik.freehealth.middleware.domain.RateLimitEntry
import org.taktik.freehealth.middleware.domain.RateLimitResult
import org.taktik.freehealth.middleware.domain.RateLimitSerializableConstants

class RateLimitEntryProcessor() : EntryProcessor<String, RateLimitEntry, RateLimitResult>, IdentifiedDataSerializable {
    private var nowMillis: Long = 0
    private var windowMillis: Long = 0
    private var maxRequests: Int = 0

    constructor(nowMillis: Long, windowSeconds: Long, maxRequests: Int) : this() {
        this.nowMillis = nowMillis
        this.windowMillis = windowSeconds * 1000
        this.maxRequests = maxRequests
    }

    @Suppress("UNCHECKED_CAST")
    override fun process(entry: MutableMap.MutableEntry<String, RateLimitEntry>): RateLimitResult {
        // Hazelcast passes null value for missing keys despite the non-null type signature
        val raw = (entry as MutableMap.MutableEntry<String, RateLimitEntry?>).value
            ?: RateLimitEntry(windowStartMillis = nowMillis)

        // Advance windows if needed
        val elapsed = nowMillis - raw.windowStartMillis
        val e = when {
            elapsed >= windowMillis * 2 -> raw.copy(previousCount = 0, currentCount = 0, windowStartMillis = nowMillis)
            elapsed >= windowMillis -> raw.copy(previousCount = raw.currentCount, currentCount = 0, windowStartMillis = raw.windowStartMillis + windowMillis)
            else -> raw
        }

        // Weighted estimate: previous window contribution decays linearly
        val elapsedInWindow = nowMillis - e.windowStartMillis
        val previousWeight = 1.0 - elapsedInWindow.toDouble() / windowMillis
        val estimatedCount = (e.previousCount * previousWeight + e.currentCount).toLong()

        val resetEpochSeconds = (e.windowStartMillis + windowMillis) / 1000
        val remaining = (maxRequests - estimatedCount - 1).coerceAtLeast(0).toInt()

        return if (estimatedCount < maxRequests) {
            entry.setValue(e.copy(currentCount = e.currentCount + 1))
            RateLimitResult(
                allowed = true,
                remaining = remaining,
                resetEpochSeconds = resetEpochSeconds,
                retryAfterSeconds = 0
            )
        } else {
            entry.setValue(e)
            val retryAfterSeconds = ((e.windowStartMillis + windowMillis - nowMillis) / 1000).coerceAtLeast(1)
            RateLimitResult(
                allowed = false,
                remaining = 0,
                resetEpochSeconds = resetEpochSeconds,
                retryAfterSeconds = retryAfterSeconds
            )
        }
    }

    override fun getFactoryId(): Int = RateLimitSerializableConstants.FACTORY_ID
    override fun getClassId(): Int = RateLimitSerializableConstants.RATE_LIMIT_ENTRY_PROCESSOR_CLASS_ID

    override fun writeData(out: ObjectDataOutput) {
        out.writeLong(nowMillis)
        out.writeLong(windowMillis)
        out.writeInt(maxRequests)
    }

    override fun readData(input: ObjectDataInput) {
        nowMillis = input.readLong()
        windowMillis = input.readLong()
        maxRequests = input.readInt()
    }
}

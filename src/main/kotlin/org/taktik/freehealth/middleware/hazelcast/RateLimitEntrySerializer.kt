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

import com.hazelcast.nio.ObjectDataInput
import com.hazelcast.nio.ObjectDataOutput
import com.hazelcast.nio.serialization.StreamSerializer
import org.taktik.freehealth.middleware.domain.RateLimitEntry
import org.taktik.freehealth.middleware.domain.RateLimitSerializableConstants

class RateLimitEntrySerializer : StreamSerializer<RateLimitEntry> {
    override fun getTypeId(): Int = RateLimitSerializableConstants.RATE_LIMIT_ENTRY_SERIALIZER_TYPE_ID

    override fun write(out: ObjectDataOutput, entry: RateLimitEntry) {
        out.writeInt(entry.previousCount)
        out.writeInt(entry.currentCount)
        out.writeLong(entry.windowStartMillis)
    }

    override fun read(input: ObjectDataInput): RateLimitEntry {
        return RateLimitEntry(
            previousCount = input.readInt(),
            currentCount = input.readInt(),
            windowStartMillis = input.readLong()
        )
    }
}

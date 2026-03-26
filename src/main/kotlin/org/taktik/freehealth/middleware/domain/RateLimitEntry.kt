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

package org.taktik.freehealth.middleware.domain

import com.hazelcast.nio.ObjectDataInput
import com.hazelcast.nio.ObjectDataOutput
import com.hazelcast.nio.serialization.IdentifiedDataSerializable

class RateLimitEntry() : IdentifiedDataSerializable {
    var previousCount: Int = 0
    var currentCount: Int = 0
    var windowStartMillis: Long = 0

    constructor(previousCount: Int, currentCount: Int, windowStartMillis: Long) : this() {
        this.previousCount = previousCount
        this.currentCount = currentCount
        this.windowStartMillis = windowStartMillis
    }

    override fun getFactoryId(): Int = RateLimitSerializableConstants.FACTORY_ID
    override fun getClassId(): Int = RateLimitSerializableConstants.RATE_LIMIT_ENTRY_CLASS_ID

    override fun writeData(out: ObjectDataOutput) {
        out.writeInt(previousCount)
        out.writeInt(currentCount)
        out.writeLong(windowStartMillis)
    }

    override fun readData(input: ObjectDataInput) {
        previousCount = input.readInt()
        currentCount = input.readInt()
        windowStartMillis = input.readLong()
    }
}
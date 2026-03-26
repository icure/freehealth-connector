/*
 *
 * Copyright (C) 2018 Taktik SA
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

class RateLimitResult() : IdentifiedDataSerializable {
    var allowed: Boolean = true
    var remaining: Int = 0
    var resetEpochSeconds: Long = 0
    var retryAfterSeconds: Long = 0

    constructor(allowed: Boolean, remaining: Int, resetEpochSeconds: Long, retryAfterSeconds: Long) : this() {
        this.allowed = allowed
        this.remaining = remaining
        this.resetEpochSeconds = resetEpochSeconds
        this.retryAfterSeconds = retryAfterSeconds
    }

    override fun getFactoryId(): Int = RateLimitSerializableConstants.FACTORY_ID
    override fun getClassId(): Int = RateLimitSerializableConstants.RATE_LIMIT_RESULT_CLASS_ID

    override fun writeData(out: ObjectDataOutput) {
        out.writeBoolean(allowed)
        out.writeInt(remaining)
        out.writeLong(resetEpochSeconds)
        out.writeLong(retryAfterSeconds)
    }

    override fun readData(input: ObjectDataInput) {
        allowed = input.readBoolean()
        remaining = input.readInt()
        resetEpochSeconds = input.readLong()
        retryAfterSeconds = input.readLong()
    }
}
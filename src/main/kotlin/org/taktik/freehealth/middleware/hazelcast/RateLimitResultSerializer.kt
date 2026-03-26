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
import org.taktik.freehealth.middleware.domain.RateLimitResult
import org.taktik.freehealth.middleware.domain.RateLimitSerializableConstants

class RateLimitResultSerializer : StreamSerializer<RateLimitResult> {
    override fun getTypeId(): Int = RateLimitSerializableConstants.RATE_LIMIT_RESULT_SERIALIZER_TYPE_ID

    override fun write(out: ObjectDataOutput, result: RateLimitResult) {
        out.writeBoolean(result.allowed)
        out.writeInt(result.remaining)
        out.writeLong(result.resetEpochSeconds)
        out.writeLong(result.retryAfterSeconds)
    }

    override fun read(input: ObjectDataInput): RateLimitResult {
        return RateLimitResult(
            allowed = input.readBoolean(),
            remaining = input.readInt(),
            resetEpochSeconds = input.readLong(),
            retryAfterSeconds = input.readLong()
        )
    }
}
